package pizzeria;

import java.util.ArrayList;
import java.util.List;

/**Trieda reprezentujuca objednavku */
public class Order {

	/**enumeracny typ stavov ktore moze objednavka dosiahnut */
	public enum State { New, InProgress, Completed, Finished, Repayment}
	
	/**aktualny stav objednavky */
	private State state;
	/**id objednavky */
	private int orderID;
	/**klient ktory si objednal pizzu */
	private Client client;
	/**pizze ktore su na objednavke */
	private List<Pizza> pizzas;
	
	/**konstruktor vytvarajuci objednavku */
	public Order(int orderID,Client client,List<Pizza> pizzas)
	{
		this.state = State.New;
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
	public int getOrderBill()
	{
		int bill = 0;
		for(Pizza actualPizza : pizzas)
		{
			bill += actualPizza.getPrize();
		}
		return bill;
	}
	
	/**vrati ID objednavky ktore sa pouziva v databaze pri objednavke */ 
	public int getOrderID()
	{
		return orderID;
	}
	
	/**vrati klienta ktory si objednal jedlo */
	public Client getClient()
	{
		return client;
	}
	
	/**vrati aktualny stav objednavky */
	public State getState()
	{
		return state;
	}
	
	/**nastavi objednavke stav na stav newState */
	public void setState(State newState)
	{
		state = newState;
	}
}
