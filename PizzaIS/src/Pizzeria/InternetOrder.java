package pizzeria;

import java.util.List;

/**Trieda reprezentujuca objednavku z internetu */
public class InternetOrder extends AOrder{

	public InternetOrder(AClient client, List<Pizza> pizzas) {
		super(client, pizzas);
	}

}
