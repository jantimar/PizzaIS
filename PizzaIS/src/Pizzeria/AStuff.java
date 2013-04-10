package pizzeria;

/**Abstraktna trieda reprezentujuca zamestnanca */
public abstract class AStuff {

	/**meno zamestnanca */
	public String name;
	/**prizvisko zamestnanca */
	public String lastName;
	/**plat zamestnanca */
	public float salary;
	/**instancia obchodu */
	public PizzaShop pizzaShop;
	
	/**konstruktor vytvarajuci zamestnanca */
	public AStuff(String name, String lastName, float salary,PizzaShop pizzaShop)
	{
		this.lastName = lastName;
		this.name = name;
		this.salary = salary;
		this.pizzaShop = pizzaShop;
	}
	
	/**vrati meno zamestnanca */
	public String getName()
	{
		return name;
	}
	
	/**vrati priezvisko zamestnanca */
	public String getLastName()
	{
		return lastName;
	}
	
	/**vrati plat zamestnanca */
	public float getSalary()
	{
		return salary;
	}
	
	/**vrati instanciu obchodu kde zamestnanec pracuje */
	public PizzaShop getPizzaShop()
	{
		return pizzaShop;
	}
}
