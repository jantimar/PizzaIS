package pizzeria.core.stock;
/**
 * Ingrediancia jedla
 * @author Michal Vrabel
 *
 */
public class Ingredient {

	private int id = -1;
	private String name;
	private float price;
	
	/**
	 * Identifikator
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Meno
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Cena
	 * @return
	 */
	public float getPrice() {
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
	 * Nastavenie ceny
	 * @param price
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	
	public Ingredient(int id, String name, float price ){
		this.id = id;
		this.name = name;
		this.price = price;		
	}
	
	public Ingredient(String name, float price){
		this(-1,name,price);
	}
	
	/**
	 * Ak sa porovava s ingredienciou porovnava sa na id, name, price
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Ingredient){
			Ingredient ingredient = (Ingredient)obj;
			return (this.id == ingredient.id) && (this.name == ingredient.name) && (this.price == ingredient.price); 
		}
		return super.equals(obj);
	}
	
}