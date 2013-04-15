package pizzeria.swingui.threadsafety;

public abstract class RunnableWithReturn implements Runnable {
	protected Object _returnValue;
	public Object getReturnValue() {
		return _returnValue;
	}
}
