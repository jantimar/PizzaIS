package pizzeria.core.orders;

import pizzeria.core.userroles.*;
import pizzeria.core.utils.ActionUnsuccessfullException;

/**
 * Poskytuje zmenu stavu objednavky pre rozlicne akcie rôl v systeme 
 * @author Michal Vrabel
 *
 */
public aspect OrderStateAspect {

	//after() : call(Waiter.acceptOrder) - New  
	after(IOrder order) throws ActionUnsuccessfullException : call(public void IWaiterUserRole+.acceptOrder(IOrder)) && args(order) {
		order.setState(OrderState.NEW);
	}
	
	//before() : call(Cheif.cookOrder) - InProgress
	before(IOrder order) throws ActionUnsuccessfullException : call(public void ICookUserRole+.cookOrderMeals(IOrder)) && args(order) {
		order.setState(OrderState.IN_PROGRESS);
	}
	
	//after() : call(Cheif.cookOrder) - Ready
	after(IOrder order) throws ActionUnsuccessfullException : call(public void ICookUserRole+.cookOrderMeals(IOrder)) && args(order) {
		order.setState(OrderState.READY);
	}
	
	//before() : call(Delivery.shipOrder) - Shipping
	before(IOrder order) throws ActionUnsuccessfullException : call(public void IDeliveryUserRole+.shipOrder(IOrder)) && args(order) {
		order.setState(OrderState.SHIPPING);
	}
	//after() : call(Delivery.shipOrder) - Finished
	after(IOrder order) throws ActionUnsuccessfullException : call(public void IDeliveryUserRole+.shipOrder(IOrder)) && args(order) {
		order.setState(OrderState.FINISHED);
	}
}
