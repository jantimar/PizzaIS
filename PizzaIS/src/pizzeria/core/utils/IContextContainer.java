package pizzeria.core.utils;

/**
 * Moze uchovavat kontextove informacie rozneho typu
 * @author Michal Vrabel
 *
 */
public interface IContextContainer {

	/**
	 * Ulozi do kluca
	 * @param key
	 * @param data
	 */
	public void setContextData(String key, Object data);

	/**
	 * Ziska z kontexotvych dat pozadovany kluc
	 * @param key
	 */
	public Object getContextData(String key);
	/**
	 * Zistuje ci sa v kontextovych informaciach nachadza pozadovany kluc
	 * @param key
	 * @return
	 */
	public boolean containsContextKey(String key);

}