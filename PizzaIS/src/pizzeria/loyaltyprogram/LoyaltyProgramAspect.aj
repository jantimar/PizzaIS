package pizzeria.loyaltyprogram;

import pizzeria.core.PizzaShop;
import pizzeria.core.orders.*;

public aspect LoyaltyProgramAspect  {
	
	pointcut pizzaShopCreation(PizzaShop shop) : execution(PizzaShop.new(..)) && this(shop); 
	
	
	private LoyaltyProgram PizzaShop.loyaltyProgram;
	
	public LoyaltyProgram PizzaShop.getLoyaltyProgram(){
		return this.loyaltyProgram;
	}
	
	after(PizzaShop shop) : pizzaShopCreation(shop){
		shop.loyaltyProgram = new LoyaltyProgram(shop);
	}
	
	
	pointcut getOrderBillPointcut(IOrder order): 
		call(public float IOrder+.getOrderBill()) && target(order) && !within(IExchangeAction+) && !within(LoyaltyProgram)
	;
	
	pointcut orderSetState(IOrder order, OrderState newState):
		call(public void IOrder+.setState(OrderState)) && target(order) && args(newState);
	
	float around(IOrder order) : getOrderBillPointcut(order) 
	{
		return order.getShop().getLoyaltyProgram().getOrderBill(order);
	}
	
	
	
	before(IOrder order, OrderState newState) :
		orderSetState(order,newState) && if(newState == OrderState.IN_PROGRESS)
	{
		order.getShop().getLoyaltyProgram().exchange(order);
	}	
	
	before(IOrder order, OrderState newState) : 
		orderSetState(order,newState) && if(newState == OrderState.FINISHED)
	{
		order.getShop().getLoyaltyProgram().addPointsForOrder(order);	
	}
		
	
}
