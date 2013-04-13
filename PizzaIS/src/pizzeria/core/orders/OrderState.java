package pizzeria.core.orders;

public enum OrderState {
	NEW(1), IN_PROGRESS(2), READY(3), SHIPPING(4), FINISHED(5), REPAYMENT(6);
	
	public static boolean isValidTransition(OrderState originalState, OrderState newState){
		
		int diff = (newState.step - originalState.step);
		
		return (diff >= -1) || (diff<=1);
	}
	
	private int step;
	
	private OrderState(int step) {
		this.step = step;
	}
}
