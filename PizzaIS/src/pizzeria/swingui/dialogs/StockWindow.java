package pizzeria.swingui.dialogs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import pizzeria.core.stock.Ingredient;
import pizzeria.core.stock.IngredientAssoc;
import pizzeria.core.userroles.IStockManUserRole;
//import pizzeria.core.userroles.IUserRole;
import pizzeria.core.utils.ActionUnsuccessfullException;
import pizzeria.swingui.tablemodels.IngredientsTableModel;
import pizzeria.swingui.util.ShopActionListener;

import net.miginfocom.swing.MigLayout;
import javax.swing.border.EtchedBorder;

public class StockWindow {

	private JTable table;
	
	private JTextField nameTextField;
	
	private JTextField priceTextField;
	private JTextField ingQuantityTextField;
	
	private IngredientsTableModel tableModel;
	
	private JComboBox<Ingredient> ingComboBox;
	
	private JFrame frame;

	private IStockManUserRole userRole;
	private JTextField increaseQuantityTextField;
	
	/**
	 * Create the application.
	 */
	public StockWindow(IStockManUserRole userRole, Collection<IngredientAssoc> ingredients) {
		this.userRole = userRole;
		initialize(ingredients);
	}
	
	private void initialize(Collection<IngredientAssoc> ingredients) {
		
		frame.setTitle("Register new meal");
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int windowWidth = 460;
		int windowHeight = 300;
		int[] center = pizzeria.swingui.dialogs.Utilities.getCenterPosition(windowWidth, windowHeight);
		frame.setBounds(center[0], center[1], windowWidth, windowHeight);
		
		JPanel contentPanel = new JPanel();

		frame.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setLayout(new MigLayout("", "[][grow][]", "[20px][][][][grow][]"));
		
		// Name
		
		{
			JLabel lblAddress = new JLabel("Name");
			contentPanel.add(lblAddress, "cell 0 0,alignx left,aligny center");
		}
		{
			nameTextField = new JTextField();
			contentPanel.add(nameTextField, "cell 1 0 2 1,grow");
			nameTextField.setColumns(10);
		}

		// Price
		
		{
			JLabel lblAddress = new JLabel("Price");
			contentPanel.add(lblAddress, "cell 0 1,alignx left,aligny center");
		}
		{
			priceTextField = new JTextField();
			contentPanel.add(priceTextField, "cell 1 1 2 1,grow");
			priceTextField.setColumns(10);
		}
		
		// Quantity
		
		{
			JLabel lblQuantity = new JLabel("Quantity");
			contentPanel.add(lblQuantity, "cell 0 2");
		}
		{
			ingQuantityTextField = new JTextField();
			contentPanel.add(ingQuantityTextField, "cell 1 2");
			ingQuantityTextField.setColumns(6);
		}
		{
			JButton btnAddIngredient = new JButton("Add ingredient");
			btnAddIngredient.addActionListener(new ShopActionListener(frame) {
				
				@Override
				public void shopActionPerformed(ActionEvent ev) throws ActionUnsuccessfullException {
					
					try {
						userRole.buyIngredient(
							new Ingredient(nameTextField.getText(),Float.parseFloat(priceTextField.getText())),
							Integer.parseInt(ingQuantityTextField.getText())
						);
					} catch (NumberFormatException e1) {
						throw new ActionUnsuccessfullException("Invalid price");
					}
					
					tableModel.addIngredient(
						new IngredientAssoc(
							(Ingredient)ingComboBox.getSelectedItem(),
							Integer.parseInt( ingQuantityTextField.getText() )
						));
				}
			});
			contentPanel.add(btnAddIngredient, "flowx,cell 2 3,alignx leading");
		}
		
		
		{
			tableModel = new IngredientsTableModel(ingredients);
			table = new JTable(tableModel);
			table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			contentPanel.add(table, "cell 0 4 3 1,grow");
		}
		JButton btnRemoveIngredient = new JButton("Increase quantity of selected");
		btnRemoveIngredient.setHorizontalAlignment(SwingConstants.RIGHT);
		btnRemoveIngredient.addActionListener(new ShopActionListener(frame) {
			@Override
			public void shopActionPerformed(ActionEvent ev) throws ActionUnsuccessfullException {
				userRole.buyIngredient(
					new Ingredient(nameTextField.getText(),Float.parseFloat(priceTextField.getText())),
					Integer.parseInt(increaseQuantityTextField.getText())
				);
			}
		});
		contentPanel.add(btnRemoveIngredient, "flowx,cell 0 5 2 1");
		{
			JLabel lblBy = new JLabel("by");
			contentPanel.add(lblBy, "cell 0 5 2 1");
		}
		{
			increaseQuantityTextField = new JTextField();
			contentPanel.add(increaseQuantityTextField, "cell 0 5 2 1");
			increaseQuantityTextField.setColumns(5);
		}
		

	}
	
	
	public void updateIngredients(Collection<IngredientAssoc> ingredients){
		tableModel.setData(ingredients);
	}
	
}
