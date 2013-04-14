package pizzeria;

import java.util.Arrays;
import java.util.Collection;


import pizzeria.core.PizzaShop;
import pizzeria.core.meals.Meal;
import pizzeria.core.orders.PersonalOrder;
import pizzeria.core.utils.ActionUnsuccessfullException;
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
		
		//------------------------
		
		
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
		
		// Vypisanie orderBill 
		System.out.println( myOrder.getOrderBill() );
		
		
	}
	
	
}
