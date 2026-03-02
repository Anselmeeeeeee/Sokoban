import java.util.*;

class TableauCirculaire<T> {
    T[] tab;
    int nbelem;
    int fin;
    int debut;

    @SuppressWarnings("unchecked")
    TableauCirculaire(int taille) {
        tab = (T[]) new Object[taille];
        nbelem = 0;
        debut = 0;
        fin = 0;
    }

    public IterateurTableau<T> iterateur(){
        return new IterateurTableau<T>(this);
    }

    @SuppressWarnings("unchecked")
    private void redimensionner() {
        T[] nouveauTab = (T[]) new Object[tab.length * 2];
        for (int i = 0; i < nbelem; i++) {
            nouveauTab[i] = tab[(debut + i) % tab.length];
        }
        tab = nouveauTab;
        debut = 0;
        fin = nbelem;
    }

    void inserer_tete(T element) {
        if (estPlein())
            redimensionner();
        debut = (debut - 1 + tab.length) % tab.length;
        tab[debut] = element;
        nbelem++;
    }

    void inserer_queue(T element) {
        if (estPlein())
            redimensionner();

        tab[fin] = element;
        fin = (fin + 1) % tab.length;
        nbelem++;
    }

    T extraire_tete() {
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

    public String to_String() {
        if (this.estVide()) {
            return "tableau vide";
        }
        String tableau = "";
        int i = debut;
        for ( int cpt = 0; cpt < nbelem ; cpt++){
            tableau = tableau.concat(tab[i]+" ");
            i = (i + 1) % tab.length;
        }
        return tableau;
    }

    public static void main(String[] args) {
        TableauCirculaire<Integer> tC = new TableauCirculaire<>(4);
        tC.inserer_tete(5);
        tC.inserer_queue(1);
        tC.inserer_queue(2);
        tC.inserer_queue(3);
        System.out.println("Plein : " + tC.to_String());

        tC.inserer_queue(4);
        System.out.println("Après agrandissement : " + tC.to_String());
    }
}