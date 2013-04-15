package pizzeria.core.orders;

import pizzeria.core.utils.ActionUnsuccessfullException;



/**
 * Poskytuje zmenu stavu objednavky pre rozlicne akcie r�l v systeme 
 * @author Jan Timar
 *
 */
public aspect OrderStateAspect {

	pointcut stateChangeBefore(IOrder order,BeforeOrderState annotationState) : execution(* pizzeria.userroles.*.*(..)) && args(order) && @annotation(annotationState);
	
	pointcut stateChangeAfter(IOrder order,AfterOrderState annotationState) : execution(* pizzeria.userroles.*.*(..)) && args(order) && @annotation(annotationState);
  
  
	before (IOrder order,BeforeOrderState annotationState) throws ActionUnsuccessfullException : stateChangeBefore(order, annotationState)
	{
		order.setState(annotationState.orderState());
	}
	
	after (IOrder order, AfterOrderState annotationState) throws ActionUnsuccessfullException : stateChangeAfter(order, annotationState)
	{
		order.setState(annotationState.orderState());
	}
	
	after (IOrder order,OrderState state): execution(public void IOrder.setState(OrderState)) && this(order) && args(state) 
	{
		//TODO odosielanie na server asi vytvorit novu triedu na komunikaciu a tam to hodit
	}
}
