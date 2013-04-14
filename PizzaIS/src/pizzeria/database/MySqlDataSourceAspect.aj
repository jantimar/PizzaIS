package pizzeria.database;

import pizzeria.core.PizzaShop;

//import pizzais.mvcore.PizzaShop;

public aspect MySqlDataSourceAspect {
	
	
	private MySqlDataLoader PizzaShop.dataLoader;
	
	public MySqlDataLoader PizzaShop.getDataLoader(){
		return this.dataLoader;
	}
	
	after(PizzaShop shop) : execution(PizzaShop.new(..)) && this(shop) {
		shop.dataLoader = new MySqlDataLoader();
		shop.dataLoader.LoadBasicStructure(shop);
	}
		
//	after(PizzaShop pizzashop) : execution(PizzaShop.new(..)) && this(pizzashop) {
//		if(pizzashop.getContextData("installDatabase").equals(true)){
//			
//		}
//		System.out.println("hello we got new pizzashop");
//	}
	
//	after(IContextContainer outer, IContextContainer inner) : call(IContextContainer.new(..)) {
//	after() returning (IContextContainer obj) : call( IContextContainer+.new(..)) && within(IContextContainer+) {
//		System.out.println("hello we got new "+obj.getClass()+" thing within ");
//		
//	}
//	after(IContextContainer a): this(a) && within(IContextContainer+) && call(IContextContainer+.new(..)) {
//		//System.out.println("hello we have this "+a.getClass());
//		
//	}
//	after(IContextContainer a): this(a) && within(IContextContainer+) && call(new(..)) {
//		System.out.println("hello we have this "+a.getClass());
//		
//	}
}
