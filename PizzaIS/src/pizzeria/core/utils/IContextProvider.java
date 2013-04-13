package pizzeria.core.utils;

public interface IContextProvider {
	public IContextContainer getContext();
	public void setContext(IContextContainer context);
}
