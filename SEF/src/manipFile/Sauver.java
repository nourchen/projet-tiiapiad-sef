package manipFile;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import manipSef.SEF;

public class Sauver {

	// Avant de sauver : 
	// 1)verif que c'est une fonction (pas de y avec deux abscisses) pas de y > 1
	// 2)ordonner selon les x
	
	public Sauver () {
		
	}
	
	public void infoFichier(String chemin, ArrayList<SEF> mesSEF) throws IOException{
		chemin = chemin+".SEF";
		System.out.println("test sauvegarde");		
		DataOutputStream ecrit = new DataOutputStream(new FileOutputStream(chemin));
	/*
		//on supprime les retours a la ligne :
		contenu = contenu.replace("\n", "");
		// on supprime la "décoration" ( les = )
		contenu = contenu.replace("=", "");
		//on supprime le premier SEF :
		contenu = contenu.replaceFirst("SEF :", "");
		// on supprime les autres :
		contenu = contenu.replace("SEF :", "\n");
		// on supprime les "borne inf"
		contenu = contenu.replace("borne inf : ", "");
		// on supprime les "borne sup"
		contenu = contenu.replace("borne sup : ", ",");
		// on supprime les "point(s)"
		contenu = contenu.replace("point(s) :", "#");
		// on remplace les " " par des ","
		contenu = contenu.replace(" ",",");
		*/
	//	-10,10#1,0;2,1;3,0
		String contenu = "";
		for (int i = 0 ; i < mesSEF.size();i++){
			contenu += ""+mesSEF.get(i).getBorneInf()+","+mesSEF.get(i).getBorneSup()+"#";
			for (int j =0 ; j <mesSEF.get(i).getInflexions().getItemCount();j++){
				contenu +=""+mesSEF.get(i).getInflexions().getX(j)+","+mesSEF.get(i).getInflexions().getY(j)+";";
			}
			contenu = contenu.substring(0, contenu.length() - 1);
		if(i != (mesSEF.size()-1)) {
			contenu +="\n";
		}
		}
		ecrit.writeBytes(contenu);
		ecrit.close();
		System.out.println("test sauvegarde fini");
	}
	   	 	
	
}
