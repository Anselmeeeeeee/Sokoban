import java.util.*;
import java.io.*;

class Liste_Chainee<T> {
    Noeud<T> tete;
    Noeud<T> queue;

    Liste_Chainee() {
        tete = null;
        queue = null;
    }
void insererTete(T element) {
    Noeud<T> tempo = new Noeud<T>(element);

    if (this.tete == null) {
        // Cas 1 : La liste est vide
        this.tete = tempo;
        this.queue = tempo; // Le premier nœud est aussi le dernier
    } else {
        // Cas 2 : La liste contient déjà des éléments
        tempo.suivant = this.tete;
        this.tete = tempo;
    }
}
    public Iterateur<T> iterateur() {
        return new IterateurListe<T>(this);
    }

    void insererQueue(T element){
        Noeud<T> tempo = new Noeud<T>(element);
        this.queue.suivant = tempo;
        queue = queue.suivant;
    }

    T extraireTete(){
        if (this.tete == null){
            throw new RuntimeException("Séquence vide");
        }
        T res = this.tete.valeur;
        this.tete = this.tete.suivant;

        return res;
    }

    boolean estVide(){
        if (this.tete == null){//si la tete est nulle alors la suite l'est aussi 
            return true;
        }
        return false;
    }

    void affiche(){
        Liste_Chainee<T> tempo = new Liste_Chainee<T>();
        tempo.tete = this.tete;
        tempo.queue = this.queue;
        System.out.println("Affichage liste chainee : ");
        while (tempo.tete != null){
            System.out.println(tempo.tete.valeur);
            tempo.tete = tempo.tete.suivant;
        }
    }
}