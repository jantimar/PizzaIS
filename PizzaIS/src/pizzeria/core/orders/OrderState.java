package pizzeria.core.orders;
/**
 * Stav objednavky
 * @author Michal Vrabel
 *
 */
public enum OrderState {
	// Nova objednavka
	NEW(1),
	// Na spracovani objednavky sa pracuje - napr vari sa jedlo
	IN_PROGRESS(2),
	// Objednavka je pripravena na dorucenie/prinesenie
	READY(3),
	// Objednavka sa dorucuje/prinasa
	SHIPPING(4),
	// Objednavka je dorucena a ukoncena
	FINISHED(5),
	// Objednavka ??? bola zrusena ??? - nevyuzivane
	REPAYMENT(6);
	
	/**
	 * Kontroluje ci je zmena stavu maximalne o jeden krok 
	 * @param originalState
	 * @param newState
	 * @return True ak je zmena maximalne jeden krok
	 */
	public static boolean isValidTransition(OrderState originalState, OrderState newState){
		
		int diff = (newState.step - originalState.step);
		
		return (diff >= -1) || (diff<=1);
	}
	
	private int step;
	
	private OrderState(int step) {
		this.step = step;
	}
}
