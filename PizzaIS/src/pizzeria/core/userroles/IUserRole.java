package pizzeria.core.userroles;
/**
 * Aktiva entita v obchode poskytujuca nejake akcie pre pracu s obchodom
 * @author uzivatel
 *
 */
public interface IUserRole {

	/**vrati meno zamestnanca */
	public String getName();
	
	/**vrati popis roly */
	public String getDescription();
		
}
