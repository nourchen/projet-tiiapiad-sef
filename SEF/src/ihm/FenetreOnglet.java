package ihm;

import java.awt.Checkbox;
import java.awt.Container;

import javax.swing.Box;
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
	
	// Onglet operation ( peut etre trouver un autre nom
	private Checkbox complementaire = new Checkbox("Tracer Le Complementaire");
	private Checkbox intersection = new Checkbox("Tracer L'intersection");
	private Checkbox union = new Checkbox("Tracer L'Union");
	private Box boxope = Box.createVerticalBox();
	private Box boxh = Box.createHorizontalBox();
	
	//onglet Tnorme
	private Checkbox tnZadeh = new Checkbox("T-Norme de Zadeh");
	private Checkbox tnLuka = new Checkbox("T-Norme de Lukasuexicz");
	private Checkbox tnProba = new Checkbox("T-Norme Probabiliste");
	private Checkbox tnWeber = new Checkbox("T-Norme de Weber");
	private Box boxtn = Box.createVerticalBox();
	
	//onglet T-conorme
	private Checkbox tcnZadeh = new Checkbox("T-Conorme de Zadeh");
	private Checkbox tcnLuka = new Checkbox("T-Conorme de Lukasuexicz");
	private Checkbox tcnProba = new Checkbox("T-Conorme Probabiliste");
	private Checkbox tcnWeber = new Checkbox("T-Conorme de Weber");
	private Box boxtcn = Box.createVerticalBox();
	

	public FenetreOnglet(){
		setTitle("Choisir les courbes a generer");
		setSize(430,300);
		
		//Onglet COmplementaire :
		// supprimer checkbox et remplacer par un combobox avec les sef
		JPanel ongletOperation = new JPanel();
	//	boxh.add(warning);
	//	boxope.add(warning);
		boxope.add(complementaire);
		boxope.add(intersection);
		boxope.add(union);
		ongletOperation.add(boxh);
		ongletOperation.add(boxope);
		
		// onglet intersection :
		// 3 combox 1 choix Tnorme 2 pour les sefs 
		JPanel ongletTnorme = new JPanel();
	//	boxtn.add(warning);
		boxtn.add(tnZadeh);
		boxtn.add(tnLuka);
		boxtn.add(tnProba);
		boxtn.add(tnWeber);
		ongletTnorme.add(boxtn);
		
		// onglet union
		// idem mais avec Tconorme
		JPanel ongletTconorme = new JPanel();
	//	boxtcn.add(warning);
		boxtcn.add(tcnZadeh);
		boxtcn.add(tcnLuka);
		boxtcn.add(tcnProba);
		boxtcn.add(tcnWeber);
		ongletTconorme.add(boxtcn);
		
		// Liste de fonctions à appliquer en combobox
		// choix du sef
		// 2 combobox donc
		JPanel ongletFonction = new JPanel();
		
		Container contenu = getContentPane();
		
		onglets.addTab("Complementaire", ongletOperation);
		onglets.addTab("Intersection", ongletTnorme);
		onglets.addTab("Union", ongletTconorme);
		onglets.addTab("Extension", ongletFonction);
		
		contenu.add(onglets);
		setResizable(false);
		setVisible(true);
		
	}
}
