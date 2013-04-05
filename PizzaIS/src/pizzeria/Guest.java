package pizzeria;

/**Trieda reprezentujuca neregistrovaneho pouzivatela */
public class Guest implements Client{

	/**meno pouzivatela */
	private final String name;
	/**priezvisko pouzivatela */
	private final String lastName;
	/**adresa pouzivatela */
	private String adress;
	
	/**konstruktor vytvarajuci neregistrovaneho klienta */
	public Guest(String name,String lastName,String adress)
	{
		this.name = name;
		this.lastName = lastName;
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
	
	/**zaplatenie objednavky peniazmi */
	public void payOrder(Order orderForPay)
	{		
		//TODO neskor riesit aspektom a zapisanie do databazy , tiez zmenu stavu sumy v pokladni a zmenu stavu objednavky
	}
}
