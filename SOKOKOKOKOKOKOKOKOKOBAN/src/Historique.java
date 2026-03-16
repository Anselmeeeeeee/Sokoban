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
        while(!futur.isEmpty()){
            futur.pop();
        }
    }

    public Coup supprimerCoup(){
        if (!historique.isEmpty()){
            Coup coup = historique.pop();
            coup.desexecute();
            futur.push(coup);
            return coup;
        }
        else{
            return null;
        }
    }

    public Coup recupererCoup(){
        if (!historique.isEmpty()){
            Coup coup = futur.pop();
            coup.execute();
            historique.push(coup);
            return coup;
        }
        else{
            return null;
        }
    }

}