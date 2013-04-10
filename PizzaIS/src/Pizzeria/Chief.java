package pizzeria;

import pizzeria.AOrder.State;

/**Trieda reprezentujuca kuchara */
public class Chief extends AStuff{

	/**konstruktor vytvarajuci kuchara */
	public Chief(String name, String lastName, float salary,PizzaShop pizzaShop) {
		super(name, lastName, salary, pizzaShop);
	}
	
	/**kuchar vytvori pizzu */
	public void makePizza(AOrder order)
	{
		if(order.getState() != State.InProgress)
		{
			super.pizzaShop.addOutcomme(order.getOrderOutcomme());
			//TODO vyplata za spravenu pracu moze by riesena tiez aspektom
			super.pizzaShop.addOutcomme(salary);
			//TODO zmena stavu riesit aspektom
			order.setState(State.Completed);
		}
		else
		{
			//TODO aspekt zachyti chybu ak je state iny ako InProgress
		}
	}

}
