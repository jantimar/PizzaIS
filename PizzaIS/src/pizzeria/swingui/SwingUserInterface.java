package pizzeria.swingui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import pizzeria.core.PizzaShop;
import pizzeria.core.customers.GuestCustomer;
import pizzeria.core.customers.ICustomer;
import pizzeria.core.customers.IRegisteredCustomer;
import pizzeria.core.meals.Meal;
import pizzeria.core.orders.IOrder;
import pizzeria.core.stock.Ingredient;
import pizzeria.core.userroles.IUserRole;
import pizzeria.loyaltyprogram.IExchangeAction;
import pizzeria.loyaltyprogram.LoyaltyProgram;
import pizzeria.loyaltyprogram.exchangeactions.PriceExchange;
import pizzeria.swingui.dialogs.LoginDialog;
import pizzeria.swingui.dialogs.OrdersWindow;
import pizzeria.swingui.users.UserManagement;
import pizzeria.swingui.util.EditOrderDialog;
import pizzeria.ui.IUserInterface;

public class SwingUserInterface implements IUserInterface{

	private PizzaShop shop;
	private LoyaltyProgram loyaltyProgram;
	
//	private MainWindow mainWindow;
		
	private UserManagement userManagement;
	
	private boolean exitRequired = false;
	
	public SwingUserInterface(PizzaShop shop, UserManagement userManagement, LoyaltyProgram loyaltyProgram){
		this.shop = shop;
		this.userManagement = userManagement;
		this.loyaltyProgram = loyaltyProgram;
		setupLookAndFeel();
	}	
	
	public PizzaShop getShop() {
		return shop;
	}

	
	public void openOrders(){
		OrdersWindow window = new OrdersWindow(this, userManagement.getCurrentUserRole());
		window.open();
	}
	
	public void openFinance(){
		openFinance(null);
	}
	
	public IUserRole requireUser(){
		return LoginDialog.showDialog(userManagement.getUserRoles());
	}
	public IUserRole requireUser(JFrame parent){
		return LoginDialog.showDialog(parent,userManagement.getUserRoles());
	}
	public IUserRole requireUser(JDialog parent){
		return LoginDialog.showDialog(parent,userManagement.getUserRoles());
	}
	
	public IOrder requireNewOrder(){
		return requireNewOrder((JFrame)null);
	}
	public IOrder requireNewOrder(JFrame parent){
		return null;
//		EditOrderDialog dialog = new EditOrderDialog(parent, getAvailableCustomers(), getExchangeActions());
//		dialog.setVisible(true);
//		return dialog.getOrder();
	}
	public IOrder requireNewOrder(JDialog parent){
		Collection<ICustomer> customers = getAvailableCustomers();
		Collection<IExchangeAction> exch = getExchangeActions();
		
		EditOrderDialog dialog = new EditOrderDialog(parent, customers, exch);
		dialog.setVisible(true);
		return dialog.getOrder();
	}
	
	
	public Meal requireNewMeal(){
		return requireNewMeal((JFrame)null);
	}
	public Meal requireNewMeal(JFrame parent){
		return null;
	}
	public Meal requireNewMeal(JDialog parent){
		return null;
	}
	
	public ICustomer requireNewCustomer(){
		return requireNewCustomer((JFrame)null);
	}
	public ICustomer requireNewCustomer(JFrame parent){
		return null;
	}
	public ICustomer requireNewCustomer(JDialog parent){
		return null;
	}
	
	
	public void openFinance(JFrame parent){
		
	}
	
	
	public void setExitRequired(boolean exitRequired){
		this.exitRequired = exitRequired;
	}
	
	public boolean isExitRequired(){
		return this.exitRequired;
	}
	
	private Collection<ICustomer> getAvailableCustomers(){
		Collection<IRegisteredCustomer> registered
			= loyaltyProgram.getCustomerPointsMap().keySet();
		Collection<ICustomer> customers = new ArrayList<ICustomer>();
		
		customers.add(new GuestCustomer("Unknown customer"));
		customers.addAll(registered);
		
		return customers;
	}
	
	
	private Collection<IExchangeAction> getExchangeActions(){
		ArrayList<IExchangeAction> exchanges = new ArrayList<IExchangeAction>();
		exchanges.add(new PriceExchange(1/5));
		return exchanges;
	}
	
	protected void setupLookAndFeel(){
		
		try {
			
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
			
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
	}
		
}
//
////	public MainWindow getMainWindow() {
////		return mainWindow;
////	}
//
//	public SwingUserInterfaceActions getActions() {
//		return actions;
//	}
//	
//	public SwingUserInterfaceDialogs getDialogs(){
//		return dialogs;
//	}