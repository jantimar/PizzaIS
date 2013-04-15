package pizzeria.swingui;

import pizzeria.core.PizzaShop;
import pizzeria.core.orders.IOrder;
import pizzeria.database.MySqlDataSourceAspect;
import pizzeria.database.MySqlDataLoader;

public aspect SwingUserInterfaceAspect {
	
	declare precedence: pizzeria.database.MySqlDataSourceAspect;

// zakomentovane kdeze to nie je dokoncene

//	private SwingUserInterfaceModel swingUserInterface = new SwingUserInterfaceModel();
//	
//	private SwingUserInterfaceModel PizzaShop.swingUserInterface;
//	
//	public SwingUserInterfaceModel PizzaShop.getUserInterface(){
//		return this.swingUserInterface;
//	}
//	
//	after(PizzaShop shop) : execution(PizzaShop.new(..)) && this(shop){
//		shop.swingUserInterface = new SwingUserInterfaceModel();
//		shop.swingUserInterface.setShop(shop);
//		shop.swingUserInterface.start();
//	}
//
//	after(PizzaShop shop, IOrder order) 
//		: call(void PizzaShop.addOrder(IOrder)) 
//		&& target(shop) && args(order)
//		&& !withincode(* MySqlDataLoader.LoadBasicStructure(..)) 
//	{
//		shop.swingUserInterface.getActions().addOrder(order);
////		swingUserInterface.
//	}
//	
	
}
