package pizzeria.restnotificator;

import org.json.JSONException;
import org.json.JSONObject;

import pizzeria.core.customers.IRegisteredCustomer;
import pizzeria.core.orders.IOrder;
import pizzeria.restnotificator.RestClient.RequestMethod;

public aspect RestNotificatorAspect {


	
	after(IOrder order) : call(* IOrder.setState(..)) && target(order) && if(order.getCustomer() instanceof IRegisteredCustomer)
	{
		JSONObject obj = new JSONObject();
		try {
			obj.put("State", order.getState());
			obj.put("OrderID", order.getId());
			obj.put("ClientID", ((IRegisteredCustomer) order.getCustomer()).getId());
			sendPost("order/changestate", obj.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private String sendPost(String urlEnd, String json) {
		String url = "http://pizzais.apphb.com/" + urlEnd;
		//String url = "http://localhost:54387/" + urlEnd;
		String response = null;
		RestClient restClient = new RestClient(url);
		// RestClient restClient = new
		// RestClient("http://localhost:54387/order/changestate");
		try {
			restClient.SetPostParam(json);
			restClient.Execute(RequestMethod.POST);
			response = restClient.getResponse();
			// System.out.println("response " + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;

	}
}
