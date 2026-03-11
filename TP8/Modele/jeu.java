package Modele;
import Global.Configuration;

import java.io.IOException;

public class jeu  {
    public Niveau actuel;
    public RedacteurNiveau redacteur;
    public LecteurNiveaux lecteur;

    // Le constructeur prend maintenant directement le lecteur de fichier
    public jeu(LecteurNiveaux lecteurFichier) {
        this.lecteur = lecteurFichier;
        this.redacteur = new RedacteurNiveau(System.out);
    }

    public Niveau niveau(){
        return actuel;
    }

    public void chargerNiveauSuivant() {
        Niveau n = lecteur.lisProchainNiveau();
        if (n != null) {
            actuel = n;
        } else {
            System.out.println("Félicitations, vous avez fini tous les niveaux !");
            System.exit(0);
        }
    }
}