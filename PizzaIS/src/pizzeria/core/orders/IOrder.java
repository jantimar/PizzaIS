package pizzeria.core.orders;

import java.util.List;

import pizzeria.core.customers.ICustomer;
import pizzeria.core.meals.Meal;
import pizzeria.core.utils.ActionUnsuccessfullException;
import pizzeria.core.utils.IMetaContainer;
/**
 * Rozhranie definujuce objednavku
 * @author Michal Vrabel
 *
 */
public interface IOrder extends IMetaContainer {
	/**
	 * @return Identifikator objednavky
	 */
	public int getId();
	/**
	 * @return Suma ktoru ma zaplatit zakaznik. (Tolko penazi obchod dostane z dorucenia)
	 */
	public float getOrderBill();
	/**
	 * @return Suma ktora je potrebna pre vytvorenie tejto objednavky. Naklady na suroviny, ludsku cinnost atd...
	 */
	public float getOrderCost();
	/**
	 * @return Rozdiel zisku a nakladov
	 */
	public float getOrderProfit();
	/**
	 * @return Stav objednavky
	 */
	public OrderState getState();
	/**
	 * Nastanie stavu objednavky
	 * @param state
	 * @throws ActionUnsuccessfullException Ak sa akcia z akehokolvek dovodu nepodarila vykonat
	 */
	public void setState(OrderState state) throws ActionUnsuccessfullException;
	/**
	 * Pridanie jedla do objednavky
	 * @param meal
	 */
	public void addMeal(Meal meal);
	/**
	 * Odstranenie jedla z objednavky
	 * @param meal
	 */
	public void removeMeal(Meal meal);
	/**
	 * @return Zoznam jedal v objednavke
	 */
	public List<Meal> getMealsList();
	/**
	 * @return Zakaznik ktory je prideleny ku objenavke
	 */
	public ICustomer getCustomer();
	
}
