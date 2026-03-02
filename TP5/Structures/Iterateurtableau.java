import java.util.*;
import java.io.*;

public class Iterateurtableau <T> implements Iterateur <T>{
    TableauCirculaire <T> Seq;
    int avant;
    int curseur;
    int apres;


    public Iterateurtableau(TableauCirculaire <T> seq){
        this.Seq = seq;
        this.curseur = seq.debut;
        this.avant = (seq.debut - 1 + seq.tab.length) % seq.tab.length;
        this.apres = seq.fin;
    }

    public boolean aProchain() {

        return curseur != Seq.fin;

    }

    public T prochain(){
        if(aProchain()){
            throw new RuntimeException("Pas de prochain");
        }
        avant = curseur;
        curseur = apres;
        apres = (curseur + 1) %Seq.tab.length;
        return Seq.tab[curseur];
    }

    public void supprimer() {

    }
}