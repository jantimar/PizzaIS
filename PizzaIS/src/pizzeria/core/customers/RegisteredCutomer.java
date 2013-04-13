package pizzeria.core.customers;
/**
 * Pasivna entita systemu predstavujuca registrovaneho zakaznika
 * @author Michal Vrabel
 *
 */
public class RegisteredCutomer implements IRegisteredCustomer {
	private int id = -1;
	private String name;
	private String address;
	private String description;
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
}
