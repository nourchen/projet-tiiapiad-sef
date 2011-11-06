package manipSef;

import org.jfree.data.xy.XYSeries;

/**
 * Classe permettant d'effectuer l'opération ensembliste du complémentaire
 * sur un sous ensemble flou donné
 * @author Sylvia Vieira
 *
 */
public final class SefComplement {

	/**
	 * Constructeur vide
	 * Il est présent par défaut, mais il figure ici explicitement
	 * au cas où l'on ajouterait plus tard un constructeur,
	 * qui ferait disparaitre le constructeur vide
	 */
	public SefComplement(){
	}
	/**
	 * Méthode permettant d'effectuer l'opération ensembliste 
	 * du complémentaire sur un sous ensemble flou donné
	 * @param sef1 : le sous ensemble flou à complémenter
	 * @return le sous ensemble flou complémentaire de sef1
	 */
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
