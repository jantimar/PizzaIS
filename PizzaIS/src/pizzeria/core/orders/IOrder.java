package pizzeria.core.orders;

import java.util.List;

import pizzeria.core.customers.ICustomer;
import pizzeria.core.meals.Meal;
import pizzeria.core.utils.ActionUnsuccessfullException;

public interface IOrder {
	public int getId();
	
	public float getOrderBill();
	public float getOrderCost();
	public float getOrderProfit();
	
	public OrderState getState();
	
	public void setState(OrderState state) throws ActionUnsuccessfullException; // neviem ine riesenie
	public void addMeal(Meal meal);
	public void removeMeal(Meal meal);
	public List<Meal> getMealsList();
	
	public ICustomer getCustomer();
}
