package DataCollection;

import java.util.ArrayList;

public class MenuTranslate_null {
	private static final ArrayList<String> transList = new ArrayList<String>();
	private static final MenuTranslate_null menutranslate = new MenuTranslate_null();
	
	public MenuTranslate_null(){
		RemoveAllList();
	}
	
	public void AddList(String value){
		transList.add(value);
	}
	
	public void DelList(int value){
		transList.remove(value);
	}
	
	public void RemoveAllList(){
		transList.clear();
	}
	
	public ArrayList<String> getList(){
		return transList;
	}
	
	public static MenuTranslate_null getADO(){
		return menutranslate;
	}
}
