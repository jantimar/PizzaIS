package pizzeria.ui;

import pizzeria.core.customers.ICustomer;
import pizzeria.core.meals.Meal;
import pizzeria.core.orders.IOrder;
import pizzeria.core.userroles.IUserRole;

/**
 * Definicia zakladnych vlatnosti pouzivatelskeho rozhrania - predbezne
 */
public interface IUserInterface {
	public void openOrders();
	public void openFinance();
	public IUserRole requireUser();
	public IOrder requireNewOrder();
	public Meal requireNewMeal();
	public ICustomer requireNewCustomer();
	public boolean isExitRequired();
	
}
