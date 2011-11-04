package ihm;

import javax.swing.*;

import controllers.ControllerFenetrePincipale;


import manipFile.Charger;
import manipFile.Filtre;
import manipFile.Sauver;




/**
 * 
 * @author Frederic
 *
 */


public class FenetrePrincipale extends JFrame {

	private JMenuBar menubar = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier");
	
	private JMenuItem charger = new JMenuItem("Charger ...");
	private JMenuItem sauver  = new JMenuItem("Sauvegarder...");
	
	private JTextArea sef_entrer = new JTextArea();
	private JTextArea sef_stocker = new JTextArea();
	
	private JButton generer = new JButton("Generer");
	private JButton valider = new JButton("Valider");
	
	private JLabel ptsSEF = new JLabel("Entrez les Pts du sef :");
	private JLabel borneinf = new JLabel("Borne Inf : ");
	private JLabel bornesup = new JLabel("Borne Sup :");
	private JLabel ptsEntrer = new JLabel("Point déjà entrés :");
	
	private JTextField entreBorneInf = new JTextField();
	private JTextField entreBorneSup = new JTextField();
	
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    
    private JTextField tfX;
    private JTextField tfY;
    private JButton ajouterpts;
//	private BorderLayout fond = new BorderLayout();
	
	
	public FenetrePrincipale() {
		
		
        jScrollPane2 = new JScrollPane();
        jScrollPane1 = new JScrollPane();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jScrollPane3 = new JScrollPane();
        jLabel5 = new JLabel();
        tfX = new JTextField();
        jLabel6 = new JLabel();
        tfY = new JTextField();
        ajouterpts = new JButton();
        
        if(sef_stocker.getText().equals("")) {
        	generer.setEnabled(false);
        }
        
        valider.setEnabled(false);
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sef_stocker.setColumns(20);
        sef_stocker.setEditable(false);
        sef_stocker.setRows(5);
        jScrollPane1.setViewportView(sef_stocker);

        generer.setText("Générer");

        jLabel1.setText("Borne inf :");

        jLabel2.setText("Borne sup :");

        jLabel3.setText("Saisissez un nouveau sef :");

        jLabel4.setText("Saisissez les points : ");

        sef_entrer.setColumns(20);
        sef_entrer.setEditable(false);
        sef_entrer.setRows(5);
        jScrollPane3.setViewportView(sef_entrer);

        valider.setText("Valider SEF");

        jLabel5.setText("X :");

        jLabel6.setText("Y :");

        ajouterpts.setText("Ajouter point");

        fichier.setText("Fichier");

        charger.setText("Charger");
        fichier.add(charger);

        sauver.setText("Sauvegarder");
        fichier.add(sauver);

        menubar.add(fichier);

        setJMenuBar(menubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(generer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                .addComponent(valider)
                .addGap(88, 88, 88))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(97, 97, 97)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(76, 76, 76)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel2))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(entreBorneSup, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                                .addComponent(entreBorneInf, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))))))
                            .addGap(37, 37, 37))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel4)
                            .addGap(85, 85, 85)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ajouterpts)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfX, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfY, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(64, 64, 64))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(entreBorneInf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(entreBorneSup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tfX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(tfY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(ajouterpts)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generer)
                    .addComponent(valider))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        setTitle("S.E.F");
        setVisible(true);
        pack();
      //ActionListener
		ControllerFenetrePincipale cfp = new ControllerFenetrePincipale(this);
		
	}
	
	public void pointEntree(String borneinf, String borneSup, String point) {
		// Bon y a moyen de tout mettre dans le meme append mais je prefere comme ca :
		point = point.replaceAll("\n", ";\n");
		sef_stocker.append("SEF :");
		sef_stocker.append("\n==========");
		sef_stocker.append("\nborne inf : "+borneinf);
		sef_stocker.append("\nborne sup : "+borneSup);
		sef_stocker.append("\npoint(s) :\n"+point);
		sef_stocker.append("\n==========\n");
	}
	public JMenuItem getCharger() {
		return charger;
	}
	public JMenuItem getSauver() {
		return sauver;
	}
	public JButton getGenerer() {
		return generer;
	}
	public JButton getValider() {
		return valider;
	}
	public JTextArea getSef_entrer() {
		return sef_entrer;
	}
	public JTextArea getSef_stocker() {
		return sef_stocker;
	}
	public JTextField getEntreBorneInf() {
		return entreBorneInf;
	}
	public JTextField getEntreBorneSup() {
		return entreBorneSup;
	}
	public void setSef_entrer(JTextArea sefEntrer) {
		sef_entrer = sefEntrer;
	}

	public javax.swing.JTextField getTfX() {
		return tfX;
	}

	public javax.swing.JTextField getTfY() {
		return tfY;
	}

	public JButton getAjouterpts() {
		return ajouterpts;
	}

		
	
	
}
