package pizzeria;

/**Trieda reprezentujuca neregistrovaneho pouzivatela */
public class Guest extends AClient{
		
	/**konstruktor vytvarajuci neregistrovaneho klienta */
	public Guest(String name,String lastName,String adress)
	{
		super(name,lastName,adress);
	}
			
	/**zaplatenie objednavky peniazmi */
	public void payOrder(AOrder orderForPay)
	{		
		//TODO neskor riesit aspektom a zapisanie do databazy , tiez zmenu stavu sumy v pokladni a zmenu stavu objednavky
	}
}
