package aspects;

import helpClass.RestClient;
import helpClass.RestClient.RequestMethod;

import org.json.JSONObject;

import pizzeria.*;
import pizzeria.AOrder.State;

/**Aspekty IS pizzerie */
public aspect AspectPizzaIS
{
	//pri volani funkcii makePizza 
	pointcut MakePizzas(AOrder order) : call(* Chief.makePizza(..))&& args(order);	

	//pizza stav je zly
	pointcut MakePizzasError(AOrder order) : call(* Chief.makePizza(..))&& args(order) && if(!order.getState().equals(State.InProgress));	

	//pri volani funkcii makePizza 
	pointcut DelieverMakeOrder() : call(* Deliver.makeOrder(..));
		
	//pri vytvarani objednavky
	pointcut WaiterMakeOrder() : call(* Waiter.makeOrder(..));
	
	//pri odovzdani pizze
	pointcut DelieverTakeOrder(AOrder order) : call(* Deliver.takeOrder(..))&& args(order);	
		
	//pri odovzdani pizze
	pointcut WaiterTakeOrder(AOrder order) : call(* Waiter.takeOrder(..))&& args(order);	
	
	// pri vytvoreni objektu AOrder
	pointcut CreateOrder(AOrder order) : execution(public AOrder.new(..)) && target(order);
	
	pointcut SetState(AOrder order) : call(* AOrder.setState(..)) && target(order);	
	
	
	
	after(AOrder order) : CreateOrder(order)
	{
		//TODO zaregistrovat ju do datbaazy a ulozit jej id
		order.setState(State.New);
	}
	
	after() returning (AOrder order) : DelieverMakeOrder() 
	{
		order.setState(State.InProgress);
	}
	
	after() returning (AOrder order) : WaiterMakeOrder() 
	{
		order.setState(State.InProgress);
	}
	
	after(AOrder order) : MakePizzas(order) 
	{
		order.setState(State.Completed);
	}
	
	after(AOrder order) : DelieverTakeOrder(order) 
	{
		order.setState(State.Finished);
	}
	
	after(AOrder order) : WaiterTakeOrder(order) 
	{
		order.setState(State.Finished);
	}
	
	before(AOrder order) : MakePizzasError(order)
	{
		//throw new Exception("");
	}

	//before(State newState) : SetState(newState)
	after(AOrder order) : SetState(order)
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
