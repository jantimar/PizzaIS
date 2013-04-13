package pizzeria.core.utils;

//import java.lang.reflect.Field;
import pizzeria.core.PizzaShop;
import pizzeria.core.orders.IOrder;

public aspect ContextPropagatorAspect {
	
	after(PizzaShop shop,IContextContainer context) : 
		call(public void IContextProvider+.setContext(IContextContainer)) && target(shop) && args(context)
	{
		shop.getStock().setContext(context);
		shop.getMealsMenu().setContext(context);
	}

//	after(PizzaShop shop) returning (Stock stock): 
//	after(Stock stock,PizzaShop shop): call(Stock.new(..)) && target(stock) && this(shop)
//	after(PizzaShop shop) returning (Stock stock): call(Stock.new(..)) && this(shop)
	
//	after(IContextProvider upper) returning (IContextProvider lower) :
//		call(IContextProvider+.new(..)) && this(upper) && !this(PizzaShop)
//	{
//		lower.setContext(upper.getContext());
//	}
	
	//zatial to nefunguje - neviem ci bude tak sa to musi zatial riesit manualne
//	after(IContextProvider upper,IContextContainer context) :
//		execution(public void IContextProvider+.setContext(IContextContainer)) && this(upper) && args(context)
//	{
//		Class<?> upperClass = upper.getClass();
//		String iccName = IContextContainer.class.getName();
//		Field[] declaredFields = upperClass.getDeclaredFields();
//		for(Field field : declaredFields){
//			try{
//						
//				Class<?>[] interfaces = field.getType().getInterfaces();
//				for(Class<?> classObj : interfaces){
//					if(classObj.getName() == iccName){
//							field.set(upperClass, context);
//						break;
//					}	
//				}
//			}			
//			catch(NullPointerException|IllegalAccessException ex){
//				
//			}
//		}
//	}
	
	
//	after(PizzaShop shop,IContextContainer context) : 
//		call(public void PizzaShop.addOrder(IOrder)) && target(shop) && args(context)
//	{
//		
//	}
	
}
