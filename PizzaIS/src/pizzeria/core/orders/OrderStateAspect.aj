package pizzeria.core.orders;

import pizzeria.core.userroles.*;
import pizzeria.core.utils.ActionUnsuccessfullException;

/**
 * Poskytuje zmenu stavu objednavky pre rozlicne akcie rôl v systeme 
 * @author Michal Vrabel
 *
 */
public aspect OrderStateAspect {

	/**
	 * Potom ako IWaiterUserRole prijme objednavku sa nastavi na stav NEW 
	 * @param order
	 * @throws ActionUnsuccessfullException
	 */  
	after(IOrder order) throws ActionUnsuccessfullException : call(public void IWaiterUserRole+.acceptOrder(IOrder)) && args(order) {
		order.setState(OrderState.NEW);
	}
	
	/**
	 * Pred tym ako ju spracuje ICookUserRole sa nastavi na IN_PROGRESS
	 * @param order
	 * @throws ActionUnsuccessfullException
	 */
	before(IOrder order) throws ActionUnsuccessfullException : call(public void ICookUserRole+.cookOrderMeals(IOrder)) && args(order) {
		order.setState(OrderState.IN_PROGRESS);
	}
	
	/**
	 * Potom ako ju spracuje ICookUserRole sa nastavi na READY
	 * @param order
	 * @throws ActionUnsuccessfullException
	 */
	after(IOrder order) throws ActionUnsuccessfullException : call(public void ICookUserRole+.cookOrderMeals(IOrder)) && args(order) {
		order.setState(OrderState.READY);
	}
	
	/**
	 * Pred tym ako ju IDeliveryUserRole doruci je nastavena na SHIPPING
	 * @param order
	 * @throws ActionUnsuccessfullException
	 */
	before(IOrder order) throws ActionUnsuccessfullException : call(public void IDeliveryUserRole+.shipOrder(IOrder)) && args(order) {
		order.setState(OrderState.SHIPPING);
	}
	
	/**
	 * Potom ako ju IDeliveryUserRole doruci je nastavena na FINISHED
	 * @param order
	 * @throws ActionUnsuccessfullException
	 */
	after(IOrder order) throws ActionUnsuccessfullException : call(public void IDeliveryUserRole+.shipOrder(IOrder)) && args(order) {
		order.setState(OrderState.FINISHED);
	}
}
