package pizzeria.userroles;

import java.util.Collection;
import java.util.List;

import pizzeria.core.PizzaShop;
import pizzeria.core.meals.Meal;
import pizzeria.core.orders.IOrder;
import pizzeria.core.stock.IngredientAssoc;
import pizzeria.core.stock.Stock;
import pizzeria.core.stock.UnsatisfiableQuantityException;
import pizzeria.core.userroles.ICookUserRole;
import pizzeria.core.utils.ActionUnsuccessfullException;

/** Trieda reprezentujuca kuchara */
public class Cook extends AbstractRole implements ICookUserRole {
	
	public Cook(PizzaShop shop) {
		this.name = DeliveryGuy.class.getSimpleName();
		this.description = "Cooks food";
		this.pizzaShop = shop;
	}

	/** 
	 * kuchar vytvori pizzu 
	 * @throws CookingImpossibleException
	 */
	@Override
	public void cookOrderMeals(IOrder order) throws ActionUnsuccessfullException {
		
		List<Meal> meals = order.getMealsList();
		Stock stock = this.pizzaShop.getStock();
		
		try{
			for(Meal meal : meals){
				Collection<IngredientAssoc> ingredientAssocs = meal.getIngredientAssocsCollection();
				for(IngredientAssoc assoc : ingredientAssocs){
					stock.takeIngredient(assoc.getIngredient(), assoc.getQuantity());
				}
			}			
		}
		catch(UnsatisfiableQuantityException ex){
			
			throw new ActionUnsuccessfullException(ex);
		}
		
	}


}
