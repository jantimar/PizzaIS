package pizzeria.core.orders;

import java.util.Collection;

import pizzeria.core.customers.ICustomer;
import pizzeria.core.meals.Meal;

/**
 * Trieda reprezentujuca objednavku z pizzerie 
 * */
public class PersonalOrder extends BasicOrder {

	private int tableNumber;
	/**
	 * Cislo stola
	 * @return
	 */
	public int getTableNumber() {
		return tableNumber;
	}
	/**
	 * Nastavenie cisla stola
	 * @param tableNumber
	 */
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	
	public PersonalOrder(ICustomer customer){
		super(customer);
	}
		
	public PersonalOrder(ICustomer customer, Collection<Meal> meals){
		super(customer, meals);
	}
	
	public PersonalOrder(ICustomer customer, OrderState orderState, Collection<Meal> meals){
		this(-1,customer,orderState,meals);
	}
	
	public PersonalOrder(int id, ICustomer customer, OrderState orderState, Collection<Meal> meals){
		super(id, customer, orderState, meals);
	}

}
