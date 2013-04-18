package pizzeria.swingui.tablemodels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import pizzeria.core.meals.Meal;

public class MealsTableModel extends AbstractTableModel {
private static final long serialVersionUID = 1L;
	
	public static enum ColumnType { 
		SELECTED, ID, NAME, PRICE, COST  
	}
	
	public static final int NUMBER_OF_COLUMN_TYPES = 9;

//	private LinkedHashMap<ColumnType,String> columns;
	
	private ColumnType[] columnTypes;
	private String[] columnNames;
	
	private List<Meal> meals;

	public MealsTableModel(){
		this.meals = new ArrayList<Meal>();
		loadColumns();
	}
	
	public MealsTableModel(Collection<Meal> meals) {
		this.meals = new ArrayList<Meal>(meals);
		loadColumns();
	}
	
	protected void loadColumns(){
		LinkedHashMap<ColumnType,String> columns = new LinkedHashMap<>(NUMBER_OF_COLUMN_TYPES);
		columns.put(ColumnType.SELECTED, "");
		columns.put(ColumnType.ID, "#Id");
		columns.put(ColumnType.PRICE, "Price");
		columns.put(ColumnType.COST, "Cost");
		
		columnNames = columns.values().toArray(new String[0]); 
		columnTypes = columns.keySet().toArray(new ColumnType[0]);
	}

	
	@Override
	public int getRowCount() {
		return meals.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return getOrderAttribute(meals.get(rowIndex), columnIndex);
	}

	public String getColumnName(int col)
	{
		return columnNames[col];
	}
	
	public Class<?> getColumnClass(int c){
		return getValueAt(0, c).getClass();
	}
	
	private Object getOrderAttribute(Meal meal, int columnIndex){
		switch(columnTypes[columnIndex]){
			case ID:
				return meal.getId();
			case SELECTED:
				return false;
			case COST:
				return meal.getCost();
			case PRICE:
				return meal.getPrice();
			case NAME:
				return meal.getName();
		}
		return null;
	}
	
	public Meal getMealAt(int rowIndex){
		return meals.get(rowIndex);
	}
	
	public void setData(Collection<Meal> meals) {
		this.meals = new ArrayList<Meal>(meals);
		fireTableDataChanged();
	}
	
	public void addMeal(Meal meal){
		meals.add(meal);
		int len = meals.size()-1;
		fireTableRowsInserted(len, len);
	}
	
	public void removeMeal(Meal meal){
		int index = meals.indexOf(meal);
		meals.remove(index);
		fireTableRowsDeleted(index, index);
	}
	
	public void removeMealAt(int index){;
		meals.remove(index);
		fireTableRowsDeleted(index, index);
	}
	
	public void removeMealsAt(int[] indexes){
		Arrays.sort(indexes);
		for(int index : indexes){
			meals.remove(index);
		}
		fireTableRowsDeleted(indexes[0], indexes[indexes.length-1]);
	}
	
	public List<Meal> getData(){
		return Collections.unmodifiableList(meals);
	}
}
