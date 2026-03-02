class runner {
    public static void main (String[] args){
        Liste_Chainee LC = new Liste_Chainee();
        LC.insererTete(1);
        LC.affiche();
        LC.insererTete(3);
        LC.affiche();
        LC.insererQueue(5);
        LC.affiche();
        int a = LC.extraireTete();
        System.out.println("tete" + a);
        LC.affiche();
        LC.extraireTete();
        LC.extraireTete();
        if (LC.estVide()){
            System.out.println("La liste est vide tut tut fils de pute ");
        }
    }
}