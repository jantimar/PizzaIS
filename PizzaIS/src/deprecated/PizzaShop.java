package deprecated;
//package pizzeria;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Hashtable;
//import java.util.List;
//
//import pizzeria.AOrder.State;
//
///** Hlavna trieda reprezentujúca obchod */
//public class PizzaShop {
//
//	/** vydaje obchodu */
//	private float outcomme;
//	/** prijem obchodu */
//	private float incomme;
//	/** kuchar v pizzerii */
//	private Chief chief;
//	/** casnik v pizzerii*/
//	private Waiter waiter;
//	/** clovek zodpovedny za donasku*/
//	private Deliver deliver;
//	
//	/** main funkcia obchodu*/
//	public static void main(String args[]) {
//		// TODO z databazy nacitat aktualny stav prijmu a vydaju a s tymyto hodnotamy zavolat PizzaShop
//		new PizzaShop(10.0f, 20.0f);
//	}
//
//	/**konstruktor pizzerie */
//	private PizzaShop(float outcomme, float incomme) {
//		this.incomme = incomme;
//		this.outcomme = outcomme;
//		// TODO z databazy sa nacitaju suroviny alebo ich tu staticky ponechame
//		Ingredient.createIngredient(0, "Syr", 10, 2);
//		Ingredient.createIngredient(1, "Kukurica", 10, 1);
//		Ingredient.createIngredient(2, "Sunka", 10, 3);
//		Ingredient.createIngredient(3, "Sampiony", 10, 1);
//		// TODO z databazy sa nacitaju pizze
//		List<Ingredient> stockMargarita = Arrays.asList(
//				Ingredient.ingredientWithID(0), Ingredient.ingredientWithID(1),Ingredient.ingredientWithID(1),Ingredient.ingredientWithID(2));
//		Pizza.createPizza(0, "Margarita", stockMargarita);
//		//TODO mozno tiez nacitat z databazi zamestnancov 
//		chief = new Chief("Jozko","Mrkvicka", 5.0f,this);
//		waiter = new Waiter("Peter","Broskynka", 2.5f,this);
//		deliver = new Deliver("Jan","Hrasko", 3.0f,this);
//	}
//
//	
//	public void makeOrder(AOrder order)
//	{
//		chief.makePizza(order);
//	}
//	
//	
//	/**objednavka z internetu */
//	public void receiveInternetOrder(List<Pizza> pizzas,AClient client)
//	{
//		//TODO pomocou aspektu zaregistrovat objednavku a cislo objednavky pouzit vo volani makeOrder
//		deliver.makeOrder(client, pizzas);
//	}
//	
//	public void takeInternetOrder(AOrder order)
//	{
//		deliver.takeOrder(order);
//	}
//	
//	
//	/**osobna objednavka */
//	public void receivePersonalOrder(List<Pizza> pizzas,AClient client)
//	{
//		//TODO pomocou aspektu zaregistrovat objednavku a cislo objednavky pouzit vo volani makeOrder
//		waiter.makeOrder(client, pizzas);
//	}
//	
//	public void takePersonalOrder(AOrder order)
//	{
//		waiter.takeOrder(order);
//	}
//	
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
//}
