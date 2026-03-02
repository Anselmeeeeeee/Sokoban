class Noeud {
    int valeur;
    Noeud suivant;

    public Noeud(int elem){
        this.valeur = elem;
        this.suivant = null;
    }

    public Noeud next(){
        return this.suivant; 
    }
}