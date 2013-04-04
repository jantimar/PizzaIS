package pizzeria;

import java.util.ArrayList;
import java.util.List;

/**Trieda reprezentujuca pizzu */
public class Pizza {	

	/**list vsetkych pizz ktore sa predavaju */
	private static List<Pizza> pizzaList = new ArrayList<Pizza>();		
	
	/**suroviny ktore dana pizza obsahuje */
	private List<Stock> stocks;		
	/**nazov pizze */
	private String name;		
	//ID ktore sa ziska z tabulky pizz z databazy */
	private final int pizzaID;		
	
	/**privatny konstruktor ze by niekto nevytvoril pizze z 2 rovnakymi pizzaID v programe */
	private Pizza(int pizzaID,String name, List<Stock> stocks)
	{			
		this.pizzaID = pizzaID; 
		this.stocks = stocks;
		this.name = name;
		pizzaList.add(this);
	}
	
	/**bud funkcia vytvori novu pizzu a tu vrati alebo ak uz existuje pizza z rovankym pizzaID zmeni iba jej name a suroviny */
	public static Pizza newPizza(int pizzaID,String name, List<Stock> stocks)
	{
		Pizza existPizza = pizzaWithID(pizzaID);
		if(existPizza != null)
		{
			existPizza.name = name;
			existPizza.stocks = stocks;
			return existPizza;
		}
		return new Pizza(pizzaID,name,stocks);
	}
	
	/**vrati kopiu zoznamu vsetkych pizz */
	public static List<Pizza> getAllPizza()
	{
		//TODO hadzalo chybu teraz sa robi z rovnakymu referenciami teda nie z kopiou , potrebne prerobit
		//Collection.clone(copyPizza,pizzaList);
		List<Pizza> copyPizza = new ArrayList<Pizza>(pizzaList);
		return copyPizza;
	}
	
	/**odstrani pizzu zo zoznamu pizz pricom vracia true alebo false ci sa to podarilo*/
	public static boolean removPizza(Pizza pizzaToRemove)
	{
		return pizzaList.remove(pizzaToRemove);
	}
	
	/**na zaklade pouzitych surovin v pizzy vyrata jej cenu */
	public int getPrize()
	{
		int pizzaPrize = 0;
		for(Stock pizzaStock : stocks)
		{
			pizzaPrize += pizzaStock.getPrize();
		}
		return pizzaPrize;
	}
	
	/**vrati suroviny na danu pizzy */
	public List<Stock> getStocks()
	{
		return stocks;
	}
	
	/**vrati nazov danej pizze */
	public String getName()
	{
		return name;
	}
	
	/**vrati id danej pizzy ktore sa pouziva aj v tabulke databazy */
	public int getPizzaID()
	{
		return pizzaID;
	}
	
	/**vrati pizzu z ID pizze ak je v zozname mojich pizz ak nie vrati null */
	public static Pizza pizzaWithID(int pizzaID)
	{
		for(Pizza actualPizza : pizzaList)
		{
			if(actualPizza.getPizzaID() == pizzaID)
			{
				return actualPizza;
			}
		}
		return null;
	}
	
}
