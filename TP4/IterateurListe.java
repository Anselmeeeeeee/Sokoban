public class IterateurListe<T> implements Iterateur<T> {
    Liste_Chainee<T> LC;
    Noeud<T> avant;
    Noeud<T> curseur;
    Noeud<T> apres;

    public IterateurListe(Liste_Chainee<T> seq) {
        this.LC = seq;
        this.apres = seq.tete;
        this.curseur = null;
        this.avant = null;
    }

    public boolean aProchain() {
        return apres != null;
    }

    public T prochain() {
        if (!aProchain()) {
            throw new RuntimeException("Fin de liste atteinte");
        }
        if (curseur != null) {
            avant = curseur;
        }
        curseur = apres;
        apres = apres.suivant;

        return curseur.valeur;
    }

    public void supprime() {
        if (curseur == null) {
            throw new IllegalStateException("prochain() doit être appelé avant supprime()");
        }

        if (avant == null) {
            LC.tete = apres;
        } else {
            avant.suivant = apres;
        }
        if (apres == null) {
            LC.queue = avant;
        }
        curseur = null;
    }
}