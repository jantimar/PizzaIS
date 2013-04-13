package deprecated;


/** Trieda reprezentujuca registrovaneho pouzivatela */
public class RegistredCustomer extends AbstractCustomer {

	/** id registrovaneho klienta */
	private final int clientID;
	/** zlava pouzivatela */
	private float discount;

	/** konstruktor vytvarajuci registrovaneho klienta */
	public RegistredCustomer(int clientID, String name, String lastName,
			String adress, float discount) {
		super(name, lastName, adress);
		this.clientID = clientID;
		this.discount = discount;
	}

	/** vrati id registrovaneho klienta */
	public int getClientID() {
		return clientID;
	}

	/** zaplatenie objednavky peniazmi */
	public void payOrderByCash(OldAbstractOrder orderForPay) {
		// TODO neskor riesit aspektom zvysenie discount , a zapisanie do
		// databazy , tiez zmenu stavu sumy v pokladni a zmenu stavu objednavky
		discount += ((float) orderForPay.getOrderBill() % 5.0f);
	}

	/** zaplatenie objednavky kartou */
	public void payOrderByCard(OldAbstractOrder orderForPay) {
		// TODO neskor riesit aspektom zvysenie discount , a zapisanie do
		// databazy , tiez zmenu stavu sumy v pokladni a zmenu stavu objednavky
		discount += ((float) orderForPay.getOrderBill() % 10.0f);
	}
}
