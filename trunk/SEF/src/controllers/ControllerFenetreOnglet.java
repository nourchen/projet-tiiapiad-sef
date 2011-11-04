package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ihm.FenetreGeometrique;
import ihm.FenetreOnglet;


import javax.swing.JButton;
import javax.swing.JComboBox;

import manipSef.SEF;
import manipSef.SefComplement;
import manipSef.SefManager;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import exceptions.UnknownNormeException;
import exceptions.UnknownOperationException;

import tools.Norme;
import tools.OperationEnsembliste;

public class ControllerFenetreOnglet implements ActionListener {

	private FenetreOnglet fo;
	
	private JButton traceComp;
	private JButton traceInter;
	private JButton traceUni;
	private JButton traceExt;
	private JComboBox sefComp;
	
	public ControllerFenetreOnglet(FenetreOnglet fo){
		this.fo = fo;
		traceComp = fo.getTraceComp();
		traceInter = fo.getTraceInter();
		traceUni = fo.getTraceUni();
		traceExt = fo.getTraceExt();
		sefComp = fo.getSefComp();
		
		traceComp.addActionListener(this);
		traceInter.addActionListener(this);
		traceUni.addActionListener(this);
		traceExt.addActionListener(this);
		sefComp.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==traceComp){
			System.out.println("comp");
			System.out.println(""+fo.getSefComp().getSelectedIndex());
			int index = fo.getSefComp().getSelectedIndex();
			// pour test rapide OK 
			//mais pourquoi la fermeture de la XY ferme tout ?
			
			SEF test = fo.getMesSEF().get(index);
		//	fo.getDonnees().setText("Borne inf"+test.getBorneInf()+" borne sup"+test.getBorneSup());
			XYSeriesCollection mesSefs= new XYSeriesCollection();
			mesSefs.addSeries(test.getInflexions());
			mesSefs.addSeries(SefComplement.getComplement(test).getInflexions());
			FenetreGeometrique frame = new FenetreGeometrique("Manipulation des Sous Ensembles Flous", mesSefs);
			//La string passée en param du constructeur est le titre de la fenetre
			frame.pack();//? Que fait cette commande?
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
			
			//Zadeh
			if ( fo.getChoixTnorme().getSelectedIndex() == 0){
				SEF iinterSef;
				try {
					iinterSef = SefManager.getResultOperation(sefi1, sefi2, Norme.ZADEH,OperationEnsembliste.INTERSECTION);
					iinterSef.printInflexions();
					mesSefs.addSeries(iinterSef.getInflexions());
					FenetreGeometrique frame = new FenetreGeometrique("Manipulation des Sous Ensembles Flous", mesSefs);
					//La string passée en param du constructeur est le titre de la fenetre
					frame.pack();//? Que fait cette commande?
					RefineryUtilities.centerFrameOnScreen(frame);
					frame.setVisible(true);
				} catch (UnknownNormeException e) {
					System.out.println("La norme n'est pas connue!");
					
					e.printStackTrace();
				} catch (UnknownOperationException e) {
					System.out.println("L'operation n'est pas connue!");
					
					e.printStackTrace();
				}
			}
			//Luka
			if ( fo.getChoixTnorme().getSelectedIndex() == 1){
				SEF lukainterSef;
				try {
					lukainterSef = SefManager.getResultOperation(sefi1, sefi2, Norme.LUKASIEWICZ,OperationEnsembliste.UNION);
					mesSefs.addSeries(lukainterSef.getInflexions());
					FenetreGeometrique frame = new FenetreGeometrique("Manipulation des Sous Ensembles Flous", mesSefs);
					//La string passée en param du constructeur est le titre de la fenetre
					frame.pack();//? Que fait cette commande?
					RefineryUtilities.centerFrameOnScreen(frame);
					frame.setVisible(true);
				} catch (UnknownNormeException e) {
					System.out.println("La norme n'est pas connue!");
					
					e.printStackTrace();
				} catch (UnknownOperationException e) {
					System.out.println("L'operation n'est pas connue!");
					
					e.printStackTrace();
				}
			}	
			
		}
		
		if(arg0.getSource()==traceUni){
			System.out.println("Uni");
		}
		
		if(arg0.getSource()==traceExt){
			System.out.println("Ext");
		}
		
		//Gere la gestion si modif de la JCombobox
		if(arg0.getSource()==sefComp){
			//fo.getJta().setText("");
			//System.out.println("Quand ?");
			int index = fo.getSefComp().getSelectedIndex();
			SEF test = fo.getMesSEF().get(index);
			//fo.getJta().append("Borne inf : "+test.getBorneInf()+"\n");//+" borne sup"+test.getBorneSup()+"  "+test.getInflexions());
			//fo.getJta().append("Borne sup : "+test.getBorneSup()+"\n");
			//fo.getJta().append("\nListe des points :\n");
			test.getInflexions().getItemCount();
			//test.getInflexions().getDataItem(index);
			
		/*	for (int i = 0; i < test.getInflexions().getItemCount(); i++) {
				fo.getJta().append("x: "+test.getInflexions().getDataItem(i).getXValue()+", ");
				fo.getJta().append("y: "+test.getInflexions().getDataItem(i).getYValue()+".\n");
			}
			*/
		}
	}
	
}
