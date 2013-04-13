package pizzeria.core;

import java.util.ArrayList;
import java.util.List;
import pizzeria.core.meals.MealsMenu;
import pizzeria.core.orders.IOrder;
import pizzeria.core.stock.Stock;
import pizzeria.core.utils.AbstractContextProvider;

/** Hlavna trieda reprezentujúca obchod */
public class PizzaShop extends AbstractContextProvider {
	
	/** Poskytuje ingrediencie pre vytvorenie jedal  */
	private Stock stock;
	
	/** Poskytuje dostupne jedla */
	private MealsMenu mealsMenu;
	
	private List<IOrder> orders = new ArrayList<IOrder>();
	
	public MealsMenu getMealsMenu() {
		return mealsMenu;
	}

	public void setMealsMenu(MealsMenu mealsMenu) {
		this.mealsMenu = mealsMenu;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	/**konstruktor pizzerie */
	public PizzaShop() {
				
		this.stock = new Stock();
		this.mealsMenu = new MealsMenu();
	
	}
	
	public void addOrder(IOrder order){
		orders.add(order);
	}
	
	public void removeOrder(IOrder order){
		orders.remove(order);
	}
	
}

//		//TODO mozno tiez nacitat z databazi zamestnancov 
//		chief = new Chief("Jozko","Mrkvicka", 5.0f,this);
//		waiter = new Waiter("Peter","Broskynka", 2.5f,this);
//		deliver = new Delivery("Jan","Hrasko", 3.0f,this);

//	/**objednavka z internetu */
//	public void receiveInternetOrder(List<Pizza> pizzas,AbstractClient client)
//	{
//		//TODO pomocou aspektu zaregistrovat objednavku a cislo objednavky pouzit vo volani makeOrder
//		AbstractOrder order = deliver.makeOrder(client, pizzas);
//		chief.makePizza(order);
//		deliver.takeOrder(order);
//	}
//	
//	/**osobna objednavka */
//	public void receivePersonalOrder(List<Pizza> pizzas,AbstractClient client)
//	{
//		//TODO pomocou aspektu zaregistrovat objednavku a cislo objednavky pouzit vo volani makeOrder
//		AbstractOrder order = waiter.makeOrder(client, pizzas);
//		chief.makePizza(order);
//		waiter.takeOrder(order);
//	}
//
//	/**prijem vinancií */
//	public void addIncomme(float addIncomme) {
//		incomme += addIncomme;
//	}
//
//	/**priratanie vydavok */
//	public void addOutcomme(float addOutcomme) {
//		outcomme += addOutcomme;
//	}
//
//	/** vrati celkovy stav pizzerii*/
//	public float getProfit() {
//		return (incomme - outcomme);
//	}
