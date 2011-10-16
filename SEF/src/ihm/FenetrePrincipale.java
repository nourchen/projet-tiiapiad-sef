package ihm;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import controllers.ControllerFenetrePincipale;


import manipFile.Charger;
import manipFile.Filtre;
import manipFile.Sauver;




/**
 * 
 * @author Frederic
 *
 */


public class FenetrePrincipale extends JFrame {

	private JMenuBar menubar = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier");
	
	private JMenuItem charger = new JMenuItem("Charger ...");
	private JMenuItem sauver  = new JMenuItem("Sauvegarder...");
	
	private JTextArea sef_entrer = new JTextArea();
	private JTextArea sef_stocker = new JTextArea();
	
	private JButton generer = new JButton("Generer");
	private JButton valider = new JButton("Valider");
	
	private JLabel ptsSEF = new JLabel("Entrez les Pts du sef :");
	private JLabel borneinf = new JLabel("Borne Inf : ");
	private JLabel bornesup = new JLabel("Borne Sup :");
	private JLabel ptsEntrer = new JLabel("Point déjà entrés :");
	
	private JTextField entreBorneInf = new JTextField();
	private JTextField entreBorneSup = new JTextField();
	
	private Box toutborneinf = Box.createHorizontalBox();
	private Box toutbornesup = Box.createHorizontalBox();
	private Box toutptsSEF = Box.createVerticalBox();
	private Box total = Box.createVerticalBox();
	private Box zonestock = Box.createVerticalBox();
	
	private Box blocSEF_entrer = Box.createHorizontalBox();
	private Box blocSEF_stocker = Box.createHorizontalBox();
	
	private Box letout = Box.createHorizontalBox();
	private Box letoutmenubar = Box.createVerticalBox();
	
//	private BorderLayout fond = new BorderLayout();
	
	
	public FenetrePrincipale() {
		setBounds(200,100, 500, 500); // a verfier sur d'autres résolutions
		setTitle("S.E.F");
		BorderLayout fond = new BorderLayout();
		Box test = Box.createHorizontalBox();
		menubar.add(fichier);
		
		fichier.add(charger);
		fichier.add(sauver);
		
		//sef_stocker.setEditable(false);
		Container contenu = getContentPane();
		JScrollPane scrollPane = new JScrollPane(sef_stocker);
	    scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    
	    JScrollPane entreepoint = new JScrollPane(sef_entrer);
	    entreepoint.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    
	    sef_entrer.setWrapStyleWord(true);
	    sef_entrer.setLineWrap(true);
		//sef_entrer
		
		//Forcer la taille sinon bug d'affichage :
		 Dimension dsef_entrer = new Dimension(230, 190);
	        sef_entrer.setSize(dsef_entrer) ;
	        sef_entrer.setMaximumSize(dsef_entrer) ;
	        sef_entrer.setMinimumSize(dsef_entrer) ;
	    
	     Dimension dborneinf = new Dimension(140,20);
	     	entreBorneInf.setSize(dborneinf);
	     	entreBorneInf.setMaximumSize(dborneinf);
	     	entreBorneInf.setMinimumSize(dborneinf);
	     	
	     //Dimension dborneinf = new Dimension(140,140);
	     	entreBorneSup.setSize(dborneinf);
	     	entreBorneSup.setMaximumSize(dborneinf);
	     	entreBorneSup.setMinimumSize(dborneinf);
	    	
	     	
	     
	     	Dimension dsef_stocker = new Dimension(250,410);
	     	sef_stocker.setSize(dsef_stocker);
	        sef_stocker.setMaximumSize(dsef_stocker);
	        sef_stocker.setMinimumSize(dsef_stocker);
	        
		///////////////////////////////////////////////////
	    	
	     	
	   
		// zone gauche qui contient la zone de texte et le bouton generer :
	    zonestock.add(ptsEntrer);
		blocSEF_entrer.add(scrollPane);
		zonestock.add(blocSEF_entrer);
		zonestock.add(generer);
		
		// zone qui contient borne inf
		toutborneinf.add(borneinf);
		toutborneinf.add(entreBorneInf);
		
		// zone qui contient borne sup
		toutbornesup.add(bornesup);
		toutbornesup.add(entreBorneSup);
		
		//zone qui contient la zone d'entrer des points
		toutptsSEF.add(ptsSEF);
		toutptsSEF.add(sef_entrer);
		toutptsSEF.add(valider);
		
		//Zone qui contient le tout de CENTER:
		total.add(toutborneinf);
		total.add(toutbornesup);
		total.add(toutptsSEF);
		
		//contenu.add(menubar);
		letout.add(zonestock);
		Box.createVerticalGlue();
		letout.add(total);
		Box.createVerticalGlue();
		contenu.add(menubar,fond.NORTH);
		contenu.add(letout,fond.CENTER);
		//letoutmenubar.add(letout);
		//contenu.add(letout);
		setResizable(false);
		setVisible(true);
		
		
		sef_stocker.setEditable(false);
		
		
		//ActionListener
//		valider.addActionListener(this);
//		generer.addActionListener(this);
//		charger.addActionListener(this);
//		sauver.addActionListener(this);
		ControllerFenetrePincipale cfp = new ControllerFenetrePincipale(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	public void pointEntree(String borneinf, String borneSup, String point) {
		// Bon y a moyen de tout mettre dans le meme append mais je prefere comme ca :
		point = point.replaceAll("\n", ";\n");
		sef_stocker.append("SEF :");
		sef_stocker.append("\n==========");
		sef_stocker.append("\nborne inf : "+borneinf);
		sef_stocker.append("\nborne sup : "+borneSup);
		sef_stocker.append("\npoint(s) :\n"+point);
		sef_stocker.append("\n==========\n");
	}
	public JMenuItem getCharger() {
		return charger;
	}
	public JMenuItem getSauver() {
		return sauver;
	}
	public JButton getGenerer() {
		return generer;
	}
	public JButton getValider() {
		return valider;
	}
	public JTextArea getSef_entrer() {
		return sef_entrer;
	}
	public JTextArea getSef_stocker() {
		return sef_stocker;
	}
	public JTextField getEntreBorneInf() {
		return entreBorneInf;
	}
	public JTextField getEntreBorneSup() {
		return entreBorneSup;
	}
	public void setSef_entrer(JTextArea sefEntrer) {
		sef_entrer = sefEntrer;
	}

		
	
	
}
