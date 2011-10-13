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
		new JLabel("Attention ! cochez plusieurs cases ouvrira plusieurs fenetres de dessins");
	
	// Onglet operation ( peut etre trouver un autre nom
	private Checkbox complementaire = new Checkbox("Tracer Le Complementaire");
	private Checkbox intersection = new Checkbox("Tracer L'intersection");
	private Checkbox union = new Checkbox("Tracer L'Union");
	private Box boxope = Box.createVerticalBox();
	
	//onglet Tnorme
	private Checkbox tnZadeh = new Checkbox("T-Norme de Zadeh");
	private Checkbox tnLuka = new Checkbox("T-Norme de Lukasuexicz");
	private Checkbox tnProba = new Checkbox("T-Norme Probabiliste");
	private Checkbox tnWeber = new Checkbox("T-Norme de Weber");
	private Box boxtn = Box.createVerticalBox();
	

	public FenetreOnglet(){
		setTitle("Choisir les courbes a generer");
		setSize(400,300);
		
		//Onglet operation
		JPanel ongletOperation = new JPanel();
		boxope.add(complementaire);
		boxope.add(intersection);
		boxope.add(union);
		ongletOperation.add(boxope);
		
		// onglet Tnorme
		JPanel ongletTnorme = new JPanel();
		boxtn.add(tnZadeh);
		boxtn.add(tnLuka);
		boxtn.add(tnProba);
		boxtn.add(tnWeber);
		ongletOperation.add(boxtn);
		
		Container contenu = getContentPane();
		
		onglets.addTab("Operation", ongletOperation);
		onglets.addTab("T-norme", ongletTnorme);
		onglets.addTab("T-conorme", null);
		onglets.addTab("Fonction", null);
		
		contenu.add(onglets);
		setResizable(false);
		setVisible(true);
		
	}
}
