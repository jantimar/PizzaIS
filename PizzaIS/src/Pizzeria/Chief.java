package pizzeria;

import pizzeria.AOrder.State;

/** Trieda reprezentujuca kuchara */
public class Chief extends AStuff {

	/** konstruktor vytvarajuci kuchara */
	public Chief(String name, String lastName, float salary, PizzaShop pizzaShop) {
		super(name, lastName, salary, pizzaShop);
	}

	/** kuchar vytvori pizzu */
	@StateAnotation(orderState = State.Completed)
	public void makePizza(AOrder order) {
		super.pizzaShop.addOutcomme(order.getOrderOutcomme());
		// TODO vyplata za spravenu pracu moze by riesena tiez aspektom
		super.pizzaShop.addOutcomme(salary);
	}

}
