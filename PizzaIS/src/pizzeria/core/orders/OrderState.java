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
	
    public int getStep() {
        return step;
    }
    
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
	
	/**
	 * Vrati integer, ktory sa zapise do db ako stav
	 * @param stav
	 */
	public static Integer getDBValue(OrderState stav) {
		switch(stav) {
			case NEW: return 1;
			case IN_PROGRESS: return 2;
			case READY: return 3;
			case SHIPPING: return 4;
			case FINISHED: return 5;
			default: return 6;
		}
	}
	
	/**
	 * Vrati stav na zaklade integera z db
	 * @param stav
	 */
	public static OrderState getValueFromDB(Integer stav) {
		switch(stav) {
			case 1: return OrderState.NEW;
			case 2: return OrderState.IN_PROGRESS;
			case 3: return OrderState.READY;
			case 4: return OrderState.SHIPPING;
			case 5: return OrderState.FINISHED;
			default: return OrderState.REPAYMENT;
		}
	}
	
	private int step;
	
	private OrderState(int step) {
		this.step = step;
	}
}
