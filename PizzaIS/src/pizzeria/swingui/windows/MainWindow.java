package pizzeria.swingui.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import pizzeria.swingui.IUser;

import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private IUser userCapabilities;
	
	private JFrame frmPizzaIs;
	private JTable table;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainWindow window = new MainWindow();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public MainWindow(IUser userCapabilities) {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPizzaIs = new JFrame();
		frmPizzaIs.setTitle("Pizza IS");
		frmPizzaIs.setBounds(100, 100, 450, 300);
		frmPizzaIs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPizzaIs.getContentPane().setLayout(new MigLayout("", "[434px]", "[][grow,fill][]"));
		
		JButton btnAddOrder = new JButton("Add order");
		
		btnAddOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		
		frmPizzaIs.getContentPane().add(btnAddOrder, "flowx,cell 0 0");
		
		JButton btnNewButton = new JButton("Remove selected");
		frmPizzaIs.getContentPane().add(btnNewButton, "cell 0 0");
		
		table = new JTable();
		frmPizzaIs.getContentPane().add(table, "cell 0 1,grow");
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		
		JLabel lblSignedInAs = new JLabel("Signed in as:");
		frmPizzaIs.getContentPane().add(lblSignedInAs, "flowx,cell 0 2,alignx right");
		lblSignedInAs.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel lblWaiter = new JLabel("waiter");
		frmPizzaIs.getContentPane().add(lblWaiter, "cell 0 2,alignx right");
		lblWaiter.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JButton btnSignOut = new JButton("Sign out");
		frmPizzaIs.getContentPane().add(btnSignOut, "cell 0 2,alignx right");
		btnSignOut.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
//		JLabel label = new JLabel("");
//		frame.getContentPane().add(label);
//		
//		JLabel lblAaa = new JLabel("Signed in as ");
//		frame.getContentPane().add(lblAaa);
	}

	public  void renderOrders(){
		
	}
	
	public void open(){
		
	}
	
}
