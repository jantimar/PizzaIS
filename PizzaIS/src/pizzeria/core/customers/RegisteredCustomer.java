package pizzeria.core.customers;
/**
 * Pasivna entita systemu predstavujuca registrovaneho zakaznika
 * @author Michal Vrabel
 *
 */
public class RegisteredCustomer implements IRegisteredCustomer {
	private int id = -1;
	private String name;
	private String address;
	private String description;
	
	public RegisteredCutomer(String description,int id, String name,String address)
	{
		this.description = description;
		this.id = id;
		this.name = name;
		this.address = address;
	}
	
	/**
	 * Jenoduchy popis
	 */
	@Override
	public String getDescription() {
		return description;
	}
	/**
	 * Identifikator
	 */
	@Override
	public int getId() {
		return id;
	}
	/**
	 * Meno
	 */
	@Override
	public String getName() {
		return name;
	}
	/**
	 * Adresa
	 */
	@Override
	public String getAddress() {
		return address;
	}
	/**
	 * Porovnavanie ak sa porovnava s IRegisteredCustomer pozera sa na id, name, address
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof IRegisteredCustomer){
			IRegisteredCustomer cust = (IRegisteredCustomer) obj;
			return (this.id == cust.getId()) && (this.name == cust.getName()) && (this.address == cust.getAddress());
		}
		return super.equals(obj);
	}
}
