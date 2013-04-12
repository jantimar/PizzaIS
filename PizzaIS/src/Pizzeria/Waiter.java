package pizzeria;

import java.util.List;

import pizzeria.AOrder.State;

/**Trieda reprezentujuca casnika*/
public class Waiter extends AStuff{

	public Waiter(String name, String lastName, float salary, PizzaShop pizzaShop) {
		super(name, lastName, salary, pizzaShop);
	}

	/**prijme objednavku z restauracie a nasladne ju vytvory do systemu */
	@StateAnotation(orderState = State.InProgress)
	public AOrder makeOrder(AClient client,List<Pizza> pizzas)
	{
		//TODO vyplata za spravenu pracu moze by riesena tiez aspektom
		super.pizzaShop.addOutcomme(salary);
		AOrder order = new PersonalOrder(client, pizzas);
		return order;
	}
	
	/**odnesie objednavku z pizzerie */
	@StateAnotation(orderState = State.Finished)
	public void takeOrder(AOrder order)
	{
		//TODO sumu riesit aspektom podla toho ci klient je Registrovany alebo Guest
		// a ci plati kartov alebo v hotovosti
		super.pizzaShop.addIncomme(order.getOrderBill());
	}
}
