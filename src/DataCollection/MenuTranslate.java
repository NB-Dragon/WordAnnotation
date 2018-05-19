package DataCollection;

import java.util.HashMap;

public class MenuTranslate {
	private static final HashMap<String,String> transMap=new HashMap<String,String>();
	private static final MenuTranslate menutranslate =new MenuTranslate();
	
	public MenuTranslate(){
		RemoveAllMap();
	}
	
	public HashMap<String,String> getMap(){
		return transMap;
	}
	public void AddMap(String key,String value){
		transMap.put(key, value);
	}
	
	public void DelMap(String key){
		transMap.remove(key);
	}
	
	public void RemoveAllMap(){
		transMap.clear();
	}
	
	public String getValue(String key){
		return transMap.get(key);
	}
	
	public static MenuTranslate getADO(){
		return menutranslate;
	}
}
