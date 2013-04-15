package pizzeria.core.orders;

import java.util.Collection;

import pizzeria.core.customers.ICustomer;
import pizzeria.core.meals.Meal;

/**Trieda reprezentujuca objednavku z internetu */
public class DeliveryOrder extends BasicOrder {

	private String destination;
	/**
	 * Adresa na ktoru sa ma urobit donaska
	 * @param destination
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDestination(){
		return this.destination;
	}
	
	public DeliveryOrder(ICustomer customer){
		super(customer);
	}
		
	public DeliveryOrder(ICustomer customer, Collection<Meal> meals){
		super(customer, meals);
	}
	
	public DeliveryOrder(ICustomer customer, OrderState orderState, Collection<Meal> meals){
		this(-1,customer,orderState,meals);
	}
	
	public DeliveryOrder(int id, ICustomer customer, OrderState orderState, Collection<Meal> meals){
		super(id, customer, orderState, meals);
	}

}
