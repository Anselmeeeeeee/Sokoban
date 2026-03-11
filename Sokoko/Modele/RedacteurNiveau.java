package Modele;
import Global.Configuration;

import java.io.*;

public class RedacteurNiveau {
    PrintStream sortie;

    public RedacteurNiveau(OutputStream flux) {
        this.sortie = new PrintStream(flux);
    }
    
    public void ecrisNiveau(Niveau niveau) {
        // Écrire toutes les lignes du niveau
        for (int i = 0; i < niveau.lignes(); i++) {
            for (int j = 0; j < niveau.colonnes(); j++) {
                if (niveau.aMur(i, j)) {
                    sortie.print('#');
                } else if (niveau.aPousseur(i, j)) {
                    sortie.print('@');
                } else if (niveau.aCaisse(i, j)) {
                    sortie.print('$');
                } else if (niveau.aBut(i, j)) {
                    sortie.print('.');
                } else {
                    sortie.print(' ');  // Case vide
                }
            }
            sortie.println();  // Fin de ligne
        }
        
        // Écrire le nom après ; 
        if (!niveau.nom().isEmpty()) {
            sortie.println("; " + niveau.nom());
        }
        
        // Ligne vide entre niveaux 
        sortie.println();
    }
}
