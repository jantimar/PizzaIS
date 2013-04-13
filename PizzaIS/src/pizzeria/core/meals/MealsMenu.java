package pizzeria.core.meals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import pizzeria.core.utils.AbstractContextContainer;


public class MealsMenu extends AbstractContextContainer {
	
	private MealCategory defaultCategory = null;
	
	private Set<MealCategory> mealCategories;
	
	public class InvalidMealCategoryException extends Exception {
		private static final long serialVersionUID = 1L;

		public InvalidMealCategoryException(String message){
			super(message);
		}
	}
	
	public MealCategory findCategoryByName(String name){
		MealCategory result = null;
		for(MealCategory category : mealCategories){
			if(category.getName().equals(name)){
				result = category;
				break;
			}
		}
		return result;
	}
	
	public MealCategory getDefaultCategory() {
		if(defaultCategory == null){
			setDefaultCategoryByName("pizza");
		}
		return defaultCategory;
	}

	public void setDefaultCategoryByName(String name) {
		MealCategory mealCategory = this.findCategoryByName(name);
		if(mealCategory == null){
			mealCategory =  new MealCategory(name);
			this.mealCategories.add(mealCategory);
		}
		this.defaultCategory = mealCategory;
	}

	public Collection<MealCategory> getMealCategoriesCollection() {
		return new ArrayList<MealCategory>(mealCategories);
	}
	
	public Collection<Meal> getAllMealsCollection() {
		ArrayList<Meal> meals = new ArrayList<Meal>();
		
		for(MealCategory mealCategory : this.mealCategories){
			Collection<Meal> categoryMeals = mealCategory.getMealsCollection();
			for(Meal meal : categoryMeals){
				meals.add(meal);
			}
		}
		
		return meals;
	}

	/**
	 * 
	 * @param category
	 * @throws InvalidMealCategoryException 
	 */
	public void registerCategory(MealCategory category) throws InvalidMealCategoryException {
		if(findCategoryByName(category.getName()) != null){
			throw new InvalidMealCategoryException(String.format("Category with name \"%s\" already exists",category.getName()));
		}
		mealCategories.add(category);
	}

	/**
	 * 
	 * @param category
	 */
	public void unregisteCategory(MealCategory category) {
		mealCategories.remove(category);
	}

	public void registerCategoryByName(String categoryName) throws InvalidMealCategoryException{
		if(findCategoryByName(categoryName) != null){
			throw new InvalidMealCategoryException(String.format("Category with name \"%s\" already exists",categoryName));
		}
		mealCategories.add(new MealCategory(categoryName));
	}
	
	/**
	 * 
	 * @param category
	 */
	public void registerMeal(Meal meal) {
		getDefaultCategory().registerMeal(meal);
	}

	/**
	 * 
	 * @param category
	 */
	public void unregisteMeal(Meal meal) {
		getDefaultCategory().unregisterMeal(meal);
	}
	
	
	public MealsMenu(){
		this.mealCategories = new HashSet<MealCategory>();
	}
	
	public MealsMenu(Collection<MealCategory> mealCategories){
		this.mealCategories = new HashSet<MealCategory>(mealCategories);
	}
	
	public MealsMenu(Collection<Meal> meals, String defaultCategoryName){
		this.mealCategories = new HashSet<MealCategory>(); 
		this.mealCategories.add(new MealCategory(defaultCategoryName,meals));
	}
	
}