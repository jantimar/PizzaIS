package pizzeria.swingui.extrauserroles;

import pizzeria.core.PizzaShop;
import pizzeria.core.meals.Meal;
import pizzeria.core.orders.IOrder;
import pizzeria.core.stock.Ingredient;
import pizzeria.core.userroles.ICookUserRole;
import pizzeria.core.userroles.IDeliveryUserRole;
import pizzeria.core.userroles.IMealsMenuManUserRole;
import pizzeria.core.userroles.IStockManUserRole;
import pizzeria.core.userroles.IWaiterUserRole;
import pizzeria.core.utils.ActionUnsuccessfullException;
import pizzeria.userroles.AbstractRole;
import pizzeria.userroles.Cook;
import pizzeria.userroles.RestaurantWaiter;

public class Director extends AbstractRole implements 
	IWaiterUserRole, IDeliveryUserRole, ICookUserRole, IMealsMenuManUserRole, IStockManUserRole {

	private RestaurantWaiter waiter;
	private Cook cook;
	
	public Director(PizzaShop shop){
		this.name = Director.class.getSimpleName();
		this.description = "Does everything";
		this.pizzaShop = shop;
		this.waiter = new RestaurantWaiter(shop);
		this.cook = new Cook(shop);
	}
	
	@Override
	public void registerMeal(Meal meal) {
		pizzaShop.getMealsMenu().registerMeal(meal);
	}

	@Override
	public void unregisterMeal(Meal meal) {
		pizzaShop.getMealsMenu().unregisteMeal(meal);
	}

	@Override
	public void cookOrderMeals(IOrder order)
			throws ActionUnsuccessfullException {
		this.cook.cookOrderMeals(order);
	}

	@Override
	public void shipOrder(IOrder order) throws ActionUnsuccessfullException {
		this.waiter.shipOrder(order);
	}

	@Override
	public void acceptOrder(IOrder order) throws ActionUnsuccessfullException {
		this.waiter.acceptOrder(order);
	}

	@Override
	public void removeOrder(IOrder order) throws ActionUnsuccessfullException {
		this.waiter.removeOrder(order);
	}

	
	@Override
	public void buyIngredient(Ingredient ingredient, int quantity)
			throws ActionUnsuccessfullException {
		pizzaShop.getStock().addIngredient(ingredient, quantity);
		
	}

}
