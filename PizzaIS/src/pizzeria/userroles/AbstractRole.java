package pizzeria.userroles;

import pizzeria.core.PizzaShop;
import pizzeria.core.userroles.IUserRole;

/**Abstraktna trieda reprezentujuca zamestnanca */
public abstract class AbstractRole implements IUserRole {

	/** nazov roly */
	protected String name;
	/** popis roly */
	protected String description;
	/**instancia obchodu */
	protected PizzaShop pizzaShop;
	
	/**
	 * vrati meno zamestnanca
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * vrati popis roly
	 */
	public String getDescription()
	{
		return description;
	}

	
	/**vrati instanciu obchodu kde zamestnanec pracuje */
	public PizzaShop getPizzaShop()
	{
		return pizzaShop;
	}
}
