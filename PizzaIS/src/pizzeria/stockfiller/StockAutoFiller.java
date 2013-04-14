package pizzeria.stockfiller;

import java.util.HashMap;
import java.util.Map;

import pizzeria.core.stock.Ingredient;
import pizzeria.core.utils.IContextProvider;
import pizzeria.core.utils.IPizzaShopExtension;
public class StockAutoFiller implements IPizzaShopExtension{
	
	public static final String CONTEXT_KEY = StockAutoFiller.class.getName();
	
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
	
	@Override
	public void installTo(IContextProvider contextProvider) {
		contextProvider.getContext().putData(CONTEXT_KEY,this);
	}
	
}