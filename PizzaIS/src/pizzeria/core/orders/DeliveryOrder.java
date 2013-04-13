package pizzeria.core.orders;

/**Trieda reprezentujuca objednavku z internetu */
public class DeliveryOrder extends BasicOrder {

	private String destination;

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDestination(){
		return this.destination;
	}

}
