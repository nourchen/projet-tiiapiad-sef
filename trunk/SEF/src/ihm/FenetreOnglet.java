package ihm;

import java.awt.Checkbox;
import java.awt.Container;

import javax.swing.Box;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel; //peut etre besoin rapidement
import javax.swing.JTabbedPane;

// Pas fini, encore des bugs a traiter.
public class FenetreOnglet extends JFrame{
	
	private JTabbedPane onglets = new JTabbedPane();
	private JLabel warning = 
		new JLabel("Attention ! cochez plusieurs cases ouvrira " +
				"plusieurs fenetres de dessins");
	
	private Box boxComp = Box.createVerticalBox();
	private Box boxInter = Box.createVerticalBox();
	private Box boxUni = Box.createVerticalBox();
	
	
	private JComboBox sefComp = new JComboBox();
	
	//Intersection
	private JComboBox sefChoixinter1 = new JComboBox();
	private JComboBox sefChoixinter2 = new JComboBox();
	private JComboBox choixTnorme = new JComboBox();

	//Union
	private JComboBox sefChoixUni1 = new JComboBox();
	private JComboBox sefChoixUni2 = new JComboBox();
	private JComboBox choixTconorme = new JComboBox();

	
	public FenetreOnglet(){
		setTitle("Choisir les courbes a generer");
		setSize(430,300);
		
		//Onglet COmplementaire :
		// supprimer checkbox et remplacer par un combobox avec les sef
		JPanel ongletComp = new JPanel();
		//boxh.add(warning);
		//boxope.add(warning);
		boxComp.add(sefComp);
		//ongletOperation.add(boxh);
		ongletComp.add(boxComp);
		
		// onglet intersection :
		// 3 combox 1 choix Tnorme 2 pour les sefs 
		JPanel ongletInter = new JPanel();
		boxInter.add(choixTnorme);
		boxInter.add(sefChoixinter1);
		boxInter.add(sefChoixinter2);
	//	boxtn.add(warning);
		ongletInter.add(boxInter);
		
		// onglet union
		// idem mais avec Tconorme
		JPanel ongletUni = new JPanel();
		boxUni.add(choixTconorme);
		boxUni.add(sefChoixUni1);
		boxUni.add(sefChoixUni2);
	//	
	//	boxtcn.add(warning);
		ongletUni.add(boxUni);
		
		// Liste de fonctions à appliquer en combobox
		// choix du sef
		// 2 combobox donc
		JPanel ongletFonction = new JPanel();
		
		Container contenu = getContentPane();
		
		onglets.addTab("Complementaire", ongletComp);
		onglets.addTab("Intersection", ongletInter);
		onglets.addTab("Union", ongletUni);
		onglets.addTab("Extension", ongletFonction);
		
		contenu.add(onglets);
		setResizable(false);
		setVisible(true);
		
	}
}
