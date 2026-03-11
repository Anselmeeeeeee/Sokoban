package Modele;
import Global.Configuration;

import java.util.*;

public class Niveau {
    String nom;
    char[][] carte;
    boolean[][] estUnBut;

    public Niveau(int lignes, int colonnes) {
        this.nom = "";
        this.carte = new char[lignes][colonnes];
        this.estUnBut = new boolean[lignes][colonnes];
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                this.carte[i][j] = ' ';
            }
        }
    }


    public void fixeNom(String s) {
        this.nom = s;
    }


    public void videCase(int i, int j) {
        this.carte[i][j] = ' ';
    }


    public void ajouteMur(int i, int j) {
        this.carte[i][j] = '#';
    }


    public void ajoutePousseur(int i, int j) {
        this.carte[i][j] = '@';
    }


    public void ajouteCaisse(int i, int j) {
        this.carte[i][j] = '$';
    }


    public void ajouteBut(int i, int j) {
        this.carte[i][j] = '.';
        this.estUnBut[i][j] = true;
    }


    public int lignes() {
        return this.carte.length;
    }


    public int colonnes() {
        return this.carte[0].length;
    }

    public String nom() {
        return this.nom;
    }

    public boolean estVide(int i, int j) {
        return this.carte[i][j] == ' ';
    }

    public boolean aMur(int i, int j) {
        return this.carte[i][j] == '#';
    }


    public boolean aPousseur(int i, int j) {
        return this.carte[i][j] == '@';
    }


    public boolean aCaisse(int i, int j) {
        return this.carte[i][j] == '$';
    }


    public boolean aBut(int i, int j) {
        return this.estUnBut[i][j];
    }

    public boolean aCaisseSurBut(int i, int j){
        return aCaisse(i,j)&&aBut(i,j);
    }

    public int get_pousseurX() {
        for (int i = 0; i < lignes(); i++) {
            for (int j = 0; j < colonnes(); j++) {
                if (aPousseur(i, j)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int get_pousseurY() {
        for (int i = 0; i < lignes(); i++) {
            for (int j = 0; j < colonnes(); j++) {
                if (aPousseur(i, j)) {
                    return j;
                }
            }
        }
        return -1;
    }

    public boolean moveautorise(int i, int j) {
        int pL = get_pousseurX();
        int pC = get_pousseurY();
        return (Math.abs(pL - i) + Math.abs(pC - j) == 1) && !aMur(i, j);
    }

    public void deplacePousseur(int nouvelleL, int nouvelleC) {
        int pX = get_pousseurX();
        int pY = get_pousseurY();

        if (!moveautorise(nouvelleL, nouvelleC)) {
            return;
        }

        int di = nouvelleL - pX; // Direction ligne (-1, 0, ou 1)
        int dj = nouvelleC - pY; // Direction colonne (-1, 0, ou 1)


        if (aCaisse(nouvelleL, nouvelleC)) {
            int destCaisseL = nouvelleL + di;
            int destCaisseC = nouvelleC + dj;


            if (estVide(destCaisseL, destCaisseC) || (aBut(destCaisseL, destCaisseC) && !aCaisse(destCaisseL, destCaisseC))) {
                deplaceCaisse(nouvelleL, nouvelleC, destCaisseL, destCaisseC);
            } else {

                return;
            }
        }

        videCase(pX, pY);
        ajoutePousseur(nouvelleL, nouvelleC);
    }

    public void deplaceCaisse(int caisseL, int caisseC, int destL, int destC) {
        videCase(caisseL, caisseC);

        ajouteCaisse(destL, destC);

    }
    public boolean estGagne() {
        for (int i = 0; i < lignes(); i++) {
            for (int j = 0; j < colonnes(); j++) {
                // Si c'est un but, mais qu'il n'y a PAS de caisse dessus
                if (aBut(i, j) && !aCaisse(i, j)) {
                    return false; // Pas encore gagné
                }
            }
        }
        return true; // Toutes les cibles ont une caisse !
    }
}