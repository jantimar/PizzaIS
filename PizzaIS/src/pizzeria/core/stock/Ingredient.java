package pizzeria.core.stock;

public class Ingredient {

	private int id = -1;
	private String name;
	private float price;
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	public void setName(String name) {
		this.name = name;
	}

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
}