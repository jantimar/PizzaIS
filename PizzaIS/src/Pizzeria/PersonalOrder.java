package pizzeria;

import java.util.List;

/**Trieda reprezentujuca objednavku z pizzerie */
public class PersonalOrder extends AOrder{

	public PersonalOrder(AClient client, List<Pizza> pizzas) {
		super(client, pizzas);
	}

}
