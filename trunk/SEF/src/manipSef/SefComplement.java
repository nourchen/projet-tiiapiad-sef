package manipSef;

import org.jfree.data.xy.XYSeries;

public class SefComplement {

	private XYSeries sef1;
	
	public SefComplement(XYSeries sef1){
		this.sef1=sef1;
	}
	
	public XYSeries getComplement(){
		XYSeries comp=new XYSeries("Complémentaire de "+sef1.getDescription());
		int indiceParcoursSef;
		for(indiceParcoursSef=0; indiceParcoursSef < sef1.getItemCount();indiceParcoursSef++){
			comp.add(1 - sef1.getX(indiceParcoursSef).doubleValue(), 1 - sef1.getY(indiceParcoursSef).doubleValue());
		}
		
		return comp;
	}
}
