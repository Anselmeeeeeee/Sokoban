import java.util.*;
import java.io.*;

class  SequenceTableau{
    int[] tab;
    int taille;
    int fin;

    SequenceTableau(int taille){ //met toutes les valeurs du tableau jusqu'à taille à 0
        this.taille = taille;
        tab = new int[taille];
        for (int i = 0; i<taille; i++){
            tab[i] = 0;
        }
    }
    SequenceTableau(){
        tab = new int[0];
        taille = 0;
        fin = 0;
    }
    void inserer_tete(int element){
        int tempo = tab[0];
        tab[0]=element;
        fin = fin + 1;
        int i = 1 ;
        while (i<fin){
            tab[i]=tempo;
            tempo ++;
            i++;
        }
    }

    void inserer_queue(int element){
        if (fin<taille){
            tab[fin]=element;
            fin +=1;
        }
        else {
            throw new RuntimeException("Séquence pleine");
        }
    }
    int extraire_tete(){
        if (!estVide()){
            int res = tab[0];
            for (int i = 1; i<fin; i++){
                tab[i-1] = tab[i];
            }
            fin --;
            tab[fin]=0;
            return res;
        }
        else {
            throw new RuntimeException("Séquence vide");

        }
    }

    boolean estVide(){
        if (fin==0){
            return true;
        }
        return false;
    }
    public String toString(){
        String s = "";
        for(int i=0;i<taille;i++){
            s += tab[i]+" ";
        }
        return s;
    }

    public static void main(String[] args){
        //tests
        SequenceTableau sT = new SequenceTableau(5);
        System.out.println(sT.estVide());
        sT.inserer_tete(1);
        System.out.println(sT.toString());
        sT.inserer_tete(2);
        System.out.println(sT.toString());
        sT.inserer_queue(3);
        System.out.println(sT.toString());
        sT.inserer_queue(4);
        System.out.println(sT.toString());
        int tete = sT.extraire_tete();
        System.out.println(tete);
        System.out.println(sT.toString());
        tete = sT.extraire_tete();
        System.out.println(tete);
        System.out.println(sT.toString());
    }

}