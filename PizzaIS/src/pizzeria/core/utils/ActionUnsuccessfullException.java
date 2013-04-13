package pizzeria.core.utils;

/**
 * Vynimka vyhadzovana ak sa operacia v IUserRole nepodari uskutocnit
 * @author Michal Vrabel
 *
 */
public class ActionUnsuccessfullException extends Exception {

	private static final long serialVersionUID = 1L;

	public ActionUnsuccessfullException(){
		super("For some reason it is not possible successfuly finish action");
	}
	
	public ActionUnsuccessfullException(String message){
		super(message);
	}
	
	public ActionUnsuccessfullException(Throwable throwable){
		super(throwable);
	}
	
	public ActionUnsuccessfullException(String message,Throwable throwable){
		super(message,throwable);
	}
}
