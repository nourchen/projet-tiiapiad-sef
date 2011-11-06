package controllers;

import ihm.FenetreSauver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import manipFile.Filtre;
import manipFile.Sauver;
import manipSef.SEF;

/**
 * Classe permetttant de gérer tout les evenements de click dans la fenetre Sauver
 * @author Frederic
 *
 */
public class ControllerFenetreSauver implements ActionListener, ListSelectionListener {

	private JButton sauver;
	private FenetreSauver fs;
	private String[] total;
	private Object jlist;
	private ListSelectionModel listSelectionModel;
	private ArrayList<Integer> sefchoisi = new ArrayList<Integer> ();
	private ArrayList<SEF> sefpris;
	private ArrayList<SEF> mesSEF;
	private JFileChooser fc;
	
	/**
	 * Constructeur faisant le lien entre le controlleur et la fenetre sauver fs et
	 * remplit la fenetre avec le contenu ACTUEL de l'arraylist de SEF
	 * @param fs
	 * @param mesSEF
	 */
	public ControllerFenetreSauver(FenetreSauver fs,ArrayList<SEF> mesSEF){
		this.fs = fs;
		this.sauver = fs.getSauver();
		this.total = fs.getTotal();
		this.jlist = fs.getjlist();
		this.mesSEF = mesSEF;
		sauver.addActionListener(this);
		listSelectionModel = fs.getjlist().getSelectionModel();
		listSelectionModel.addListSelectionListener(this);
		sefpris = new ArrayList<SEF> ();
		fc = new JFileChooser("./fichiersSef");
		fc.setFileFilter(new Filtre());
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == sauver){
			System.out.println("click sur Sauver");
			System.out.println( "Votre sélection:" );
			System.out.println("taille messef "+mesSEF.size());
		//	System.out.println(""+((JList)couleurJList).getSelectedIndices().length);
			int txt = ((JList)jlist).getSelectedIndices().length;
			for (int i = 0; i < sefchoisi.size();i++){
				System.out.println("choisi au clic "+sefchoisi.get(i));
			sefpris.add(mesSEF.get(sefchoisi.get(i)));			
			}
			
			int returnVal = fc.showSaveDialog(fs);
			
			if(returnVal == JFileChooser.APPROVE_OPTION){
				String chemin = fc.getSelectedFile().getAbsolutePath();
			
			
			Sauver sv = new Sauver();
			try {
				sv.infoFichier(chemin,sefpris);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Erreur fichier");
				e.printStackTrace();
				return;
			}
		 fs.dispose();
		 }
		}
		
	}

	// Ecouteur sur les click dans la JList
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("on est la");
		//On vide la liste a chaque fois sinon on conserve les SEF déselectionnées
		sefchoisi.clear();
		ListSelectionModel lsm = (ListSelectionModel)arg0.getSource();
		int minIndex = lsm.getMinSelectionIndex();
        int maxIndex = lsm.getMaxSelectionIndex();
        for (int i = minIndex; i <= maxIndex; i++) {
            if (lsm.isSelectedIndex(i)) {
               // System.out.println(" " + i);
                sefchoisi.add(i);
            }
		
	}
	}
}
