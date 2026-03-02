import java.util.*;
import java.io.*;

public class Iterateurliste <T> implements Iterateur <T>{
    Liste_Chainee <T> Liste;
    Noeud <T> curseur;
    Noeud <T> ancien;
    Noeud <T> apres;

    public Iterateurliste(Liste_Chainee <T> liste){
        this.Liste = liste;
        this.apres = liste.tete;
        this.curseur = null;
        this.ancien = null;
    }

    public boolean aProchain() {
        return apres != null;
    }

    public T prochain(){
        if (aProchain()) {
            throw new RuntimeException("Pas de prochain");
        }
        if ( curseur != null){
            ancien = curseur;
        }
        curseur = apres;
        apres = apres.suivant;
        return curseur.valeur;
    }

    public void supprimer(){
        if ( curseur == null ){
           throw new RuntimeException("IllegalStateException");
        }
        if ( ancien == null){
            Liste.tete = apres;
        }
        else{
            ancien.suivant = apres;
        }
        if ( apres == null){
            Liste.queue = ancien ;
        }
        curseur = null;
    }
}