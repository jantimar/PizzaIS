package Pizzeria;

import java.util.ArrayList;
import java.util.List;

/**trieda reprezentujuca surovinu */
public class Stock {
	
	/**zoznam vsetkych doposial inicializovanych surovin */
	private static List<Stock> stockList = new ArrayList<Stock>();
	
	/**ID suroviny ktore je v globalnej databaze programu */
	private final int stockID;
	/**nazov suroviny */
	private String name;
	/**mnozstvo danej suroviny */
	private int count;
	/**cena suroviny */
	private int prize;
	

	
	/**privatny konstruktor vytvoarajuci surovinu*/
	private Stock(int stockID,String name,int count,int prize)
	{
		this.stockID = stockID;
		this.name = name;
		this.count = count;
		this.prize = prize;
		stockList.add(this);
	}
	
	/**inicializuje novu surovinu ak uz surovina z danym stockID existuje iba ju prepise a vrati existujucu zo zmenenymi hodnotami */
	public static Stock newStock(int stockID,String name,int count,int prize)
	{
		Stock existStock = stockWithID(stockID);
		if(existStock != null)
		{
			existStock.count = count;
			existStock.name = name;
			existStock.prize = prize;
			return existStock;
		}
		return new Stock(stockID,name,count,prize);
	}
	
	/**zmeni mnozstvo suroviny o hodnotu changeCount */
	public void addCount(int changeCount)
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
	public int getStockID()
	{
		return stockID;
	}
	
	/**zmeni cenu suroviny o hodnotu changePrize */
	public void addPrize(int changePrize)
	{
		prize += changePrize;
	}
	
	/**vrati cenu suroviny */
	public int getPrize()
	{
		return prize;
	}
	
	/**cena suroviny sa nastavy na novu cenu*/
	public void setPrize(int newPrize)
	{
		prize = newPrize;
	}
	
	
	/**vrati pizzu z ID pizze ak je v zozname mojich pizz ak nie vrati null */
	public static Stock stockWithID(int stockID)
	{
		for(Stock actualStock : stockList)
		{
			if(actualStock.getStockID() == stockID)
			{
				return actualStock;
			}
		}
		return null;
	}

}
