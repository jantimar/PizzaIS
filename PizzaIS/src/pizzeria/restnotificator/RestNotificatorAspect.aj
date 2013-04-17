package pizzeria.restnotificator;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONException;
import org.json.JSONObject;

import pizzeria.core.PizzaShop;
import pizzeria.core.customers.IRegisteredCustomer;
import pizzeria.core.customers.RegisteredCustomer;
import pizzeria.core.meals.Meal;
import pizzeria.core.meals.MealsMenu;
import pizzeria.core.orders.DeliveryOrder;
import pizzeria.core.orders.IOrder;
import pizzeria.core.orders.OrderState;
import pizzeria.restnotificator.RestClient.RequestMethod;

import com.google.gson.Gson;

public aspect RestNotificatorAspect {

	private static MealsMenu mealsMenu;

	pointcut pizzaShopStart() : initialization(PizzaShop.new(..));

	pointcut mealsStart(MealsMenu menu) : initialization(MealsMenu.new(..)) && target(menu);

	pointcut getNewOrder() : execution(* RestNotificatorAspect.newDeliveryOrder(..));

	// uchovam si instanciu mealsMenu
	after(MealsMenu menu) : mealsStart(menu)
	{
		mealsMenu = menu;
	}

	// spusti vlakno ktore bude dokola vykonvat volanie na server
	after() : pizzaShopStart()
	{
		Thread refreshThread = new Thread() {
			public void run() {
				newDeliveryOrder();
			}
		};
		refreshThread.start();
	}

	// uspi sa na 5 sekund a zavola server ci ma nove objednavky
	after(): getNewOrder()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		newDeliveryOrder();
	}

	// zisti ci su nove objednavky na servery ak hej vytvori ich
	public void newDeliveryOrder() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("PizzaIS", "PizzaIS");
			String response = sendPost("order/getNew", obj.toString());

			OrderDTO[] orders = new Gson().fromJson(response, OrderDTO[].class);
			for (OrderDTO order : orders) {
				Collection<Meal> meal = new ArrayList<Meal>();
				meal.add(mealsMenu.getMealByName(order.pizzaName));
				new DeliveryOrder(order.clientID, new RegisteredCustomer(order.clientID), OrderState.NEW, meal);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	// v pripade zeny stavu sa posle notifikacia na server
	after(IOrder order) : call(* IOrder.setState(..)) && target(order) && if(order.getCustomer() instanceof IRegisteredCustomer)
	{
		JSONObject obj = new JSONObject();
		try {
			obj.put("State", order.getState().getStep());
			obj.put("OrderID", order.getId());
			obj.put("Price", order.getOrderBill());
			sendPost("order/changestate", obj.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	// posielanie POST restu na server
	private String sendPost(String urlEnd, String json) {
		String response = null;
//		// String url = "http://pizzais.apphb.com/" + urlEnd;
//		// String url = "http://localhost:49516/" + urlEnd;
//		String url = "http://jan:8080/" + urlEnd;
//		RestClient restClient = new RestClient(url);
//		try {
//			restClient.SetPostParam(json);
//			restClient.Execute(RequestMethod.POST);
//			response = restClient.getResponse();
//			System.out.println("response " + response);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return response;
	}

	private class OrderDTO {
		public int state;
		public int orderID;
		public int clientID;
		public String pizzaName;
	}
}
