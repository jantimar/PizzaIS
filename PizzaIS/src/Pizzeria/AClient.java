package pizzeria;

/**Interface reprezentujuci klienta */
public abstract class AClient {

	/**meno pouzivatela */
	private final String name;
	/**priezvisko pouzivatela */
	private final String lastName;
	/**adresa pouzivatela */
	private String adress;
	
	public AClient(String name,String lastName, String adress)
	{
		this.name = name;
		this.lastName  = lastName;
		this.adress = adress;
	}
	
	/**vrati meno klienta */
	public String getName()
	{
		return name;
	}
	
	/**vrati priezvisko pouzivatela */
	public String getLastName()
	{
		return lastName;
	}
	
	/**vrati adresu pouzivatela */
	public String getAdress()
	{
		return adress;
	}
}
