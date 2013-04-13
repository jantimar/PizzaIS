package pizzeria.stockfiller;

import pizzeria.core.PizzaShop;
import pizzeria.core.stock.Ingredient;
import pizzeria.core.stock.Stock;

public privileged aspect StockFillerAspect {
	
//	private ThresholdConfig config = new ThresholdConfig(5);
	
	/**
	 * Prekopirovanie kontextovych informacii skladu pri jeho vytvoreni v PizzaShop	
	 * @param shop
	 * @param stock
	 */
//	after(PizzaShop shop) returning (Stock stock): 
//	after(Stock stock,PizzaShop shop): call(Stock.new(..)) && target(stock) && this(shop)
	after(PizzaShop shop) returning (Stock stock): call(Stock.new(..)) && this(shop)
	{
		if(shop.containsContextKey(ThresholdConfig.DEFAULT_CONTEXT_KEY)){
			stock.setContextData(ThresholdConfig.DEFAULT_CONTEXT_KEY, shop.getContextData(ThresholdConfig.DEFAULT_CONTEXT_KEY));
		}
	}

	after(Ingredient ingredient,Stock stock): 
		call(Ingredient Stock.takeIngredient(Ingredient,int)) && args(ingredient,int) && target(stock)
//		execution(Ingredient Stock.takeIngredient(Ingredient,int)) && args(ingredient,int) && this(stock)
	{
		if(stock.containsContextKey(ThresholdConfig.DEFAULT_CONTEXT_KEY)){
			
			ThresholdConfig config = (ThresholdConfig)stock.getContextData(ThresholdConfig.DEFAULT_CONTEXT_KEY);
			
			int ingredientCount = stock.getIngredientQuantity(ingredient);
			int ingredientThreshold = config.getThreshold(ingredient);
			int diff = ingredientCount - ingredientThreshold;
			
			if(diff < 0){
				stock.addIngredient(ingredient, -diff);
			}
		}
	}
}
