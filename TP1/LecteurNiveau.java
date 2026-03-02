import java.util.*;
import java.io.*;

class LecteurNiveau {

    public Niveau lisProchainNiveau(InputStream fichier) throws IOException {
        // Correction de la hiérarchie des lecteurs
        BufferedReader br = new BufferedReader(new InputStreamReader(fichier));
        String ligne; // Changé de char à String
        Niveau niv = new Niveau();
        
        int i = 0;
        // Attention : ligne = br.readLine() doit être entre parenthèses
       while ((ligne = br.readLine()) != null && i < niv.map.length) { // Ajout de la sécurité i
        int j = 0;
            if (ligne.startsWith(";")) {
                niv.fixeNom(ligne.substring(1).trim());
                continue;
                }
            // Utilisation de .length() pour un String
            while (j < ligne.length() && j < niv.map[i].length) {
                // Utilisation de .charAt(j) avec des parenthèses
                switch (ligne.charAt(j)) {
                    case ' ':
                        niv.videCase(i, j);
                        break;
                    case '#':
                        niv.ajouteMur(i, j);
                        break;
                    case '.':
                        niv.ajouteBut(i, j);
                        break;
                    case '@':
                        niv.ajoutePousseur(i, j);
                        break;
                    case '$':
                        niv.ajouteCaisse(i, j);
                        break;
                    default:
                        System.err.println("Mauvais caractere : " + ligne.charAt(j));
                }
                j++;
            }
            i++; // i doit être incrémenté à chaque ligne lue
        }
        return niv; // Retourner l'objet créé au lieu de null
    }
}