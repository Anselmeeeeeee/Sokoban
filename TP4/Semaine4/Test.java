class Test{
    public static void main (String[] args){
        /*Liste_Chainee LC = new Liste_Chainee();
        LC.insererTete(0);
        LC.insererTete(1);
        LC.insererTete(2);
        LC.insererTete(0);
        LC.affiche();
        Iterateur it = LC.iterateur();
        while (it.aProchain()) {
            int n = it.prochain();
            if (n == 0)
                it.supprimer();
        }
        */
        TableauCirculaire TC = new TableauCirculaire(5);
        TC.inserer_tete(1);
        TC.inserer_tete(2);
        TC.inserer_queue(3);
        
        System.out.println(TC.toString());
    }
}