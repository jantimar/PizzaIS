package pizzeria.swingui.extrauserroles;

import pizzeria.core.customers.ICustomer;
import pizzeria.core.userroles.IUserRole;
import pizzeria.core.utils.ActionUnsuccessfullException;

public interface ICustomerManger extends IUserRole {
	public void registerCustomer(ICustomer customer) throws ActionUnsuccessfullException;
	public void unregisterCustomer(ICustomer customer) throws ActionUnsuccessfullException;
}
