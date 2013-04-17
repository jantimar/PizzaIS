package pizzeria.database;

//import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import pizzeria.core.PizzaShop;
import pizzeria.core.customers.IRegisteredCustomer;
import pizzeria.core.customers.RegisteredCustomer;
import pizzeria.core.meals.Meal;
import pizzeria.core.meals.MealsMenu;
import pizzeria.core.orders.DeliveryOrder;
import pizzeria.core.orders.IOrder;
import pizzeria.core.orders.OrderState;
import pizzeria.core.orders.PersonalOrder;
import pizzeria.core.stock.Ingredient;
import pizzeria.core.stock.IngredientAssoc;
import pizzeria.core.stock.Stock;
import pizzeria.loyaltyprogram.LoyaltyProgram;

/**
 * Trieda zaobalujuca operacie pre nacitavanie informacii z databazy
 * @author uzivatel
 *
 */
public class MySqlDataLoader {
    
    //	public List<Order> getOrders() {
    //		throw new UnsupportedOperationException();
    //	}
    //
    //	public List<Meal> getMeals() {
    //		throw new UnsupportedOperationException();
    //	}
    //
    //	public List<Ingredient> getIngredients() {
    //		throw new UnsupportedOperationException();
    //	}
    
	/**
	 * Naplni udajove struktury obchodu 
	 * @param shop
	 */
	public void LoadBasicStructure(PizzaShop shop){
		DatabaseConnection dbc;
		Stock stock = shop.getStock();
		MealsMenu menu = shop.getMealsMenu();
		try {
			dbc = new DatabaseConnection();
			
			// vyberie suroviny a ulozi ich do skladu
			ResultSet surovina = dbc.getResultSet("SELECT * FROM suroviny");
			while(surovina.next()) {
				stock.addIngredient(new Ingredient(surovina.getInt("id"), surovina.getString("nazov"), surovina.getFloat("cena")), 
									surovina.getInt("pocet"));
			}
			
			// vyberie jedla a zaregistruje ich do menu
			ResultSet jedlo = dbc.getResultSet("SELECT * FROM jedla");
			while(jedlo.next()) {
				// ku kazdemu jedlu zisti potrebne suroviny a vytvori asociacie
				ArrayList<IngredientAssoc> suroviny = new ArrayList<IngredientAssoc>();
				ResultSet surovina_do_jedla = dbc.getResultSet("SELECT id_suroviny, mnozstvo FROM suroviny_jedla WHERE id_jedlo="+jedlo.getInt("id"));
				while(surovina_do_jedla.next()) {
					// najde surovinu v sklade podla ID a vlozi do zoznamu surovin
					suroviny.add(new IngredientAssoc(stock.getIngredient(surovina_do_jedla.getInt("id_suroviny")), surovina_do_jedla.getInt("mnozstvo")));
				}
				
				Meal meal = new Meal(jedlo.getInt("id"), jedlo.getString("nazov"), jedlo.getFloat("cena"), suroviny);
				menu.registerMeal(meal);
			}
			
			// vyberie objednavky a zapise ich
			ResultSet objednavka = dbc.getResultSet("SELECT objednavky.*, klienti.* " +
													"FROM objednavky RIGHT JOIN klienti ON klienti.id=objednavky.klient_id");
			while(objednavka.next()) {
				RegisteredCustomer klient = null;
				// ak je zakaznik, vytvorime ho
				if(objednavka.getString("klienti.id")!=null) {
					klient = new RegisteredCustomer(objednavka.getString("klienti.poznamka"), 
																	  objednavka.getInt("klienti.id"), 
																	  objednavka.getString("klienti.meno")+" "+objednavka.getString("klienti.priezvisko"), 
																	  objednavka.getString("klienti.adresa"));
				}
				OrderState stav;
				switch(objednavka.getInt("objednavky.stav")) {
					case 1: stav = OrderState.NEW; break;
					case 2: stav = OrderState.IN_PROGRESS; break;
					case 3: stav = OrderState.READY; break;
					case 4: stav = OrderState.SHIPPING; break;
					case 5: stav = OrderState.FINISHED; break;
					default: stav = OrderState.REPAYMENT; break;
				}
				
				ArrayList<Meal> jedla = new ArrayList<Meal>();
				ResultSet jedla_v_objednavke = dbc.getResultSet("SELECT id_jedla FROM objednavka_jedlo WHERE id_objednavka="+objednavka.getInt("objednavky.id"));
				while(jedla_v_objednavke.next()) {
					jedla.add(menu.getMealByID(jedla_v_objednavke.getInt("objednavka_jedlo")));
				}
				
				IOrder order;
				if(objednavka.getString("objednavky.typ_objednavky").equals("restauracia")) {
					order = new PersonalOrder(objednavka.getInt("objednavky.id"), klient, stav, jedla);
				} else {
					order = new DeliveryOrder(objednavka.getInt("objednavky.id"), klient, stav, jedla);
				}
				shop.addOrder(order);
			}
			
			
		} catch (SQLException e) { 
			// keby sa to nepodarilo vypise stack trace a nahodi tam tie defaultne, ktore si tu mal napisane
			// mozete vymazat ak neuznate za vhodne
			e.printStackTrace();
			
			Ingredient syr = new Ingredient("gouda",new Float(0.65/100));
			Ingredient kecup = new Ingredient("kecup",new Float(0.60/450));
			Ingredient drozdie = new Ingredient("drozdie",new Float(0.35/42));
			Ingredient muka = new Ingredient("muka",new Float(0.89/1000));
			Ingredient sunka = new Ingredient("sunka",new Float(8.99/1000));
			Ingredient olej = new Ingredient("olej olivovy",new Float(6.95/500));
			Ingredient cestoviny = new Ingredient("cestoviny kolienka",new Float(0.65/500));
			
			stock.addIngredient(syr, 500);
			stock.addIngredient(kecup, 500);
			stock.addIngredient(drozdie, 200);
			stock.addIngredient(muka, 5000);
			stock.addIngredient(sunka, 5000);
			stock.addIngredient(cestoviny, 500);
			
			Meal pizza = new Meal("pizza",4, Arrays.asList(new IngredientAssoc[]{
				new IngredientAssoc(muka, 520/4),
				new IngredientAssoc(syr, 100),
				new IngredientAssoc(olej, 15),
				new IngredientAssoc(kecup, 30),
				new IngredientAssoc(drozdie, 10),
				new IngredientAssoc(sunka, 100)
			}));
			
			Meal spaegty = new Meal("spagety",2.2f,Arrays.asList(new IngredientAssoc[]{
				new IngredientAssoc(syr, 100),
				new IngredientAssoc(kecup, 30),
				new IngredientAssoc(cestoviny, 250)
			}));
			
			menu.registerMeal(pizza);
			menu.registerMeal(spaegty);
		}
		
		
	}
	
	/**
	 * Naplni zoznam pouzivatelov do Loyalty programu
	 * @param shop
	 */
	public void LoadLoyaltyProgram(LoyaltyProgram lp){
		DatabaseConnection dbc;
		try {
			dbc = new DatabaseConnection();
			ResultSet pouzivatel = dbc.getResultSet("SELECT * FROM klienti");
			Map<IRegisteredCustomer,Integer> map = new HashMap<IRegisteredCustomer,Integer>();
			while(pouzivatel.next()) {
				RegisteredCustomer klient = new RegisteredCustomer(pouzivatel.getString("poznamka"), 
						pouzivatel.getInt("id"), 
						pouzivatel.getString("meno")+" "+pouzivatel.getString("priezvisko"), 
						pouzivatel.getString("adresa"));
				map.put(klient, pouzivatel.getInt("body"));
			}
			lp.setCustomerPointsMap(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * Vyprazdni udajove struktury obchodu
	 * @param shop
	 */
	private void EmptyShop(PizzaShop shop){
		
	}
    
}