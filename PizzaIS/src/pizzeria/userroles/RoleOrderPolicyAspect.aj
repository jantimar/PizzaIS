package pizzeria.userroles;

import pizzeria.core.orders.*;
import pizzeria.core.utils.ActionUnsuccessfullException;

/**
 * Kontrola aby sa objednavka nepouzia u nespravnej roly  
 * @author uzivatel
 *
 */
public aspect RoleOrderPolicyAspect {

	before(IOrder order) throws ActionUnsuccessfullException 
		: call(public void DeliveryGuy.*Order(IOrder)) && args(order) {
		
		if(!(order instanceof DeliveryOrder)){
			throw new ActionUnsuccessfullException("DeliveryGuy requires DeliveryOrder");
		}
		
	}

	before(IOrder order) throws ActionUnsuccessfullException 
		: call(public void RestaurantWaiter.*Order(IOrder)) && args(order) {
		
		if(!(order instanceof PersonalOrder)){
			throw new ActionUnsuccessfullException("RestaurantWaiter requires PersonalOrder");
		}
		
	}	
	
	
}
