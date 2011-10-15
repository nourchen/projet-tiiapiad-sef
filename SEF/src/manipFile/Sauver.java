package manipFile;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Sauver {

	
	
	public Sauver () {
		
	}
	
	public void infoFichier(String chemin, String contenu) throws IOException{
		chemin = chemin+".SEF";
		System.out.println("test sauvegarde");
		
		DataOutputStream ecrit = new DataOutputStream(new FileOutputStream(chemin));
		//on supprime les retours a la ligne :
		contenu = contenu.replace("\n", "");
		// on supprime la "d�coration" ( les = )
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
		
		ecrit.writeBytes(contenu);
		ecrit.close();
		System.out.println("test sauvegarde fini");
	}
	
	
}