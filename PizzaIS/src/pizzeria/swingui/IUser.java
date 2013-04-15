package pizzeria.swingui;

public interface IUser {
	public boolean canCookOrder();
	public boolean canDeliverOrder();
//	public boolean canAcceptOrder();
	public boolean canAddOrder();
	public boolean canRemoveOrder();
	public boolean canAddMeal();
	public boolean canRemoveMeal();
	
	public String getDisplayName();
}
