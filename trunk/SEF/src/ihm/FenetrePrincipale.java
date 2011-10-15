package ihm;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import manipFile.Charger;
import manipFile.Filtre;
import manipFile.Sauver;




/**
 * 
 * @author Frederic
 *
 */


public class FenetrePrincipale extends JFrame implements ActionListener{

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
	
	private String fichierouvert;
	
	JFileChooser fc = new JFileChooser();
	
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
		valider.addActionListener(this);
		generer.addActionListener(this);
		charger.addActionListener(this);
		sauver.addActionListener(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	public void pointEntree(String borneinf, String borneSup, String point) {
		// Bon y a moyen de tout mettre dans le meme append mais je prefere comme ca :
		sef_stocker.append("SEF :");
		sef_stocker.append("\n==========");
		sef_stocker.append("\nborne inf : "+borneinf);
		sef_stocker.append("\nborne sup : "+borneSup);
		sef_stocker.append("\npoint(s) :\n"+point);
		sef_stocker.append("\n---------------------\n");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		if(arg0.getSource()==valider){
			System.out.println("click sur valider");
			System.out.println(entreBorneInf.getText());
			System.out.println(sef_entrer.getText());
			pointEntree(entreBorneInf.getText(), entreBorneSup.getText(),sef_entrer.getText());
			
		}
		
		if(arg0.getSource()==generer){
			System.out.println("click sur generer");
			FenetreOnglet fo = new FenetreOnglet();
		}
		
		if(arg0.getSource()==charger){
			System.out.println("click sur charger");
			fc.setFileFilter(new Filtre());
			 int returnVal = fc.showOpenDialog(this);
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
			int returnVal = fc.showSaveDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("Fichier a ecrire : " +
			             fc.getSelectedFile().getName());
				String nom = fc.getSelectedFile().getName();
				System.out.println("Fichier a ecrire : " +
			             fc.getSelectedFile().getAbsolutePath());
				String chemin = fc.getSelectedFile().getAbsolutePath();
				
				String envoie = sef_stocker.getText();
				
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
