package ihm;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controllers.ControllerFenetreSauver;

import manipSef.SEF;

public class FenetreSauver extends JFrame {

	private JList jlist;
	private JButton sauver;
	private String[] total;
	
	
	private ArrayList<SEF> mesSEF;
	
	
	public FenetreSauver(ArrayList<SEF> mesSEF){
		setTitle("Sauvegarder...");
		setSize(300,200);
	
		total = recupNom(mesSEF);
		
		jlist = new JList(total);
		// nb de lignes visbles
		//jlist.setVisibleRowCount( 5 );
		// autorise la selection multiple
		jlist.setSelectionMode( ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
		// ajoute une scrollBar
		JScrollPane spList = new JScrollPane( jlist );
		// ajoute le composant dans la fenêtre
		add( spList, BorderLayout.CENTER );
		
		
		
//		label = new JLabel( " Sélection: [Ctrl]+[click] ou [Shift]+[click] " );
//		add( label, BorderLayout.NORTH );
		
		sauver = new JButton( "Sauver les SEF séléctionnés" );
		add(sauver, BorderLayout.SOUTH );
		setVisible(true);
	//	pack();
		new ControllerFenetreSauver(this,mesSEF);
	}
	
	public String[] recupNom(ArrayList<SEF> mesSEF){
		String[] res = new String[mesSEF.size()];
		for(int i =0; i<mesSEF.size();i++){
			res[i]= (String)mesSEF.get(i).getInflexions().getKey();
		}
		
		return res;
	}

	public JList getjlist() {
		return jlist;
	}

	public JButton getSauver() {
		return sauver;
	}

	public String[] getTotal() {
		return total;
	}

	public ArrayList<SEF> getMesSEF() {
		return mesSEF;
	}
	
	
	
	
}
