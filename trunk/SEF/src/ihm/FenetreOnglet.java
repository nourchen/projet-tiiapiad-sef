package ihm;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel; //peut etre besoin rapidement
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import principeExtension.FunctionChoice;

import tools.Norme;

import manipSef.SEF;

import controllers.ControllerFenetreOnglet;



// Pas fini, encore des bugs a traiter.
public class FenetreOnglet extends JFrame{
	
	private static final String tracer = "Tracer";
	
	private JComboBox sefComp = new JComboBox();
	
	//Intersection
	private JComboBox sefChoixinter1 = new JComboBox();
	private JComboBox sefChoixinter2 = new JComboBox();
	private JComboBox choixTnorme = new JComboBox();

	//Union
	private JComboBox sefChoixUni1 = new JComboBox();
	private JComboBox sefChoixUni2 = new JComboBox();
	private JComboBox choixTconorme = new JComboBox();
	
	//Extension
//	private JComboBox choixFontion = new JComboBox();
	private JComboBox ChoixFoncSef = new JComboBox();
	
	//Bouton pour valider la trace (je vais metre un bouton par panel laissant ainsi le choix)
	private JButton traceComp = new JButton(tracer);
	private JButton traceInter = new JButton(tracer);
	private JButton traceUni = new JButton(tracer);
	private JButton traceExt = new JButton(tracer);

	// l'ArrayList 
	private ArrayList<SEF> mesSEF;
	
	// gestion de l'affichage sur Complémentaire
	private JTextField donneesBorneInf; // = new JTextField();
	private JTextField donneesBorneSup;
	
	//un textArea
	private JTextArea jta;
	/////////////////////////////////////////////////////////////
    private JComboBox choixFontion = new JComboBox ();
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel16;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane4;
    private JScrollPane jScrollPane5;
    private JScrollPane jScrollPane6;
    private JTabbedPane jTabbedPane1;
    private JTextArea jtaComp;
    private JTextArea jtaExt;
    private JTextField jtaExtsup;
    private JTextArea jtaInter1;
    private JTextArea jtaInter2;
    private JTextArea jtaUni1;
    private JTextArea jtaUni2;
    private JTextField jtfExtinf;

	
	public FenetreOnglet(ArrayList<SEF> mesSEF){
	
		setTitle("Choisir les courbes a generer");
		this.mesSEF = mesSEF;
		setVisible(true);
		// remplissage des combobox:
		for (int i = 0; i < mesSEF.size(); i++){
			sefComp.addItem("SEF "+(i+1));
			
			sefChoixinter1.addItem("SEF "+(i+1));
			sefChoixinter2.addItem("SEF "+(i+1));
			
			sefChoixUni1.addItem("SEF "+(i+1));
			sefChoixUni2.addItem("SEF "+(i+1));
			
			ChoixFoncSef.addItem("SEF "+(i+1));
		}
	
	/*	choixTnorme.addItem("Zadeh");
		choixTnorme.addItem("Lukasiewicz");
		choixTnorme.addItem("probabiliste");
	*/
		//Test avec Norme[]
		Norme testNorme[] = Norme.values();
		for (int i=0 ; i < testNorme.length ; i++){
			choixTnorme.addItem(testNorme[i]);
			choixTconorme.addItem(testNorme[i]);
		}
		
		FunctionChoice fc[] = FunctionChoice.values();
		for (int j=0; j < fc.length ; j++){
			choixFontion.addItem(fc[j]);
		}
		
		/*
		choixTconorme.addItem("Zadeh");
		choixTconorme.addItem("Lukasiewicz");
		choixTconorme.addItem("probabiliste");		
		choixFontion.addItem("Square");
		choixFontion.addItem("Cos");
		*/
		
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
     //   sefComp = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
     //   traceComp = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaComp = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
    //    traceInter = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
    //    sefChoixinter1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaInter1 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtaInter2 = new javax.swing.JTextArea();
    //    sefChoixinter2 = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
     //   choixTnorme = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        traceUni = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
    //    sefChoixUni1 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtaUni1 = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtaUni2 = new javax.swing.JTextArea();
     //   sefChoixUni2 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
     //   choixTconorme = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
    //    choixFontion = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();

    //    ChoixFoncSef = new javax.swing.JComboBox();
        traceExt = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtaExt = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        jtfExtinf = new javax.swing.JTextField();
        jtaExtsup = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();

       JLabel jLabel17 = new JLabel();
       JLabel jLabel18 = new JLabel();
       JLabel jLabel19 = new JLabel();
   //     ChoixFoncSef = new javax.swing.JComboBox();
   //     traceExt = new javax.swing.JButton();		
		// code pour la fenetre

       jLabel1.setText("Selectionner le SEF ");

       traceComp.setText("Tracer");

       jtaComp.setColumns(20);
       jtaComp.setEditable(false);
       jtaComp.setRows(5);
       jScrollPane1.setViewportView(jtaComp);

       jLabel2.setText("Information sur le SEF :");

       javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
       jPanel1.setLayout(jPanel1Layout);
       jPanel1Layout.setHorizontalGroup(
           jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel1Layout.createSequentialGroup()
               .addGap(168, 168, 168)
               .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addContainerGap(174, Short.MAX_VALUE))
           .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
               .addContainerGap(265, Short.MAX_VALUE)
               .addComponent(traceComp)
               .addGap(263, 263, 263))
           .addGroup(jPanel1Layout.createSequentialGroup()
               .addGap(223, 223, 223)
               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                       .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addComponent(sefComp, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
               .addContainerGap(220, Short.MAX_VALUE))
       );
       jPanel1Layout.setVerticalGroup(
           jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel1Layout.createSequentialGroup()
               .addGap(26, 26, 26)
               .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGap(18, 18, 18)
               .addComponent(sefComp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGap(18, 18, 18)
               .addComponent(jLabel2)
               .addGap(26, 26, 26)
               .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
               .addGap(18, 18, 18)
               .addComponent(traceComp)
               .addGap(70, 70, 70))
       );

       jTabbedPane1.addTab("Complementaire", jPanel1);

       traceInter.setText("Tracer");

       jLabel3.setText("SEF numero 1 :");

       jLabel4.setText("information :");

       jtaInter1.setColumns(20);
       jtaInter1.setEditable(false);
       jtaInter1.setRows(5);
       jScrollPane2.setViewportView(jtaInter1);

       jLabel5.setText("SEF numero 2 :");

       jLabel6.setText("information :");

       jtaInter2.setColumns(20);
       jtaInter2.setEditable(false);
       jtaInter2.setRows(5);
       jScrollPane3.setViewportView(jtaInter2);

       jLabel13.setText("Choix T-Norme :");

       javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
       jPanel2.setLayout(jPanel2Layout);
       jPanel2Layout.setHorizontalGroup(
           jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel2Layout.createSequentialGroup()
               .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                       .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                           .addGap(52, 52, 52)
                           .addComponent(jLabel4))
                       .addGroup(jPanel2Layout.createSequentialGroup()
                           .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                               .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                   .addGap(44, 44, 44)
                                   .addComponent(sefChoixinter1, 0, 80, Short.MAX_VALUE))
                               .addGroup(jPanel2Layout.createSequentialGroup()
                                   .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(jLabel3)))
                           .addGap(52, 52, 52)))
                   .addGroup(jPanel2Layout.createSequentialGroup()
                       .addContainerGap()
                       .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
               .addGap(59, 59, 59)
               .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                   .addComponent(jLabel13)
                   .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                       .addComponent(traceInter)
                       .addComponent(choixTnorme, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
               .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                   .addGroup(jPanel2Layout.createSequentialGroup()
                       .addComponent(jLabel6)
                       .addGap(52, 52, 52))
                   .addGroup(jPanel2Layout.createSequentialGroup()
                       .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                           .addComponent(sefChoixinter2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addComponent(jLabel5))
                       .addGap(48, 48, 48))
                   .addGroup(jPanel2Layout.createSequentialGroup()
                       .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addContainerGap())))
       );
       jPanel2Layout.setVerticalGroup(
           jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel2Layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(jLabel5)
                   .addComponent(jLabel13))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
               .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(sefChoixinter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(sefChoixinter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(choixTnorme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                   .addGroup(jPanel2Layout.createSequentialGroup()
                       .addGap(19, 19, 19)
                       .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                           .addComponent(jLabel4)
                           .addComponent(jLabel6))
                       .addGap(18, 18, 18)
                       .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                           .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                           .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)))
                   .addGroup(jPanel2Layout.createSequentialGroup()
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(traceInter)))
               .addContainerGap())
       );

       jTabbedPane1.addTab("Intersection", jPanel2);

       traceUni.setText("Tracer");


       jLabel7.setText("SEF numero 1 :");


       jLabel8.setText("information :");

       jtaUni1.setColumns(20);
       jtaUni1.setEditable(false);
       jtaUni1.setRows(5);
       jScrollPane4.setViewportView(jtaUni1);

       jLabel9.setText("SEF numero 2 :");

       jLabel10.setText("information :");

       jtaUni2.setColumns(20);
       jtaUni2.setEditable(false);
       jtaUni2.setRows(5);
       jScrollPane5.setViewportView(jtaUni2);

       jLabel14.setText("Choix T-Conorme :");

       javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
       jPanel5.setLayout(jPanel5Layout);
       jPanel5Layout.setHorizontalGroup(
           jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel5Layout.createSequentialGroup()
               .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                       .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                           .addGap(52, 52, 52)
                           .addComponent(jLabel8))
                       .addGroup(jPanel5Layout.createSequentialGroup()
                           .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                               .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                   .addGap(44, 44, 44)
                                   .addComponent(sefChoixUni1, 0, 80, Short.MAX_VALUE))
                               .addGroup(jPanel5Layout.createSequentialGroup()
                                   .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(jLabel7)))
                           .addGap(52, 52, 52)))
                   .addGroup(jPanel5Layout.createSequentialGroup()
                       .addContainerGap()
                       .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(jPanel5Layout.createSequentialGroup()
                       .addGap(45, 45, 45)
                       .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                           .addComponent(jLabel14)
                           .addComponent(choixTconorme, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addComponent(traceUni))
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                       .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                               .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addComponent(jLabel9)
                                   .addComponent(sefChoixUni2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                               .addGap(40, 40, 40))
                           .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                               .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                               .addContainerGap())))
                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                       .addComponent(jLabel10)
                       .addGap(80, 80, 80))))
       );
       jPanel5Layout.setVerticalGroup(
           jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel5Layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(jLabel14)
                   .addComponent(jLabel9))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
               .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(sefChoixUni1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(choixTconorme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(sefChoixUni2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(16, 16, 16)
               .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                   .addGroup(jPanel5Layout.createSequentialGroup()
                       .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                           .addComponent(jLabel8)
                           .addComponent(jLabel10))
                       .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGroup(jPanel5Layout.createSequentialGroup()
                               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                               .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                           .addGroup(jPanel5Layout.createSequentialGroup()
                               .addGap(18, 18, 18)
                               .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))))
                   .addComponent(traceUni))
               .addGap(23, 23, 23))
       );

       javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
       jPanel3.setLayout(jPanel3Layout);
       jPanel3Layout.setHorizontalGroup(
           jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGap(0, 591, Short.MAX_VALUE)
           .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       );
       jPanel3Layout.setVerticalGroup(
           jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGap(0, 404, Short.MAX_VALUE)
           .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                   .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addContainerGap(14, Short.MAX_VALUE)))
       );

       jTabbedPane1.addTab("Union", jPanel3);

       jLabel11.setText("Choisissez la fonction à etendre :");

       jLabel12.setText("Choisissez le sous ensemble flou :");

       traceExt.setText("Tracer");

       jtaExt.setColumns(20);
       jtaExt.setEditable(false);
       jtaExt.setRows(5);
       jScrollPane6.setViewportView(jtaExt);

       jLabel15.setText("Information sur le sous ensemble choisi :");

       jLabel16.setText("borne sup :");

       jLabel17.setText("borne inf :");

       jLabel18.setText("Sur quel intervalle voulez vous étendre votre sef ? ");

       javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
       jPanel4.setLayout(jPanel4Layout);
       jPanel4Layout.setHorizontalGroup(
           jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel4Layout.createSequentialGroup()
               .addGap(40, 40, 40)
               .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(jPanel4Layout.createSequentialGroup()
                       .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addGap(18, 18, 18)
                       .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                           .addComponent(ChoixFoncSef, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                           .addComponent(choixFontion, javax.swing.GroupLayout.Alignment.LEADING, 0, 110, Short.MAX_VALUE)
                           .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                               .addGap(6, 6, 6)
                               .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                               .addContainerGap())))
                   .addGroup(jPanel4Layout.createSequentialGroup()
                       .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addContainerGap())
                   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                       .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addComponent(jLabel11)
                           .addGroup(jPanel4Layout.createSequentialGroup()
                               .addGap(10, 10, 10)
                               .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                       .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addComponent(traceExt, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addGroup(jPanel4Layout.createSequentialGroup()
                               .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                               .addGap(52, 52, 52)
                               .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                   .addComponent(jtfExtinf, javax.swing.GroupLayout.Alignment.TRAILING)
                                   .addComponent(jtaExtsup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))))
                       .addGap(49, 49, 49))))
       );
       jPanel4Layout.setVerticalGroup(
           jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel4Layout.createSequentialGroup()
               .addGap(42, 42, 42)
               .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(choixFontion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(33, 33, 33)
               .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(ChoixFoncSef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(27, 27, 27)
               .addComponent(jLabel15)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
               .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(jPanel4Layout.createSequentialGroup()
                       .addComponent(jLabel18)
                       .addGap(52, 52, 52)
                       .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                           .addComponent(jtfExtinf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addComponent(jLabel17))
                       .addGap(28, 28, 28)
                       .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                           .addComponent(jtaExtsup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                           .addComponent(jLabel16)))
                   .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGap(7, 7, 7)
               .addComponent(traceExt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGap(24, 24, 24))
       );

       jTabbedPane1.addTab("Extension", jPanel4);

       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
       getContentPane().setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
       );

       pack();
		ControllerFenetreOnglet cfo = new ControllerFenetreOnglet(this);
		
	}


	public JButton getTraceComp() {
		return traceComp;
	}


	public JButton getTraceInter() {
		return traceInter;
	}


	public JButton getTraceUni() {
		return traceUni;
	}


	public JButton getTraceExt() {
		return traceExt;
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

	public JTextArea getJta() {
		return jta;
	}

	public void setJta(JTextArea jta) {
		this.jta = jta;
	}

	public JComboBox getChoixTnorme() {
		return choixTnorme;
	}

	public JComboBox getChoixTconorme() {
		return choixTconorme;
	}


	public JComboBox getChoixFontion() {
		return choixFontion;
	}


	public JComboBox getChoixFoncSef() {
		return ChoixFoncSef;
	}


	public javax.swing.JTextArea getJtaComp() {
		return jtaComp;
	}


	public javax.swing.JTextArea getJtaExt() {
		return jtaExt;
	}


	public javax.swing.JTextField getJtaExtsup() {
		return jtaExtsup;
	}


	public javax.swing.JTextArea getJtaInter1() {
		return jtaInter1;
	}


	public javax.swing.JTextArea getJtaInter2() {
		return jtaInter2;
	}


	public javax.swing.JTextArea getJtaUni1() {
		return jtaUni1;
	}


	public javax.swing.JTextArea getJtaUni2() {
		return jtaUni2;
	}


	public javax.swing.JTextField getJtfExtinf() {
		return jtfExtinf;
	}
	
	
	
}
