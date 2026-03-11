import java.util.*;

public class TableauCirculaire <T>{
    T [] tab;
    int nbelem;
    int fin;
    int debut;

    @SuppressWarnings("unchecked")
    public TableauCirculaire(int taille) {
        tab =(T[]) new Object [taille];
        nbelem = 0;
        debut = 0;
        fin = 0;
    }

    public Iterateur <T> iterateur(){

        return new Iterateurtableau <T>(this);

    }
    @SuppressWarnings("unchecked")
    private void redimensionner() {
        T[] nouveauTab =(T[]) new Object [tab.length * 2];
        for (int i = 0; i < nbelem; i++) {

            nouveauTab[i] = tab[(debut + i) % tab.length];
        }
        tab = nouveauTab;
        debut = 0;
        fin = nbelem;
    }

    public void inserer_tete(T element) {
        if (estPlein())
            redimensionner();
        debut = (debut - 1 + tab.length) % tab.length;
        tab[debut] = element;
        nbelem++;
    }

    public void inserer_queue(T element) {
        if (estPlein())
            redimensionner();

        tab[fin] = element;
        fin = (fin + 1) % tab.length;
        nbelem++;
    }

    Object extraire_tete() {
        if (estVide())
            throw new RuntimeException("Séquence vide");

        T res = tab[debut];
        tab[debut] = null;
        debut = (debut + 1) % tab.length;
        nbelem--;
        return res;
    }

    boolean estPlein() {
        return nbelem == tab.length;
    }
    boolean estVide() {
        return nbelem == 0;
    }

    public String toString() {
        return Arrays.toString(tab) + " | nbelem: " + nbelem;
    }

    /*public static void main(String[] args) {
        TableauCirculaire tC = new TableauCirculaire(4);
        tC.inserer_tete(5);
        tC.inserer_queue(1);
        tC.inserer_queue(2);
        tC.inserer_queue(3);
        System.out.println("Plein : " + tC);

        tC.inserer_queue(4);
        System.out.println("Après agrandissement : " + tC);
    }
    */

}