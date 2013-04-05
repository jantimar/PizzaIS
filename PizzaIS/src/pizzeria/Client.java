package pizzeria;

/**Interface reprezentujuci klienta */
public interface Client {

	/**vrati meno klienta */
	public String getName();
	
	/**vrati priezvisko pouzivatela */
	public String getLastName();
	
	/**vrati adresu pouzivatela */
	public String getAdress();
}
