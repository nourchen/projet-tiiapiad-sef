package manipSef;

import org.jfree.data.xy.XYDataItem;

public class CouplePointSef {

	private XYDataItem point;
	private int sef;
	
	public CouplePointSef(XYDataItem point,int sefAssocie) {
		this.point=point;
		sef=sefAssocie;
	}
	
	public XYDataItem getPoint() {
		return point;
	}
	public int getSef() {
		return sef;
	}
}
