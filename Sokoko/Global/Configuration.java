package Global;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Configuration {
    private static int silence = 0;
    private static boolean choice = true;

    public static void setSilence(int niveau) {
        silence = niveau;
    }
    // Méthode globale pour charger un flux de fichier
    public static InputStream chargeFlux(String nomFichier) {
        try {
            return new FileInputStream(nomFichier);
        } catch (FileNotFoundException e) {
            System.err.println("Erreur : Impossible de trouver le fichier " + nomFichier);
            return null;
        }
    }

    // On peut aussi centraliser ici les chemins vers les ressources
    public static String cheminImages() {
        return "res/Images/";
    }

    public static InputStream ouvre(String cheminRelatif) {
        // Construction du chemin complet à partir de la racine du projet
        String cheminComplet = cheminRelatif;

        try {
            return new FileInputStream(cheminComplet);
        } catch (FileNotFoundException e) {
            System.err.println("ERREUR : impossible de trouver le fichier " + cheminComplet);
            System.exit(2);
            return null;
        }
    }

    // Méthode pour les avertissements (ne stoppe pas le programme)
    public static void alerte(String message) {
        if (silence < 1) {
            System.err.println("ALERTE : " + message);
        }
    }

    // Méthode pour les erreurs fatales (stoppe le programme)
    public static void erreur(String message) {
        if (silence < 2) {
            System.err.println("ERREUR : " + message);
        }
        System.exit(1); // L'arrêt du programme reste obligatoire pour une erreur fatale
    }

    public static boolean setChoice(boolean choix) {
        choice = choix;
        return choice;
    }
}