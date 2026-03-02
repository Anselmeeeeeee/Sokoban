import java.util.NoSuchElementException;

public class IterateurTableau<T> implements Iterateur<T> {
    int curseur;
    int apres;
    TableauCirculaire<T> seq;

    public IterateurTableau(TableauCirculaire<T> seq) {
        this.seq = seq;
        apres = seq.debut;
        curseur = -1;
    }

    public boolean aProchain() {
        return apres != seq.fin;
    }

    public T prochain() {
        if (!aProchain()) {
            throw new RuntimeException("Fin de liste atteinte");
        }

        curseur = apres;
        apres = (apres + 1) % seq.tab.length;
        return seq.tab[curseur];
    }

    public void supprime(){
        if (curseur == -1) {
            throw new IllegalStateException("prochain() doit être appelé avant supprime()");
        }
        else {
            int souvenir = curseur;
            while (aProchain()) {
                seq.tab[curseur] = seq.tab[apres];
                curseur = apres ;
                apres = (apres + 1) % seq.tab.length;
            }

            seq.tab[(seq.fin - 1 + seq.tab.length) % seq.tab.length] = null;
            seq.fin = (seq.fin - 1 + seq.tab.length) % seq.tab.length;
            seq.nbelem = seq.nbelem - 1 ;
            apres = souvenir ;
            curseur = -1;
        }
    }
}