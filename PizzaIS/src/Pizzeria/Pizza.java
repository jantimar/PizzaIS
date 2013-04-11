package pizzeria;

import java.util.ArrayList;
import java.util.List;

/**Trieda reprezentujuca pizzu */
public class Pizza {	

	/**list vsetkych pizz ktore sa predavaju */
	private static List<Pizza> pizzaList = new ArrayList<Pizza>();		
	
	/**suroviny ktore dana pizza obsahuje */
	private List<Ingredient> ingredients;		
	/**nazov pizze */
	private String name;		
	//ID ktore sa ziska z tabulky pizz z databazy */
	private final int pizzaID;		
	
	/**privatny konstruktor ze by niekto nevytvoril pizze z 2 rovnakymi pizzaID v programe */
	private Pizza(int pizzaID,String name, List<Ingredient> ingredients)
	{			
		this.pizzaID = pizzaID; 
		this.name = name;
		this.ingredients = ingredients;
		pizzaList.add(this);
	}
	
	/**bud funkcia vytvori novu pizzu a tu vrati alebo ak uz existuje pizza z rovankym pizzaID zmeni iba jej name a suroviny */
	public static Pizza createPizza(int pizzaID,String name, List<Ingredient> ingredients)
	{
		Pizza existPizza = pizzaWithID(pizzaID);
		if(existPizza != null)
		{
			existPizza.name = name;
			existPizza.ingredients = ingredients;
			return existPizza;
		}
		return new Pizza(pizzaID,name,ingredients);
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
	
	/**vrati sumu ktora je potrebna na vyrobu pizze */
	public float getIngredientPrize()
	{
		float pizzaPrize = 0;
		for(Ingredient pizzaStock : ingredients)
		{
			pizzaPrize += pizzaStock.getPrize();
		}
		return pizzaPrize;
	}
	
	/**na zaklade pouzitych surovin v pizzy vyrata jej cenu */
	public float getPrize()
	{
		return getIngredientPrize()*1.5f; 
	}
	
	/**vrati suroviny na danu pizzy */
	public List<Ingredient> getIngredients()
	{
		return ingredients;
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
