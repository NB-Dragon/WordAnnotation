package DataCollection;

import MainVisual.MainVisual;

public class RelEnt {
	private String mainEnt="";
	private String decoEnt="";
	private String relaEnt="";
	final MenuTranslate menutranslate=MenuTranslate.getADO();
	final MenuTranslate_null menutranslate_null=MenuTranslate_null.getADO();
	
	public String getMainEnt() {
		return mainEnt;
	}
	public void setMainEnt(String mainEnt) {
		this.mainEnt = mainEnt;
	}
	public String getDecoEnt() {
		return decoEnt;
	}
	public void setDecoEnt(String decoEnt) {
		this.decoEnt = decoEnt;
	}
	public String getRelaEnt() {
		return relaEnt;
	}
	public void setRelaEnt(String relaEnt) {
		this.relaEnt = relaEnt;
	}
	public String toString(int arg[]) {
		String TextDescribe="";String dec=MainVisual.RelDec;
		for(int i=0;i<menutranslate_null.getList().size();i++){
			if(relaEnt.indexOf(menutranslate_null.getList().get(i))>=0){
				String textone=menutranslate.getValue(menutranslate_null.getList().get(i));
				String texttwo[]=relaEnt.split(relaEnt.indexOf("音"+dec)>=0?"音"+dec:dec);
				TextDescribe=texttwo[0].equals(menutranslate_null.getList().get(i))?textone+menutranslate.getValue(texttwo[1]):menutranslate.getValue(texttwo[0])+textone;
				return "R"+arg[0]+"\t"+TextDescribe+" Arg1:T"+arg[2]+" Arg2:T"+arg[1]+"\t\n";
			}
		}
		if(relaEnt.indexOf("音"+dec)>=0){
			String[] str_temp_ent=relaEnt.split("音"+dec);
			TextDescribe=menutranslate.getValue(str_temp_ent[0])+"without"+menutranslate.getValue(str_temp_ent[1]);
		}else if (relaEnt.indexOf(dec)>=0){
			String[] str_temp_ent=relaEnt.split(dec);
			TextDescribe=menutranslate.getValue(str_temp_ent[0])+"with"+menutranslate.getValue(str_temp_ent[1]);
		}
		return "R"+arg[0]+"\t"+TextDescribe+" Arg1:T"+arg[2]+" Arg2:T"+arg[1]+"\t\n";
	}
}
