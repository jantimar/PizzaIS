package pizzeria;

import helpClass.RestClient;
import helpClass.RestClient.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;



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
		System.out.println("Vola sa zmena stavu");
		state = newState;
		
		//TODO toto pojde neskor do Aspektu
		// poslanie zmeny stavu na server
		if(client instanceof RegistredUser)
		{
			RestClient restClient = new RestClient("http://pizzais.apphb.com/order/changestate");
			JSONObject obj = new JSONObject();
			try {
				obj.put("State", state);
				obj.put("OrderID",orderID);
				obj.put("ClientID",((RegistredUser)client).getClientID());
				restClient.SetPostParam(obj.toString());
				restClient.Execute(RequestMethod.POST);
				String response = restClient.getResponse();
				System.out.println("response " + response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
}
