package pizzeria.core.userroles;

import pizzeria.core.orders.IOrder;
import pizzeria.core.utils.ActionUnsuccessfullException;

/**
 * Pohlad na obchod z pohladu donasky objednavok - aktivnej entity systemu
 * @author Michal Vrabel
 *
 */
public interface IDeliveryUserRole {
	/**
	 * Odoslanie (dorucenie) objednavky
	 */
	public void shipOrder(IOrder order) throws ActionUnsuccessfullException;
}
