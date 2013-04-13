package pizzeria.core.utils;

import java.util.HashMap;
import java.util.Map;

public class HashMapContextContainer implements IContextContainer {
	
	private Map<String,Object> contextData = new HashMap<String,Object>();
	
	@Override
	public void putData(String key, Object data) {
		contextData.put(key, data);
	}

	@Override
	public Object getData(String key) {
		return contextData.get(key);
	}
	
	@Override
	public boolean containsKey(String key){
		return contextData.containsKey(key);
	}

}
