package manipSef;

import java.util.ArrayList;

import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/**
 * *La classe depuis laquelle on pourra
 * => Recuperer tous les sous ensembles flous
 * => Les normaliser (les mettre sous forme de XYSeries, affichables par JFreeChart
 * => Effectuer des operations sur ceux ci
 * @author Sylvia Vieira
 *
 */
public class SefManager {

	private ArrayList<SEF> mesSef;
	private int minX;
	private int maxX;
	private final int nbValDiscretes;
	
	public SefManager(ArrayList<SEF> mS,double min, double max){
		this.mesSef=mS;
		this.maxX=(int)Math.ceil(max);
		this.minX=(int)Math.floor(min);		
		nbValDiscretes=10000;
	}
	
	private double getPasX(){
		//System.out.println(maxX-minX);
		//System.out.println(nbValDiscretes - 1);
		return (double)(maxX -minX)/(nbValDiscretes-1);
	}
	
	private double[] xDiscretized(){
		double[] liste = new double[nbValDiscretes];
		double pas=getPasX();
		double xToAdd=minX;
		for(int i=0;i<nbValDiscretes;i++){
			liste[i]=xToAdd;
			xToAdd+=pas;
			//System.out.println(liste [i]);
		}
		
		return liste;
	}
	/**
	 * Methode permettant la creation de la collection (genre de liste)
	 * de mes sefs normalises selon l'intervalle des x discretises
	 * @return
	 */
	public XYSeriesCollection createMySefCollection(){
		/*
		 * Fix Bug => Dans les cas ou la liste des sous ensembles flous
		 * en contient plusieurs, il y a un souci avec la discretisation...
		 * BUG FIXED!!!
		 */
		
		//Creation d'un nouvel objet collection, vide, que l'on va remplir de XYSeries...
		XYSeriesCollection myListOfSefs=new XYSeriesCollection();
		//Tableau representant la liste des abscisses discretisees
		double[]mesNvxX = xDiscretized();
		double pas=this.getPasX();
		//System.out.println(pas);
		int noSef=1;
		double a, b, xDroite, xGauche, xDiscret;
		int indicePoint;
		int indiceXdiscret;
		//System.out.println("J'ai "+mesSef.size()+" sous ensemble flou dans ma liste!\n");
		for(SEF sef:mesSef){
			//ATTENTION!! Je pars du principe que le sef est ordonné par x croissant!!
			final XYSeries courbe = new XYSeries("Sous ensemble flou "+noSef);
			indiceXdiscret=0;
			
			for(indicePoint =0;indicePoint<sef.getInflexions().getItemCount()-1;indicePoint++){
				//Je recupere les deux points consecutifs qui caracterisent la portion de droite
				XYDataItem pgauche=sef.getInflexions().getDataItem(indicePoint);
				XYDataItem pdroit=sef.getInflexions().getDataItem(indicePoint+1);
				xGauche=pgauche.getXValue();
				xDroite=pdroit.getXValue();//me servira pour savoir si je suis encore dans la portion de droite consideree
				
				//Je stocke les coefficients caracteristiques de l'equation de droite
				a=(pgauche.getYValue()-pdroit.getYValue())/(xGauche-xDroite);
				b=pdroit.getYValue()-a*pdroit.getXValue();
				do{
					xDiscret = mesNvxX[indiceXdiscret];
					if((xDiscret<xGauche)||(xDiscret > xDroite)){
						courbe.add(xDiscret,0);
						
					}else{
						courbe.add(xDiscret, xDiscret*a +b);

					}
					
					xDiscret+= pas;
					indiceXdiscret++;
				
					
				}while(xDiscret<xDroite);
				
			}
			for(int i=indiceXdiscret;i<nbValDiscretes;i++){
				courbe.add(mesNvxX[i],0);
			}
			noSef++;
			myListOfSefs.addSeries(courbe);
		}
		return myListOfSefs;
	}
}
