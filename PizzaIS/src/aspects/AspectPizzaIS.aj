package aspects;

import helpClass.RestClient;
import helpClass.RestClient.RequestMethod;

import org.json.JSONObject;

import pizzeria.*;
import pizzeria.AOrder.State;

/**Aspekty IS pizzerie */
public aspect AspectPizzaIS
{		

	pointcut stateChange(StateAnotation state,AOrder order) : execution(* pizzeria.*.*(..)) && args(order) && @annotation(state);
	
	after(StateAnotation state,AOrder order): stateChange(state,order)
	{
		order.setState(state.orderState());
	}
	
	
	after(AOrder order) : execution(public AOrder.new(..)) && target(order)
	{
		//TODO zaregistrovat ju do datbaazy a ulozit jej id
		order.setState(State.New);
	}
	
	before(AOrder order) :  call(* Chief.makePizza(..))&& args(order) && if(!order.getState().equals(State.InProgress))
	{
		//throw new Exception("");
	}

	//before(State newState) : SetState(newState)
	after(AOrder order) : call(* AOrder.setState(..)) && target(order)
	{
		if(order.getClient() instanceof RegistredUser)
		{
			RestClient restClient = new RestClient("http://pizzais.apphb.com/order/changestate");
			//RestClient restClient = new RestClient("http://localhost:54387/order/changestate");
			JSONObject obj = new JSONObject();
			try {
				obj.put("State", order.getState());
				obj.put("OrderID",order.getOrderID());
				obj.put("ClientID",((RegistredUser)order.getClient()).getClientID());
				restClient.SetPostParam(obj.toString());
				restClient.Execute(RequestMethod.POST);
				//String response = restClient.getResponse();
				//System.out.println("response " + response);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}	
	}
}
