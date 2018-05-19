package DataCollection;

import java.util.ArrayList;

import MainVisual.MainVisual;

public class RelFactory {
	private ArrayList<RelEnt> booksList=new ArrayList<RelEnt>();

	public RelFactory(String textflag[],String file_text){
		final MenuTranslate menutranslate=MenuTranslate.getADO();
		final MenuTranslate_null menutranslate_null=MenuTranslate_null.getADO();
		for(String LineText:textflag){
			RelEnt relent=new RelEnt();
			String str_temp[]=LineText.split("\t")[1].split(" ");
			relent.setDecoEnt(str_temp[1].split(":")[1]);
			relent.setMainEnt(str_temp[2].split(":")[1]);
			if(str_temp[0].indexOf("without")>=0){
				String[] str_temp_ent=str_temp[0].split("without");
				relent.setRelaEnt(menutranslate.getValue(str_temp_ent[0])+"²»"+MainVisual.RelDec+menutranslate.getValue(str_temp_ent[1]));
			}else if (str_temp[0].indexOf("with")>=0){
				String[] str_temp_ent=str_temp[0].split("with");
				relent.setRelaEnt(menutranslate.getValue(str_temp_ent[0])+MainVisual.RelDec+menutranslate.getValue(str_temp_ent[1]));
			}else{
				int i;
				for(i=0;i<menutranslate_null.getList().size();i++){
					if(str_temp[0].indexOf(menutranslate.getValue(menutranslate_null.getList().get(i)))>=0){
						String textone=menutranslate_null.getList().get(i);
						String texttwo[]=str_temp[0].split(menutranslate.getValue(menutranslate_null.getList().get(i)));
						relent.setRelaEnt(texttwo[0].equals("")?(textone+MainVisual.RelDec+menutranslate.getValue(texttwo[1])):(menutranslate.getValue(texttwo[0])+MainVisual.RelDec+textone));
						break;
					}
				}
				if(i==menutranslate_null.getList().size())break;
			}
			if(!booksList.contains(relent))booksList.add(relent);
		}
	}
	
	public ArrayList<RelEnt> getBooksList() {
		return booksList;
	}
}
