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

        try {
            if (args.length > 0) {

                in = new FileInputStream(args[0]);
            } else {

                System.out.println("Veuillez indiquer un fichier de niveau en argument.");
                System.out.println("Usage: java Main <chemin_du_fichier>");
                System.exit(1);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Erreur : Le fichier " + args[0] + " est introuvable.");
            System.exit(1);
        }

        LecteurNiveaux lecteur = new LecteurNiveaux(in);
        jeu modele = new jeu(lecteur);
        modele.chargerNiveauSuivant();

        InterfaceGraphique vue = new InterfaceGraphique(modele);

        EcouteurDeClavier ec = new EcouteurDeClavier(vue, modele);
        EcouteurDeSouris es = new EcouteurDeSouris(vue, modele);

        vue.getAire().addKeyListener(ec);
        vue.getAire().addMouseListener(es);

        SwingUtilities.invokeLater(vue);
    }
}