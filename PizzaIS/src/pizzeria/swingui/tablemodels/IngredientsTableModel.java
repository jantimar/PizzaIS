package pizzeria.swingui.tablemodels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import pizzeria.core.stock.IngredientAssoc;

public class IngredientsTableModel extends AbstractTableModel  {
private static final long serialVersionUID = 1L;
	
	public static enum ColumnType{ 
		SELECTED, ID, NAME, PRICE, SUM_PRICE, QUANTITY
	}
	
	public static final int NUMBER_OF_COLUMN_TYPES = 9;

//	private LinkedHashMap<ColumnType,String> columns;
	
	private ColumnType[] columnTypes;
	private String[] columnNames;
	
	private List<IngredientAssoc> ingredients;

	public IngredientsTableModel(){
		this.ingredients = new ArrayList<IngredientAssoc>();
		loadColumns();
	}
	
	public IngredientsTableModel(Collection<IngredientAssoc> ingredients) {
		this.ingredients = new ArrayList<IngredientAssoc>(ingredients);
		loadColumns();
	}
	
	protected void loadColumns(){
		LinkedHashMap<ColumnType,String> columns = new LinkedHashMap<>(NUMBER_OF_COLUMN_TYPES);
		columns.put(ColumnType.SELECTED, "");
		columns.put(ColumnType.ID, "#Id");
		columns.put(ColumnType.NAME, "Name");
		columns.put(ColumnType.PRICE, "Price per one");
		columns.put(ColumnType.QUANTITY, "Quantity");
		columns.put(ColumnType.SUM_PRICE, "Price sum");
		
		columnNames = columns.values().toArray(new String[0]); 
		columnTypes = columns.keySet().toArray(new ColumnType[0]);
	}

	
	@Override
	public int getRowCount() {
		return ingredients.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return getOrderAttribute(ingredients.get(rowIndex), columnIndex);
	}

	public String getColumnName(int col)
	{
		return columnNames[col];
	}
	
	public Class<?> getColumnClass(int c){
		return getValueAt(0, c).getClass();
	}
	
	private Object getOrderAttribute(IngredientAssoc ingredient, int columnIndex){
		switch(columnTypes[columnIndex]){
			case ID:
				return ingredient.getId();
			case SELECTED:
				return false;
			case SUM_PRICE:
				return ingredient.getIngredient().getPrice()*ingredient.getQuantity();
			case PRICE:
				return ingredient.getIngredient().getPrice();
			case NAME:
				return ingredient.getIngredient().getName();
			case QUANTITY:
				return ingredient.getQuantity();
		}
		return null;
	}
	
	public IngredientAssoc getIngredientAt(int rowIndex){
		return ingredients.get(rowIndex);
	}
	
	public void setData(Collection<IngredientAssoc> orders) {
		orders.clear();
		orders = new ArrayList<IngredientAssoc>(orders);
		fireTableDataChanged();
	}
	
	public void addIngredient(IngredientAssoc ingredient){
		ingredients.add(ingredient);
		int len = ingredients.size()-1;
		fireTableRowsInserted(len, len);
	}
	
	public void removeIngredient(IngredientAssoc ingredient){
		int index = ingredients.indexOf(ingredient);
		ingredients.remove(index);
		fireTableRowsDeleted(index, index);
	}
	
	public void removeIngredientAt(int index){;
		ingredients.remove(index);
		fireTableRowsDeleted(index, index);
	}
	
	public void removeIngredientsAt(int[] indexes){
		Arrays.sort(indexes);
		for(int index : indexes){
			ingredients.remove(index);
		}
		fireTableRowsDeleted(indexes[0], indexes[indexes.length-1]);
	}
	
	public List<IngredientAssoc> getData(){
		return java.util.Collections.unmodifiableList(ingredients);
	}
}
