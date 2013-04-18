package pizzeria.swingui.dialogs;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import pizzeria.core.orders.IOrder;
import pizzeria.core.userroles.ICookUserRole;
import pizzeria.core.userroles.IUserRole;
import pizzeria.core.userroles.IWaiterUserRole;
import pizzeria.core.utils.ActionUnsuccessfullException;
import pizzeria.swingui.SwingUserInterface;
//import pizzeria.swingui.extrauserroles.IFinanceManager;
import pizzeria.swingui.tablemodels.OrdersTableModel;
import pizzeria.swingui.util.ConfirmedShopActionListener;
import pizzeria.swingui.util.ShopActionListener;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.Collection;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.border.EtchedBorder;


public class OrdersWindow {

	private IUserRole currentUser;
	
	private JDialog dialog;
	private JTable table;
	private OrdersTableModel ordersTableModel;
	
	private SwingUserInterface ui;

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
	public OrdersWindow( SwingUserInterface ui, IUserRole currentUser ) {
		this.ui = ui;
		this.currentUser = currentUser;
		
		this.ordersTableModel = new OrdersTableModel();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dialog = new JDialog();
		
		dialog.setModal(true);
		dialog.setTitle("Pizza IS");
		
		int winWidth = 450;int winHeight = 300;
		int[] center = Utilities.getCenterPosition(winWidth, winHeight);
		
		java.awt.Container container = dialog.getContentPane();
		
		dialog.setBounds(center[0], center[1], winWidth, winHeight);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		container.setLayout(new MigLayout("", "[434px][]", "[][grow,fill][]"));

		createMenubar(container);
		
		createButtons(container);
		
		createTable(container);
		
		createStatusbar(container);
		
		dialog.addWindowListener(new java.awt.event.WindowAdapter() {
	        public void windowClosing(WindowEvent winEvt) {
	            ui.setExitRequired(true);
	        }
	    });
		
	}

	private void createButtons(java.awt.Container container){
		
		if(currentUser instanceof IWaiterUserRole){
			
			JButton addOrderBtn = new JButton("Add order");
			addOrderBtn.addActionListener(new ShopActionListener(this.dialog) {
				@Override
				public void shopActionPerformed(ActionEvent ev) throws ActionUnsuccessfullException {
					IOrder order = ui.requireNewOrder(OrdersWindow.this.dialog);
					((IWaiterUserRole)currentUser).acceptOrder( order );
					ordersTableModel.addIOrder(order);
				}
			});
			
			container.add(addOrderBtn, "cell 0 0");
			
			JButton removeOrderBtn = new JButton("Remove selected");
			
			removeOrderBtn.addActionListener(new ConfirmedShopActionListener(this.dialog) {
				@Override
				public void shopActionPerformed(ActionEvent ev) throws ActionUnsuccessfullException {					
					for(int rowIndex : table.getSelectedRows()){
						((IWaiterUserRole)currentUser).removeOrder( ordersTableModel.getOrderAt(rowIndex) );
					}
					ordersTableModel.removeOrdersAt(table.getSelectedRows());
				}

				@Override
				public String getConfirmMessage() {
					return String.format("Do you really wanna remove %d orders?", table.getSelectedRows().length);
				}
			});
			
			container.add(removeOrderBtn, "cell 0 0");
		}
		
		if(currentUser instanceof ICookUserRole){

			JButton cookOrderBtn = new JButton("Cook selected");
			
			cookOrderBtn.addActionListener(new ConfirmedShopActionListener(this.dialog) {
				@Override
				public void shopActionPerformed(ActionEvent ev) throws ActionUnsuccessfullException {
					for(int rowIndex : table.getSelectedRows()){
						((ICookUserRole)currentUser).cookOrderMeals( ordersTableModel.getOrderAt(rowIndex) );
						ordersTableModel.fireTableRowsUpdated(rowIndex, rowIndex);
					}
				}
				
				@Override
				public String getConfirmMessage() {
					return String.format("Do you really wanna cook %d orders?", table.getSelectedRows().length);
				}
			});
			
			container.add(cookOrderBtn, "cell 0 0");
			
		}
		
	}
	
	private void createTable(java.awt.Container container){
		
		table = new JTable();
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setFillsViewportHeight(true);
		container.add(table, "cell 0 1 2 1,grow");
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setModel(ordersTableModel);
		
	}
	
	private void createStatusbar(java.awt.Container container){

		JLabel lblSignedInAs = new JLabel("Signed in as:");
		container.add(lblSignedInAs, "flowx,cell 0 2 2 1,alignx right");
		lblSignedInAs.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel lblWaiter = new JLabel(currentUser.getName());
		container.add(lblWaiter, "cell 0 2 2 1,alignx right");
		lblWaiter.setHorizontalAlignment(SwingConstants.TRAILING);
	}
	
	private void createMenubar(java.awt.Container container){
		JMenuBar menuBar = new JMenuBar();
		dialog.setJMenuBar(menuBar);
		
		JMenu mnUser = new JMenu("User");
		menuBar.add(mnUser);
		
		JMenuItem mntmSignOut = new JMenuItem("Sign out");
		mntmSignOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});
		mnUser.add(mntmSignOut);
		
		JSeparator separator = new JSeparator();
		mnUser.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ui.setExitRequired(true);
				dialog.setVisible(false);
			}
		});
		mnUser.add(mntmExit);
		
		JMenu mnManage = new JMenu("Manage");
		menuBar.add(mnManage);
		
		JMenuItem mntmStock = new JMenuItem("Stock");
		mnManage.add(mntmStock);
		
		JMenuItem mntmFinance = new JMenuItem("Finance");
		mnManage.add(mntmFinance);
		
		JMenuItem mntmRoyaltyProgram = new JMenuItem("Royalty program");
		mnManage.add(mntmRoyaltyProgram);
		
	}
	
	
	public void open(){
		this.dialog.setVisible(true);
	}
	
	
	public void updateOrders(Collection<IOrder> orders){
		ordersTableModel.setData(orders);
	}
	
	

}
