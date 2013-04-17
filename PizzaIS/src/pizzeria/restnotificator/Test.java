package pizzeria.restnotificator;

import pizzeria.core.customers.RegisteredCustomer;
import pizzeria.core.orders.DeliveryOrder;
import pizzeria.core.orders.OrderState;
import pizzeria.core.utils.ActionUnsuccessfullException;

//TODO po skonceni testovania zmazat
public class Test {

	public static void main(String[] args) {
		DeliveryOrder delivery = new DeliveryOrder(new RegisteredCustomer(1));
		try {
			delivery.setState(OrderState.READY);
//			delivery.setState(OrderState.IN_PROGRESS);
//			delivery.setState(OrderState.READY);
		} catch (ActionUnsuccessfullException e) {
			System.out.println("bug");
		}
	}
}
