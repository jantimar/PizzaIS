package pizzeria.swingui;

import pizzeria.core.PizzaShop;

public abstract class RunnablePizzaShopWrapper implements Runnable {

	protected PizzaShop shop;
	
	public PizzaShop getShop;
	
	@Override
	public abstract void run();

}
