package manipFile;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Classe permettant de filter les fichier en entrée sur le type SEF.
 * On peut aussi charger les fichiers avec une autre extention. 
 * @author Frederic
 *
 */
public class Filtre extends FileFilter {

	/**
	 * Fitre avec Util.java
	 */
	@Override
	public boolean accept(File arg0) {
		if (arg0.isDirectory()) {
            return true;
        }
 
        String extension = Utils.getExtension(arg0);
        if (extension != null) {
            if (extension.equals(Utils.sef)){
                    return true;
            } else {
                return false;
            }
        }
 
        return false;
		
	}

	@Override
	public String getDescription() {
		return "Fichier SEF";
	}

}
