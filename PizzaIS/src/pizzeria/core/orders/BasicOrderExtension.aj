package pizzeria.core.orders;

import pizzeria.core.PizzaShop;

/**
 * Rozsirenie triedy BasicOrder o referenciu na PizzaShop
 * @author Michal Vrabel
 *
 */
public aspect BasicOrderExtension {
	
	after(PizzaShop shop,IOrder order) : 
		call(public void PizzaShop.addOrder(IOrder)) && target(shop) && args(order)
	{
		if(order instanceof BasicOrder){
			((BasicOrder)order).setShop(shop);
		}
	}	
		
	private interface IPizzaShopProvider {
		public PizzaShop getShop();
		public void setShop(PizzaShop shop);
	}
	
	declare parents : BasicOrder implements IPizzaShopProvider;
		
	private PizzaShop BasicOrder.shop;
	
	public PizzaShop BasicOrder.getShop(){
		return this.shop;
	}
	
	public void BasicOrder.setShop(PizzaShop shop){
		this.shop = shop;
	}
		
		
}
