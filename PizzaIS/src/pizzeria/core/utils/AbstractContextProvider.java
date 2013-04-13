package pizzeria.core.utils;

public class AbstractContextProvider implements IContextProvider {

	private IContextContainer context;
	
	@Override
	public IContextContainer getContext() {
		return this.context;
	}

	@Override
	public void setContext(IContextContainer context) {
		this.context = context;
	}

}
