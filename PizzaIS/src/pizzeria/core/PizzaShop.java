package pizzeria.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import pizzeria.core.meals.MealsMenu;
import pizzeria.core.orders.IOrder;
import pizzeria.core.stock.Stock;

/** Hlavna trieda reprezentujúca obchod */
public class PizzaShop {
	
	/** Poskytuje ingrediencie pre vytvorenie jedal  */
	private Stock stock;
	
	/** Poskytuje dostupne jedla */
	private MealsMenu mealsMenu;
	
	/** Objednavky v obchode */
	private List<IOrder> orders = new ArrayList<IOrder>();
	
	/**
     * @return Ponuka jedal v obchode 
	 */
	public MealsMenu getMealsMenu() {
		return mealsMenu;
	}
	
	/**
	 * Nastavenie ponuky jedal
	 * @param mealsMenu
	 */
	public void setMealsMenu(MealsMenu mealsMenu) {
		this.mealsMenu = mealsMenu;
	}

	/**
	 * @return Sklad jedal
	 */
	public Stock getStock() {
		return stock;
	}
	/**
	 * Nastavenie skladu jedal
	 * @param stock
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}
		
	/**
	 * Pridanie objednavky. 
	 * Na pridavane objednavok sa odporuca pouzivat triedy deciace od IWaiterUserRole. 
	 * @param order
	 */
	public void addOrder(IOrder order){
		orders.add(order);
	}
	/**
 	 * Odstranenie objednavky.
 	 * Na odstranenie objednavok sa odporuca pouzivat triedy deciace od IRemovesOrderUserRole.
 	 * @param order 
	 */
	public void removeOrder(IOrder order){
		orders.remove(order);
	}
	/**
	 * @return Zoznam objednavok v obchode
	 */
	public Collection<IOrder> getOrdersCollection(){
		return new ArrayList<IOrder>(orders);
	}
	
	public PizzaShop() {
				
		this.setStock(new Stock());
		this.setMealsMenu(new MealsMenu());
	
	}
	
	public PizzaShop(Stock stock, MealsMenu mealsMenu){
		this.setStock(stock);
		this.setMealsMenu(mealsMenu);
	}
}

