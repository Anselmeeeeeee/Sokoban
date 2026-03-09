import Global.Configuration;

import java.io.*;
import java.util.Scanner;

public class LecteurNiveaux {
    Scanner my_scanner;

    public LecteurNiveaux(InputStream flux) {
        this.my_scanner = new Scanner(flux);
    }
    
    public Niveau lisProchainNiveau() {
    String nomNiveau = "";
    java.util.List<String> lignesNiveau = new java.util.ArrayList<>();

    System.out.println("test1");
    System.out.println(my_scanner.toString());
    System.out.println(lignesNiveau.toString());
    while (my_scanner.hasNextLine()) {
        System.out.println("testb");
        String ligneOriginale = my_scanner.nextLine();
        System.out.println("testa");

        // Ligne vide = fin niveau
        if (ligneOriginale.trim().isEmpty()) {  
            break;
        }

        
        int posComment = ligneOriginale.indexOf(';');
        String ligneCases = ligneOriginale;
        if (posComment >= 0) {
            nomNiveau = ligneOriginale.substring(posComment + 1).trim();
            ligneCases = ligneOriginale.substring(0, posComment);
        }

        System.out.println(ligneCases);
        if (!ligneCases.trim().isEmpty()) {
            lignesNiveau.add(ligneCases);  
        }
    }
    System.out.println("test2");

    if (lignesNiveau.isEmpty()) {
        return null;
    }
    System.out.println("test3");

    // Dimensions réelles
    int nbLignes = lignesNiveau.size();
    int nbColonnes = 0;
    for (String ligne : lignesNiveau) {
        nbColonnes = Math.max(nbColonnes, ligne.length());
    }
    System.out.println("test4");
    
    Niveau niveau = new Niveau(nbLignes, nbColonnes);
    niveau.fixeNom(nomNiveau);
    System.out.println("test5");

    // Remplissage avec switch
    for (int i = 0; i < nbLignes; i++) {
        String ligne = lignesNiveau.get(i);
        for (int j = 0; j < nbColonnes; j++) {
            char c;
            if (j < ligne.length()) {
                c = ligne.charAt(j);
            } else {
                c = ' ';
            }
            switch (c) {
                case '#': 
                niveau.ajouteMur(i, j); 
                break;
                case '@': 
                niveau.ajoutePousseur(i, j); 
                break;
                case '$': 
                niveau.ajouteCaisse(i, j); 
                break;
                case '.': 
                niveau.ajouteBut(i, j); 
                break;
            }
        }
    }
    return niveau;
    }
}