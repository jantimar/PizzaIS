package pizzeria.swingui.extrauserroles;

import pizzeria.core.PizzaShop;
import pizzeria.core.customers.ICustomer;
import pizzeria.core.customers.IRegisteredCustomer;
import pizzeria.core.orders.IOrder;
import pizzeria.core.userroles.IDeliveryUserRole;
import pizzeria.core.userroles.IWaiterUserRole;
import pizzeria.core.utils.ActionUnsuccessfullException;
import pizzeria.loyaltyprogram.LoyaltyProgram;
import pizzeria.userroles.AbstractRole;
import pizzeria.userroles.DeliveryGuy;


public class UIDeliveryGuy extends AbstractRole implements ICustomerManger, IWaiterUserRole, IDeliveryUserRole {

	private DeliveryGuy waiter;
	
	private LoyaltyProgram loyaltyProgram;
	
	public UIDeliveryGuy(PizzaShop shop,LoyaltyProgram loyaltyProgram) {
		this.loyaltyProgram = loyaltyProgram;
		this.waiter = new DeliveryGuy(shop);
		this.name = this.waiter.getName();
		this.description = this.waiter.getDescription();
	}

	@Override
	public void registerCustomer(ICustomer customer) throws ActionUnsuccessfullException {
		if(customer instanceof IRegisteredCustomer){
			loyaltyProgram.addCustomer((IRegisteredCustomer)customer);			
		}
		else{
			throw new ActionUnsuccessfullException("Customer must be IRegisteredCustomer");
		}
	}

	@Override
	public void unregisterCustomer(ICustomer customer) throws ActionUnsuccessfullException {
		if(customer instanceof IRegisteredCustomer){
			loyaltyProgram.removeCustomer((IRegisteredCustomer)customer);			
		}
		else{
			throw new ActionUnsuccessfullException("Customer must be IRegisteredCustomer");
		}
	}

	@Override
	public void shipOrder(IOrder order) throws ActionUnsuccessfullException {
		waiter.shipOrder(order);
	}

	@Override
	public void acceptOrder(IOrder order) throws ActionUnsuccessfullException {
		waiter.acceptOrder(order);
	}

	@Override
	public void removeOrder(IOrder order) throws ActionUnsuccessfullException {
		waiter.removeOrder(order);
	}
	
	@Override
	public String toString() {
		return waiter.toString();
	}
	
}
