package pizzeria.core.utils;

//import java.lang.reflect.Field;
import pizzeria.core.PizzaShop;

public aspect ContextPropagatorAspect {
	
	after(PizzaShop shop,IContextContainer context) : 
		call(public void IContextProvider+.setContext(IContextContainer)) && target(shop) && args(context)
	{
		shop.getStock().setContext(context);
		shop.getMealsMenu().setContext(context);
	}
	
}
