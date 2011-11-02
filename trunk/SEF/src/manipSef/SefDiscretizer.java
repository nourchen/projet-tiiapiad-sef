package manipSef;

import java.util.ArrayList;

import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import exceptions.NormalizationException;

/**
 * Classe permettant de discrétiser un sef ou plusieurs
 * sur un certain intervalle
 * 
 * ---------Cette classe n'est pour le moment plus utilisable, elle sera surement nécessaire -------
 * ---------- (pour l'intersection probabiliste par exemple ou le principe d'extension) ------------
 * ---------- mais pour ce faire il faudra la modifier et la remettre à jour!!! --------------------
 * 
 * => Normaliser les sef 
 * (faire en sorte que les listes de points des deux sef ait la meme taille, pour faciliter l'opération)
 * 
 * @author Sylvia Vieira
 *
 */
public class SefDiscretizer {

	private ArrayList<SEF> mesSef;
	private int minX;
	private int maxX;
	private final int nbValDiscretes;
	
	public SefDiscretizer(ArrayList<SEF> mS,double min, double max){
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
	@Deprecated
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
	
	/**
	 * Cette methode permet de normaliser les deux sef (représentés ici par la liste de leur points d'inflexions respectifs)
	 * En d'autres termes, en sortie, les points d'inflexion des deux sefs seront définis selon les memes abscisses
	 * donc les deux listes de points auront la meme longueur.
	 * @param toNormalize1 : premier sous ensemble flou à normaliser
	 * @param inf borne inferieure du premier sef "toNormalize1"
	 * @param sup borne superieure du premier sef "toNormalize1"
	 * @param toNormalize2 : second sous ensemble flou à normaliser
	 * @param inf2 borne inferieure du second sef "toNormalize2"
	 * @param sup2 borne superieure du second sef "toNormalize2"
	 */
	public static void normalizeSerie(XYSeries toNormalize1,double inf,double sup, 
			XYSeries toNormalize2,double inf2,double sup2) throws NormalizationException{

		double xminRef,xmaxRef,xminRef2,xmaxRef2;
		xminRef=toNormalize2.getMinX();
		xmaxRef=toNormalize2.getMaxX();
		xminRef2=toNormalize1.getMinX();
		xmaxRef2=toNormalize1.getMaxX();

		//On commence par ajouter à chaque sef, si nécessaire
		// des couples de points, de sorte que les points "a l'extremite" des deux sef auront les meme abscisses
		XYDataItem firstPoint = toNormalize1.getDataItem(0);
		XYDataItem lastPoint = toNormalize1.getDataItem(toNormalize1.getItemCount()-1);
		if( xminRef< firstPoint.getXValue()){
			if(xminRef < inf){
				toNormalize1.add(xminRef, 0 );
			}else{
				toNormalize1.add(xminRef, firstPoint.getYValue() );
			}
		}
		if( xmaxRef > lastPoint.getXValue()){
			if(xmaxRef > sup){
				toNormalize1.add(xmaxRef, 0 );
			}else{
				toNormalize1.add(xmaxRef, lastPoint.getYValue() );
			}
		}
		XYDataItem firstPoint2 = toNormalize2.getDataItem(0);
		XYDataItem lastPoint2 = toNormalize2.getDataItem(toNormalize2.getItemCount()-1);
		if( xminRef2< firstPoint2.getXValue()){
			if(xminRef2 < inf2){
				toNormalize2.add(xminRef2, 0 );
			}else{
				toNormalize2.add(xminRef2, firstPoint2.getYValue() );
			}
		}
		if( xmaxRef2 > lastPoint2.getXValue()){
			if(xmaxRef2 > sup2){
				toNormalize2.add(xmaxRef2, 0 );
			}else{
				toNormalize2.add(xmaxRef2, lastPoint2.getYValue() );
			}
		}

		//Balayons maintenant les deux sefs en ajoutant a chacun les points manquants
		int i1,i2;
		i2=1;
		XYDataItem ptG1,ptD1,ptG2,ptD2;
		ptG1 = toNormalize1.getDataItem(0);
		ptG2 = toNormalize2.getDataItem(0);
		ptD1 = toNormalize1.getDataItem(1);
		ptD2 = toNormalize2.getDataItem(1);
		double a1,a2,b1,b2;
		i1=1;
		a2=(ptG2.getYValue()-ptD2.getYValue())/(ptG2.getXValue()-ptD2.getXValue());
		b2=ptD2.getYValue()-a2*ptD2.getXValue();

		while (i1< (toNormalize1.getItemCount()-1) || i2 < (toNormalize2.getItemCount() -1)){
			a1=(ptG1.getYValue()-ptD1.getYValue())/(ptG1.getXValue()-ptD1.getXValue());
			b1=ptD1.getYValue()-a1*ptD1.getXValue();


			while(ptD2.getXValue() < ptD1.getXValue()){
				if(ptD2.getXValue() <= sup){
					toNormalize1.add(ptD2.getXValue(), ptD2.getXValue()* a1 + b1);
				}else{
					toNormalize1.add(ptD2.getXValue(), 0);
				}
				i2++;
				i1++;
				ptG2=ptD2;
				ptD2=toNormalize2.getDataItem(i2);
				a2=(ptG2.getYValue()-ptD2.getYValue())/(ptG2.getXValue()-ptD2.getXValue());
				b2=ptD2.getYValue()-a2*ptD2.getXValue();

			}//xD2 > = xD1
			if(i1 < toNormalize1.getItemCount()-1 && ptD1.getXValue()!=ptD2.getXValue()){
				if(ptD1.getXValue() <= sup2){
					toNormalize2.add(ptD1.getXValue(), ptD1.getXValue()* a2 + b2);
				}else{
					toNormalize2.add(ptD1.getXValue(), 0);
				}
				i1++;
				i2++;
				ptG1=ptD1;
				if(i1<(toNormalize1.getItemCount())){
					ptD1=toNormalize1.getDataItem(i1);	
				}
			}

			if(ptD1.getXValue()==ptD2.getXValue()){
				i1++;
				i2++;
				ptG1=ptD1;
				if(i1<(toNormalize1.getItemCount())){
					ptD1=toNormalize1.getDataItem(i1);	
				}
				ptG2=ptD2;
				if(i1<(toNormalize2.getItemCount())){
					ptD2=toNormalize2.getDataItem(i2);
				}
			}

		}
		if(toNormalize1.getItemCount() != toNormalize2.getItemCount()){
			throw new NormalizationException(
					"La normalisation a échoué, les deux listes ne font pas la meme taille!\n");
		}
	}


	
	
}
