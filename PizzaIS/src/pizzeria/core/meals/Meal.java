package pizzeria.core.meals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pizzeria.core.stock.Ingredient;
import pizzeria.core.stock.IngredientAssoc;

/**
 * Jedlo
 */
public class Meal {

	public int id = -1;
	private float price;
	private String name;
	private MealCategory category;
	
	private List<IngredientAssoc> ingredientAssocs = new ArrayList<IngredientAssoc>();
	
	public Collection<IngredientAssoc> getIngredientAssocsCollection() {
		return new ArrayList<IngredientAssoc>(ingredientAssocs);
	}
	
	/**
	 * Pridanie ingrediencie
	 * @param ingredient
	 * @param quantity
	 */
	public void AddIngredient(Ingredient ingredient, int quantity){
		ingredientAssocs.add(new IngredientAssoc(ingredient, quantity));
	}
	/**
	 * Identifikator
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * Nastavenie id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Cena jedla ktoru musi zaplatit zakaznik
	 * @return
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * Nazov jedla
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * Nastavenie ceny ktoru musi zaplatit zakaznik
	 * @param price
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	/**
	 * Suma penazi potrebna pre vytvorenie jedla
	 * @return
	 */
	public float getCost(){
		float price = 0;
		for(IngredientAssoc assoc : ingredientAssocs)
		{
			price += assoc.getQuantity() * assoc.getIngredient().getPrice();
		}
		return price;
	}
	/**
	 * Nastavenie mena
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Kategoria jedna. Aktualne nevyuzivane.
	 * @return
	 */
	public MealCategory getCategory() {
		return category;
	}
	/**
	 * Nastavenie kategorie jedna. Aktualne nevyuzivane
	 * @param category
	 */
	public void setCategory(MealCategory category) {
		this.category = category;
	}
	
	
	
	public Meal( String name, float price){
		this.name = name;
		this.price = price;
	}
	
	public Meal( String name, float price, Collection<IngredientAssoc> ingredientAssocs){
		this(-1,name,price,ingredientAssocs);
	}
	
	public Meal(int id, String name, float price, Collection<IngredientAssoc> ingredientAssocs){
		this.id = id;
		this.name = name;
		this.price = price;
		this.ingredientAssocs = new ArrayList<IngredientAssoc>(ingredientAssocs);
	}

//  priprava jedla	
//	public void prepare(Stock stock) throws UnsatisfiableQuantityException {
//		for(IngredientAssoc assoc : ingredientAssocs){
//			stock.takeIngredient(assoc.getIngredient(), assoc.getQuantity());
//		}
//	}
	
}