package controllers;

import ihm.FenetreOnglet;
import ihm.FenetrePrincipale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import org.jfree.data.xy.XYSeries;

import manipFile.Charger;
import manipFile.Filtre;
import manipFile.Sauver;
import manipSef.SEF;

public class ControllerFenetrePincipale implements ActionListener {

	private FenetrePrincipale fp;
	private JFileChooser fc;
	
	private JButton generer;
	private JButton valider;
	private JMenuItem charger;
	private JMenuItem sauver;
	private String fichierouvert;
	
	public ControllerFenetrePincipale(FenetrePrincipale fp){
		this.fp = fp;
		generer = fp.getGenerer();
		generer.addActionListener(this);
		
		valider = fp.getValider();
		valider.addActionListener(this);
		
		charger = fp.getCharger();
		charger.addActionListener(this);
		
		sauver = fp.getSauver();
		sauver.addActionListener(this);
		fc = new JFileChooser();
		
	}

	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==valider){
			System.out.println("click sur valider");
			System.out.println(fp.getEntreBorneInf().getText());
			System.out.println(fp.getEntreBorneSup().getText());
			fp.pointEntree(fp.getEntreBorneInf().getText(), 
					fp.getEntreBorneSup().getText(),fp.getSef_entrer().getText());
			// cree un SEF avec nouveau parser
			
		}
		
		if(arg0.getSource()==generer){
			System.out.println("click sur generer");
			ArrayList<SEF> mesSEF = new ArrayList<SEF> ();
			//test de remplissage de combobox
			SEF sef1 = new SEF(-30, 60, new XYSeries("sef1"));
			sef1.getInflexions().add(-1.5,0);
			sef1.getInflexions().add(1.25,1);
			sef1.getInflexions().add(4.2,0.75);
			sef1.getInflexions().add(5,0);
			
			SEF sef2 = new SEF(-40, 70, new XYSeries("sef2"));
			sef2.getInflexions().add(-1.5,0);
			sef2.getInflexions().add(1.25,1);
			sef2.getInflexions().add(4.2,0.75);			
			
			//SEF essai = new SEF(binf,bsup, pts);
			mesSEF.add(sef1);
			mesSEF.add(sef2);
			
			FenetreOnglet fo = new FenetreOnglet(mesSEF);
		}
		
		if(arg0.getSource()==charger){
			System.out.println("click sur charger");
			fc.setFileFilter(new Filtre());
			 int returnVal = fc.showOpenDialog(fp);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    // Juste un affichage pour verifier
			       System.out.println("Fichier a ouvrir : " +
			             fc.getSelectedFile().getAbsolutePath());
			       fichierouvert = fc.getSelectedFile().getAbsolutePath();
			       Charger ch = new Charger();
			       ch.chargerfichier(fichierouvert);
			       // recupere les points avec getmesSEF

			     }
			 
		}
		
		if(arg0.getSource()==sauver){
			System.out.println("click sur sauvegarder");
			fc.setFileFilter(new Filtre());
			int returnVal = fc.showSaveDialog(fp);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("Fichier a ecrire : " +
			             fc.getSelectedFile().getName());
				String nom = fc.getSelectedFile().getName();
				System.out.println("Fichier a ecrire : " +
			             fc.getSelectedFile().getAbsolutePath());
				String chemin = fc.getSelectedFile().getAbsolutePath();
				//pas plutot sef_stocker ?
				String envoie = fp.getSef_stocker().getText();
				
				Sauver sv = new Sauver();
				try {
					sv.infoFichier(chemin,envoie);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
	
		
	}
	
}

