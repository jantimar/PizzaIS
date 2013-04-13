package pizzeria.core.userroles;

import pizzeria.core.orders.IOrder;
import pizzeria.core.utils.ActionUnsuccessfullException;

/**
 * Aktivna entita systemu poskytjuca akciu uvarenia jedal v objednavke
 * @author Michal Vrabel
 *
 */
public interface ICookUserRole {
	/**
	 * Uvarenie jedal v objednavke
	 * @param order
	 */
	public void cookOrderMeals(IOrder order) throws ActionUnsuccessfullException;
}
