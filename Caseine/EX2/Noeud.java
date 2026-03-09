public class Noeud {
    char lettre;
    boolean estFinal;
    Noeud droit; //autre choix
    Noeud gauche; //lettre suivante

    public Noeud() {
        lettre = ' ';
        estFinal = false;
        droit = null;
        gauche = null;
    }
    public Noeud(char lettre, boolean estFinal ){
        this.lettre = lettre;
        this.estFinal=estFinal;
        this.droit = null;
        this.gauche = null;
    }

}