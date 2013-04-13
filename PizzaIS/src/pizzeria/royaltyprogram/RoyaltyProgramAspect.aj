package pizzeria.royaltyprogram;

import pizzeria.core.orders.IOrder;
import pizzeria.core.orders.BasicOrder;

public aspect RoyaltyProgramAspect {
	
	float around(IOrder order) : call(public float IOrder+.getOrderBill()) && target(order)
	{
		
		if(order instanceof BasicOrder){
			System.out.println( ((BasicOrder) order).getShop().getContext().getData("kausta") );
		}
		
		return proceed(order);
	}
}
