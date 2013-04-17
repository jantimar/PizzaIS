package pizzeria.core.meals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import pizzeria.core.stock.Ingredient;
import pizzeria.core.stock.IngredientAssoc;


/**
 * Ponuka jedal v obchode
 * @author Michal Vrabel
 *
 */
public class MealsMenu {
	
	private MealCategory defaultCategory = null;
	
	private Set<MealCategory> mealCategories;
	
	/**
	 * Vynimka hovriaca o tom ze sa pouzila nespravna kategoria jedal
	 * @author Michal Vrabel
	 *
	 */
	public class InvalidMealCategoryException extends Exception {
		private static final long serialVersionUID = 1L;

		public InvalidMealCategoryException(String message){
			super(message);
		}
	}
	/**
	 * Najdenie kategorie na zaklade mena
	 * @param name
	 * @return
	 */
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
	/**
	 * Ziskanie zakladnej kategorie
	 * @return
	 */
	public MealCategory getDefaultCategory() {
		if(defaultCategory == null){
			setDefaultCategoryByName("pizza");
		}
		return defaultCategory;
	}
	/**
	 * Nastavenie zakladnej kategorie
	 * @param name
	 */
	public void setDefaultCategoryByName(String name) {
		MealCategory mealCategory = this.findCategoryByName(name);
		if(mealCategory == null){
			mealCategory =  new MealCategory(name);
			this.mealCategories.add(mealCategory);
		}
		this.defaultCategory = mealCategory;
	}
	/**
	 * Ziskanie zoznamu kategorii
	 * @return
	 */
	public Collection<MealCategory> getMealCategoriesCollection() {
		return new ArrayList<MealCategory>(mealCategories);
	}
	/**
	 * Ziskanie vsetkych jedal na jedalnom listku
	 * @return
	 */
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
	 * Zaregistorvanie kategorie
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
	 * Odregistorvanie kategorie
	 * @param category
	 */
	public void unregisteCategory(MealCategory category) {
		mealCategories.remove(category);
	}
	/**
	 * Zaregistrovanie kategorie pouzitim mena kategorie
	 * @param categoryName
	 * @throws InvalidMealCategoryException
	 */
	public void registerCategoryByName(String categoryName) throws InvalidMealCategoryException{
		if(findCategoryByName(categoryName) != null){
			throw new InvalidMealCategoryException(String.format("Category with name \"%s\" already exists",categoryName));
		}
		mealCategories.add(new MealCategory(categoryName));
	}
	
	/**
	 * Zaregistrovanie jedla na jedalny listok (do vychodzej kategorie)
	 * @param category
	 */
	public void registerMeal(Meal meal) {
		getDefaultCategory().registerMeal(meal);
	}

	/**
	 * Odregistrovanie jedla z listku (z vychodzej kategorie)
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
	
	public Meal getMealByName(String name)
	{
		Meal returnMeal = null;
		for(Meal actualMeal : getAllMealsCollection())
		{
			if(actualMeal.getName() == name){
				returnMeal = actualMeal;
				break;
			}
		}
		return returnMeal;
	}
	
	/**
	 * Ziska jedlo podla ID
	 * @param Integer id
	 * @return
	 */
	public Meal getMealByID(Integer id){
		Meal returnMeal = null;
		
		for(Meal actualMeal : getAllMealsCollection())
		{
			if(actualMeal.getId() == id){
				returnMeal = actualMeal;
				break;
			}
		}
		return returnMeal;
	}
}