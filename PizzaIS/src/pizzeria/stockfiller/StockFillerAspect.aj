package pizzeria.stockfiller;

import pizzeria.core.PizzaShop;
import pizzeria.core.stock.Stock;
import pizzeria.core.stock.Ingredient;

public aspect StockFillerAspect {
	
	
	pointcut pizzaShopCreation(PizzaShop shop) : execution(PizzaShop.new(..)) && this(shop); 
	
	private StockAutoFiller PizzaShop.stockFiller;
	
	public StockAutoFiller PizzaShop.getStockFiller(){
		return this.stockFiller;
	}
	

	after(PizzaShop shop) : pizzaShopCreation(shop){
		
		shop.stockFiller = new StockAutoFiller(50);
		
	}

	after(Ingredient ingredient,Stock stock): 
		call(Ingredient Stock.takeIngredient(Ingredient,int)) && args(ingredient,int) && target(stock)
//		execution(Ingredient Stock.takeIngredient(Ingredient,int)) && args(ingredient,int) && this(stock)
	{
			
		int ingredientCount = stock.getIngredientQuantity(ingredient);
		int ingredientThreshold = stock.getShop().getStockFiller().getThreshold(ingredient);
		int diff = ingredientCount - ingredientThreshold;
		
		if(diff < 0){
			stock.addIngredient(ingredient, -diff);
		}
	}
}
