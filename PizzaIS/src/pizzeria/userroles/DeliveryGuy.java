package pizzeria.userroles;

import pizzeria.core.PizzaShop;
import pizzeria.core.orders.IOrder;

import pizzeria.core.orders.OrderState;
import pizzeria.core.userroles.IDeliveryUserRole;
import pizzeria.core.userroles.IWaiterUserRole;
import pizzeria.core.utils.ActionUnsuccessfullException;
import pizzeria.userroles.AbstractRole;

/**Trieda reprezentujuca donasku pizze*/
public class DeliveryGuy extends AbstractRole implements IDeliveryUserRole, IWaiterUserRole {

	public DeliveryGuy(PizzaShop shop) {
		this.name = DeliveryGuy.class.getSimpleName();
		this.description = "Brings food to customer";
		this.pizzaShop = shop;
	}

	@Override
	public void shipOrder(IOrder order) throws ActionUnsuccessfullException {
		// na toto pravdepodobne bude asi aj existovat advice ale by tu bola nejaka akcia.
		order.setState(OrderState.SHIPPING); 
	}

	@Override
	public void acceptOrder(IOrder order) throws ActionUnsuccessfullException {
		pizzaShop.addOrder(order);
	}

	@Override
	public void removeOrder(IOrder order) throws ActionUnsuccessfullException {
		pizzaShop.removeOrder(order);
	}
	
}
