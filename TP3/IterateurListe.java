public class IterateurListe implements Iterateur {
    Liste_Chainee LC;
    Noeud avant;
    Noeud curseur;
    Noeud apres;

    public IterateurListe(Liste_Chainee seq) {
        this.LC = seq;
        this.apres = seq.tete;
        this.curseur = null;
        this.avant = null;
    }

    public boolean aProchain() {
        return apres != null;
    }

    public int prochain() {
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