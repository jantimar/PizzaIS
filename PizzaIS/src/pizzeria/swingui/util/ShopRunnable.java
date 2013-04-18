package pizzeria.swingui.util;

import pizzeria.core.PizzaShop;
import pizzeria.swingui.SwingUserInterface;

public abstract class ShopRunnable implements Runnable {

	public SwingUserInterface sui;

	public ShopRunnable(SwingUserInterface sui) {
		this.sui = sui;  
	}
	
	@Override
	public abstract void run();

}
