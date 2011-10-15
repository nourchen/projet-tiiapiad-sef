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
		// on supprime la "décoration" ( les = )
		contenu = contenu.replace("=", "");
		
		ecrit.writeBytes(contenu);
		ecrit.close();
		System.out.println("test sauvegarde fini");
	}
	
	
}
