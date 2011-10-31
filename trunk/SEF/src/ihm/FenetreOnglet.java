package ihm;

import java.awt.Checkbox;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel; //peut etre besoin rapidement
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import manipSef.SEF;

import controllers.ControllerFenetreOnglet;



// Pas fini, encore des bugs a traiter.
public class FenetreOnglet extends JFrame{
	
	private static final String txtNorme = "Choisissez la Tnorme";
	private static final String txtCNorme = "Choisissez la T-conorme";
	private static final String txtFonction = "Choisissez la fonction";
	
	private static final String txtChoix0 = "Selectionnez le SEF";
	private static final String txtChoix1 = "Selectionnez le SEF n°1";
	private static final String txtChoix2 = "Selectionnez le SEF n°2";
	
	private static final String tracer = "Tracer";
	private static final String borneInf = "Borne inf";	
	private static final String borneSup = "Borne Sup";
	
	private JTabbedPane onglets = new JTabbedPane();
	private JLabel warning = 
		new JLabel("Attention ! cochez plusieurs cases ouvrira " +
				"plusieurs fenetres de dessins");
	
	private Box boxCompGauche = Box.createVerticalBox();
	private Box boxCompDroite = Box.createVerticalBox();
	private Box boxInter = Box.createVerticalBox();
	private Box boxUni = Box.createVerticalBox();
	private Box boxFonc = Box.createVerticalBox();
	
	
	private JComboBox sefComp = new JComboBox();
	
	//Intersection
	private JComboBox sefChoixinter1 = new JComboBox();
	private JComboBox sefChoixinter2 = new JComboBox();
	private JComboBox choixTnorme = new JComboBox();

	//Union
	private JComboBox sefChoixUni1 = new JComboBox();
	private JComboBox sefChoixUni2 = new JComboBox();
	private JComboBox choixTconorme = new JComboBox();
	
	//Extention
	private JComboBox choixFontion = new JComboBox();
	private JComboBox ChoixFoncSef = new JComboBox();
	
	//Bouton pour valider la trace (je vais metre un bouton par panel laissant ainsi le choix)
	private JButton TraceComp = new JButton(tracer);
	private JButton TraceInter = new JButton(tracer);
	private JButton TraceUni = new JButton(tracer);
	private JButton TraceExt = new JButton(tracer);

	// l'ArrayList 
	private ArrayList<SEF> mesSEF;
	
	// gestion de l'affichage sur Complémentaire
	private JTextField donneesBorneInf; // = new JTextField();
	private JTextField donneesBorneSup;
	
	
	public FenetreOnglet(ArrayList<SEF> mesSEF){
		setTitle("Choisir les courbes a generer");
		setSize(430,300);
		this.mesSEF = mesSEF;
		
		donneesBorneInf = new JTextField();
		donneesBorneInf.setEditable(false);
		donneesBorneSup = new JTextField();
		donneesBorneSup.setEditable(false);
		
		//Onglet COmplementaire :
		// supprimer checkbox et remplacer par un combobox avec les sef
		JPanel ongletComp = new JPanel();
		JLabel choixSEFCompl = new JLabel(txtChoix0);
		
		//Autre JLabel
		JLabel labelborneInf = new JLabel(borneInf);
		JLabel lableborneSup = new JLabel(borneSup);
		
		boxCompGauche.add(choixSEFCompl);
		boxCompDroite.add(labelborneInf);
		
		for (int i = 0; i < mesSEF.size(); i++){
			sefComp.addItem("SEF "+(i+1));
		}
		
		boxCompGauche.add(sefComp);
		
		Box total = Box.createHorizontalBox();
		
	//	boxInfobornes.add(labelborneInf);
	//	boxInfobornes.add(donneesBorneInf);
	//	boxInfobornes.add(donneesBorneSup);
		
		boxCompDroite.add(donneesBorneInf);
		boxCompDroite.add(donneesBorneSup);
		
	//	boxComp.add(boxInfobornes);
		
		boxCompGauche.add(TraceComp);
	//	ongletComp.add(boxComp);
	//	ongletComp.add(boxInfobornes);
		total.add(boxCompGauche);
		total.add(boxCompDroite);
		ongletComp.add(total);
		
		// onglet intersection :
		// 3 combox 1 choix Tnorme 2 pour les sefs 
		JPanel ongletInter = new JPanel();
		JLabel choixLabelTnorme = new JLabel(txtNorme);
		JLabel choixLabelTnormeSEF1 = new JLabel(txtChoix1);
		JLabel choixLabelTnormeSEF2 = new JLabel(txtChoix2);
		
		choixTnorme.addItem("Zadeh");
		choixTnorme.addItem("Lukasiewicz");
		choixTnorme.addItem("probabiliste");
		//choixTnorme.addItem("Weber");
		
		// Faut aussi remplir les combobox contenant les SEF
		
		boxInter.add(choixLabelTnorme);
		boxInter.add(choixTnorme);
		boxInter.add(choixLabelTnormeSEF1);
		boxInter.add(sefChoixinter1);
		boxInter.add(choixLabelTnormeSEF2);
		boxInter.add(sefChoixinter2);
		boxInter.add(TraceInter);
	//	boxtn.add(warning);
		ongletInter.add(boxInter);
		
		// onglet union
		// idem mais avec Tconorme
		JPanel ongletUni = new JPanel();
		JLabel choixLabelTconorme = new JLabel(txtCNorme);
		JLabel choixLabelTconormeSEF1 = new JLabel(txtChoix1);
		JLabel choixLabelTconormeSEF2 = new JLabel(txtChoix2);
		
		choixTconorme.addItem("Zadeh");
		choixTconorme.addItem("Lukasiewicz");
		choixTconorme.addItem("probabiliste");
		
		boxUni.add(choixLabelTconorme);
		boxUni.add(choixTconorme);
		boxUni.add(choixLabelTconormeSEF1);
		boxUni.add(sefChoixUni1);
		boxUni.add(choixLabelTconormeSEF2);
		boxUni.add(sefChoixUni2);
		boxUni.add(TraceUni);
	//	
	//	boxtcn.add(warning);
		ongletUni.add(boxUni);
		
		// Liste de fonctions à appliquer en combobox
		// choix du sef
		// 2 combobox donc
		JPanel ongletFonction = new JPanel();
		JLabel choixLabelFonction = new JLabel(txtFonction);
		JLabel choixLabelSef = new JLabel(txtChoix0);
		boxFonc.add(choixLabelFonction);
		boxFonc.add(choixFontion);
		boxFonc.add(choixLabelSef);
		boxFonc.add(ChoixFoncSef);
		boxFonc.add(TraceExt);
		
		ongletFonction.add(boxFonc);

		
		Container contenu = getContentPane();
		
		onglets.addTab("Complementaire", ongletComp);
		onglets.addTab("Intersection", ongletInter);
		onglets.addTab("Union", ongletUni);
		onglets.addTab("Extension", ongletFonction);
		
		contenu.add(onglets);
		setResizable(false);
		setVisible(true);
		
		ControllerFenetreOnglet cfo = new ControllerFenetreOnglet(this);
		
	}


	public JButton getTraceComp() {
		return TraceComp;
	}


	public JButton getTraceInter() {
		return TraceInter;
	}


	public JButton getTraceUni() {
		return TraceUni;
	}


	public JButton getTraceExt() {
		return TraceExt;
	}


	public JComboBox getSefComp() {
		return sefComp;
	}


	public JComboBox getSefChoixinter1() {
		return sefChoixinter1;
	}


	public JComboBox getSefChoixinter2() {
		return sefChoixinter2;
	}


	public JComboBox getSefChoixUni1() {
		return sefChoixUni1;
	}


	public JComboBox getSefChoixUni2() {
		return sefChoixUni2;
	}


	public ArrayList<SEF> getMesSEF() {
		return mesSEF;
	}


	public JTextField getDonneesBorneinf() {
		return donneesBorneInf;
	}


	public JTextField getDonneesBorneSup() {
		return donneesBorneSup;
	}
	
	
	
}
