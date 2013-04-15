package pizzeria.core.orders;

import java.util.List;

import pizzeria.core.customers.ICustomer;
import pizzeria.core.meals.Meal;
import pizzeria.core.utils.ActionUnsuccessfullException;
import pizzeria.core.utils.IMetaContainer;

public interface IOrder extends IMetaContainer {
	public int getId();
	
	public float getOrderBill();	//kolko som zaplatil pre zakaznika
	public float getOrderCost();	//kolko mna stala
	public float getOrderProfit();	//rozdiel
	
	public OrderState getState();
	
	public void setState(OrderState state) throws ActionUnsuccessfullException; // neviem ine riesenie
	public void addMeal(Meal meal);
	public void removeMeal(Meal meal);
	public List<Meal> getMealsList();
	
	public ICustomer getCustomer();
	
}
