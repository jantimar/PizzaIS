package pizzeria.stockfiller;

import pizzeria.Ingredient;
import pizzeria.PizzaShop;

public privileged aspect StockFillerAspect {
	
	private ThresholdConfig config = new ThresholdConfig(5);
		
//	after(Ingredient ingredient): call(void Ingredient.addCount(int)) && target(ingredient)
	after(Ingredient ingredient): execution(void Ingredient.addCount(int)) && this(ingredient)
	{
		int ingredientCount = ingredient.getCount();
		int ingredientThreshold = config.getThreshold(ingredient);
		int diff = ingredientCount - ingredientThreshold;
		
		if(diff < 0){
			ingredient.addCount(-diff);
		}
	}
}