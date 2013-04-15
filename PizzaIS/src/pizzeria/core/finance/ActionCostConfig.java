package pizzeria.core.finance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
/**
 * Nastroj pre pracu s mapou typu Map<String,Float> a moznostou ulozit udaje do suboru.
 * Moze byt odstranene.
 * @author Michal Vrabel
 *
 */
public class ActionCostConfig {
	
	private float defaultActionCost;
	
	Map<String,Float> actionCosts = new HashMap<String,Float>();
	
	public ActionCostConfig(float defaultActionCost){
		this.defaultActionCost = defaultActionCost;
	}
	
	public ActionCostConfig(String actionCostsFile, float defaultActionCost) throws FileNotFoundException, IOException {
		loadPricesFromFile(actionCostsFile);
		this.defaultActionCost = defaultActionCost;
	}
	
	public float getDefaultActionCost() {
		return defaultActionCost;
	}

	public float getActionCost(String name){
		return 0;
	}
	
	public void setActionCost(String name,float price){
		actionCosts.put(name,price);
	}
	
	public void saveChanges(String actionCostsFile) throws FileNotFoundException, IOException{
		
		Properties actionPricesProp;
		actionPricesProp = new Properties();
		
		Set<Entry<String,Float>> actionsSet = actionCosts.entrySet();
		for(Entry<String,Float> actionEntry : actionsSet){
			actionPricesProp.put(actionEntry.getKey(), actionEntry.getValue());
		}
		
		actionPricesProp.store(new FileOutputStream(actionCostsFile), null);
		
	}
	
	public void loadPricesFromFile(String actionPricesFile) throws FileNotFoundException, IOException{
		Properties actionPricesProp = new Properties();
		actionPricesProp.load(new FileInputStream(actionPricesFile));
		
		Set<Entry<Object,Object>> actionsSet = actionPricesProp.entrySet();
		for(Entry<Object,Object> entry : actionsSet){
			this.actionCosts.put(entry.getKey().toString(), new Float(entry.getValue().toString()));
		}
		
	}	
}
