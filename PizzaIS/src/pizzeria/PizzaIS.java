package pizzeria;

import java.util.Arrays;
import java.util.Collection;


import pizzeria.core.PizzaShop;
import pizzeria.core.meals.Meal;
import pizzeria.core.meals.MealsMenu;
import pizzeria.core.orders.PersonalOrder;
import pizzeria.core.stock.Ingredient;
import pizzeria.core.stock.IngredientAssoc;
import pizzeria.core.stock.Stock;
import pizzeria.core.utils.ActionUnsuccessfullException;
import pizzeria.core.utils.HashMapContextContainer;
import pizzeria.stockfiller.ThresholdConfig;
import pizzeria.userroles.Cook;
import pizzeria.userroles.DeliveryGuy;
import pizzeria.userroles.RestaurantWaiter;

public class PizzaIS {

	/**
	 * Inicializacia systemu
	 * @param args
	 */
	public static void main(String args[]) {
		
		PizzaShop shop = new PizzaShop();
		
		HashMapContextContainer appContext = new HashMapContextContainer(); 
		shop.setContext(appContext);
		
		// princip nastavenia kontextovych informacii shopu 
		// (takto by sa napriklad odovzdali aj konfiguracne udaje pre databazu, ktora by sa pouzili podobne ako v StockFillerAspect)
		// - mozno existuje riesenie pouzitim aop ako sa tomu vyhnut - A ZANECHAT PRI TOM OBYCAJNU INSTANCIU PizzaShop a je contextu 
		appContext.putData(ThresholdConfig.DEFAULT_CONTEXT_KEY, new ThresholdConfig(5));
//		appContext.putData(key, data)
		
		//------------------------
		
		Stock stock = shop.getStock();
		MealsMenu menu = shop.getMealsMenu();

//		stock.setContext(appContext);
//		menu.setContext(appContext);
		
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
		
		//--------------------------
		
		Collection<Meal> meals = shop.getMealsMenu().getAllMealsCollection();
		
		// napr v UI sa tieto jedla teraz vypisu
		
		Meal mymeal = meals.iterator().next(); // nejake sa zvoli
		
		PersonalOrder myOrder = new PersonalOrder(5);
		myOrder.addMeal(mymeal);
		
		//--------------------------
		
		// Vsetky tie roly pouzivatelov implementujuce IUserRole nie su uplne nevyhnutne preto som sa ich dal mimo PizzaShop, 
		// da na nich naviazat nejake aspekty a mozno to nie je zle takto uchovavat operacie. 
		
		RestaurantWaiter waiter = new RestaurantWaiter(shop); // toto sa vyvori niekde v ui napr
		Cook chef = new Cook(shop);
		DeliveryGuy someGuy = new DeliveryGuy(shop);
		
		try {
			waiter.acceptOrder(myOrder);
		
		//--------------------------
		} catch (ActionUnsuccessfullException e) {
			System.out.println("Nemalo by nastat - acceptOrder: "+e.getMessage());
		}
		
		//--------------------------
		try {
			chef.cookOrderMeals(myOrder); 	// hadze exception vyzera ze tu je nastave ze ma malo surovin .. treba to skontrolovat	
		} catch (ActionUnsuccessfullException e) {
			System.out.println("Nemalo by nastat - cookOrderMeals: "+e.getMessage());
		}
		
		//--------------------------
		
		try{
			
			someGuy.shipOrder(myOrder);//teoreticky by toto malo hadzat dajaku chybu
		}
		catch(ActionUnsuccessfullException e){
			System.out.println("MALO by nastat someGuy.shipOrder: "+e.getMessage());
		}
		
		
//		// TODO z databazy sa nacitaju suroviny alebo ich tu staticky ponechame
//		Ingredient.createIngredient(0, "Syr", 10, 2);
//		Ingredient.createIngredient(1, "Kukurica", 10, 1);
//		Ingredient.createIngredient(2, "Sunka", 10, 3);
//		Ingredient.createIngredient(3, "Sampiony", 10, 1);
//		
//		
//		// TODO z databazy sa nacitaju pizze
//		List<Ingredient> stockMargarita = Arrays.asList(
//				Ingredient.ingredientWithID(0), Ingredient.ingredientWithID(1),Ingredient.ingredientWithID(1),Ingredient.ingredientWithID(2));
//		Pizza.createPizza(0, "Margarita", stockMargarita);
		
		
	}
	
	
}
