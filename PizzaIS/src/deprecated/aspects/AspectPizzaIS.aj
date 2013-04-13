//package aspects;
//
//import pizzeria.core.*;
//import pizzeria.core.orders.AbstractOrder.State;
//import pizzeria.core.utils.StateAnotation;
//
///**Aspekty IS pizzerie */
//public aspect AspectPizzaIS
//{		
//
//	pointcut stateChange(StateAnotation state,AbstractOrder order) : execution(* pizzeria.*.*(..)) && args(order) && @annotation(state);
//	
//	before(StateAnotation state,AbstractOrder order): stateChange(state,order)
//	{
//		order.setState(state.orderState());
//	}
//	
//	
//	after(AbstractOrder order) : execution(public AOrder.new(..)) && target(order)
//	{
//		//TODO zaregistrovat ju do datbaazy a ulozit jej id
//		order.setState(State.New);
//	}
//	
//	before(AbstractOrder order) :  call(* Chief.makePizza(..))&& args(order) && if(!order.getState().equals(State.InProgress))
//	{
//		//throw new Exception("");
//	}
//
//}
