package pizzeria.loyaltyprogram.exchangeactions;

import pizzeria.core.PizzaShop;
import pizzeria.core.orders.IOrder;
import pizzeria.loyaltyprogram.IExchangeAction;
import pizzeria.loyaltyprogram.LoyaltyProgram;

public abstract class AbstractExchangeAction implements IExchangeAction {

	protected String name;
	protected String description;
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public abstract void exchange(int points, IOrder order, PizzaShop shop);

	@Override
	public abstract float getOrderBill(int points, IOrder order, PizzaShop shop);
	
	@Override
	public String toString() {
		return this.getName();
	}
}
