package DataCollection;

import java.util.ArrayList;
import org.eclipse.swt.graphics.Point;

public class NameFactory {
	private ArrayList<NameEnt> booksList=new ArrayList<NameEnt>();

	public NameFactory(String textflag[],String file_text){
		int n=0,f=0,i;String LineText;
		final MenuTranslate menutranslate=MenuTranslate.getADO();
		for(i=0;i<textflag.length;i++){
			LineText=textflag[i];
			NameEnt nameent=new NameEnt();
			String str_temp[]=LineText.split("\t");
			while(n<Integer.parseInt(str_temp[1].split(" ")[1])-f){
				if(String.valueOf(file_text.charAt(n++)).equals("\n"))f++;
			}//ann格式的行数识别
			nameent.setPoint(new Point(Integer.parseInt(str_temp[1].split(" ")[1])-f,Integer.parseInt(str_temp[1].split(" ")[2])-f));
			nameent.setEnt(str_temp[2]);
			nameent.setType(menutranslate.getValue(str_temp[1].split(" ")[0]));
			if(i+1<textflag.length&&textflag[i+1].charAt(0)=='A'){
				nameent.setType_tf(textflag[i+1].split("\t")[1].indexOf("present")>=0?"当前的":"否认的");i++;
			}else{nameent.setType_tf("");}
			if(!booksList.contains(nameent))booksList.add(nameent);
		}
	}
	
	public ArrayList<NameEnt> getBooksList() {
		return booksList;
	}
}
