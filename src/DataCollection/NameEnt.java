package DataCollection;

import org.eclipse.swt.graphics.Point;

public class NameEnt {
	private Point point;
	private String ent="";
	private String type="";
	private String type_tf="";
	final MenuTranslate menutranslate=MenuTranslate.getADO();
	
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public String getEnt() {
		return ent;
	}
	public void setEnt(String ent) {
		this.ent = ent+"["+this.getPoint().x+"-"+this.getPoint().y+"]";
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType_tf() {
		return type_tf;
	}
	public void setType_tf(String type_tf) {
		this.type_tf = type_tf;
	}
	public String toString(int n,int f,int an) {
		return "T"+n+"\t"+menutranslate.getValue(type)+" "+(point.x+f)+" "+(point.y+f)+"\t"+ent.substring(0, ent.lastIndexOf("["))+"\n"+(type_tf.equals("")?"":("A"+(an)+"\tState T"+n+" "+(type_tf.equals("µ±Ç°µÄ")?"present":"absent")+"\n"));
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NameEnt other = (NameEnt) obj;
		if (ent == null) {
			if (other.ent != null)
				return false;
		} else if (!ent.equals(other.ent))
			return false;
		return true;
	}
}
