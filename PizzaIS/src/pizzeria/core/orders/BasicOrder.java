package pizzeria.core.orders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pizzeria.core.customers.ICustomer;
import pizzeria.core.meals.Meal;
import pizzeria.core.utils.ActionUnsuccessfullException;
/**
 * Zakladna nespecifikovana objednavka splnajuca vsetky poziadavky na objednavku
 * @author Michal Vrabel
 *
 */
public class BasicOrder implements IOrder {
	/** jedla v objednavke */
	protected List<Meal> meals;
	/** identifikator objednavky */
	protected int id = -1;
	/** stav objednavky */
	protected OrderState orderState = OrderState.NEW;
	/** zakaznik ktoremu prislucha objednavka */
	protected ICustomer customer;
	/** meta informacie objednavky - napriklad informacie o pracovnikoch ktory ju spracovali */
	protected Map<String,Object> orderMeta = new HashMap<String,Object>();
	
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
			prize += actualPizza.getPrice();
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

	public BasicOrder(ICustomer customer){
		this.meals = new ArrayList<Meal>();
		this.customer = customer;
	}
		
	public BasicOrder(ICustomer customer, Collection<Meal> meals){
		this.meals = new ArrayList<Meal>(meals); 
		this.customer = customer;
	}
	
	public BasicOrder(ICustomer customer, OrderState orderState, Collection<Meal> meals){
		this(-1,customer,orderState,meals);
	}
	
	public BasicOrder(int id, ICustomer customer, OrderState orderState, Collection<Meal> meals){
		this.id = id;
		this.orderState = orderState;
		this.meals = new ArrayList<Meal>(meals); 
		this.customer = customer;
	}
	
	/**
	 * Vlozenie meta informacie
	 */
	@Override
	public void putMeta(String key, Object data) {
		this.orderMeta.put(key, data);
	}
	
	/**
	 * Ziskanie meta informacie
	 */
	@Override
	public Object getMeta(String key) {
		return this.orderMeta.get(key);
	}

	/**
	 * Zistenie ci ma nastavenu meta informaciu pre zadany kluc
	 */
	@Override
	public boolean containsMetaKey(String key) {
		return this.orderMeta.containsKey(key);
	}
	
}
