package pizzeria.swingui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Collection;

import pizzeria.core.userroles.IUserRole;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private Collection<IUserRole> users;
	
	private final JPanel contentPanel = new JPanel();

	private JComboBox<IUserRole> comboBox;
	
	private IUserRole selectedUser;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			LoginDialog dialog = new LoginDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public LoginDialog(Collection<IUserRole> users) {
		this.users = users;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		initialize();
	}
	
	/**
	 * Create the dialog.
	 */
	public LoginDialog(JFrame owner, Collection<IUserRole> users) {
		super(owner);
		this.users = users;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		initialize();
	}
	
	/**
	 * Create the dialog.
	 */
	public LoginDialog(JDialog owner, Collection<IUserRole> users) {
		super(owner);
		this.users = users;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		initialize();
	}
	private void initialize(){
		
		setTitle("Sign in");
		
		setResizable(false);
		
		
		
	    int windowWidth = 300;
	    int windowHeight = 130;
	    
	    int[] center = Utilities.getCenterPosition(windowWidth, windowHeight);
	    // set position and size
	    setBounds(center[0], center[1], windowWidth, windowHeight);
	    		
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		getContentPane().setLayout(new BorderLayout());
		
		FlowLayout fl_contentPanel = new FlowLayout();
		fl_contentPanel.setVgap(15);
		
		contentPanel.setLayout(fl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		{
			JLabel lblSignInAs = new JLabel("Sign in as");
			lblSignInAs.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblSignInAs);
		}
		
		{
			comboBox = new JComboBox<IUserRole>();
			comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			for (IUserRole user : users) {
				comboBox.addItem(user);
			}
			
			contentPanel.add(comboBox);
			
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						selectedUser = (IUserRole)comboBox.getSelectedItem();
						LoginDialog.this.dispose();
					}
				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						LoginDialog.this.dispose();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public IUserRole getSelectedUser(){
		return selectedUser;
	}
	
	public static IUserRole showDialog(Collection<IUserRole> users){
		LoginDialog dialog = new LoginDialog(users);
		dialog.setVisible(true);
		
		return dialog.getSelectedUser();
	}
	
	public static IUserRole showDialog(JFrame owner, Collection<IUserRole> users){
		LoginDialog dialog = new LoginDialog(owner, users);
		dialog.setVisible(true);
		
		return dialog.getSelectedUser();
	}
	
	public static IUserRole showDialog(JDialog owner, Collection<IUserRole> users){
		LoginDialog dialog = new LoginDialog(owner, users);
		dialog.setVisible(true);
		
		return dialog.getSelectedUser();
	}
}
