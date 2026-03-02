import java.util.*;
import java.io.*;

class FAP<E extends Comparable<E>> {
    private Liste_Chainee<E> LC;

    public FAP() {
        LC = new Liste_Chainee<>();
    }

    void enfile(E e) {

        Noeud <E> nouveau = new Noeud<>(e);

        if (LC.tete == null){
            LC.tete = nouveau;
            LC.queue = nouveau;
            return;
        }
        if ((e.compareTo(LC.tete.valeur) <= 0)) {
            nouveau.suivant = LC.tete;
            LC.tete = nouveau;
            return;
        }
        Noeud<E> courant = LC.tete;
        while (courant.suivant != null &&
                e.compareTo(courant.suivant.valeur) > 0) {
            courant = courant.suivant;
        }

        nouveau.suivant = courant.suivant;
        courant.suivant = nouveau;

        if (nouveau.suivant == null) {
            LC.queue = nouveau;
        }
    }

    E supprimer_tete(){
        if(estVide()){return null;}
        if (LC.tete.suivant == null){
            E res = LC.tete.valeur;
            LC.tete = null;
            LC.queue = null;
            return res;
        }
        E resultat = LC.queue.valeur;
        Noeud<E> tempo = LC.tete;
        while(tempo.suivant.suivant != null){
            tempo = tempo.suivant;
        }
        tempo.suivant =null;
        LC.queue = tempo;
        return resultat;
    }

    boolean estVide(){
        return LC.estVide();
    }

    public static void main(String[] args) {
        FAP<CoupleFAPInt<Double>> f = new FAP<>();
        f.enfile(new CoupleFAPInt<>(3.14, 3));
        f.enfile(new CoupleFAPInt<>(4.8, 5));
        f.enfile(new CoupleFAPInt<>(5.2, 5));
        f.enfile(new CoupleFAPInt<>(42.0, 1));
        while (!f.estVide()) {
            System.out.println(f.supprimer_tete().element);
        }
    }
}