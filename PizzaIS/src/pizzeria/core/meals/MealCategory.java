package pizzeria.core.meals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import pizzeria.core.utils.AbstractContextContainer;

public class MealCategory extends AbstractContextContainer {

	private int id = -1;
	private String name;
	
	private Set<Meal> meals;
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Collection<Meal> getMealsCollection() {
		return new ArrayList<Meal>(meals);
	}

	/**
	 * 
	 * @param meal
	 */
	public void registerMeal(Meal meal) {
		meals.add(meal);
	}

	/**
	 * 
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