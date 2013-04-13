package pizzeria.core.stock;


public class IngredientAssoc {
	
	private int id = -1;
	private Ingredient ingredient;
	private int quantity;
	
	public int getId() {
		return id;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public Ingredient getIngredient() {
		return ingredient;
	}
	
	public void increaseQuantity(int number){
		this.quantity += number;
	}
	
	public void decreaseQuantity(int number){
		this.quantity -= number;
	}
	
	public IngredientAssoc(Ingredient ingredient, int quantity){
		this(-1,ingredient,quantity);
	}
	
	public IngredientAssoc(int id, Ingredient ingredient, int quantity){
		this.id = id;
		this.ingredient = ingredient;
		this.quantity = quantity;
	}
}