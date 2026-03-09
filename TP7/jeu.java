import Global.Configuration;

import javax.swing.*;
import java.util.Scanner;
import java.io.IOException;


public class jeu  {
    public Niveau actuel;
    public Scanner scan;
    public RedacteurNiveau redacteur;

    public jeu(Scanner s) {
        scan = s;
        redacteur = new RedacteurNiveau(System.out);
    }
    Niveau niveau(){
        return actuel;
    }

    public boolean prochainNiveau() {
        try {
            if (scan.hasNext()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Configuration.erreur("Lors du passage au prochain niveau : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}