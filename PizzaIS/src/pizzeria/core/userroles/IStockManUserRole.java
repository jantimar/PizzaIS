package pizzeria.core.userroles;

import pizzeria.core.stock.Ingredient;
import pizzeria.core.utils.ActionUnsuccessfullException;

public interface IStockManUserRole extends IUserRole {
	public void buyIngredient(Ingredient ingredient,int quantity) throws ActionUnsuccessfullException;
	
}
