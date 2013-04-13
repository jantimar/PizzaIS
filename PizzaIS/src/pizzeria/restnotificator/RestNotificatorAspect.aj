package pizzeria.restnotificator;

import helpClass.RestClient;
import helpClass.RestClient.RequestMethod;

import org.json.JSONObject;

import pizzeria.core.customers.IRegisteredCustomer;
import pizzeria.core.orders.IOrder;


public aspect RestNotificatorAspect {

	//before(State newState) : SetState(newState)
	after(IOrder order) : call(* IOrder+.setState(..)) && target(order)
	{
		if(order.getCustomer() instanceof IRegisteredCustomer)
		{
//			RestClient restClient = new RestClient("http://pizzais.apphb.com/order/changestate");
//			//RestClient restClient = new RestClient("http://localhost:54387/order/changestate");
//			JSONObject obj = new JSONObject();
//			try {
//				obj.put("State", order.getState());
//				obj.put("OrderID",order.getId());
//				obj.put("ClientID",((IRegisteredCustomer)order.getCustomer()).getId());
//				restClient.SetPostParam(obj.toString());
//				restClient.Execute(RequestMethod.POST);
//				//String response = restClient.getResponse();
//				//System.out.println("response " + response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}			
		}	
	}
}
