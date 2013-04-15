package pizzeria.core.finance;
import pizzeria.core.PizzaShop;


public aspect FinanceAspect {
	
	pointcut pizzaShopCreation(PizzaShop shop) : execution(PizzaShop.new(..)) && this(shop); 
	
	private Finance PizzaShop.finance;
	/**
	 * Ziskanie instancie objektu s finnacnymi akciami
	 * @return
	 */
	public Finance PizzaShop.getFinance(){
		return this.finance;
	}
	/**
	 * Instalacia finnacii do instancie PizzaShop po jej vytvoreni 
	 * @param shop
	 */
	after(PizzaShop shop) : pizzaShopCreation(shop){
		shop.finance = new Finance(shop);
	}

	
	//float around() : call(public float Order.getPrice())
	
}
