package pizzeria.core.finance;

import java.util.ArrayList;
import java.util.List;

public class Finance {

	private List<FinanceTransaction> transactions = new ArrayList<FinanceTransaction>();
	
	private ActionCostConfig costConfig;
		
	
	public float getProfit() {
		float profit = 0;
		
		for(FinanceTransaction transaction : transactions){
			profit += transaction.getAmount();
		}
		
		return profit;
	}

	public Finance(ActionCostConfig costConfig) {
		this.costConfig = costConfig;
	}
	
	public void logTransaction(String subject, float amount){
		transactions.add(new FinanceTransaction(subject, amount));
	}
	
	public void logTransaction(FinanceTransaction transaction){
		transactions.add(transaction);
	}

	public ActionCostConfig getCostConfig() {
		return costConfig;
	}
	
}
