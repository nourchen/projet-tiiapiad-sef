package controllers;

import ihm.FenetreOnglet;
import ihm.FenetrePrincipale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import manipFile.Charger;
import manipFile.Filtre;
import manipFile.Sauver;

public class ControllerFenetrePincipale implements ActionListener {

	private FenetrePrincipale fp;
	private JFileChooser fc = new JFileChooser();
	
	private JButton generer = null;
	private JButton valider = null;
	private JMenuItem charger = null;
	private JMenuItem sauver = null;
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
		
	}

	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==valider){
			System.out.println("click sur valider");
			System.out.println(fp.getEntreBorneInf().getText());
			System.out.println(fp.getEntreBorneSup().getText());
			fp.pointEntree(fp.getEntreBorneInf().getText(), 
					fp.getEntreBorneSup().getText(),fp.getSef_entrer().getText());
			
		}
		
		if(arg0.getSource()==generer){
			System.out.println("click sur generer");
			FenetreOnglet fo = new FenetreOnglet();
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
				
				String envoie = fp.getSef_entrer().getText();
				
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

