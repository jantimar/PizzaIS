package pizzeria.swingui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EditCustomerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditCustomerDialog dialog = new EditCustomerDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditCustomerDialog() {
		setTitle("Register new customer");
		
		int windowWidth = 320;
		int windowHeight = 160;
		int[] center = pizzeria.swingui.dialogs.Utilities.getCenterPosition(windowWidth, windowHeight);
		setBounds(center[0], center[1], windowWidth, windowHeight);
	
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[20px][][][grow]"));
		{
			JLabel lblName = new JLabel("Name");
			lblName.setHorizontalAlignment(SwingConstants.LEFT);
			contentPanel.add(lblName, "cell 0 0,alignx trailing");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "cell 1 0,growx");
			textField.setColumns(10);
		}
		{
			JLabel lblDescription = new JLabel("Description");
			lblDescription.setHorizontalAlignment(SwingConstants.LEFT);
			contentPanel.add(lblDescription, "cell 0 1,alignx trailing");
		}
		{
			textField_1 = new JTextField();
			contentPanel.add(textField_1, "cell 1 1,growx");
			textField_1.setColumns(10);
		}
		{
			JLabel lblAddress = new JLabel("Address");
			contentPanel.add(lblAddress, "cell 0 2,alignx trailing");
		}
		{
			textField_2 = new JTextField();
			contentPanel.add(textField_2, "cell 1 2,growx");
			textField_2.setColumns(10);
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
