package pizzeria.core.orders;

/**Trieda reprezentujuca objednavku z pizzerie */
public class PersonalOrder extends BasicOrder {

	private int tableNumber;

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	
	public PersonalOrder(int tableNumber) {
		this.tableNumber = tableNumber;
	}

}
