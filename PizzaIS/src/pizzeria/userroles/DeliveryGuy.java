package pizzeria.userroles;

import pizzeria.core.PizzaShop;
import pizzeria.core.orders.AfterOrderState;
import pizzeria.core.orders.BeforeOrderState;
import pizzeria.core.orders.IOrder;

import pizzeria.core.orders.OrderState;
import pizzeria.core.userroles.IDeliveryUserRole;
import pizzeria.core.userroles.IWaiterUserRole;
import pizzeria.core.utils.ActionUnsuccessfullException;
import pizzeria.userroles.AbstractRole;

/**Trieda reprezentujuca donasku pizze*/
public class DeliveryGuy extends AbstractRole implements IDeliveryUserRole, IWaiterUserRole {

	public DeliveryGuy(PizzaShop shop) {
		this.name = DeliveryGuy.class.toString();
		this.description = "Brings food to customer";
		this.pizzaShop = shop;
	}

	@Override
	@BeforeOrderState(orderState = OrderState.SHIPPING)
	@AfterOrderState(orderState = OrderState.FINISHED)
	public void shipOrder(IOrder order) throws ActionUnsuccessfullException {
		order.setState(OrderState.SHIPPING); 
	}

	@Override
	@AfterOrderState(orderState = OrderState.NEW)
	public void acceptOrder(IOrder order) throws ActionUnsuccessfullException {
		pizzaShop.addOrder(order);
	}

	
}
