import java.util.*;

class TableauCirculaire {
    int[] tab;
    int nbelem;
    int fin;
    int debut;

    TableauCirculaire(int taille) {
        tab = new int[taille];
        nbelem = 0;
        debut = 0;
        fin = 0;
    }


    private void redimensionner() {
        int[] nouveauTab = new int[tab.length * 2];
        for (int i = 0; i < nbelem; i++) {

            nouveauTab[i] = tab[(debut + i) % tab.length];
        }
        tab = nouveauTab;
        debut = 0;
        fin = nbelem;
    }

    void inserer_tete(int element) {
        if (estPlein())
            redimensionner();
        debut = (debut - 1 + tab.length) % tab.length;
        tab[debut] = element;
        nbelem++;
    }

    void inserer_queue(int element) {
        if (estPlein())
            redimensionner();

        tab[fin] = element;
        fin = (fin + 1) % tab.length;
        nbelem++;
    }

    int extraire_tete() {
        if (estVide())
            throw new RuntimeException("Séquence vide");

        int res = tab[debut];
        tab[debut] = 0;
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

    public static void main(String[] args) {
        TableauCirculaire tC = new TableauCirculaire(4);
        tC.inserer_tete(5);
        tC.inserer_queue(1);
        tC.inserer_queue(2);
        tC.inserer_queue(3);
        System.out.println("Plein : " + tC);

        tC.inserer_queue(4);
        System.out.println("Après agrandissement : " + tC);
    }
}