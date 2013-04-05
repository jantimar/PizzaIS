package pizzeria;

import java.util.Arrays;
import java.util.List;

import pizzeria.Order.State;

/**Hlavna trieda reprezentujúca obchod */
public class PizzaShop {

	/**vydaje obchodu */
	private float outcomme;
	/**prijem obchodu */
	private float incomme;
	/**jedina instancia triedy pizzaShop */
	private static PizzaShop pizzaShop;
	
	public static void main(String args[])
	{
		new PizzaShop(0.0f,0.0f);
	}
	
	private PizzaShop(float outcomme,float incomme)
	{
		this.incomme = incomme;
		this.outcomme = outcomme;
	}
	
	public static PizzaShop getInstance()
	{
		return pizzaShop;
	}
	
	public void addIncomme(float addIncomme)
	{
		incomme += addIncomme;
	}
}
