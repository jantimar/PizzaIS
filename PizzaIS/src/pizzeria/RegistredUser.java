package pizzeria;

/**Trieda reprezentujuca registrovaneho pouzivatela */
public class RegistredUser implements Client {

	/**id registrovaneho klienta */
	private final int clientID;
	/**meno pouzivatela */
	private final String name;
	/**priezvisko pouzivatela */
	private final String lastName;
	/**adresa pouzivatela */
	private String adress;
	/**zlava pouzivatela */
	private float discount;
	
	/**konstruktor vytvarajuci registrovaneho klienta */
	public RegistredUser(int clientID,String name,String lastName,String adress,float discount)
	{
		this.clientID = clientID;
		this.name = name;
		this.lastName = lastName;
		this.adress = adress;
		this.discount = discount;
	}
	
	/**vrati id registrovaneho klienta */
	public int getClientID()
	{
		return clientID;
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
	public void payOrderByCash(Order orderForPay)
	{		
		//TODO neskor riesit aspektom zvysenie discount , a zapisanie do databazy , tiez zmenu stavu sumy v pokladni a zmenu stavu objednavky
		discount += ((float)orderForPay.getOrderBill()%10.0f);
	}
	
	/**zaplatenie objednavky kartou */
	public void payOrderByCard(Order orderForPay)
	{
		//TODO neskor riesit aspektom zvysenie discount , a zapisanie do databazy , tiez zmenu stavu sumy v pokladni a zmenu stavu objednavky
		discount += ((float)orderForPay.getOrderBill()%20.0f);
	}
}
