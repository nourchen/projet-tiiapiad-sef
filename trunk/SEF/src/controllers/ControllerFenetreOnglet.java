package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ihm.FenetreGeometrique;
import ihm.FenetreOnglet;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

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

public class ControllerFenetreOnglet implements ActionListener {

	private FenetreOnglet fo;
	
	private JButton traceComp;
	private JButton traceInter;
	private JButton traceUni;
	private JButton traceExt;
	private JComboBox sefComp;
	private JComboBox sefChoixinter1;
	private JComboBox sefChoixinter2;
	private JComboBox sefChoixuni1;
	private JComboBox sefChoixuni2;
	private JComboBox sefExt;
	
	public ControllerFenetreOnglet(FenetreOnglet fo){
		this.fo = fo;
		traceComp = fo.getTraceComp();
		traceInter = fo.getTraceInter();
		traceUni = fo.getTraceUni();
		traceExt = fo.getTraceExt();
		
		sefComp = fo.getSefComp();
		sefChoixinter1 = fo.getSefChoixinter1();
		sefChoixinter2 = fo.getSefChoixinter2();
		sefChoixuni1 = fo.getSefChoixUni1();
		sefChoixuni2 = fo.getSefChoixUni2();
		sefExt = fo.getChoixFoncSef();
		
		
		traceComp.addActionListener(this);
		traceInter.addActionListener(this);
		traceUni.addActionListener(this);
		traceExt.addActionListener(this);
		
		sefComp.addActionListener(this);
		sefChoixinter1.addActionListener(this);
		sefChoixinter2.addActionListener(this);
		sefChoixuni1.addActionListener(this);
		sefChoixuni2.addActionListener(this);
		sefExt.addActionListener(this);
		
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
			//La string pass�e en param du constructeur est le titre de la fenetre
			fo.getMesSEF().add(inverse);
			rajouter(inverse);
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
			
			int recup = fo.getChoixTnorme().getSelectedIndex();
			Norme norme[] = Norme.values();
				SEF iinterSef;
				try {
						iinterSef = SefManager.getResultOperation(sefi1, sefi2, norme[recup],OperationEnsembliste.INTERSECTION);
						iinterSef.printInflexions();
						mesSefs.addSeries(iinterSef.getInflexions());
						FenetreGeometrique frame = new FenetreGeometrique("Manipulation des Sous Ensembles Flous", mesSefs);
						//La string pass�e en param du constructeur est le titre de la fenetre
						frame.pack();//? Que fait cette commande?
						RefineryUtilities.centerFrameOnScreen(frame);
						frame.setVisible(true);
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
					System.out.println("La normalisation n'a pas fonctionn�!");
					e.printStackTrace();
					return;
				} catch(NullPointerException e) {
					System.out.println("Les points du SEF calcul�s sont vide");
					return;
				}
				
				
	}
		
		if(arg0.getSource()==traceUni){
			System.out.println("Uni");
			
			int indexSEF1 = fo.getSefChoixinter1().getSelectedIndex();
			int indexSEF2 = fo.getSefChoixinter2().getSelectedIndex();
			
			SEF sefi1 = fo.getMesSEF().get(indexSEF1);
			SEF sefi2 = fo.getMesSEF().get(indexSEF2);
			XYSeriesCollection mesSefs= new XYSeriesCollection();
			
			//Zadeh
			int recupuni = fo.getChoixTconorme().getSelectedIndex();
			Norme normeuni[] = Norme.values();
				SEF uniSef;
				try {
					uniSef = SefManager.getResultOperation(sefi1, sefi2, normeuni[recupuni],OperationEnsembliste.UNION);
					//iinterSef.printInflexions();
					mesSefs.addSeries(uniSef.getInflexions());
					FenetreGeometrique frame = new FenetreGeometrique("Manipulation des Sous Ensembles Flous", mesSefs);
					//La string pass�e en param du constructeur est le titre de la fenetre
					frame.pack();//? Que fait cette commande?
					RefineryUtilities.centerFrameOnScreen(frame);
					fo.getMesSEF().add(uniSef);
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
					System.out.println("La normalisation n'a pas fonctionn�!");
					return;
				} catch(NullPointerException e) {
			System.out.println("Les points du SEF calcul�s sont vide");
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
//							System.out.println("Les points du sef �tendu que l'on demande � afficher:\n");
//							toto.printInflexions();
							mesSefs.addSeries(toto.getInflexions());
							FenetreGeometrique frame = new FenetreGeometrique("Manipulation des Sous Ensembles Flous", mesSefs);
							//La string pass�e en param du constructeur est le titre de la fenetre
							frame.pack();//? Que fait cette commande?
							RefineryUtilities.centerFrameOnScreen(frame);
							frame.setVisible(true);
							fo.getMesSEF().add(toto);
							rajouter(toto);
						} catch (UnknownFunctionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				
				}
			}
		}
		
		//Gere la gestion si modif de la JCombobox
		if(arg0.getSource()==sefComp){
			fo.getJtaComp().setText("");
			//System.out.println("Quand ?");
			int index = fo.getSefComp().getSelectedIndex();
			SEF test = fo.getMesSEF().get(index);
			fo.getJtaComp().append(""+ test.getInflexions().getKey()+"\n");
			fo.getJtaComp().append("Borne inf : "+test.getBorneInf()+"\n");//+" borne sup"+test.getBorneSup()+"  "+test.getInflexions());
			fo.getJtaComp().append("Borne sup : "+test.getBorneSup()+"\n");
			fo.getJtaComp().append("\nListe des points :\n");
			test.getInflexions().getItemCount();
			//test.getInflexions().getDataItem(index);
			
			for (int i = 0; i < test.getInflexions().getItemCount(); i++) {
				fo.getJtaComp().append("x: "+test.getInflexions().getDataItem(i).getXValue()+", ");
				fo.getJtaComp().append("y: "+test.getInflexions().getDataItem(i).getYValue()+".\n");
			}
			
		}
		
		if(arg0.getSource()==sefChoixinter1){
			fo.getJtaInter1().setText("");
			System.out.println("Quand ?");
			int index = fo.getSefChoixinter1().getSelectedIndex();
			SEF test = fo.getMesSEF().get(index);
			fo.getJtaInter1().append(""+ test.getInflexions().getKey()+"\n");
			fo.getJtaInter1().append("Borne inf : "+test.getBorneInf()+"\n");//+" borne sup"+test.getBorneSup()+"  "+test.getInflexions());
			fo.getJtaInter1().append("Borne sup : "+test.getBorneSup()+"\n");
			fo.getJtaInter1().append("\nListe des points :\n");
			test.getInflexions().getItemCount();
			//test.getInflexions().getDataItem(index);
			
			for (int i = 0; i < test.getInflexions().getItemCount(); i++) {
				fo.getJtaInter1().append("x: "+test.getInflexions().getDataItem(i).getXValue()+", ");
				fo.getJtaInter1().append("y: "+test.getInflexions().getDataItem(i).getYValue()+".\n");
			}
			
		}
		
		if(arg0.getSource()==sefChoixinter2){
			fo.getJtaInter2().setText("");
			System.out.println("Quand ?");
			int index = fo.getSefChoixinter2().getSelectedIndex();
			SEF test = fo.getMesSEF().get(index);
			fo.getJtaInter2().append(""+ test.getInflexions().getKey()+"\n");
			fo.getJtaInter2().append("Borne inf : "+test.getBorneInf()+"\n");//+" borne sup"+test.getBorneSup()+"  "+test.getInflexions());
			fo.getJtaInter2().append("Borne sup : "+test.getBorneSup()+"\n");
			fo.getJtaInter2().append("\nListe des points :\n");
			test.getInflexions().getItemCount();
			//test.getInflexions().getDataItem(index);
			
			for (int i = 0; i < test.getInflexions().getItemCount(); i++) {
				fo.getJtaInter2().append("x: "+test.getInflexions().getDataItem(i).getXValue()+", ");
				fo.getJtaInter2().append("y: "+test.getInflexions().getDataItem(i).getYValue()+".\n");
			}
			
		}
		
		if(arg0.getSource()== sefChoixuni1){
			fo.getJtaUni1().setText("");
			System.out.println("Quand ?");
			int index = fo.getSefChoixUni1().getSelectedIndex();
			SEF test = fo.getMesSEF().get(index);
			fo.getJtaUni1().append(""+ test.getInflexions().getKey()+"\n");
			fo.getJtaUni1().append("Borne inf : "+test.getBorneInf()+"\n");//+" borne sup"+test.getBorneSup()+"  "+test.getInflexions());
			fo.getJtaUni1().append("Borne sup : "+test.getBorneSup()+"\n");
			fo.getJtaUni1().append("\nListe des points :\n");
			test.getInflexions().getItemCount();
			//test.getInflexions().getDataItem(index);
			
			for (int i = 0; i < test.getInflexions().getItemCount(); i++) {
				fo.getJtaUni1().append("x: "+test.getInflexions().getDataItem(i).getXValue()+", ");
				fo.getJtaUni1().append("y: "+test.getInflexions().getDataItem(i).getYValue()+".\n");
			}
			
		}
		
		if(arg0.getSource()== sefChoixuni2){
			fo.getJtaUni2().setText("");
			System.out.println("Quand ?");
			int index = fo.getSefChoixUni2().getSelectedIndex();
			SEF test = fo.getMesSEF().get(index);
			fo.getJtaUni2().append(""+ test.getInflexions().getKey()+"\n");
			fo.getJtaUni2().append("Borne inf : "+test.getBorneInf()+"\n");//+" borne sup"+test.getBorneSup()+"  "+test.getInflexions());
			fo.getJtaUni2().append("Borne sup : "+test.getBorneSup()+"\n");
			fo.getJtaUni2().append("\nListe des points :\n");
			test.getInflexions().getItemCount();
			//test.getInflexions().getDataItem(index);
			
			for (int i = 0; i < test.getInflexions().getItemCount(); i++) {
				fo.getJtaUni2().append("x: "+test.getInflexions().getDataItem(i).getXValue()+", ");
				fo.getJtaUni2().append("y: "+test.getInflexions().getDataItem(i).getYValue()+".\n");
			}
			
		}
		
		if(arg0.getSource()== sefExt){
			fo.getJtaExt().setText("");
			System.out.println("Quand ?");
			int index = fo.getChoixFoncSef().getSelectedIndex();
			SEF test = fo.getMesSEF().get(index);
			fo.getJtaExt().append(""+ test.getInflexions().getKey()+"\n");
			fo.getJtaExt().append("Borne inf : "+test.getBorneInf()+"\n");//+" borne sup"+test.getBorneSup()+"  "+test.getInflexions());
			fo.getJtaExt().append("Borne sup : "+test.getBorneSup()+"\n");
			fo.getJtaExt().append("\nListe des points :\n");
			test.getInflexions().getItemCount();
			//test.getInflexions().getDataItem(index);
			
			for (int i = 0; i < test.getInflexions().getItemCount(); i++) {
				fo.getJtaExt().append("x: "+test.getInflexions().getDataItem(i).getXValue()+", ");
				fo.getJtaExt().append("y: "+test.getInflexions().getDataItem(i).getYValue()+".\n");
			}
			
		}
		
		
	}
	
	public void rajouter(SEF sef){
		fo.getSefChoixinter1().addItem(sef.getInflexions().getKey());
		fo.getSefChoixinter2().addItem(sef.getInflexions().getKey());
		fo.getSefChoixUni1().addItem(sef.getInflexions().getKey());
		fo.getSefChoixUni2().addItem(sef.getInflexions().getKey());
		fo.getSefComp().addItem(sef.getInflexions().getKey());
		fo.getChoixFoncSef().addItem(sef.getInflexions().getKey());
		//fo.getTotal()
		//fo.getjListAffSEF().add
	}
	
	
}
