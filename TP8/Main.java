import Modele.*;
import Vue.*;
import Controleur.*;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        InputStream in = null;

        // 1. Gestion de l'argument en ligne de commande
        try {
            if (args.length > 0) {
                // On ouvre le fichier passé en paramètre (ex: java Main niveaux.txt)
                in = new FileInputStream(args[0]);
            } else {
                // Si on oublie l'argument, on peut mettre un fichier par défaut ou quitter
                System.out.println("Veuillez indiquer un fichier de niveau en argument.");
                System.out.println("Usage: java Main <chemin_du_fichier>");
                System.exit(1);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Erreur : Le fichier " + args[0] + " est introuvable.");
            System.exit(1);
        }

        // 2. MODÈLE
        LecteurNiveaux lecteur = new LecteurNiveaux(in);
        jeu modele = new jeu(lecteur);
        modele.chargerNiveauSuivant();

        // 3. VUE
        InterfaceGraphique vue = new InterfaceGraphique(modele);

        // 4. CONTRÔLEUR
        EcouteurDeClavier ec = new EcouteurDeClavier(vue, modele);
        EcouteurDeSouris es = new EcouteurDeSouris(vue, modele);

        // 5. ASSEMBLAGE
        vue.getAire().addKeyListener(ec);
        vue.getAire().addMouseListener(es);

        // 6. MOTEUR
        SwingUtilities.invokeLater(vue);
    }
}