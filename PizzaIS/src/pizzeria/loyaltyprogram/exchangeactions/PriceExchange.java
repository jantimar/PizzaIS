package pizzeria.loyaltyprogram.exchangeactions;

import pizzeria.core.PizzaShop;
import pizzeria.core.orders.IOrder;

public class PriceExchange extends AbstractExchangeAction {

	private float exchangeRatio;
	
	public PriceExchange(float exchangeRatio){
		this.name = "Price exchange";
		this.description = "Changes points to lower price";
		this.exchangeRatio = exchangeRatio;
	}
	
	@Override
	public void exchange(int points, IOrder order, PizzaShop shop) {
		
	}

	@Override
	public float getOrderBill(int points, IOrder order,  PizzaShop shop) {
		return order.getOrderBill() - (points*exchangeRatio);
	}

	public float getExchangeRatio() {
		return exchangeRatio;
	}

	public void setExchangeRatio(float exchangeRatio) {
		this.exchangeRatio = exchangeRatio;
	}


}
