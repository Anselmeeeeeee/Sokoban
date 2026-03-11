package Controleur;

import Modele.*;
import Vue.InterfaceGraphique;
import Vue.NiveauGraphique;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class EcouteurDeSouris implements MouseListener {
    InterfaceGraphique vue;
    jeu Sokoban;

    public EcouteurDeSouris(InterfaceGraphique vue, jeu Sokoban) {
        this.vue = vue;
        this.Sokoban = Sokoban;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        NiveauGraphique aire = vue.getAire();

        // Calcul de la case visée
        int j = (e.getX() - aire.offsetX) / aire.tailleCase;
        int i = (e.getY() - aire.offsetY) / aire.tailleCase;

        // On vérifie les bornes
        if (i >= 0 && i < Sokoban.niveau().lignes() && j >= 0 && j < Sokoban.niveau().colonnes()) {
            Sokoban.niveau().deplacePousseur(i, j);
            if (Sokoban.niveau().estGagne()) {
                System.out.println("Niveau terminé !");
                Sokoban.chargerNiveauSuivant();
            }
            aire.repaint();
        }
    }

    // Toutes les méthodes qui suivent font partie de l'interface. Si nous ne
    // nous en servons pas, il suffit de les déclarer vides.
    // Une autre manière de faire, plus simple, est d'hériter de MouseAdapter,
    // qui est une classe qui hérite de MouseListener avec une implémentation
    // vide de toutes ses méthodes.
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
}