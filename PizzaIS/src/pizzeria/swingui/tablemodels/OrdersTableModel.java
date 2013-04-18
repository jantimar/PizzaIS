package pizzeria.swingui.tablemodels;

import pizzeria.core.customers.ICustomer;
import pizzeria.core.customers.IRegisteredCustomer;
import pizzeria.core.orders.DeliveryOrder;
import pizzeria.core.orders.IOrder;
import pizzeria.core.orders.PersonalOrder;

import javax.swing.table.AbstractTableModel;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;

public class OrdersTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	public static enum ColumnType { 
		SELECTED, ID, CUSTOMER, TYPE, DESTINATION, BILL, COST, STATE   
	}
	
	public static final int NUMBER_OF_COLUMN_TYPES = 9;

//	private LinkedHashMap<ColumnType,String> columns;
	
	private ColumnType[] columnTypes;
	private String[] columnNames;
	
	private List<IOrder> orders;

	public OrdersTableModel(){
		this.orders = new ArrayList<IOrder>();
		loadColumns();
	}
	
	public OrdersTableModel(Collection<IOrder> orders) {
		this.orders = new ArrayList<IOrder>(orders);
		loadColumns();
	}
	
	protected void loadColumns(){
		LinkedHashMap<ColumnType,String> columns = new LinkedHashMap<>(NUMBER_OF_COLUMN_TYPES);
		columns.put(ColumnType.SELECTED, "");
		columns.put(ColumnType.ID, "#Id");
		columns.put(ColumnType.TYPE, "Type");
		columns.put(ColumnType.CUSTOMER, "Customer");
		columns.put(ColumnType.DESTINATION, "Destination");
		columns.put(ColumnType.BILL, "Bill");
		columns.put(ColumnType.COST, "Cost");
		columns.put(ColumnType.STATE, "State");
		
		columnNames = columns.values().toArray(new String[0]); //(String[]) 
		columnTypes = columns.keySet().toArray(new ColumnType[0]);
	}

	
	@Override
	public int getRowCount() {
		return orders.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return getOrderAttribute(orders.get(rowIndex), columnIndex);
	}

	public String getColumnName(int col)
	{
		return columnNames[col];
	}
	
	public Class<?> getColumnClass(int c){
		return getValueAt(0, c).getClass();
	}
	
	private Object getOrderAttribute(IOrder order, int columnIndex){
		switch(columnTypes[columnIndex]){
			case ID:
				return order.getId();
			case CUSTOMER:
				ICustomer customer = order.getCustomer();
				if(customer instanceof IRegisteredCustomer){
					IRegisteredCustomer registred = (IRegisteredCustomer)customer;
					return String.format("Registred: %s", registred.getName());
				}
				else{
					return String.format("Other: %s", customer.getDescription());
				}
			case SELECTED:
				return false;
			case DESTINATION:
				if(order instanceof PersonalOrder){
					return String.format("Table number: %d",((PersonalOrder)order).getTableNumber());
				}
				else if(order instanceof DeliveryOrder){
					return ((DeliveryOrder)order).getDestination();
				}
				return "Unknown";
			case COST:
				return order.getOrderCost();
			case BILL:
				return order.getOrderBill();
			case TYPE:
				if(order instanceof PersonalOrder) return "Personal";
				else if(order instanceof DeliveryOrder) return "Delivery";
				else return "Other";
			case STATE:
				return order.getState().toString();
		}
		return null;
	}
	
	public IOrder getOrderAt(int rowIndex){
		return orders.get(rowIndex);
	}
	
	public void setData(Collection<IOrder> orders) {
		this.orders = new ArrayList<IOrder>(orders);
		fireTableDataChanged();
	}
	public List<IOrder> getData(){
		return java.util.Collections.unmodifiableList(this.orders);
	}
	
	public void addIOrder(IOrder order){
		orders.add(order);
		int len = orders.size()-1;
		fireTableRowsInserted(len, len);
	}
	
	public void removeOrder(IOrder order){
		int index = orders.indexOf(order);
		orders.remove(index);
		fireTableRowsDeleted(index, index);
	}
	
	public void removeOrderAt(int index){;
		orders.remove(index);
		fireTableRowsDeleted(index, index);
	}
	
	public void removeOrdersAt(int[] indexes){
		Arrays.sort(indexes);
		for(int index : indexes){
			orders.remove(index);
		}
		fireTableRowsDeleted(indexes[0], indexes[indexes.length-1]);
	}
	
}