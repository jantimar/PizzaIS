package pizzeria.loyaltyprogram;

import pizzeria.core.PizzaShop;
import pizzeria.core.orders.IOrder;

public interface IExchangeAction {
	public String getName();
	public String getDescription();
	public void exchange(int points, IOrder order, PizzaShop shop);
	public float getOrderBill(int points, IOrder order, PizzaShop shop);
}
