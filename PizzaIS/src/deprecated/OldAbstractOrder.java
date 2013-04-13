package deprecated;


import java.util.ArrayList;
import java.util.List;

import pizzeria.core.orders.OrderState;




/**Trieda reprezentujuca objednavku */
public abstract class OldAbstractOrder {
	
	/**aktualny stav objednavky */
	private OrderState state;
	/**id objednavky */
	private int orderID;
	/**klient ktory si objednal pizzu */
	private AbstractCustomer client;
	/**pizze ktore su na objednavke */
	private List<Pizza> pizzas;
	
	/**konstruktor vytvarajuci objednavku */
	public OldAbstractOrder(AbstractCustomer client,List<Pizza> pizzas)
	{
		//TODO nastavit aspektom
		this.orderID = orderID;
		this.client = client;
		this.pizzas = pizzas;
	}
	
	/**vrati kopiu listu jedal objednavky */
	public List<Pizza> getPizzas()
	{
		List<Pizza> copyPizzas = new ArrayList<Pizza>(pizzas);
		//TODO pravdepodobne tiez hadze chybu ako v triede Pizza Collections.copy(copyPizzas,pizzas);
		return copyPizzas;
	}
	
	/**vrati sumu ktoru ma zakaznik zaplatit za objednavku */
	public float getOrderBill()
	{
		float bill = 0;
		for(Pizza actualPizza : pizzas)
		{
			bill += actualPizza.getPrize();
		}
		return bill;
	}
	
	/**vrati sumu ktora je potrebna na vytvorenie objednavky*/
	public float getOrderOutcomme()
	{
		float prize = 0;
		for(Pizza actualPizza : pizzas)
		{
			prize += actualPizza.getIngredientPrize();
		}
		return prize;
	}
	
	/**vrati ID objednavky ktore sa pouziva v databaze pri objednavke */ 
	public int getOrderID()
	{
		return orderID;
	}
	
	/**vrati klienta ktory si objednal jedlo */
	public AbstractCustomer getClient()
	{
		return client;
	}
	
	/**vrati aktualny stav objednavky */
	public OrderState getState()
	{
		return state;
	}
	
	/**nastavi objednavke stav na stav newState */
	public void setState(OrderState newState)
	{
		System.out.println("Vola sa zmena stavu");
		state = newState;			
	}
}
