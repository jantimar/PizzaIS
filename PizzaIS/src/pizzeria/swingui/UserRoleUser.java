package pizzeria.swingui;

import pizzeria.core.userroles.*;

public class UserRoleUser implements IUser {

	private IUserRole userRole;
	
	private String displayName; 
	
	@Override
	public boolean canCookOrder() {
		return (userRole instanceof ICookUserRole);
	}

	@Override
	public boolean canDeliverOrder() {
		return (userRole instanceof IDeliveryUserRole);
	}

//	@Override
//	public boolean canAcceptOrder() {
//		return (userRole instanceof IWaiterUserRole);
//	}

	@Override
	public boolean canAddOrder() {
		return (userRole instanceof IWaiterUserRole);
	}

	@Override
	public boolean canRemoveOrder() {
		return canAddOrder();
	}

	@Override
	public boolean canAddMeal() {
		return canAddOrder();
	}

	@Override
	public boolean canRemoveMeal() {
		return canAddOrder();
	}
	
	public UserRoleUser(String displayName,IUserRole role){
		this.displayName = displayName;
		this.userRole = role;
	}

	public IUserRole getUserRole(){
		return userRole;
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}
	
}
