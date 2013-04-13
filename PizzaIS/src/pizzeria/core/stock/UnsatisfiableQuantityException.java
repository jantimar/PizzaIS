package pizzeria.core.stock;

public class UnsatisfiableQuantityException extends Exception {

	private static final long serialVersionUID = 1L;

	protected Ingredient ingredient = null;
	
	public Ingredient getIngredient() {
		return ingredient;
	}

	public UnsatisfiableQuantityException(){
		super("Stock does not store satisfiable quantidy of demanded ingredient");
	}
	
	public UnsatisfiableQuantityException(String message){
		super(message);
	}
	
	public UnsatisfiableQuantityException(Ingredient ingredient){
		super("Stock does not store satisfiable quantidy of "+ingredient.getName());
		this.ingredient = ingredient;
	}
	
	public UnsatisfiableQuantityException(String message, Ingredient ingredient){
		super(String.format(message, ingredient.getName()));
		this.ingredient = ingredient;
	}
	
}