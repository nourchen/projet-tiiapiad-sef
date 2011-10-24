package manipSef;

import org.jfree.data.xy.XYSeries;

public class SefIntersection {
	
	private XYSeries sef1;
	private XYSeries sef2;
	private Norme tnorme;
	
		
	public SefIntersection(XYSeries sef1, XYSeries sef2,Norme tnorme){
		this.sef1=sef1;
		this.sef2=sef2;
		this.tnorme=tnorme;
	}
	
	public XYSeries getIntersection(){
		XYSeries inter=new XYSeries("Intersection de "+sef1.getDescription()+" et: "+sef2.getDescription());
		int indiceParcoursSef1;
		int indiceParcoursSef2;
		Number xrefGauche,xrefDroit;
		for(indiceParcoursSef1=0 ; indiceParcoursSef1< sef1.getItemCount(); indiceParcoursSef1++){
			
		}
		
		return inter;
	}
	
	
}
