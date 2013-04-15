package pizzeria.core.userroles;
/**
 * Aktiva entita v obchode poskytujuca akcie s objednavkami v PizzaShop
 * @author uzivatel
 *
 */
public interface IUserRole {

	/**vrati meno zamestnanca */
	public String getName();
	
	/**vrati popis roly */
	public String getDescription();
		
}
