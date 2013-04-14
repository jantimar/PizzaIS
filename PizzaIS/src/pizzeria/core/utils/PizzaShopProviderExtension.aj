package pizzeria.core.utils;

import pizzeria.core.PizzaShop;
import pizzeria.core.orders.*;
import pizzeria.core.stock.*;

/**
 * Rozsirenie tried o referenciu na PizzaShop
 * @author Michal Vrabel
 *
 */
public aspect PizzaShopProviderExtension {

	private interface IPizzaShopProvider {
		public PizzaShop getShop();
		public void setShop(PizzaShop shop);
	}
	
	// IOrder
	
	after(PizzaShop shop,IOrder order) : 
		call(public void PizzaShop.addOrder(IOrder)) && target(shop) && args(order)
	{
		if(order instanceof IOrder){
			((IOrder)order).setShop(shop);
		}
	}	
		
	
	declare parents : IOrder implements IPizzaShopProvider;
		
	private PizzaShop IOrder.shop;
	
	public PizzaShop IOrder.getShop(){
		return this.shop;
	}
	
	public void IOrder.setShop(PizzaShop shop){
		this.shop = shop;
	}
	
	// Stock
	
	declare parents : Stock implements IPizzaShopProvider;
	
	private PizzaShop Stock.shop;
	
	public PizzaShop Stock.getShop(){
		return this.shop;
	}
	
	public void Stock.setShop(PizzaShop shop){
		this.shop = shop;
	}
		
	after(PizzaShop shop, Stock stock) :
		execution(public void setStock(Stock)) && this(shop) && args(stock)
	{
		stock.setShop(shop);
	}

	// MealsMenu
	// ked bude treba
}
