//package deprecated;
//
//import java.util.List;
//
//import deprecated.OldAbstractOrder;
//import deprecated.Pizza;
//
//import pizzeria.core.PizzaShop;
//import pizzeria.core.clients.AbstractClient;
//import pizzeria.core.orders.InternetOrder;
//import pizzeria.core.orders.StateAnotation;
//import pizzeria.core.orders.OrderState;
//
///**Trieda reprezentujuca donasku pizze*/
//public class Delivery extends AbstractRole {
//
//	/**konstruktor vytvarajuci zamestnanca zodpovedneho za donasku pizze */
//	public Delivery(String name, String lastName, float salary,PizzaShop pizzaShop) {
//		super(name, lastName, salary, pizzaShop);
//	}
//
//	/**prijme objednavku z internetu a nasladne ju vytvory do systemu */
//	@StateAnotation(orderState = OrderState.InProgress)
//	public OldAbstractOrder makeOrder(AbstractClient client,List<Pizza> pizzas)
//	{
//		//TODO vyplata za spravenu pracu moze by riesena tiez aspektom
//		super.pizzaShop.addOutcomme(salary);
//		return new InternetOrder(client, pizzas);
//	}
//
//	/**odnesie objednavku z internetu */
//	@StateAnotation(orderState = OrderState.Finished)
//	public void takeOrder(OldAbstractOrder order)
//	{	
//		//TODO sumu riesit aspektom podla toho ci klient je Registrovany alebo Guest
//		// a ci plati kartov alebo v hotovosti
//		super.pizzaShop.addIncomme(order.getOrderBill());
//	}
//}
