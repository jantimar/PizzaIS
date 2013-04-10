package pizzeria;

import java.util.List;

import pizzeria.AOrder.State;

/**Trieda reprezentujuca casnika*/
public class Waiter extends AStuff{

	public Waiter(String name, String lastName, float salary, PizzaShop pizzaShop) {
		super(name, lastName, salary, pizzaShop);
	}

	/**prijme objednavku z restauracie a nasladne ju vytvory do systemu */
	public AOrder makeOrder(int orderID,AClient client,List<Pizza> pizzas)
	{
		//TODO vyplata za spravenu pracu moze by riesena tiez aspektom
		super.pizzaShop.addOutcomme(salary);
		AOrder order = new PersonalOrder(orderID, client, pizzas);
		return order;
	}
	
	/**odnesie objednavku z pizzerie */
	public void takeOrder(AOrder order)
	{
		//TODO nastavit stav objednavky na Finished
		order.setState(State.Finished);
		
		//TODO sumu riesit aspektom podla toho ci klient je Registrovany alebo Guest
		// a ci plati kartov alebo v hotovosti
		super.pizzaShop.addIncomme(order.getOrderBill());
	}
}
