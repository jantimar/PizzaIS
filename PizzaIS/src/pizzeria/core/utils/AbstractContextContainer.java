package pizzeria.core.utils;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractContextContainer implements IContextContainer {
	
	private Map<String,Object> contextData = new HashMap<String,Object>();
	
	@Override
	public void setContextData(String key, Object data) {
		contextData.put(key, data);
	}

	@Override
	public Object getContextData(String key) {
		return contextData.get(key);
	}
	
	@Override
	public boolean containsContextKey(String key){
		return contextData.containsKey(key);
	}

}
