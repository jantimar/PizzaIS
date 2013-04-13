//package deprecated;
//
//import deprecated.OldAbstractOrder;
//import pizzeria.core.PizzaShop;
//import pizzeria.core.orders.StateAnotation;
//import pizzeria.core.orders.OrderState;
//
///** Trieda reprezentujuca kuchara */
//public class Chief extends AbstractRole {
//
//	/** konstruktor vytvarajuci kuchara */
//	public Chief(String name, String lastName, float salary, PizzaShop pizzaShop) {
//		super(name, lastName, salary, pizzaShop);
//	}
//
//	/** kuchar vytvori pizzu */
//	@StateAnotation(orderState = OrderState.Completed)
//	public void makePizza(OldAbstractOrder order) {
//		super.pizzaShop.addOutcomme(order.getOrderOutcomme());
//		// TODO vyplata za spravenu pracu moze by riesena tiez aspektom
//		super.pizzaShop.addOutcomme(salary);
//	}
//
//}
