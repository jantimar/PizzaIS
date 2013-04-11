package pizzeria;

import java.util.List;

import pizzeria.AOrder.State;

/**Trieda reprezentujuca donasku pizze*/
public class Deliver extends AStuff {

	/**konstruktor vytvarajuci zamestnanca zodpovedneho za donasku pizze */
	public Deliver(String name, String lastName, float salary,PizzaShop pizzaShop) {
		super(name, lastName, salary, pizzaShop);
	}

	/**prijme objednavku z internetu a nasladne ju vytvory do systemu */
	public AOrder makeOrder(AClient client,List<Pizza> pizzas)
	{
		//TODO vyplata za spravenu pracu moze by riesena tiez aspektom
		super.pizzaShop.addOutcomme(salary);
		return new InternetOrder(client, pizzas);
	}

	/**odnesie objednavku z internetu */
	public void takeOrder(AOrder order)
	{
		//TODO nastavit stav objednavky na Finished
		order.setState(State.Finished);		
		//TODO sumu riesit aspektom podla toho ci klient je Registrovany alebo Guest
		// a ci plati kartov alebo v hotovosti
		super.pizzaShop.addIncomme(order.getOrderBill());
	}
}
