package manipSef;

import org.jfree.data.xy.XYSeries;

public final class SefComplement {

		
	public SefComplement(){
	}
	
	public static SEF getComplement(SEF sef1){
		XYSeries listPts= sef1.getInflexions();
		//System.out.println("Description du sef: "+listPts.getKey());
		double inf=sef1.getBorneInf();
		double sup=sef1.getBorneSup();
		/*
		 * Si par hasard les points d'inflexions extremes ( tout a gauche et tout a droite du sef)
		 * se trouvent sur l'axe des abscisses, on peut reduire l'intervalle du sef à ces points là
		 * et ceux qu'ils contiennent
		 */
		if(listPts.getY(0).doubleValue() == 0){
			inf=listPts.getY(0).doubleValue();
		}
		if(listPts.getY(listPts.getItemCount()-1).doubleValue() == 0){
			sup=listPts.getY(listPts.getItemCount()-1).doubleValue();
		}
		XYSeries comp=new XYSeries("Complémentaire de "+listPts.getKey());
		int indiceParcoursSef;
		
		for(indiceParcoursSef=0; indiceParcoursSef < listPts.getItemCount();indiceParcoursSef++){
			comp.add(listPts.getX(indiceParcoursSef).doubleValue(), 1 - listPts.getY(indiceParcoursSef).doubleValue());
			//System.out.println("Indice :"+indiceParcoursSef);
			//System.out.println("Point : "+listPts.getX(indiceParcoursSef).doubleValue()+","+listPts.getY(indiceParcoursSef).doubleValue()+"\n");
		}
		
		return new SEF(inf, sup,comp);
	}
}
