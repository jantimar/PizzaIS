package pizzeria.swingui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;

import pizzeria.core.meals.Meal;
import pizzeria.core.stock.Ingredient;
import pizzeria.core.stock.IngredientAssoc;
import pizzeria.swingui.tablemodels.IngredientsTableModel;
import javax.swing.border.EtchedBorder;

public class EditMealDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTable table;
	
	private JTextField nameTextField;
	
	private JTextField priceTextField;
	private JTextField ingQuantityTextField;
	
	private IngredientsTableModel tableModel;
	
	private JComboBox<Ingredient> ingComboBox;
	
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			MealEditorDialog dialog = new MealEditorDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}


	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public EditMealDialog(JFrame owner, Collection<IngredientAssoc> ingredients) {
		super(owner);
		initialize(ingredients);
	}
	
	/**
	 * Create the dialog.
	 */
	public EditMealDialog(JDialog owner, Collection<IngredientAssoc> ingredients) {
		super(owner);
		initialize(ingredients);
	}
	
	
	private void initialize(Collection<IngredientAssoc> ingredients) {
		setTitle("Register new meal");
		
		int windowWidth = 460;
		int windowHeight = 300;
		int[] center = pizzeria.swingui.dialogs.Utilities.getCenterPosition(windowWidth, windowHeight);
		setBounds(center[0], center[1], windowWidth, windowHeight);
		
		JPanel contentPanel = new JPanel();

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[20px][][][][grow]"));
		
		// Name
		
		{
			JLabel lblAddress = new JLabel("Name");
			contentPanel.add(lblAddress, "cell 0 0,alignx left,aligny center");
		}
		{
			nameTextField = new JTextField();
			contentPanel.add(nameTextField, "cell 1 0,grow");
			nameTextField.setColumns(10);
		}

		// Price
		
		{
			JLabel lblAddress = new JLabel("Price");
			contentPanel.add(lblAddress, "cell 0 1,alignx left,aligny center");
		}
		{
			priceTextField = new JTextField();
			contentPanel.add(priceTextField, "cell 1 1,grow");
			priceTextField.setColumns(10);
		}
		
		// Ingredients

		{
			JLabel lblAddIngredient = new JLabel("Insert ingredient");
			contentPanel.add(lblAddIngredient, "cell 0 2");
		}
		{
			ingComboBox = new JComboBox<Ingredient>();
			contentPanel.add(ingComboBox, "flowx,cell 1 2,growx");
		}
		// Quantity
		
		{
			JLabel lblQuantity = new JLabel("quantity");
			contentPanel.add(lblQuantity, "cell 1 2");
		}
		{
			ingQuantityTextField = new JTextField();
			contentPanel.add(ingQuantityTextField, "cell 1 2");
			ingQuantityTextField.setColumns(5);
		}
		{
			JButton btnAddIngredient = new JButton("Insert ingredient");
			btnAddIngredient.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					tableModel.addIngredient(
						new IngredientAssoc(
							(Ingredient)ingComboBox.getSelectedItem(),
							Integer.parseInt( ingQuantityTextField.getText() )
						));
				}
			});
			contentPanel.add(btnAddIngredient, "flowx,cell 1 3");
		}
		{
			JButton btnRemoveIngredient = new JButton("Remove selected");
			btnRemoveIngredient.setHorizontalAlignment(SwingConstants.RIGHT);
			btnRemoveIngredient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tableModel.removeIngredientsAt(table.getSelectedRows());
				}
			});
			contentPanel.add(btnRemoveIngredient, "cell 1 3");
		}
		
		
		{
			tableModel = new IngredientsTableModel();
			table = new JTable(tableModel);
			table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			contentPanel.add(table, "cell 0 4 2 1,grow");
		}
		
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
	

	public void updateAvailableIngredients(Collection<Ingredient> ingredeints){
		ingComboBox.removeAllItems();
		for(Ingredient ingrediet : ingredeints){
			ingComboBox.addItem(ingrediet);
		}
	}
	
	public void updateIngredients(Collection<IngredientAssoc> ingredients){
		tableModel.setData(ingredients);
	}
	
	public Meal getMeal(){
		Meal meal = new Meal(
			nameTextField.getText(),
			Float.parseFloat(priceTextField.getText()),
			tableModel.getData()
		);
		return meal;
	}
}
