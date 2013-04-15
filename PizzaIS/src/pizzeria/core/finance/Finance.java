package pizzeria.core.finance;

import java.util.ArrayList;
import java.util.List;

import pizzeria.core.PizzaShop;
/**
 * Zaobaluje akcie pre pracu s financiami
 * @author uzivatel
 *
 */
public class Finance {
	
	private List<FinanceTransaction> transactions = new ArrayList<FinanceTransaction>();
	
	private PizzaShop shop;
	
	/**
	 * @return Instancia obchodu
	 */
	public PizzaShop getShop() {
		return shop;
	}
	
	/**
	 * Ziskanie celkoveho profitu obchodu
	 * @return
	 */
	public float getProfit() {
		float profit = 0;
		
		for(FinanceTransaction transaction : transactions){
			profit += transaction.getAmount();
		}
		
		return profit;
	}
	/**
	 * Zalogovanie transakcie pouzitim
	 * @param subject Popis transakcie, teda od koho alebo komu
	 * @param amount Suma ktora sa pridala do financii obchodu - Kladne alebo zaporne cislo 
	 */
	public void logTransaction(String subject, float amount){
		transactions.add(new FinanceTransaction(subject, amount));
	}
	/**
	 * Zalogovanie transakcie
	 * @param transaction
	 */
	public void logTransaction(FinanceTransaction transaction){
		transactions.add(transaction);
	}
	
	
	public Finance(PizzaShop shop) {
		this.shop = shop;
	}
	
	
	
//	private ActionCostConfig costConfig;
//	/**
//	 * Ziskanie konfiguracie cien akcii
//	 * @return
//	 */
//	public ActionCostConfig getCostConfig() {
//		return costConfig;
//	}
	
}
