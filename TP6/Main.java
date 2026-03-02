import Global.Configuration;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configuration.setSilence(0);

        if (args.length != 1) {
            Configuration.erreur("Usage: java Main <fichier_niveaux.txt>");
            return;
        }
        
        try (InputStream entree = Configuration.ouvre(args[0])) {
            LecteurNiveaux lecteur = new LecteurNiveaux(entree);
            jeu monJeu = new jeu(new Scanner(System.in));

            monJeu.actuel = lecteur.lisProchainNiveau();
            if (monJeu.actuel != null) {
                InterfaceGraphique.demarrer(monJeu);
            } else {
                Configuration.erreur("Aucun niveau trouvé dans le fichier  ");
            }
            
        } catch (IOException e) {
            Configuration.erreur("lecture fichier: " + e.getMessage());
        }
    }
}
