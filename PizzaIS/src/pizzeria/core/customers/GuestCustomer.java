package pizzeria.core.customers;
/**
 * Pasivna entita systemu predsavujuca anonymneho zakaznika 
 * @author Michal Vrabel
 *
 */
public class GuestCustomer implements ICustomer {
	private String description;

	public String getDescription() {
		return description;
	}
	
	public GuestCustomer(String description){
		this.description = description;
	}
	
	@Override
	public String toString() {
		return description;
	}
}
