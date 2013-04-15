package pizzeria.core.finance;

import java.util.List;

public class Finance {

	private List<FinanceTransaction> transactions;
	
	public Finance(List<FinanceTransaction> transaction)
	{
		this.transactions = transaction;
	}
	
	public float getIncome()
	{
		float income = 0.0f;
		for(FinanceTransaction transaction : transactions)
		{
			if(transaction.getAmount() > 0)
				income += transaction.getAmount();
		}
		return income;
	}
	
	public float getOutcome()
	{
		float outcome = 0.0f;
		for(FinanceTransaction transaction : transactions)
		{
			if(transaction.getAmount() < 0)
				outcome += transaction.getAmount();
		}
		return outcome;
	}
	
	public float getProfit()
	{
		return getIncome() + getOutcome();
	}
	
	public void addTransaction(String subject,float amount)
	{
		transactions.add(new FinanceTransaction(subject,amount));
	}
	
//	private List<FinanceTransaction> transactions = new ArrayList<FinanceTransaction>();
//	
//	private ActionCostConfig costConfig;
//		
//	
//	public float getProfit() {
//		float profit = 0;
//		
//		for(FinanceTransaction transaction : transactions){
//			profit += transaction.getAmount();
//		}
//		
//		return profit;
//	}
//
//	public Finance(ActionCostConfig costConfig) {
//		this.costConfig = costConfig;
//	}
//	
//	//TODO v aspekte sa vytvara objekt 
//	public Finance() {
//	}
//	
//	//TODO volat v pripade zmene stavu kasy 
//	public void logTransaction(String subject, float amount){
//		transactions.add(new FinanceTransaction(subject, amount));
//	}
//	
//	public void logTransaction(FinanceTransaction transaction){
//		transactions.add(transaction);
//	}
//
//	public ActionCostConfig getCostConfig() {
//		return costConfig;
//	}
	
}
