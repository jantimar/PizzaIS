package pizzeria.database;

//import java.util.List;
import java.util.Arrays;
import pizzeria.core.PizzaShop;
import pizzeria.core.meals.Meal;
import pizzeria.core.meals.MealsMenu;
import pizzeria.core.stock.Ingredient;
import pizzeria.core.stock.IngredientAssoc;
import pizzeria.core.stock.Stock;

/**
 * Trieda zaobalujuca operacie pre nacitavanie informacii z databazy
 * @author uzivatel
 *
 */
public class MySqlDataLoader {

//	public List<Order> getOrders() {
//		throw new UnsupportedOperationException();
//	}
//
//	public List<Meal> getMeals() {
//		throw new UnsupportedOperationException();
//	}
//
//	public List<Ingredient> getIngredients() {
//		throw new UnsupportedOperationException();
//	}

	/**
	 * Naplni udajove struktury obchodu 
	 * @param shop
	 */
	public void LoadBasicStructure(PizzaShop shop){
		
		
		Stock stock = shop.getStock();
		MealsMenu menu = shop.getMealsMenu();

		
		Ingredient syr = new Ingredient("gouda",new Float(0.65/100));
		Ingredient kecup = new Ingredient("kecup",new Float(0.60/450));
		Ingredient drozdie = new Ingredient("drozdie",new Float(0.35/42));
		Ingredient muka = new Ingredient("muka",new Float(0.89/1000));
		Ingredient sunka = new Ingredient("sunka",new Float(8.99/1000));
		Ingredient olej = new Ingredient("olej olivovy",new Float(6.95/500));
		Ingredient cestoviny = new Ingredient("cestoviny kolienka",new Float(0.65/500));
		
		stock.addIngredient(syr, 500);
		stock.addIngredient(kecup, 500);
		stock.addIngredient(drozdie, 200);
		stock.addIngredient(muka, 5000);
		stock.addIngredient(sunka, 5000);
		stock.addIngredient(cestoviny, 500);
		
		Meal pizza = new Meal("pizza",4, Arrays.asList(new IngredientAssoc[]{
			new IngredientAssoc(muka, 520/4),
			new IngredientAssoc(syr, 100),
			new IngredientAssoc(olej, 15),
			new IngredientAssoc(kecup, 30),
			new IngredientAssoc(drozdie, 10),
			new IngredientAssoc(sunka, 100)
		}));
		
		Meal spaegty = new Meal("spagety",2.2f,Arrays.asList(new IngredientAssoc[]{
			new IngredientAssoc(syr, 100),
			new IngredientAssoc(kecup, 30),
			new IngredientAssoc(cestoviny, 250)
		}));
		
		menu.registerMeal(pizza);
		menu.registerMeal(spaegty);
		
	}
	/**
	 * Vyprazdni udajove struktury obchodu
	 * @param shop
	 */
	private void EmptyShop(PizzaShop shop){
		
	}

}