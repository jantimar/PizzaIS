package pizzeria.stockfiller;

import java.util.HashMap;
import java.util.Map;
import pizzeria.core.stock.Ingredient;
/**
 * Kontrola stavu poloziek na skalde
 * @author Michal Vrabel
 *
 */
public class StockAutoFiller{
	
	private int defaultThreshold;

	private Map<Ingredient,Integer> extra = new HashMap<Ingredient,Integer>();
	
	public int getDefaultThreshold() {
		return defaultThreshold;
	}


	public void setDefaultThreshold(int threshold) {
		this.defaultThreshold = threshold;
	}
	
	public StockAutoFiller(int defaultThreshold){
		this.defaultThreshold = defaultThreshold;
	}
	
	public StockAutoFiller(){
		this.defaultThreshold = 50;
	}
	
	
	public void addIngredeintConfig(Ingredient ingredient, int threshold){
		this.extra.put(ingredient,threshold);
	}
	
	public void removeIngredientConfig(Ingredient ingredient){
		this.extra.remove(ingredient);
	}
	
	
	public int getThreshold(Ingredient ingredient){
		int threshold = this.defaultThreshold;
		if(extra.containsKey(ingredient)){
			threshold = extra.get(ingredient);
		}
		return threshold;
	}
		
}