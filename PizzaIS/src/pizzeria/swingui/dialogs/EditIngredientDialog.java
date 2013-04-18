package pizzeria.swingui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import pizzeria.core.stock.IngredientAssoc;

import net.miginfocom.swing.MigLayout;

public class EditIngredientDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JTextField priceTextField;
	private JTextField quantityTextField;
	private JTextField nameTextField;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			EditIngredientDialog dialog = new EditIngredientDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public EditIngredientDialog() {


		setTitle("Register new ingredient");
		
		int windowWidth = 320;
		int windowHeight = 160;
		int[] center = pizzeria.swingui.dialogs.Utilities.getCenterPosition(windowWidth, windowHeight);
		setBounds(center[0], center[1], windowWidth, windowHeight);
		

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
			JLabel lblPrice = new JLabel("Price");
			contentPanel.add(lblPrice, "cell 0 1,alignx left,aligny center");
		}
		{
			priceTextField = new JTextField();
			contentPanel.add(priceTextField, "cell 1 1,grow");
			priceTextField.setColumns(10);
		}
		
		// Quantity
		
		{
			JLabel lblQuantity = new JLabel("Quantity");
			contentPanel.add(lblQuantity, "cell 0 2,alignx left,aligny center");
		}
		{
			quantityTextField = new JTextField();
			contentPanel.add(quantityTextField, "cell 1 2,grow");
			quantityTextField.setColumns(10);
		}
		

		getContentPane().add(contentPanel, BorderLayout.CENTER);
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
		
//		private Ingredient
		
	}
	

}
