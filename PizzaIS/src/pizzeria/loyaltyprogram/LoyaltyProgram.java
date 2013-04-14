package pizzeria.loyaltyprogram;

import java.util.HashMap;
import java.util.Map;

import pizzeria.core.PizzaShop;
import pizzeria.core.customers.ICustomer;
import pizzeria.core.customers.IRegisteredCustomer;
import pizzeria.core.orders.IOrder;
import pizzeria.core.utils.IContextProvider;
import pizzeria.core.utils.IPizzaShopExtension;

public class LoyaltyProgram implements IPizzaShopExtension {

	public static final String CONTEXT_KEY = LoyaltyProgram.class.getName();
	
	public static final String ORDER_META_KEY = LoyaltyProgram.class.getName();
	
	private Map<IRegisteredCustomer,Integer> pointsCustAssoc = new HashMap<IRegisteredCustomer,Integer>();
	
	private IExchangeAction exchangeAction;
	
	private PizzaShop shop;
	
	private float pointsCollectionRatio = 0.5f;
	
	public void addCustomer(IRegisteredCustomer customer){
		this.addPoints(customer, 0);
	}
	public void removeCustomer(IRegisteredCustomer customer){
		pointsCustAssoc.remove(customer);
	}
	
	public int getPoints(IRegisteredCustomer customer){
		Integer currentPoints = pointsCustAssoc.get(customer);
		if(currentPoints!=null){
			return currentPoints;
		}
		return 0;
	}
	
	public void addPoints(IRegisteredCustomer customer, int points){
		int newPoints = points + getPoints(customer);
		pointsCustAssoc.put(customer, newPoints);
	}
	
	public void addPointsForOrder(IOrder order){
		IRegisteredCustomer customer = getRegisteredCustomer(order);
		if(customer != null){			
			this.addPoints(customer, calculatePointsGain(order));
		}
	}
	
	public void takePoints(IRegisteredCustomer customer, int points){
		this.addPoints(customer, -points);
	}
	
	public int calculatePointsGain(IOrder order){
		return (int)(order.getOrderBill()*pointsCollectionRatio);
	}
	
	
	public void setExchange(IExchangeAction action){
		this.exchangeAction = action;
	}
	
	public void trySetExchangeFromOrder(IOrder order){
		Object meta = order.getMeta(ORDER_META_KEY);
		if(meta instanceof IExchangeAction){
			setExchange((IExchangeAction)meta);
		}
	}
	
	public PizzaShop getShop() {
		return shop;
	}
		
	public LoyaltyProgram(PizzaShop shop){
		this.shop = shop;
	}
	
	public float getPointsCollectionRatio() {
		return pointsCollectionRatio;
	}

	public void setPointsCollectionRatio(float pointsCollectionRatio) {
		this.pointsCollectionRatio = pointsCollectionRatio;
	}

	
	
	public void exchange(IOrder order) {
		ICustomer customer = order.getCustomer();
		if(!(customer instanceof IRegisteredCustomer)) return;
		
//		exchangeAction.exchange(, order, getShop());
	}
	
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
	

	@Override
	public void installTo(IContextProvider contextProvider) {
		contextProvider.getContext().putData(CONTEXT_KEY,this);
	}

	
}
