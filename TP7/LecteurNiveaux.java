//fichier reconstitué
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Lecteur de fichiers de niveaux au format standard Sokoban (.xsb).
 * Gère la lecture ligne par ligne et la conversion en objet Niveau.
 */
public class LecteurNiveaux {
    private Scanner scanner;

    public LecteurNiveaux(InputStream is) {
        this.scanner = new Scanner(is);
    }

    /**
     * Lit le prochain niveau disponible dans le flux.
     * @return un objet Niveau initialisé, ou null s'il n'y a plus de niveaux.
     */
    public Niveau lisProchainNiveau() {
        if (this.scanner.hasNext()) {
            String nomNiveau = "";
            ArrayList<String> lignesBrutes = new ArrayList<>();

            // 1. Lecture du bloc de texte correspondant au niveau
            while (this.scanner.hasNextLine()) {
                String ligne = this.scanner.nextLine();

                // Une ligne vide marque la fin d'un niveau dans le fichier
                if (ligne.trim().isEmpty()) {
                    if (lignesBrutes.isEmpty()) continue; // Ignore les lignes vides au début
                    break;
                }

                // Gestion des commentaires (caractère ';')
                int indexCommentaire = ligne.indexOf(';');
                String contenuLigne = ligne;

                if (indexCommentaire >= 0) {
                    nomNiveau = ligne.substring(indexCommentaire + 1).trim();
                    contenuLigne = ligne.substring(0, indexCommentaire);
                }

                // On n'ajoute la ligne que si elle contient des données de jeu
                if (!contenuLigne.trim().isEmpty()) {
                    lignesBrutes.add(contenuLigne);
                }
            }

            // Si aucune ligne n'a été lue, on est à la fin du fichier
            if (lignesBrutes.isEmpty()) {
                return null;
            }

            // 2. Calcul des dimensions de la grille
            int nbLignes = lignesBrutes.size();
            int nbColonnesMax = 0;
            for (String s : lignesBrutes) {
                nbColonnesMax = Math.max(nbColonnesMax, s.length());
            }

            // 3. Création et remplissage de l'objet Niveau
            Niveau nouveauNiveau = new Niveau(nbLignes, nbColonnesMax);
            nouveauNiveau.fixeNom(nomNiveau);

            for (int i = 0; i < nbLignes; i++) {
                String ligneCourante = lignesBrutes.get(i);

                for (int j = 0; j < nbColonnesMax; j++) {
                    char caractere;

                    // Si la ligne est plus courte que la largeur max, on complète par du vide
                    if (j < ligneCourante.length()) {
                        caractere = ligneCourante.charAt(j);
                    } else {
                        caractere = ' ';
                    }

                    // Interprétation des caractères standards du Sokoban
                    switch (caractere) {
                        case '#':
                            nouveauNiveau.ajouteMur(i, j);
                            break;
                        case '$':
                            nouveauNiveau.ajouteCaisse(i, j);
                            break;
                        case '.':
                            nouveauNiveau.ajouteBut(i, j);
                            break;
                        case '@':
                            nouveauNiveau.ajoutePousseur(i, j);
                            break;
                        // Note : Les cas '+' (joueur sur but) et '*' (caisse sur but)
                        // pourraient être ajoutés ici selon les besoins de ta classe Niveau.
                    }
                }
            }

            return nouveauNiveau;
        }
        else {
            throw new RuntimeException();
        }
    }

}