package deprecated;

import java.util.ArrayList;
import java.util.List;

/**trieda reprezentujuca surovinu */
public class Ingredient {
	
	/**zoznam vsetkych doposial inicializovanych surovin */
	private static List<Ingredient> ingredientList = new ArrayList<Ingredient>();
	
	/**ID suroviny ktore je v globalnej databaze programu */
	private final int ingredientID;
	/**nazov suroviny */
	private String name;
	/**mnozstvo danej suroviny */
	private int count;
	/**cena suroviny */
	private int prize;
	

	
	/**privatny konstruktor vytvoarajuci surovinu*/
	private Ingredient(int stockID,String name,int count,int prize)
	{
		this.ingredientID = stockID;
		this.name = name;
		this.count = count;
		this.prize = prize;
		ingredientList.add(this);
	}
	
	/**inicializuje novu surovinu ak uz surovina z danym stockID existuje iba ju prepise a vrati existujucu zo zmenenymi hodnotami */
	public static Ingredient createIngredient(int stockID,String name,int count,int prize)
	{
		Ingredient existStock = ingredientWithID(stockID);
		if(existStock != null)
		{
			existStock.count = count;
			existStock.name = name;
			existStock.prize = prize;
			return existStock;
		}
		return new Ingredient(stockID,name,count,prize);
	}
	
	/**zmeni mnozstvo suroviny o hodnotu changeCount */
	public void changeCount(int changeCount)
	{
		count += changeCount;
	}
	
	/**vrati mnozstvo surovin */
	public int getCount()
	{
		return count;
	}
	
	/**vrati nazov suroviny */
	public String getName()
	{
		return name;
	}
	
	/**vrati ID suroviny ktore je v databaze */
	public int getIngredientID()
	{
		return ingredientID;
	}
	
	/**zmeni cenu suroviny o hodnotu changePrize */
	public void changePrice(int changePrize)
	{
		prize += changePrize;
	}
	
	/**vrati cenu suroviny */
	public int getPrice()
	{
		return prize;
	}
	
	/**cena suroviny sa nastavy na novu cenu*/
	public void setPrice(int newPrize)
	{
		prize = newPrize;
	}
	
	
	/**vrati pizzu z ID pizze ak je v zozname mojich pizz ak nie vrati null */
	public static Ingredient ingredientWithID(int stockID)
	{
		for(Ingredient actualStock : ingredientList)
		{
			if(actualStock.getIngredientID() == stockID)
			{
				return actualStock;
			}
		}
		return null;
	}

}
