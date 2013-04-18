package pizzeria.swingui;

import java.awt.EventQueue;

import pizzeria.core.PizzaShop;
import pizzeria.core.orders.IOrder;
import pizzeria.core.userroles.IUserRole;
import pizzeria.database.MySqlDataLoader;
import pizzeria.swingui.extrauserroles.Director;
import pizzeria.swingui.extrauserroles.FinanceManager;
import pizzeria.swingui.users.UserManagement;
import pizzeria.swingui.util.ShopRunnable;
import pizzeria.userroles.*;
import pizzeria.loyaltyprogram.*;

public aspect SwingUserInterfaceAspect {
	
	pointcut pizzaShopCreation(PizzaShop shop) : initialization(PizzaShop.new(..)) && target(shop); 
	
// zakomentovane kdeze to nie je dokoncene
//	pizzeria.database.MySqlDataSourceAspect, 

	private SwingUserInterface PizzaShop.swingUserInterface;
	
	public SwingUserInterface PizzaShop.getUserInterface(){
		return this.swingUserInterface;
	}
	
	after(PizzaShop shop) : pizzaShopCreation(shop){
		
		UserManagement um = new UserManagement();
		LoyaltyProgram lp = shop.getLoyaltyProgram();
		SwingUserInterface ui = new SwingUserInterface(shop,um,lp);
		
		shop.swingUserInterface = ui; 
		
		um.addUser(new Director(shop));
		um.addUser(new RestaurantWaiter(shop));
		um.addUser(new Cook(shop));
//		um.addUser(new UIDeliveryGuy(shop));
		um.addUser(new FinanceManager());
		
		do{
			IUserRole currentUser = ui.requireUser();
			um.setCurrentUserRole(currentUser);
			if(currentUser == null) break;
			
			ui.openOrders();
//			try{
//				EventQueue.invokeAndWait(
//				
//
//						new ShopRunnable(ui) {
//							
//							@Override
//							public void run() {
//								sui.openOrders();
//								
//							}
//						}
////						new Runnable() {
////					
////					@Override
////					public void run() {
////						shop.swingUserInterface.openOrders();					
////					}
////				}
//						);
//			} catch(Exception e){}
		}
		while(!ui.isExitRequired());
		
		
	}

	after(PizzaShop shop, IOrder order) 
		: call(void PizzaShop.addOrder(IOrder)) 
		&& target(shop) && args(order)
		&& !withincode(* MySqlDataLoader.LoadBasicStructure(..)) 
	{
//		shop.swingUserInterface.getActions().addOrder(order);
//		swingUserInterface.
	}
		
}
