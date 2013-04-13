package pizzeria.core.customers;
/**
 * Pasivna entita v systeme predstavujuca registrovaneho zakaznika
 * @author Michal Vrabel
 *
 */
public interface IRegisteredCustomer extends ICustomer {
	/**
	 * Identifikator
	 * @return
	 */
	public int getId();
	/**
	 * Meno
	 * @return
	 */
	public String getName();
	/**
	 * Adresa
	 * @return
	 */
	public String getAddress();
}
