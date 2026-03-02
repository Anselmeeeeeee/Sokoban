class runner {
    public static void main (String[] args){
        /*Liste_Chainee LC = new Liste_Chainee();
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
        */
        /*
         Liste_Chainee LC = new Liste_Chainee();
         LC.insererTete(0);
         LC.insererTete(1);
         LC.insererTete(0);
         LC.insererQueue(2);
         LC.insererQueue(0);
         LC.insererQueue(3);
         LC.insererQueue(0);
         LC.affiche();
         Iterateur IT = LC.iterateur();
         while (IT.aProchain()) {
              int n = IT.prochain();
              if (n == 0)
                   IT.supprime();
         }
         LC.affiche();*/

        TableauCirculaire TC = new TableauCirculaire(10);
        TC.inserer_tete(0);
        TC.inserer_tete(2);
        TC.inserer_tete(0);
        TC.inserer_tete(1);
        TC.inserer_tete(0);
        TC.inserer_queue(3);
        TC.inserer_queue(0);
        System.out.println(TC.to_String());
        Iterateur IT = TC.iterateur();
        while (IT.aProchain()) {
            int n = IT.prochain();
            if (n == 0)
                IT.supprime();
        }
        System.out.println(TC.to_String());

    }
}