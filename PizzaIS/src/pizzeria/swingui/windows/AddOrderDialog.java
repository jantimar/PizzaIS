package pizzeria.swingui.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;

public class AddOrderDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;

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

	/**
	 * Create the dialog.
	 */
	public AddOrderDialog() {
		setTitle("Add order");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow][][]", "[20px][][grow]"));
		{
			JLabel lblAddress = new JLabel("address  ");
			contentPanel.add(lblAddress, "cell 0 0,alignx left,aligny center");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "cell 1 0 3 1,grow");
			textField.setColumns(10);
		}
		{
			JButton btnAddMeal = new JButton("Add meal");
			contentPanel.add(btnAddMeal, "cell 2 1");
		}
		{
			JButton btnRemoveMeal = new JButton("Remove meal");
			contentPanel.add(btnRemoveMeal, "cell 3 1");
		}
		{
			table = new JTable();
			contentPanel.add(table, "cell 0 2 4 1,grow");
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

}
