package Modele;

import java.util.Stack;
import Modele.Coup;

public class Historique{

    private Stack<Coup> historique;
    private Stack<Coup> futur;

    public Historique() {
        historique = new Stack<>();
        futur = new Stack<>();
    }

    public void jouerCoup(Coup coup){
        historique.push(coup);
        coup.execute();
        futur.clear();
    }

    public Coup supprimerCoup(){
        if (peutsuppr()){
            Coup coup = historique.pop();
            coup.desexecute();
            futur.push(coup);
            return coup;
        }
        else{
            return null;
        }
    }

    public boolean peutsuppr(){
        return !historique.isEmpty();
    }

    public Coup recupererCoup(){
        if (peutrecup()){
            Coup coup = futur.pop();
            coup.execute();
            historique.push(coup);
            return coup;
        }
        else{
            return null;
        }
    }

    public boolean peutrecup(){
        return !futur.isEmpty();
    }

}