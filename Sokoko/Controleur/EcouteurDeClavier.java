package Controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Modele.*;
import Vue.InterfaceGraphique;
import Vue.NiveauGraphique;

public class EcouteurDeClavier implements KeyListener {
    InterfaceGraphique vue;
    jeu Sokoban;

    public EcouteurDeClavier(InterfaceGraphique vue, jeu Sokoban) {
        this.vue = vue;
        this.Sokoban = Sokoban;
    }

    public void keyPressed(KeyEvent e) {
        int PousseurX = Sokoban.niveau().get_pousseurX();
        int PousseurY = Sokoban.niveau().get_pousseurY();

        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_Q:
                Sokoban.niveau().deplacePousseur(PousseurX,PousseurY-1);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                Sokoban.niveau().deplacePousseur(PousseurX,PousseurY+1);
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_Z:
                Sokoban.niveau().deplacePousseur(PousseurX-1,PousseurY);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                Sokoban.niveau().deplacePousseur(PousseurX+1,PousseurY);
                break;
            case KeyEvent.VK_A:
                System.exit(0);
                break;
            case KeyEvent.VK_ESCAPE:
                vue.toggleFullscreen();
                break;
        }
        if (Sokoban.niveau().estGagne()) {
            System.out.println("Niveau terminé !");
            Sokoban.chargerNiveauSuivant();
        }
        vue.getAire().repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // On ne fait rien
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // On ne fait rien non plus
    }

}