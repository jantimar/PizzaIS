package test;

import java.util.Arrays;
import java.util.List;

import Pizzeria.Pizza;
import Pizzeria.Stock;

/** docasna trieda urcena pre test nedokoncenej aplikacie */
public class Test {

	public static void main(String args[])
	{
		Stock.newStock(0, "Syr", 10, 2);
		Stock.newStock(1, "Kukurica", 2, 1);
		
		List<Stock> stockMargarita = Arrays.asList( Stock.stockWithID(0), Stock.stockWithID(1));
		
		Pizza.newPizza(0, "Margarita", stockMargarita);		
	}
}
