package pizzeria.swingui;

import pizzeria.loyaltyprogram.LoyaltyProgramAspect;

public aspect PrecedenceControlAspect {

	declare precedence : LoyaltyProgramAspect, SwingUserInterfaceAspect;

}
