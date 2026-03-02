class Noeud <T>{
    T valeur;
    Noeud<T> suivant;

    public Noeud(T elem){
        this.valeur = elem;
        this.suivant = null;
    }

    public Noeud <T> next(){

        return this.suivant;

    }
}