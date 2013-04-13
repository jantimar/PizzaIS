package pizzeria.database;

import java.util.List;

import pizzeria.core.utils.IContextContainer;

public class PizzaShopDataContext {

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

	public final static String DEFAULT_CONTEXT_KEY = "mysqlDataContext";
	
	private MySqlDataContextConfig config;
	
	private String contextKey; 
	
	public String getContextKey() {
		return contextKey;
	}

	/**
	 * 
	 * @param contextContainer
	 */
	public void installTo(IContextContainer contextContainer) {
		contextContainer.putData(contextKey, this);
	}

	/**
	 * 
	 * @param config
	 */
	public PizzaShopDataContext(MySqlDataContextConfig config) {
		this(config,DEFAULT_CONTEXT_KEY);
	}

	/**
	 * 
	 * @param config
	 */
	public PizzaShopDataContext(MySqlDataContextConfig config, String contextKey) {
		this.config = config;
		this.contextKey = contextKey;
	}
}