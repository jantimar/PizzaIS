package pizzeria.core.finance;
/**
 * Jednoducha struktura popisujuca tranzakciu
 * @author Michal Vrabel
 *
 */
public class FinanceTransaction {
	private String subject;
	private float amount;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public FinanceTransaction(String subject, float amount) {
		this.subject = subject;
		this.amount = amount;
	}
}
