package Vue;
import Modele.*;
import Global.Configuration;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class NiveauGraphique extends JComponent {
    Image mur, pousseur, caisse, but, sol, caissesurbut;
    public jeu JeuSokoban;
    public int tailleCase, offsetX, offsetY;


    public NiveauGraphique(jeu j) {
        this.JeuSokoban = j;
        try {
            mur = ImageIO.read(Configuration.ouvre("res/Images/Mur.png"));
            pousseur = ImageIO.read(Configuration.ouvre("res/Images/Pousseur.png"));
            caisse = ImageIO.read(Configuration.ouvre("res/Images/Caisse.png"));
            but = ImageIO.read(Configuration.ouvre("res/Images/But.png"));
            sol = ImageIO.read(Configuration.ouvre("res/Images/Dioo.png"));
            caissesurbut = ImageIO.read(Configuration.ouvre("res/Images/Caisse_sur_but.png"));
        } catch (IOException e) {
            Configuration.erreur("impossible de charger les images : " + e.getMessage());
        }
    }

    public int[] coordonneesCase(int x, int y) {
        int j = (x - offsetX) / tailleCase;
        int i = (y - offsetY) / tailleCase;
        if (i >= 0 && i < JeuSokoban.niveau().lignes() && j >= 0 && j < JeuSokoban.niveau().colonnes()) {
            return new int[]{i, j};
        }
        return null;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D drawable = (Graphics2D) g;
        Niveau niveau = JeuSokoban.niveau();

        if ( niveau == null ) {
            return;
        }
        int width = getWidth();
        int height = getHeight();

        int nbL = niveau.lignes();
        int nbC = niveau.colonnes();

        tailleCase = Math.min(width / nbC, height / nbL);

        offsetX = (width - (nbC * tailleCase)) / 2;
        offsetY = (height - (nbL * tailleCase)) / 2;

        drawable.clearRect(0,0,width,height);

        drawable.drawImage(sol, 0, 0, width, height, null);
        for (int i=0; nbL > i; i++) {
            for  (int j=0; j < nbC; j++) {
                int x = offsetX + (j * tailleCase);
                int y = offsetY + (i * tailleCase);

                if (niveau.aBut(i, j)) {
                    drawable.drawImage(but, x, y, tailleCase, tailleCase, null);
                }
                if (niveau.aMur(i, j)) {
                    drawable.drawImage(mur, x, y, tailleCase, tailleCase, null);
                }
                if (niveau.aCaisseSurBut(i,j)){
                    drawable.drawImage(caissesurbut, x, y, tailleCase, tailleCase, null);
                } else if (niveau.aCaisse(i, j)) {
                    drawable.drawImage(caisse, x, y, tailleCase, tailleCase, null);
                } else if (niveau.aPousseur(i, j)) {
                    drawable.drawImage(pousseur, x, y, tailleCase, tailleCase, null);
                }
            }
        }
    }
}