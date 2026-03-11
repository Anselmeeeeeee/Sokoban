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


    while (my_scanner.hasNextLine()) {
        String ligneOriginale = my_scanner.nextLine();

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

        if (!ligneCases.trim().isEmpty()) {
            lignesNiveau.add(ligneCases);  
        }
    }

    if (lignesNiveau.isEmpty()) {
        return null;
    }

    // Dimensions réelles
    int nbLignes = lignesNiveau.size();
    int nbColonnes = 0;
    for (String ligne : lignesNiveau) {
        nbColonnes = Math.max(nbColonnes, ligne.length());

    }

    
    Niveau niveau = new Niveau(nbLignes, nbColonnes);
    niveau.fixeNom(nomNiveau);


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