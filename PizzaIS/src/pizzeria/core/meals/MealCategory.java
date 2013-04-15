package pizzeria.core.meals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Kategoria jedla. Aktualne nevyuzivane. Ale interne v MealsMenu sa pouziva.
 * @author Michal Vrabel
 *
 */
public class MealCategory {

	private int id = -1;
	private String name;
	
	private Set<Meal> meals;
	/**
	 * Nastavenie id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Ziskanie id kategorie
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * Nastavenie mena kategorie
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Meno kategorie
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Jedla v kategorii
	 * @return
	 */
	public Collection<Meal> getMealsCollection() {
		return new ArrayList<Meal>(meals);
	}

	/**
	 * Zaregistrovanie jedla do kategorie
	 * @param meal
	 */
	public void registerMeal(Meal meal) {
		meals.add(meal);
	}

	/**
	 * Odregistorvanie jedla z kategorie
	 * @param meal
	 */
	public void unregisterMeal(Meal meal) {
		meals.remove(meal);
	}

	public MealCategory(int id, String name, Collection<Meal> meals){
		this.id = id;
		this.name = name;
		this.meals = new HashSet<Meal>(meals);
	}
	
	public MealCategory(String name, Collection<Meal> meals){
		this(-1,name,meals);
	}
	
	public MealCategory(String name){
		this.id = -1;
		this.name = name;
		this.meals = new HashSet<Meal>();
	}
}