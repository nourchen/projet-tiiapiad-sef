package manipSef;

import org.jfree.data.xy.XYSeries;

/**
 * Classe permettant d'effectuer l'op�ration ensembliste du compl�mentaire
 * sur un sous ensemble flou donn�
 * @author Sylvia Vieira
 *
 */
public final class SefComplement {

	/**
	 * Constructeur vide
	 * Il est pr�sent par d�faut, mais il figure ici explicitement
	 * au cas o� l'on ajouterait plus tard un constructeur,
	 * qui ferait disparaitre le constructeur vide
	 */
	public SefComplement(){
	}
	/**
	 * M�thode permettant d'effectuer l'op�ration ensembliste 
	 * du compl�mentaire sur un sous ensemble flou donn�
	 * @param sef1 : le sous ensemble flou � compl�menter
	 * @return le sous ensemble flou compl�mentaire de sef1
	 */
	public static SEF getComplement(SEF sef1){
		XYSeries listPts= sef1.getInflexions();
		//System.out.println("Description du sef: "+listPts.getKey());
		double inf=sef1.getBorneInf();
		double sup=sef1.getBorneSup();
		/*
		 * Si par hasard les points d'inflexions extremes ( tout a gauche et tout a droite du sef)
		 * se trouvent sur l'axe des abscisses, on peut reduire l'intervalle du sef � ces points l�
		 * et ceux qu'ils contiennent
		 */
		if(listPts.getY(0).doubleValue() == 0){
			inf=listPts.getY(0).doubleValue();
		}
		if(listPts.getY(listPts.getItemCount()-1).doubleValue() == 0){
			sup=listPts.getY(listPts.getItemCount()-1).doubleValue();
		}
		XYSeries comp=new XYSeries("Compl�mentaire de "+listPts.getKey());
		int indiceParcoursSef;
		
		for(indiceParcoursSef=0; indiceParcoursSef < listPts.getItemCount();indiceParcoursSef++){
			comp.add(listPts.getX(indiceParcoursSef).doubleValue(), 1 - listPts.getY(indiceParcoursSef).doubleValue());
			//System.out.println("Indice :"+indiceParcoursSef);
			//System.out.println("Point : "+listPts.getX(indiceParcoursSef).doubleValue()+","+listPts.getY(indiceParcoursSef).doubleValue()+"\n");
		}
		
		return new SEF(inf, sup,comp);
	}
}
