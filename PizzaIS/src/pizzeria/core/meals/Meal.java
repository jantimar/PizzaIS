package pizzeria.core.meals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pizzeria.core.stock.Ingredient;
import pizzeria.core.stock.IngredientAssoc;
//import pizzeria.core.stock.Stock;
//import pizzeria.core.stock.UnsatisfiableQuantityException;
import pizzeria.core.utils.AbstractContextContainer;

public class Meal extends AbstractContextContainer {

	public int id = -1;
	private float price;
	private String name;
	private MealCategory category;
	
	private List<IngredientAssoc> ingredientAssocs = new ArrayList<IngredientAssoc>();
	
	public Collection<IngredientAssoc> getIngredientAssocsCollection() {
		return new ArrayList<IngredientAssoc>(ingredientAssocs);
	}
	
	public void AddIngredient(Ingredient ingredient, int quantity){
		ingredientAssocs.add(new IngredientAssoc(ingredient, quantity));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public float getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getOutcome(){
		float price = 0;
		for(IngredientAssoc assoc : ingredientAssocs)
		{
			price += assoc.getQuantity() * assoc.getIngredient().getPrice();
		}
		return price;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public MealCategory getCategory() {
		return category;
	}

	public void setCategory(MealCategory category) {
		this.category = category;
	}
	
	
//	public void prepare(Stock stock) throws UnsatisfiableQuantityException {
//		for(IngredientAssoc assoc : ingredientAssocs){
//			stock.takeIngredient(assoc.getIngredient(), assoc.getQuantity());
//		}
//	}
	
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
	
	
}