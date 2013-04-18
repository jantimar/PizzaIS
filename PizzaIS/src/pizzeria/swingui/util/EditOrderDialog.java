package pizzeria.swingui.util;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
//import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Vector;

import pizzeria.core.customers.ICustomer;
import pizzeria.core.meals.Meal;
import pizzeria.core.orders.DeliveryOrder;
import pizzeria.core.orders.IOrder;
import pizzeria.core.orders.PersonalOrder;
import pizzeria.loyaltyprogram.IExchangeAction;
import pizzeria.loyaltyprogram.LoyaltyProgram;
import pizzeria.swingui.tablemodels.MealsTableModel;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;

public class EditOrderDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			AddOrder dialog = new AddOrder();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	private MealsTableModel tableModel;

	private JLabel destLabel;
	private JTextField destTextField;
	private JComboBox<String> orderTypeComboBox;
	private JComboBox<ICustomer> customersComboBox;
	private JComboBox<Meal> mealsComboBox;
	private JTable mealsTable;
	private JCheckBox loyaltyProgramCheckBox;
	private JComboBox<IExchangeAction> exchangeActionsComboBox;
	
//	/**
//	 * Create the dialog.
//	 * @wbp.parser.constructor
//	 */
//	public EditOrderDialog(JFrame owner, Collection<ICustomer> customers, Collection<IExchangeAction> exchangeActions) {
//		super(owner);
//		initialize(customers);
//	}
	
	/**
	 * Create the dialog.
	 */
	public EditOrderDialog(JDialog owner, Collection<ICustomer> customers, Collection<IExchangeAction> exchangeActions) {
		super(owner);
		initialize(customers);
	}
		
	private void initialize(Collection<ICustomer> customers){
		setTitle("Add order");
		
		int windowWidth = 460;
		int windowHeight = 300;
		int[] center = pizzeria.swingui.dialogs.Utilities.getCenterPosition(windowWidth, windowHeight);
		setBounds(center[0], center[1], windowWidth, windowHeight);
		
		JPanel contentPanel = new JPanel();
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][20px][][][][][grow]"));
		
		// Order type
		
		{
			JLabel lblOrderType = new JLabel("Order type");
			contentPanel.add(lblOrderType, "cell 0 0,alignx leading");
		}
		{
			orderTypeComboBox = new JComboBox<String>();
			//Ugly code
			
				orderTypeComboBox.addItem("Personal");
			
				orderTypeComboBox.addItem("Delivery");
			
			orderTypeComboBox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(orderTypeComboBox.getSelectedItem().equals("Personal")){
						destLabel.setText("Table number");
					}
					else {
						destLabel.setText("Destination");
					}
				}
			});
			
			contentPanel.add(orderTypeComboBox, "cell 1 0,growx");
		}
		
		// Destination
		
		{
			destLabel = new JLabel("Destination");
			contentPanel.add(destLabel, "flowx,cell 0 1,alignx left,aligny center");
		}
		
		{
			destTextField = new JTextField();
			contentPanel.add(destTextField, "cell 1 1,grow");
			destTextField.setColumns(10);
		}
				
		// Customer
		
		{
			JLabel lblCustomer = new JLabel("Customer");
			contentPanel.add(lblCustomer, "cell 0 2,alignx left");
		}
		{
			customersComboBox = new JComboBox<ICustomer>(new Vector<ICustomer>(customers));
			
			contentPanel.add(customersComboBox, "flowx,cell 1 2,growx");
		}
		
		
		// Loyalty program
		
		{
			JLabel lblLoyaltyProgram = new JLabel("Loyalty program");
			contentPanel.add(lblLoyaltyProgram, "cell 0 3");
		}
		{
			loyaltyProgramCheckBox = new JCheckBox("Apply points");
			contentPanel.add(loyaltyProgramCheckBox, "cell 1 3");
		}
		{
			exchangeActionsComboBox = new JComboBox<IExchangeAction>();
			contentPanel.add(exchangeActionsComboBox, "cell 1 3");
		}
		
		
		// Meals
		{
			JLabel lblAddMeal = new JLabel("Insert meal");
			contentPanel.add(lblAddMeal, "cell 0 4");
		}
		{
			JComboBox<Meal> melasComboBox = new JComboBox<Meal>();
			contentPanel.add(melasComboBox, "flowx,cell 1 4,growx");
		}
		{
			JButton btnAddMeal = new JButton("Insert meal");
			btnAddMeal.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent ev) {
					Meal meal = (Meal)mealsComboBox.getSelectedItem();
					tableModel.addMeal(meal);
				}
			});
			contentPanel.add(btnAddMeal, "flowx,cell 1 5");
		}
		{
			JButton btnRemoveMeal = new JButton("Remove selected");
			btnRemoveMeal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tableModel.removeMealsAt(mealsTable.getSelectedRows());
				}
			});
			contentPanel.add(btnRemoveMeal, "cell 1 5");
		}
		{
			mealsTable = new JTable();
			mealsTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			contentPanel.add(mealsTable, "cell 0 6 2 1,grow");
		}
		
		
		// Dialog buttons
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void updateAvailableCustomers(Collection<ICustomer> customers){
		customersComboBox.removeAllItems();
		for(ICustomer customer : customers){
			customersComboBox.addItem(customer);			
		}
	}

	public void updateAvailableMeals(Collection<Meal> meals){
		mealsComboBox.removeAllItems();
		for(Meal meal : meals){
			mealsComboBox.addItem(meal);			
		}
	}
	
	public void updateMeals(Collection<Meal> meals){
		tableModel.setData(meals);
	}
	
	public IOrder getOrder(){
		IOrder order;
		
		ICustomer customer = (ICustomer)customersComboBox.getSelectedItem();
		
		if(orderTypeComboBox.getSelectedItem().equals("Personal")){
			order = new PersonalOrder(customer, tableModel.getData());
			((PersonalOrder)order).setTableNumber( Integer.parseInt(destTextField.getText()) );
		}
		else{
			order = new DeliveryOrder(customer, tableModel.getData());
			((DeliveryOrder)order).setDestination( destTextField.getText() );
		}
		
		if(loyaltyProgramCheckBox.isSelected()){
			order.putMeta(LoyaltyProgram.ORDER_META_KEY, (IExchangeAction)exchangeActionsComboBox.getSelectedItem() );
		}
		
		return order;
	}
	
}
