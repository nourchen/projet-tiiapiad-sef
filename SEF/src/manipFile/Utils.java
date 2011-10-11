package manipFile;

import java.io.File;

public class Utils {
    public final static String sef = "SEF";

    /**
     * Permet de recuperer l'ext d'un fichier (SEF ici)  
     * @param f
     * @return
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1);
        }
        return ext;
    }
}
