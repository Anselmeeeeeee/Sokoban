import Global.Configuration;

import java.util.*;

public class Niveau {
    String nom;
    char[][] carte;

    public Niveau(int lignes, int colonnes) {
        this.nom = "";
        this.carte = new char[lignes][colonnes];
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                this.carte[i][j] = ' ';
            }
        }
    }

    void fixeNom(String s) {
        this.nom = s;
    }


    void videCase(int i, int j) {
        this.carte[i][j] = ' ';
    }


    void ajouteMur(int i, int j) {
        this.carte[i][j] = '#';
    }


    void ajoutePousseur(int i, int j) {
        this.carte[i][j] = '@';
    }


    void ajouteCaisse(int i, int j) {
        this.carte[i][j] = '$';
    }


    void ajouteBut(int i, int j) {
        this.carte[i][j] = '.';
    }


    int lignes() {
        return this.carte.length;
    }


    int colonnes() {
        return this.carte[0].length;
    }
   
    String nom() {
        return this.nom;
    }

    boolean estVide(int i, int j) {
        return this.carte[i][j] == ' ';
    }

    boolean aMur(int i, int j) {
        return this.carte[i][j] == '#';
    }


    boolean aPousseur(int i, int j) {
        return this.carte[i][j] == '@';
    }


    boolean aCaisse(int i, int j) {
        return this.carte[i][j] == '$';
    }


    boolean aBut(int i, int j) {
        return this.carte[i][j] == '.';
    }
}