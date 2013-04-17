package pizzeria.database;

//import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import pizzeria.core.PizzaShop;
import pizzeria.core.meals.Meal;
import pizzeria.core.meals.MealsMenu;
import pizzeria.core.stock.Ingredient;
import pizzeria.core.stock.IngredientAssoc;
import pizzeria.core.stock.Stock;

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
			
			/*dbc.getResultSet("SELECT jedla.nazov AS nazov_jedla, " +
             "jedla.cena AS cena_jedla, " +
             "suroviny.nazov AS nazov_suroviny, " +
             "suroviny.cena AS cena_suroviny, " +
             "suroviny.pocet AS ostava_suroviny, " +
             "suroviny_jedla.mnozstvo AS mnozstvo_na_jedlo " +
             "FROM jedla, suroviny, suroviny_jedla " +
             "WHERE suroviny_jedla.id_jedlo = jedla.id AND " +
             "suroviny_jedla.id_suroviny = suroviny.id");*/
			
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
	 * Vyprazdni udajove struktury obchodu
	 * @param shop
	 */
	private void EmptyShop(PizzaShop shop){
		
	}
    
}