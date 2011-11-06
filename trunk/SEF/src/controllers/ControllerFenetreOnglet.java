package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import ihm.FenetreGeometrique;
import ihm.FenetreOnglet;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import manipSef.SEF;
import manipSef.SefComplement;
import manipSef.SefManager;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import principeExtension.Extension;
import principeExtension.FunctionChoice;

import exceptions.NormalizationException;
import exceptions.UnknownFunctionException;
import exceptions.UnknownNormeException;
import exceptions.UnknownOperationException;

import tools.Norme;
import tools.OperationEnsembliste;

	/**
	 * Classe permetttant de gérer tout les evenements de click dans la fenetre onglet.
	 * @author Frederic
	 *
	 */
public class ControllerFenetreOnglet implements ActionListener,ListSelectionListener {

	private FenetreOnglet fo;
	
	private JButton traceComp;
	private JButton traceInter;
	private JButton traceUni;
	private JButton traceExt;
	private JButton tracerAffSEF;
	private JComboBox sefComp;
	private JComboBox sefChoixinter1;
	private JComboBox sefChoixinter2;
	private JComboBox sefChoixuni1;
	private JComboBox sefChoixuni2;
	private JComboBox sefExt;
	private ArrayList<Integer> sefchoisi = new ArrayList<Integer> ();
	private ArrayList<SEF> sefpris = new ArrayList<SEF> ();
	private ListSelectionModel listSelectionModel;
	
	/**
	 * Constructeur faisant le lien entre le controlleur et la fenetre onglet fo
	 * @param fo
	 */
	public ControllerFenetreOnglet(FenetreOnglet fo){
		this.fo = fo;
		//JButton
		traceComp = fo.getTraceComp();
		traceInter = fo.getTraceInter();
		traceUni = fo.getTraceUni();
		traceExt = fo.getTraceExt();
		tracerAffSEF = fo.getTracerAffSEF();
		//JCombobox
		sefComp = fo.getSefComp();
		sefChoixinter1 = fo.getSefChoixinter1();
		sefChoixinter2 = fo.getSefChoixinter2();
		sefChoixuni1 = fo.getSefChoixUni1();
		sefChoixuni2 = fo.getSefChoixUni2();
		sefExt = fo.getChoixFoncSef();
		
		//Ecouteur d'action bouton
		traceComp.addActionListener(this);
		traceInter.addActionListener(this);
		traceUni.addActionListener(this);
		traceExt.addActionListener(this);
		tracerAffSEF.addActionListener(this);
		//Ecouteur d'action liste déroulante
		sefComp.addActionListener(this);
		sefChoixinter1.addActionListener(this);
		sefChoixinter2.addActionListener(this);
		sefChoixuni1.addActionListener(this);
		sefChoixuni2.addActionListener(this);
		sefExt.addActionListener(this);
		
		//Ecouteur sur la JList
		listSelectionModel = fo.getjListAffSEF().getSelectionModel();
		listSelectionModel.addListSelectionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource()==traceComp){
			System.out.println("comp");
			System.out.println(""+fo.getSefComp().getSelectedIndex());
			int index = fo.getSefComp().getSelectedIndex();
			
			SEF test = fo.getMesSEF().get(index);
			XYSeriesCollection mesSefs= new XYSeriesCollection();
			mesSefs.addSeries(test.getInflexions());
			System.out.println("Borne inf"+test.getBorneInf()+" borne sup"+test.getBorneSup());
			SEF inverse = SefComplement.getComplement(test);
			mesSefs.addSeries(inverse.getInflexions());
			FenetreGeometrique frame = new FenetreGeometrique("Manipulation des Sous Ensembles Flous", mesSefs);
			fo.getMesSEF().add(inverse);
			rajouter(inverse);
			frame.pack();
			RefineryUtilities.centerFrameOnScreen(frame);
			frame.setVisible(true);
			
		}
		
		if(arg0.getSource()==traceInter){
			System.out.println("Inter");
			int indexSEF1 = fo.getSefChoixinter1().getSelectedIndex();
			int indexSEF2 = fo.getSefChoixinter2().getSelectedIndex();
			
			
			SEF sefi1 = fo.getMesSEF().get(indexSEF1);
			SEF sefi2 = fo.getMesSEF().get(indexSEF2);
			XYSeriesCollection mesSefs= new XYSeriesCollection();
			
			int recup = fo.getChoixTnorme().getSelectedIndex();
			Norme norme[] = Norme.values();
				SEF iinterSef;
				try {
						iinterSef = SefManager.getResultOperation(sefi1, sefi2, norme[recup],OperationEnsembliste.INTERSECTION);
						//iinterSef.printInflexions();
						mesSefs.addSeries(iinterSef.getInflexions());
						FenetreGeometrique frame = new FenetreGeometrique("Manipulation des Sous Ensembles Flous", mesSefs);
						//La string passée en param du constructeur est le titre de la fenetre
						frame.pack();//? Que fait cette commande?
						RefineryUtilities.centerFrameOnScreen(frame);
						frame.setVisible(true);
						//Rajout dans mesSEF / Combobox / Jlist
						fo.getMesSEF().add(iinterSef);
						rajouter(iinterSef);
				}
					
				 catch (UnknownNormeException e) {
					System.out.println("La norme n'est pas connue!");
					e.printStackTrace();
					return;
				} catch (UnknownOperationException e) {
					System.out.println("L'operation n'est pas connue!");
					e.printStackTrace();
					return;

				} catch (NormalizationException e) {
					System.out.println("La normalisation n'a pas fonctionné!");
					e.printStackTrace();
					return;
				} catch(NullPointerException e) {
					System.out.println("Les points du SEF calculés sont vide");
					return;
				}
				
				
	}
		
		if(arg0.getSource()==traceUni){
			System.out.println("Uni");
			
			int indexSEF1 = fo.getSefChoixUni1().getSelectedIndex();
			int indexSEF2 = fo.getSefChoixUni2().getSelectedIndex();
			
			SEF sefi1 = fo.getMesSEF().get(indexSEF1);
			SEF sefi2 = fo.getMesSEF().get(indexSEF2);
			XYSeriesCollection mesSefs= new XYSeriesCollection();
			
			int recupuni = fo.getChoixTconorme().getSelectedIndex();
			Norme normeuni[] = Norme.values();
				SEF uniSef;
				try {
					uniSef = SefManager.getResultOperation(sefi1, sefi2, normeuni[recupuni],OperationEnsembliste.UNION);
					mesSefs.addSeries(uniSef.getInflexions());
					FenetreGeometrique frame = new FenetreGeometrique("Manipulation des Sous Ensembles Flous", mesSefs);
					frame.pack();
					RefineryUtilities.centerFrameOnScreen(frame);
					//rajout dans mes sef
					fo.getMesSEF().add(uniSef);
					//rajout dans les combobox et JList
					rajouter(uniSef);
					frame.setVisible(true);
				} catch (UnknownNormeException e) {
					System.out.println("La norme n'est pas connue!");
					e.printStackTrace();
					return;
				} catch (UnknownOperationException e) {
					System.out.println("L'operation n'est pas connue!");
					e.printStackTrace();
					return;

				} catch (NormalizationException e) {
					e.printStackTrace();
					System.out.println("La normalisation n'a pas fonctionné!");
					return;
				} catch(NullPointerException e) {
			System.out.println("Les points du SEF calculés sont vide");
			return;
				}
			}			
			
		if(arg0.getSource()==traceExt){
			if(!(fo.getJtfExtinf().getText().equals("")) && !(fo.getJtaExtsup().getText().equals(""))){
				
				double borneInfEntree = Double.parseDouble(fo.getJtfExtinf().getText());
				double borneSupEntree = Double.parseDouble(fo.getJtaExtsup().getText());
				int indexchoisi = fo.getChoixFoncSef().getSelectedIndex();
				
				SEF sefExt = fo.getMesSEF().get(indexchoisi);
				
				double borneInfSEF = fo.getMesSEF().get(indexchoisi).getBorneInf();
				double pluspetitX = fo.getMesSEF().get(indexchoisi).getInflexions().getMinX();
				
				double borneSupSEF = fo.getMesSEF().get(indexchoisi).getBorneSup();
				double plusgrandX = fo.getMesSEF().get(indexchoisi).getInflexions().getMaxX();
				
				if( (borneInfEntree >= borneInfSEF) && (borneInfEntree <= pluspetitX) 
						&& (borneSupEntree >= plusgrandX) && (borneSupEntree <= borneSupSEF)){
					System.out.println("Ext");
					
					XYSeriesCollection mesSefs= new XYSeriesCollection();
					int recup = fo.getChoixFontion().getSelectedIndex();
					FunctionChoice fc[] = FunctionChoice.values();
						try {
							Extension ext = new Extension(sefExt,borneInfEntree,borneSupEntree,fc[recup]);
							SEF toto = ext.getExtendedSef();
							mesSefs.addSeries(toto.getInflexions());
							FenetreGeometrique frame = new FenetreGeometrique("Manipulation des Sous Ensembles Flous", mesSefs);
							frame.pack();
							RefineryUtilities.centerFrameOnScreen(frame);
							frame.setVisible(true);
							//Rajout dans mesSEF / Combobox / Jlist
							fo.getMesSEF().add(toto);
							rajouter(toto);
						} catch (UnknownFunctionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return;
						}				
				}
			}
		}
		
		if(arg0.getSource()==tracerAffSEF){
			System.out.println("click tracer SEF Sans");
			XYSeriesCollection mesSefs= new XYSeriesCollection();
			// boucle qui remplit la XYSeries avec les SEF choisi
			for (int i = 0; i < sefchoisi.size();i++){
				System.out.println("choisi au clic "+sefchoisi.get(i));
				int choisi = sefchoisi.get(i);
				mesSefs.addSeries(fo.getMesSEF().get(choisi).getInflexions());
			}
			FenetreGeometrique frame = new FenetreGeometrique("Manipulation des Sous Ensembles Flous", mesSefs);
			//La string passée en param du constructeur est le titre de la fenetre
			frame.pack();//? Que fait cette commande?
			RefineryUtilities.centerFrameOnScreen(frame);
			frame.setVisible(true);
		}
			
		//Gere la gestion si modif de la JCombobox et mise a jour dans les JTextArea correspondant
		if(arg0.getSource()==sefComp){
			fo.getJtaComp().setText("");
			//System.out.println("Quand ?");
			int index = fo.getSefComp().getSelectedIndex();
			remplirJtextArea(fo.getJtaComp(),index);
		}
		
		if(arg0.getSource()==sefChoixinter1){
			fo.getJtaInter1().setText("");
			System.out.println("Quand ?");
			int index = fo.getSefChoixinter1().getSelectedIndex();
			remplirJtextArea(fo.getJtaInter1(),index);
		}
		
		if(arg0.getSource()==sefChoixinter2){
			fo.getJtaInter2().setText("");
			System.out.println("Quand ?");
			int index = fo.getSefChoixinter2().getSelectedIndex();
			remplirJtextArea(fo.getJtaInter2(),index);
		}
		
		if(arg0.getSource()== sefChoixuni1){
			fo.getJtaUni1().setText("");
			System.out.println("Quand ?");
			int index = fo.getSefChoixUni1().getSelectedIndex();
			remplirJtextArea(fo.getJtaUni1(),index);
		}
		
		if(arg0.getSource()== sefChoixuni2){
			fo.getJtaUni2().setText("");
			System.out.println("Quand ?");
			int index = fo.getSefChoixUni2().getSelectedIndex();
			remplirJtextArea(fo.getJtaUni2(),index);		
		}
		
		if(arg0.getSource()== sefExt){
			fo.getJtaExt().setText("");
			System.out.println("Quand ?");
			int index = fo.getChoixFoncSef().getSelectedIndex();
			remplirJtextArea(fo.getJtaExt(),index);
		}
		
		
	}
	
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("on est la");
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
	
	
	/**
	 * Cette fonction permet de rajouter le SEF sef au combobox et au JList
	 * @param sef
	 */
	public void rajouter(SEF sef){
		fo.getSefChoixinter1().addItem(sef.getInflexions().getKey());
		fo.getSefChoixinter2().addItem(sef.getInflexions().getKey());
		fo.getSefChoixUni1().addItem(sef.getInflexions().getKey());
		fo.getSefChoixUni2().addItem(sef.getInflexions().getKey());
		fo.getSefComp().addItem(sef.getInflexions().getKey());
		fo.getChoixFoncSef().addItem(sef.getInflexions().getKey());
		System.out.println("taille "+fo.getTotal().length);
		int nouvelletaille = fo.getTotal().length+1;
		String[] rajout = new String[nouvelletaille];
		System.out.println("taille "+rajout.length);
		
		//rajout = fo.getTotal();
		
		for(int i = 0 ; i < rajout.length-1;i++ ){
		rajout[i] = fo.getTotal()[i];
		System.out.println("i "+i+" "+fo.getTotal()[i]);
		}
		
		rajout[nouvelletaille-1] = (String) sef.getInflexions().getKey();
		//System.out.println("rajout apres " +rajout[2]);
		
		fo.setTotal(rajout);
		fo.getjListAffSEF().setListData(rajout);
		//fo.setjListAffSEF(nouv);
		fo.getjListAffSEF().repaint();
	//	fo.repaint();
	}

	/**
	 * cette fonction permet de remplir le JTextArea jta avec les éléments du SEF contenu dans 
	 * l'indice index
	 * @param jta
	 * @param index
	 */
	public void remplirJtextArea(JTextArea jta, int index){
		jta.setText("");
		SEF test = fo.getMesSEF().get(index);
		jta.append(""+ test.getInflexions().getKey()+"\n");
		jta.append("Borne inf : "+test.getBorneInf()+"\n");//+" borne sup"+test.getBorneSup()+"  "+test.getInflexions());
		jta.append("Borne sup : "+test.getBorneSup()+"\n");
		jta.append("\nListe des points :\n");
		test.getInflexions().getItemCount();
		//test.getInflexions().getDataItem(index);
		
		for (int i = 0; i < test.getInflexions().getItemCount(); i++) {
			jta.append("x: "+test.getInflexions().getDataItem(i).getXValue()+", ");
			jta.append("y: "+test.getInflexions().getDataItem(i).getYValue()+".\n");
		}
		
	}
		
	
}
