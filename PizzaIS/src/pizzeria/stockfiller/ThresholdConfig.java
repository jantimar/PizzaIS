package pizzeria.stockfiller;

import java.util.HashMap;
import java.util.Map;

import pizzeria.Ingredient;

public class ThresholdConfig {
	
	private int defaultThreshold = 5;

	private Map<Ingredient,Integer> extra = new HashMap<Ingredient,Integer>();
	
	public int getDefaultThreshold() {
		return defaultThreshold;
	}


	public void setDefaultThreshold(int threshold) {
		this.defaultThreshold = threshold;
	}
	
	public ThresholdConfig(int defaultThreshold){
		this.defaultThreshold = defaultThreshold;
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