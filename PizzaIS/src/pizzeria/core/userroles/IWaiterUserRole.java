package pizzeria.core.userroles;

import pizzeria.core.orders.IOrder;
import pizzeria.core.utils.ActionUnsuccessfullException;
/**
 * Pohlad na obchod z pohladu aktivnej entity systemu casnika - pridava objednavky
 * @author Michal Vrabel
 *
 */
public interface IWaiterUserRole extends IUserRole {
	/**
	 * Prijate objednavky do systemu
	 * @param order
	 */
	public void acceptOrder(IOrder order) throws ActionUnsuccessfullException;
	public void removeOrder(IOrder order) throws ActionUnsuccessfullException;
}
