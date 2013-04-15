package pizzeria.loyaltyprogram;

import java.util.HashMap;
import java.util.Map;

import pizzeria.core.PizzaShop;
import pizzeria.core.customers.ICustomer;
import pizzeria.core.customers.IRegisteredCustomer;
import pizzeria.core.orders.IOrder;
/**
 * Vernostny program pripocitava body za vykonanu objednavku
 * @author Michal Vrabel
 *
 */
public class LoyaltyProgram {
	
	public static final String ORDER_META_KEY = LoyaltyProgram.class.getName();
	
	private Map<IRegisteredCustomer,Integer> pointsCustAssoc = new HashMap<IRegisteredCustomer,Integer>();
	
	private IExchangeAction exchangeAction;
	
	private PizzaShop shop;
	
	private float pointsCollectionRatio = 0.5f;
	
	/**
	 * Pridanie zakaznika do LoyaltyProgram
	 * @param customer
	 */
	public void addCustomer(IRegisteredCustomer customer){
		this.addPoints(customer, 0);
	}
	/**
	 * Odobratie zakaznika z LoyaltyProgram
	 * @param customer
	 */
	public void removeCustomer(IRegisteredCustomer customer){
		pointsCustAssoc.remove(customer);
	}
	/**
	 * Zisklanie mapy Zakaznikov(kluc) a bodov(hodnota)
	 * @return
	 */
	public Map<IRegisteredCustomer,Integer> getCustomerPointsMap(){
		return new HashMap<IRegisteredCustomer,Integer>(pointsCustAssoc);
	}
	/**
	 * Ziskanie poctu bodov zakaznika
	 * @param customer
	 * @return
	 */
	public int getPoints(IRegisteredCustomer customer){
		Integer currentPoints = pointsCustAssoc.get(customer);
		if(currentPoints!=null){
			return currentPoints;
		}
		return 0;
	}
	/**
	 * Pridanie bodov zakaznikovi. Jednoducha operacia scitania
	 * @param customer
	 * @param points
	 */
	public void addPoints(IRegisteredCustomer customer, int points){
		int newPoints = points + getPoints(customer);
		pointsCustAssoc.put(customer, newPoints);
	}
	/**
	 * Pridanie bodov zakaznikovi ktori je v zadanej objednavke. Jednoducha operacia scitania
	 * @param order
	 */
	public void addPointsForOrder(IOrder order){
		IRegisteredCustomer customer = getRegisteredCustomer(order);
		if(customer != null){			
			this.addPoints(customer, calculatePointsGain(order));
		}
	}
	/**
	 * Odobratie bodov zakaznikovi
	 * @param customer
	 * @param points
	 */
	public void takePoints(IRegisteredCustomer customer, int points){
		this.addPoints(customer, -points);
	}
	/**
	 * Pre zadanu objednavku sa vypocita kolko bodov by sa pridalo zakaznikovi ktory jej prislucha
	 * @param order
	 * @return
	 */
	public int calculatePointsGain(IOrder order){
		return (int)(order.getOrderBill()*pointsCollectionRatio);
	}
	/**
	 * Nastavenie akcie na prepocet/zmenu bodov
	 * @param action
	 */
	public void setExchange(IExchangeAction action){
		this.exchangeAction = action;
	}
	/**
	 * Pokusi sa z meta informacii objednavy vykonat LoyaltyProgram.setExchange(IExchangeAction action)
	 * @param order
	 */
	public void trySetExchangeFromOrder(IOrder order){
		Object meta = order.getMeta(ORDER_META_KEY);
		if(meta instanceof IExchangeAction){
			setExchange((IExchangeAction)meta);
		}
	}
	/**
	 * Vrati instanciu prideleneho obchodu
	 * @return
	 */
	public PizzaShop getShop() {
		return shop;
	}
		
	public LoyaltyProgram(PizzaShop shop){
		this.shop = shop;
	}
	/**
	 * Vrati pomer prepostu bodov na peniaze
	 * @return
	 */
	public float getPointsCollectionRatio() {
		return pointsCollectionRatio;
	}
	/**
	 * Nastavi pomer prepoctu bodov na peniaze
	 * @param pointsCollectionRatio
	 */
	public void setPointsCollectionRatio(float pointsCollectionRatio) {
		this.pointsCollectionRatio = pointsCollectionRatio;
	}

	/**
	 * Vykona pre zadanu objednavku operaciu zameny bodov.
	 * @param order
	 */
	public void exchange(IOrder order) {
		ICustomer customer = order.getCustomer();
		if(!(customer instanceof IRegisteredCustomer)) return;
		
		exchangeAction.exchange(getPoints((IRegisteredCustomer)customer), order, getShop());
	}
	/**
	 * Pre zadanu objednavku vypocita jej cenu pre zakaznika
	 * @param order
	 * @return
	 */
	public float getOrderBill(IOrder order) {
		
		float orderBill = order.getOrderBill();
		
		if(exchangeAction == null) {
			return orderBill;
		}
		
		IRegisteredCustomer customer = getRegisteredCustomer(order);
		if(customer == null){
			return orderBill;	
		}
		
		int points = getPoints(customer);
		return exchangeAction.getOrderBill(points, order, shop);
		
	}
	
	private IRegisteredCustomer getRegisteredCustomer(IOrder order){
		ICustomer customer = order.getCustomer();
		if(!(customer instanceof IRegisteredCustomer)) return null;
		return (IRegisteredCustomer)customer;
	}
	
	

	
}
