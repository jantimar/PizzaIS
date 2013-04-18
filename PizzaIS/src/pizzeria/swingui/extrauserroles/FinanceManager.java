package pizzeria.swingui.extrauserroles;

import pizzeria.core.userroles.IUserRole;
import pizzeria.userroles.AbstractRole;

public class FinanceManager extends AbstractRole implements IUserRole, IFinanceManager {

	@Override
	public String getName() {
		return "Finance manager";
	}

	@Override
	public String getDescription() {
		return "Manages finance in shop";
	}

}
