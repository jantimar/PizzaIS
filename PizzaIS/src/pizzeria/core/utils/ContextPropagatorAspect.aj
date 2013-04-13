package pizzeria.core.utils;

import java.lang.reflect.Field;

public aspect ContextPropagatorAspect {
	/**
	 * Prekopirovanie kontextovych informacii skladu pri jeho vytvoreni v PizzaShop	
	 * @param shop
	 * @param stock
	 */
//	after(PizzaShop shop) returning (Stock stock): 
//	after(Stock stock,PizzaShop shop): call(Stock.new(..)) && target(stock) && this(shop)
//	after(PizzaShop shop) returning (Stock stock): call(Stock.new(..)) && this(shop)
//	{
//		if(shop.containsContextKey(ThresholdConfig.DEFAULT_CONTEXT_KEY)){
//			stock.setContextData(ThresholdConfig.DEFAULT_CONTEXT_KEY, shop.getContextData(ThresholdConfig.DEFAULT_CONTEXT_KEY));
//		}
//	}
	
	after(IContextProvider upper) returning (IContextProvider lower) :
		call(IContextProvider+.new(..)) && this(upper) 
	{
		lower.setContext(upper.getContext());
	}
	
	//zatial to nefunguje - neviem ci bude tak sa to musi zatial riesit manualne
	after(IContextProvider upper,IContextContainer context) :
		execution(public void IContextProvider+.setContext(IContextContainer)) && this(upper) && args(context)
	{
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
	}
	
}
