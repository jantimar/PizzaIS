package deprecated;


/**Trieda reprezentujuca neregistrovaneho pouzivatela */
public class GuestCustomer extends AbstractCustomer{
		
	/**konstruktor vytvarajuci neregistrovaneho klienta */
	public GuestCustomer(String name,String lastName,String adress)
	{
		super(name,lastName,adress);
	}
			
	/**zaplatenie objednavky peniazmi */
	public void payOrder(OldAbstractOrder orderForPay)
	{		
		//TODO neskor riesit aspektom a zapisanie do databazy , tiez zmenu stavu sumy v pokladni a zmenu stavu objednavky
	}
}
