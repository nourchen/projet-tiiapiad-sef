package controllers;

import ihm.FenetreOnglet;
import ihm.FenetrePrincipale;
import ihm.FenetreSauver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import org.jfree.data.xy.XYSeries;

import manipFile.Charger;
import manipFile.Filtre;
import manipFile.Sauver;
import manipSef.SEF;
/**
 * Classe permetttant de g�rer tout les evenements de click dans la fenetre principale
 * @author Frederic
 *
 */
public class ControllerFenetrePincipale implements ActionListener {

	private FenetrePrincipale fp;
	private JFileChooser fc;
	
	private JButton generer;
	private JButton valider;
	private JButton ajouterpts;
	private JMenuItem charger;
	private JMenuItem sauver;
	private String fichierouvert;
	
	private ArrayList<SEF> mesSEF;
	
	
	//besoin de ce compteur pour la gestion des points
	private int cpt;
	
	
	/**
	 * Constructeur faisant le lien entre le controlleur et la fenetre principale fp
	 * @param fp
	 */
	public ControllerFenetrePincipale(FenetrePrincipale fp){
		this.fp = fp;
		generer = fp.getGenerer();
		generer.addActionListener(this);
		
		valider = fp.getValider();
		valider.addActionListener(this);
		
		charger = fp.getCharger();
		charger.addActionListener(this);
		
		ajouterpts = fp.getAjouterpts();
		ajouterpts.addActionListener(this);
		
		sauver = fp.getSauver();
		sauver.addActionListener(this);
		fc = new JFileChooser("./fichiersSef");
		
		mesSEF = new ArrayList<SEF> ();
		
		cpt = 0;
	}

	public ArrayList<SEF> getMesSEF() {
		return mesSEF;
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource()==valider){
			System.out.println("click sur valider");
			System.out.println(fp.getEntreBorneInf().getText());
			System.out.println(fp.getEntreBorneSup().getText());
			
			BufferedReader br = new BufferedReader(new StringReader(fp.getSef_entrer().getText()));
			try {

				double binf;
				double bsup;
				String borneinf = fp.getEntreBorneInf().getText();
				String bornesup = fp.getEntreBorneSup().getText();
				
				//Ici le seul infini tol�r� c'est -inf
				if(borneinf.equals("-inf")||borneinf.equals("inf")||borneinf.equals("+inf")){
					binf = Double.MIN_VALUE;
				} else {
					binf = Double.parseDouble(borneinf);    
				}
				// ici le seul infini tol�t� c'est +inf
				if(bornesup.equals("inf") || bornesup.equals("+inf") || bornesup.equals("-inf")) {
					bsup = Double.MAX_VALUE;
				} else {
					bsup = Double.parseDouble(bornesup);
				}
				
				// cr�ation du sef :
				SEF temp = new SEF(binf,bsup,new XYSeries("SEF"+(mesSEF.size()+1)));
				
				String line;
				//Boucle qui permet de remplir les points du sef (hors borne)
				while((line = br.readLine()) != null) {
				String[] tempo = line.split(" ");
				temp.getInflexions().add(Double.parseDouble(tempo[0]),Double.parseDouble(tempo[1]));
				
				}
				
				mesSEF.add(temp);
				//reinitialise les champs
				fp.getSef_entrer().setText("");
				fp.getTfX().setText("");
				fp.getTfY().setText("");
				fp.getEntreBorneInf().setText("");
				fp.getEntreBorneSup().setText("");
				cpt = 0;
				
				fp.getGenerer().setEnabled(true);
				fp.getGenerer().repaint();
				
				//d�activation du bouton
				fp.getValider().setEnabled(false);
				fp.getValider().repaint();
				
				//reactiver les champs des bornes :
				fp.getEntreBorneInf().setEnabled(true);
				fp.getEntreBorneSup().setEnabled(true);
				
			} catch (IOException e) {
				System.out.println("kaboom");
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}  catch (NumberFormatException e){
			//si l'utilisateur entre autrechose que des nombre on r�initialise
				fp.getEntreBorneInf().setEnabled(true);
				fp.getEntreBorneSup().setEnabled(true);
				fp.getSef_entrer().setText("");
				fp.getEntreBorneInf().setText("");
				fp.getEntreBorneSup().setText("");
				cpt = 0;
			return;
			}
			fp.pointEntree(fp.getEntreBorneInf().getText(), 
					fp.getEntreBorneSup().getText(),fp.getSef_entrer().getText());
			
			
			
		}
		
		if(arg0.getSource()==generer){
			System.out.println("click sur generer");
			FenetreOnglet fo = new FenetreOnglet(mesSEF);
		}
		
		if(arg0.getSource()==charger){
			System.out.println("click sur charger");
			fc.setFileFilter(new Filtre());
			 int returnVal = fc.showOpenDialog(fp);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       fichierouvert = fc.getSelectedFile().getAbsolutePath();
			       Charger ch = new Charger(mesSEF,this,fp);
			       ch.chargerfichier(fichierouvert);
			     }
			    if(mesSEF!=null){
					fp.getGenerer().setEnabled(true);
					fp.getGenerer().repaint();
			    }
			 
		}
		
		if(arg0.getSource()==sauver){
			System.out.println("click sur sauvegarder");
			FenetreSauver fs = new FenetreSauver(mesSEF);	
		}
		
		if (arg0.getSource()==ajouterpts){
			System.out.println(" "+!verifieChampEntrer());
			try {
			if (!verifieChampEntrer()){
				double tfy = Double.parseDouble(fp.getTfY().getText());
				if (tfy <= 1){
				System.out.println("ajouter pts");			
				if (cpt == 0){
					cpt++;
					fp.getSef_entrer().append(""+fp.getTfX().getText()+ " "+fp.getTfY().getText());
				} else {
					fp.getSef_entrer().append("\n"+fp.getTfX().getText()+ " "+fp.getTfY().getText());		
				}
				// on vide les champs X , Y
				fp.getTfX().setText("");
				fp.getTfY().setText("");
				// d�active la modification des bornes :
				fp.getEntreBorneInf().setEnabled(false);
				fp.getEntreBorneSup().setEnabled(false);
				fp.getValider().setEnabled(true);
				fp.getValider().repaint();
				}
			}else {
				
			}
			}  catch (NumberFormatException e){
				//si l'utilisateur entre autrechose que des nombre on arrete mais on bloque rien
				return;
			}
		}
		
	
		
	}
	/**
	 * Fonction qui verifie que les champs de saisi ont �t� rempli
	 * @return true si l'un des champs est vide false sinon
	 */
	public boolean verifieChampEntrer(){
		return ( 
				   (fp.getEntreBorneInf().getText().equals("")) 
				|| (fp.getEntreBorneSup().getText().equals("")) 
				|| (fp.getTfX().getText().equals("")) 
				|| (fp.getTfY().getText().equals(""))
				);
	}
	
	
}

