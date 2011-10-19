package manipSef;

import java.util.ArrayList;

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
		nbValDiscretes=2000;
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
	
	public XYSeriesCollection createMySefCollection(){
		XYSeriesCollection myListOfSefs=new XYSeriesCollection();
		double[]mesNvxX = xDiscretized();
		double pas=this.getPasX();
		System.out.println(pas);
		int noSef=1;
		double a, b, xDroite, xGauche, xDiscret;
		int indicePoint;
		int indiceXdiscret=0;
		System.out.println("J'ai "+mesSef.size()+" sous ensemble flou dans ma liste!\n");
		for(SEF sef:mesSef){
			//ATTENTION!! Je pars du principe que le sef est ordonné par x croissant!!
			final XYSeries courbe = new XYSeries("Sous ensemble flou "+noSef);
			
			for(indicePoint =0;indicePoint<sef.getInflexions().size()-1;indicePoint++){
				//Je recupere les deux points consecutifs qui caracterisent la portion de droite
				Point pgauche=sef.getInflexions().get(indicePoint);
				Point pdroit=sef.getInflexions().get(indicePoint+1);
				xGauche=pgauche.getX();
				xDroite=pdroit.getX();//me servira pour savoir si je suis encore dans la portion de droite consideree
				
				//Je stocke les coefficients caracteristiques de l'equation de droite
				a=(pgauche.getY()-pdroit.getY())/(pgauche.getX()-xDroite);
				b=pdroit.getY()-a*pdroit.getX();
				do{
					xDiscret = mesNvxX[indiceXdiscret];
					if((xDiscret<xGauche)||(xDiscret > xDroite)){
						courbe.add(xDiscret,0);
						System.out.println("Je traite le point "+indicePoint);
						System.out.println("x: "+xDiscret+", y: 0\n");
					}else{
						courbe.add(xDiscret, xDiscret*a +b);

						System.out.println("Je traite le point "+indicePoint);
						System.out.println("x: "+xDiscret+", y: "+(xDiscret*a + b));
					}
					
					xDiscret+= pas;
					indiceXdiscret++;
				
					
				}while(xDiscret<xDroite);
				//System.out.println("J'ai traité le point "+indicePoint);
				
			}
			for(int i=indiceXdiscret;i<nbValDiscretes;i++){
				courbe.add(mesNvxX[i],0);
			}
			noSef++;
			System.out.println("Il y a "+courbe.getItemCount()+" points dans ma liste de points de ma courbe");
			myListOfSefs.addSeries(courbe);
		}
		return myListOfSefs;
	}
}
