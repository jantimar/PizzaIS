package pizzeria.swingui;

import java.util.Collection;
import java.util.HashSet;

import pizzeria.core.PizzaShop;
import pizzeria.core.meals.Meal;
import pizzeria.core.orders.IOrder;
import pizzeria.core.userroles.IUserRole;
import pizzeria.swingui.windows.MainWindow;
import pizzeria.ui.IUserInterface;

public class SwingUserInterfaceModel implements IUserInterface{

	private PizzaShop shop;
	
	private MainWindow mainWindow;
//	private 
	
	private Actions actions = new Actions();
	
	private IUserRole currentUserRole;
	
	private Collection<IUserRole> userRoles = new HashSet<IUserRole>();
	
	public PizzaShop getShop() {
		return shop;
	}

	public void setShop(PizzaShop shop) {
		this.shop = shop;
	}

	public IUserRole getCurrentUserRole() {
		return currentUserRole;
	}

	public void setCurrentUserRole(IUserRole currentUserRole) {
		this.currentUserRole = currentUserRole;
		if(!userRoles.contains(currentUserRole)){
			userRoles.add(currentUserRole);
		}
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public Actions getActions() {
		return actions;
	}

	@Override
	public void start() {
		this.mainWindow = new MainWindow(new UserRoleUser("john",currentUserRole));
	}

	@Override
	public void terminate() {
		System.exit(0);
	}
	
	public SwingUserInterfaceModel(){
		
	}
	
	public SwingUserInterfaceModel(PizzaShop shop){
		this.shop = shop;
	}
	
	public void addUserRole(IUserRole userRole){
		this.userRoles.add(userRole);
	}
	
	public void removeUserRole(IUserRole userRole){
		this.userRoles.remove(userRole);
		if(currentUserRole.equals(userRole)){
			this.actions.signOut();
		}
	}
	
	//*****
 	public class Actions {
		public void updateMealMenu(Collection<Meal> meals){
			
		}
		
		public void registerMeal(Meal meal){
			shop.getMealsMenu().registerMeal(meal);
		}
		
		public void unregisterMeal(Meal meal){
			
		}
		
		//----
		
		public void removeMeal(IOrder order, Meal meal){
			
		}
		
		public void addMeal(IOrder order, Meal meal){
			
		}
		
		public void updateOrderMeals(IOrder order, Collection<Meal> meals){
			
		}
		
		//----
		
		
		public void updateOrders(Collection<IOrder> orders){
			
		}
		
		public void addOrder(IOrder order){
			
		}
		
		public void removeOrder(IOrder order){
			
		}
		
		//-----
		
		public void signIn(){
			
		}
		
		public void signOut(){
			
		}
	}
}
