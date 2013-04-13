package pizzeria.core.orders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pizzeria.core.customers.ICustomer;
import pizzeria.core.meals.Meal;
import pizzeria.core.utils.ActionUnsuccessfullException;

public class BasicOrder implements IOrder {
	/** jedla v objednavke */
	protected List<Meal> meals;
	/** identifikator objednavky */
	protected int id = -1;
	/** stav objednavky */
	protected OrderState orderState = OrderState.NEW;
	/** zakaznik ktoremu prislucha objednavka */
	protected ICustomer customer;
	
	/**
	 * Zakaznik
	 */
	public ICustomer getCustomer() {
		return customer;
	}
	
	/**
	 * Identifikator objednavky
	 */
	public int getId() {
		return id;
	}

	/**
	 * Suma ktoru je potrebne za objednavku zaplatit
	 */
	public float getOrderBill() {
		float bill = 0;
		for(Meal meal : meals){
			bill += meal.getPrice();
		}
		return bill;
	}

	/**
	 * vrati sumu ktora je potrebna na vytvorenie objednavky
	 */
	public float getOrderCost()
	{
		float prize = 0;
		for(Meal actualPizza : meals)
		{
			prize += actualPizza.getOutcome();
		}
		return prize;
	}
	
	/**
	 * Suma ktoru obchod zarobi vybavenim objednavky 
	 */
	public float getOrderProfit(){
		return getOrderBill() - getOrderCost();
	}
	
	
	/**
	 * Stav objednavky
	 */
	public OrderState getState() {
		return orderState;
	}

	/**
	 * Nastavenie stavu
	 */
	public void setState(OrderState state) throws ActionUnsuccessfullException {
		orderState = state;
	}

	/**
	 * Pridava jedlo do objednavky
	 */
	public void addMeal(Meal meal) {
		meals.add(meal);
	}
	
	/**
	 * Odobera jedlo
	 */
	public void removeMeal(Meal meal) {
		meals.remove(meal);
	}

	/**
	 * Vracia zoznam jedal. (novu instanciu) 
	 */
	public List<Meal> getMealsList() {
		return new ArrayList<Meal>(meals);
	}

	public BasicOrder(){
		this.meals = new ArrayList<Meal>();
	}
		
	public BasicOrder(Collection<Meal> meals){
		this.meals = new ArrayList<Meal>(meals); 
	}
	
	public BasicOrder(OrderState orderState, Collection<Meal> meals){
		this(-1,orderState,meals);
	}
	
	public BasicOrder(int id, OrderState orderState, Collection<Meal> meals){
		this.id = id;
		this.orderState = orderState;
		this.meals = new ArrayList<Meal>(meals); 
	}
	
}
