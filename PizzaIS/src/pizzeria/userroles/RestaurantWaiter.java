package pizzeria.userroles;

import pizzeria.core.PizzaShop;
import pizzeria.core.orders.IOrder;
import pizzeria.core.orders.OrderState;
import pizzeria.core.userroles.*;
import pizzeria.core.utils.ActionUnsuccessfullException;

/**Trieda reprezentujuca casnika*/
public class RestaurantWaiter extends AbstractRole implements IWaiterUserRole, IDeliveryUserRole {

	public RestaurantWaiter(PizzaShop shop){
		this.name = RestaurantWaiter.class.getSimpleName();
		this.description = " Accepts and ships orders";
		this.pizzaShop = shop;
	}

	@Override
	public void acceptOrder(IOrder order) throws ActionUnsuccessfullException {
		pizzaShop.addOrder(order);
	}

	@Override
	public void shipOrder(IOrder order) throws ActionUnsuccessfullException {
		order.setState(OrderState.SHIPPING);
	}

	@Override
	public void removeOrder(IOrder order) throws ActionUnsuccessfullException {
		pizzaShop.removeOrder(order);
	}

}
