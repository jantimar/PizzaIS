package pizzeria.core.finance;
import java.util.ArrayList;

import pizzeria.core.PizzaShop;
import pizzeria.core.orders.IOrder;
import pizzeria.core.stock.Ingredient;
import pizzeria.core.stock.Stock;

public aspect FinanceAspect {


	private static Finance financeShop;

	pointcut pizzaShopStart(PizzaShop pizzaShop) : initialization(PizzaShop.new(..)) && target(pizzaShop);

	pointcut shipOrder(IOrder order) : execution(* pizzeria.*.*.*.shipOrder(..)) && args(order);
	
	pointcut buyIngredient(Ingredient ingredient,int amount): execution(public void Stock.addIngredient(..)) && args(ingredient,amount);

	after(PizzaShop pizzaShop) : pizzaShopStart(pizzaShop){
		financeShop = new Finance(new ArrayList<FinanceTransaction>());
	} 

	after(IOrder order): shipOrder(order)
	{
		financeShop.addTransaction("Ship order", order.getOrderBill());
	}
	
	after(Ingredient ingredient,int amount): buyIngredient(ingredient,amount)
	{
		financeShop.addTransaction("Buy new ingredient", amount * ingredient.getPrice() * (-1));
	}
}
