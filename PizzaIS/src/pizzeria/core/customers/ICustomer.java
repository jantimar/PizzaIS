package pizzeria.core.customers;
/**
 * Pasivna entita v systeme predstavujuca zakaznika
 * @author Michal Vrabel
 *
 */
public interface ICustomer {
	/**
	 * Ziskanie popisu zakaznika. Napr.: Cerveny pan so zelenymi bodkami.
	 * @return
	 */
	public String getDescription();
}
