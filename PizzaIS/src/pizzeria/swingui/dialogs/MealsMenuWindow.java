package pizzeria.swingui.dialogs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import pizzeria.core.meals.Meal;
import pizzeria.core.userroles.IMealsMenuManUserRole;
//import pizzeria.core.userroles.IUserRole;
import pizzeria.core.utils.ActionUnsuccessfullException;
import pizzeria.swingui.SwingUserInterface;
import pizzeria.swingui.tablemodels.MealsTableModel;
import pizzeria.swingui.util.ConfirmedShopActionListener;
import pizzeria.swingui.util.ShopActionListener;

import net.miginfocom.swing.MigLayout;
import javax.swing.border.EtchedBorder;

public class MealsMenuWindow {

	private JTable table;
	
	private MealsTableModel tableModel;
		
	private JFrame frame;

	private IMealsMenuManUserRole userRole;
	
	private SwingUserInterface ui;
	
	/**
	 * Create the application.
	 */
	public MealsMenuWindow(IMealsMenuManUserRole userRole, SwingUserInterface ui, Collection<Meal> meals) {
		this.userRole = userRole;
		this.ui = ui;
		initialize(meals);
		initializeTable(meals);
	}
	
	private void initialize(Collection<Meal> meals) {
		
		frame.setTitle("Meals menu");
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int windowWidth = 460;
		int windowHeight = 300;
		int[] center = pizzeria.swingui.dialogs.Utilities.getCenterPosition(windowWidth, windowHeight);
		frame.setBounds(center[0], center[1], windowWidth, windowHeight);
		
		JPanel contentPanel = new JPanel();

		frame.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		{
			JButton btnAddIngredient = new JButton("Add meal");
			btnAddIngredient.addActionListener(new ShopActionListener(frame) {
				@Override
				public void shopActionPerformed(ActionEvent ev) throws ActionUnsuccessfullException {
					Meal meal = ui.requireNewMeal(MealsMenuWindow.this.frame);
					userRole.registerMeal(meal);
					tableModel.addMeal(meal);
				}
			});
			contentPanel.add(btnAddIngredient, "flowx,cell 0 0,alignx leading");
		}
		
		
		{
			JButton btnRemoveSelected = new JButton("Remove selected");
			btnRemoveSelected.addActionListener(new ConfirmedShopActionListener(frame) {
				
				@Override
				public void shopActionPerformed(ActionEvent ev)
						throws ActionUnsuccessfullException {
					for(int index: table.getSelectedRows()){
						Meal meal = tableModel.getMealAt(index);
						userRole.unregisterMeal(meal);
						tableModel.removeMealAt(index);
					}
				}
				
				@Override
				public String getConfirmMessage() {
					return String.format("Do you really wanna remove %d meals", table.getSelectedRows().length);
				}
			});
			
			contentPanel.add(btnRemoveSelected, "cell 0 0");
		}
		

	}
	
	public void initializeTable(Collection<Meal> meals){
		tableModel = new MealsTableModel(meals);
		table = new JTable(tableModel);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(table, "cell 0 1,grow");
		
	}
	
	
	public void updateIngredients(Collection<Meal> meals){
		tableModel.setData(meals);
	}
	
}
