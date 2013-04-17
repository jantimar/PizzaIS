package pizzeria.database;

import pizzeria.core.PizzaShop;
import pizzeria.core.customers.IRegisteredCustomer;
import pizzeria.core.orders.IOrder;
import pizzeria.core.stock.Ingredient;
import pizzeria.loyaltyprogram.LoyaltyProgram;

//import pizzais.mvcore.PizzaShop;

public aspect MySqlDataSourceAspect {
	
	
	private MySqlDataLoader PizzaShop.dataLoader;
	
	public MySqlDataLoader PizzaShop.getDataLoader(){
		return this.dataLoader;
	}
	
	after(PizzaShop shop) : execution(PizzaShop.new(..)) && this(shop) {
		shop.dataLoader = new MySqlDataLoader();
		shop.dataLoader.LoadBasicStructure(shop);
	}
	
	after(LoyaltyProgram lp) : execution(LoyaltyProgram.new(..)) && this(lp) {
		MySqlDataLoader dataLoader = new MySqlDataLoader();
		dataLoader.LoadLoyaltyProgram(lp);
	}
	
	after(IOrder order) : execution(* pizzeria.*.*.addOrder(..)) && args(order)
	{
		MySqlDataLoader dataLoader = new MySqlDataLoader();
		dataLoader.insertOrder(order);
	}
	
	after(Ingredient ingredient, int quantity) : execution(* pizzeria.*.*.*.addIngredient(..)) && args(ingredient, quantity)
	{
		MySqlDataLoader dataLoader = new MySqlDataLoader();
		dataLoader.insertIngredient(ingredient, quantity);
	}
	
	after(IRegisteredCustomer customer, int points) : execution(* pizzeria.*.*.addPoints(..)) && args(customer, points)
	{
		MySqlDataLoader dataLoader = new MySqlDataLoader();
		dataLoader.loyaltyPoints(customer, points, true);
	}
	
	after(IRegisteredCustomer customer, int points) : execution(* pizzeria.*.*.takePoints(..)) && args(customer, points)
	{
		MySqlDataLoader dataLoader = new MySqlDataLoader();
		dataLoader.loyaltyPoints(customer, points, false);
	}
		
// !within(MySqlDataLoader)
//	around() : RegisterMeal() 
//	 
//	around() : UnregisterMeal()
//	around() : AddOrder() 
//
//	around() : RemoveOrder() 
//
//	around() : GetAllIngredients()
	
//	after(PizzaShop pizzashop) : execution(PizzaShop.new(..)) && this(pizzashop) {
//		if(pizzashop.getContextData("installDatabase").equals(true)){
//			
//		}
//		System.out.println("hello we got new pizzashop");
//	}
	
//	after(IContextContainer outer, IContextContainer inner) : call(IContextContainer.new(..)) {
//	after() returning (IContextContainer obj) : call( IContextContainer+.new(..)) && within(IContextContainer+) {
//		System.out.println("hello we got new "+obj.getClass()+" thing within ");
//		
//	}
//	after(IContextContainer a): this(a) && within(IContextContainer+) && call(IContextContainer+.new(..)) {
//		//System.out.println("hello we have this "+a.getClass());
//		
//	}
//	after(IContextContainer a): this(a) && within(IContextContainer+) && call(new(..)) {
//		System.out.println("hello we have this "+a.getClass());
//		
//	}
}
