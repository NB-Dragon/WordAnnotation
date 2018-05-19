package DataCollection;

import java.util.HashMap;
import org.eclipse.swt.graphics.Color;

public class ColorBox {
	private static final HashMap<String,Color> colorMap=new HashMap<String,Color>();
	private static final ColorBox colorbox =new ColorBox();
	
	public ColorBox(){
		RemoveAllMap();
	}
	
	public HashMap<String,Color> getMap(){
		return colorMap;
	}
	public void AddMap(String key,Color value){
		colorMap.put(key, value);
	}
	
	public void DelMap(String key){
		colorMap.remove(key);
	}
	
	public void RemoveAllMap(){
		colorMap.clear();
	}
	
	public Color getValue(String key){
		return colorMap.get(key);
	}
	
	public static ColorBox getADO(){
		return colorbox;
	}
}
