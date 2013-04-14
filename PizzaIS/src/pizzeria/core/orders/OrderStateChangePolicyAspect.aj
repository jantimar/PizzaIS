package pizzeria.core.orders;

import pizzeria.core.utils.ActionUnsuccessfullException;
//import pizzeria.core.userroles.ActionUnsuccessfullException;

/**
 * Kontrola poradia zmeny stavu objednávky. Mierne diskutabilne
 * @author 
 *
 */
public aspect OrderStateChangePolicyAspect {
	
	before(IOrder order, OrderState newState) throws ActionUnsuccessfullException
		: execution(public void IOrder+.setState(OrderState)) && this(order) && args(newState) {
		
		OrderState currentState = order.getState();
		
		if(!OrderState.isValidTransition(currentState, newState)){
			throw new ActionUnsuccessfullException("Attempting to change OrderState in invalid manner");
		}
		
	}
	
}
