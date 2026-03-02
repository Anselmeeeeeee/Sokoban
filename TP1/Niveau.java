import java.util.*;
import java.io.*;
class Niveau {
    String nom ;
    char [][] map = new char[42][42]; 
    
    public Niveau() {
        for (int i = 0; i < 42; i++) {
            Arrays.fill(map[i], ' ');
        }
    }

    public static void main(String[] args) throws Exception {
        LecteurNiveau lecteur = new LecteurNiveau();
        try (InputStream in = new FileInputStream("Original.txt")) {
            Niveau niv = lecteur.lisProchainNiveau(in);
            new RedacteurNiveau().ecrit(niv);
        }
    }

    public void fixeNom(String s){
        this.nom = s ;
    }

    public void videCase(int i, int j){
        this.map[i][j] = ' ';
    }

    public void ajouteMur(int i, int j){
        this.map[i][j]='#';
    }

    public void ajoutePousseur(int i, int j){
        this.map[i][j]='@';
    }

    public void ajouteCaisse(int i, int j){
        this.map[i][j]='$';
    }

    public void ajouteBut(int i, int j){
        this.map[i][j]='.';
    }

    public int lignes(){
        return this.map[0].length;
    }

    public int colonnes(){
        return this.map.length;
    }

    public String nom(){
        return this.nom;
    }

    public boolean estVide(int l, int c ){
        boolean vide = false;
        if (this.map[l][c] == ' '){
            vide = true;
        }
        return vide;
    }

    public boolean aMur(int l, int c ){
        boolean mur = false;
        if (this.map[l][c] == '#'){
            mur = true;
        }
        return mur;
    }

    public boolean aBut(int l, int c ){
        boolean but = false;
        if (this.map[l][c] == '.'){
            but = true;
        }
        return but;
    }

    public boolean aPousseur(int l, int c ){
        boolean pousseur = false;
        if (this.map[l][c] == '@'){
            pousseur = true;
        }
        return pousseur;
    }

    public boolean aCaisse(int l, int c ){
        boolean caisse = false;
        if (this.map[l][c] == '@'){
            caisse = true;
        }
        return caisse;
    }

}