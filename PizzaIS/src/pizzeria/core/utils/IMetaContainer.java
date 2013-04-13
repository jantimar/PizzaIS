package pizzeria.core.utils;
/**
 * Meta informacie o polozke  
 * @author uzivatel
 *
 */
public interface IMetaContainer{
	/**
	 * Ulozi do kluca
	 * @param key
	 * @param data
	 */
	public void putMeta(String key, Object data);

	/**
	 * Ziska z kontexotvych dat pozadovany kluc
	 * @param key
	 */
	public Object getMeta(String key);
	/**
	 * Zistuje ci sa v kontextovych informaciach nachadza pozadovany kluc
	 * @param key
	 * @return
	 */
	public boolean containsMetaKey(String key);
}
