package pizzeria.core.userroles;

import pizzeria.core.orders.IOrder;
/**
 * Aktivna entita s pravom rusit objednavky
 * @author uzivatel
 *
 */
public interface IRemovesOrderUserRole extends IUserRole {
	public void removeOrder(IOrder order);
}
