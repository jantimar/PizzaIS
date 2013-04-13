package pizzeria.stockfiller;

import pizzeria.core.stock.Ingredient;
import pizzeria.core.stock.Stock;

public privileged aspect StockFillerAspect {
	
//	private ThresholdConfig config = new ThresholdConfig(5);

	after(Ingredient ingredient,Stock stock): 
		call(Ingredient Stock.takeIngredient(Ingredient,int)) && args(ingredient,int) && target(stock)
//		execution(Ingredient Stock.takeIngredient(Ingredient,int)) && args(ingredient,int) && this(stock)
	{
		if(stock.getContext().containsKey(ThresholdConfig.DEFAULT_CONTEXT_KEY)){
			
			ThresholdConfig config = (ThresholdConfig)stock.getContext().getData(ThresholdConfig.DEFAULT_CONTEXT_KEY);
			
			int ingredientCount = stock.getIngredientQuantity(ingredient);
			int ingredientThreshold = config.getThreshold(ingredient);
			int diff = ingredientCount - ingredientThreshold;
			
			if(diff < 0){
				stock.addIngredient(ingredient, -diff);
			}
		}
	}
}
