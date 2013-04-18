package pizzeria.core.userroles;

import pizzeria.core.meals.Meal;
/**
 * Aktivna entita s pravom rusit objednavky
 * @author uzivatel
 *
 */
public interface IMealsMenuManUserRole extends IUserRole {
	public void registerMeal(Meal meal);
	public void unregisterMeal(Meal meal);	
}
