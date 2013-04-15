package pizzeria.core.stock;

/**
 * Polozka predsatvujuca zlucenie ingrediencie a jej kvantity. 
 * Vyuzivane v Stock pre asociaciu <Ingrediencia,mnozstvo na sklade> ale aj v Meals <Ingrediancia, monozstvo ingrediencie v jedle>
 * @author Michal Vrabel
 *
 */
public class IngredientAssoc {
	
	private int id = -1;
	private Ingredient ingredient;
	private int quantity;
	/**
	 * Identifikator asociacie. Vyuzitie otazne. Kanditat na vymazanie
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * Nastavenie kvantity ingrediencie
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * Kvanitita ingrediencie
	 * @return
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * Ingrediencia
	 * @return
	 */
	public Ingredient getIngredient() {
		return ingredient;
	}
	/**
	 * Zvysenie kvanitity ingrediencie
	 * @param number Suma o ktoru sa ma zvysit
	 */
	public void increaseQuantity(int number){
		this.quantity += number;
	}
	/**
	 * Znizenie kvantity ingredienie
	 * @param number Suma o ktoru sa ma znizit
	 */
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