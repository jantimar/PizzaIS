package pizzeria.core.stock;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Trieda predsavujuca sklad surovin
 * @author Michal Vrabel
 *
 */
public class Stock {
	
	/**
	 * Zoznam asociacii surovina - pocet
	 */
	private Collection<IngredientAssoc> ingredientAssocs; 
	
	/**
	 * Vacia zoznam asociacii asociacii surovina - pocet (nova instancia)
	 * @return
	 */
	public Collection<IngredientAssoc> getIngredientAssocsCollection() {
		return new ArrayList<IngredientAssoc>(ingredientAssocs);
	}

	/**
	 * Hlada ci sa pozadocana surovina nachadza v sklade
	 * @param ingredient
	 * @return
	 */
	public IngredientAssoc findIngredientAssoc(Ingredient ingredient){
		IngredientAssoc result = null;
		
		
		for(IngredientAssoc assoc : ingredientAssocs){
			if(assoc.getIngredient().equals(ingredient)){
				result = assoc;
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * Prida zadanu surovinu do skladu v zadanej kvantite. 
	 * Ak sa surovina v sklade nachadza tak sa zvysi mnozstvo suroviny na sklade
	 * @param ingredient
	 * @param quantity
	 */
	public void addIngredient(Ingredient ingredient, int quantity) {
		IngredientAssoc assoc = findIngredientAssoc(ingredient);
		if(assoc == null){
			ingredientAssocs.add(new IngredientAssoc(ingredient,quantity));
		}
		else{
			assoc.increaseQuantity(quantity);
		}
	}

	/**
	 * Pre zadanu surovinu vrati jej mnozstvo
	 * @param ingredient
	 * @return
	 */
	public int getIngredientQuantity(Ingredient ingredient){
		int quantity = 0;
		IngredientAssoc assoc = findIngredientAssoc(ingredient);
		
		if( assoc != null ){
			quantity = assoc.getQuantity();			
		}
		
		return quantity;
	}
	
	/**
	 * Vyberie pozadovanu surovinu zo skladu. 
	 * Ak je pozadovana kvantita suroviny nedostupna vyhodi sa UnsatisfiableQuantityException.
	 * @param ingredient
	 * @param quantity
	 * @throws UnsatisfiableQuantityException 
	 */
	public Ingredient takeIngredient(Ingredient ingredient, int quantity) throws UnsatisfiableQuantityException {
		IngredientAssoc assoc = findIngredientAssoc(ingredient);
		if(assoc == null){
			throw new UnsatisfiableQuantityException("Ingredient is not present in shop");
		}
		if(assoc.getQuantity() < quantity){
			throw new UnsatisfiableQuantityException();
		}
		
		assoc.decreaseQuantity(quantity);
		
		return assoc.getIngredient();
	}

	public Stock() {
		this.ingredientAssocs = new ArrayList<IngredientAssoc>();
	}
	
	public Stock(Collection<IngredientAssoc> assoc){
		this.ingredientAssocs = new ArrayList<IngredientAssoc>(assoc);
	}
	
}