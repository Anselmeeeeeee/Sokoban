import java.util.*;

public class ConcreteDictionary extends Dictionary {
    private Noeud racine = null;


    @Override
    public void add(String mot) {
        if (mot == null || mot.isEmpty()) return;
        racine = ajouterRec(racine, mot, 0);
    }

    private Noeud ajouterRec(Noeud arbre, String mot, int index) {
        char c = mot.charAt(index);
        boolean estDerniereLettre = (index == mot.length() - 1);

        // Noeud null, on crée le nouveau
        if (arbre == null) {
            arbre = new Noeud(c, estDerniereLettre);
            if (!estDerniereLettre) {
                arbre.gauche = ajouterRec(null, mot, index + 1);
            }
            return arbre;
        }


        if (c == arbre.lettre) {// La lettre correspond
            if (estDerniereLettre) { //vérification de la fin du mot
                arbre.estFinal = true;
            } else {
                arbre.gauche = ajouterRec(arbre.gauche, mot, index + 1);
            }
        }

        else if (c < arbre.lettre) { // Si la lettre à insérer est inférieure à celle d'après, on l'insère avant
            Noeud nouveau = new Noeud(c, estDerniereLettre);
            nouveau.droit = arbre;
            if (!estDerniereLettre) {
                nouveau.gauche = ajouterRec(null, mot, index + 1);
            }
            return nouveau;
        }
        // La lettre est plus grande
        else {
            arbre.droit = ajouterRec(arbre.droit, mot, index);
        }
        return arbre;
    }

    @Override
    public boolean find(String mot) {
        if (mot == null || mot.isEmpty()) return false;
        return findRec(racine, mot, 0);
    }

    private boolean findRec(Noeud n, String mot, int index) {
        if (n==null){
            return false;
        }
        char c = mot.charAt(index);
        if (n.lettre == c){
            if (index == mot.length() - 1) return n.estFinal;
            return findRec(n.gauche, mot, index +1); //lettre suivante
        }
        else if (c > n.lettre){
            return findRec(n.droit, mot, index); //nouvel essai pour trouver cette lettre
        }
        else { //lettre non présente donc mot non présent
            return false;
        }
    }


    @Override
    public Iterator<Character> prefixedDepthFirstIterator() { return new IterPref(); }
    @Override
    public Iterator<Character> infixedDepthFirstIterator() { return new IterInfix(); }
    @Override
    public Iterator<Character> postfixedDepthFirstIterator() { return new IterPost(); }
    @Override
    public Iterator<Character> widthIterator() { return new IterLarg(); }
    @Override
    public Iterator<String> wordIterator() { return new IterMots(); }

    private class IterPref implements Iterator<Character> {
        private Stack<Noeud> st = new Stack<>();
        public IterPref() { if (racine != null) st.push(racine); }
        public boolean hasNext() { return !st.isEmpty(); }
        public Character next() {
            if (!hasNext()) throw new NoSuchElementException();
            Noeud n = st.pop();
            if (n.droit != null) st.push(n.droit);
            if (n.gauche != null) st.push(n.gauche);
            return n.lettre;
        }
    }

    private class IterInfix implements Iterator<Character> {
        private Stack<Noeud> p = new Stack<>();
        public IterInfix() { pG(racine); }
        private void pG(Noeud n) {
            while (n != null) { p.push(n); n = n.gauche; }
        }
        public boolean hasNext() { return !p.isEmpty(); }
        public Character next() {
            if (!hasNext()) throw new NoSuchElementException();
            Noeud n = p.pop();
            char c = n.lettre;
            if (n.droit != null) pG(n.droit);
            return c;
        }
    }

    private class IterPost implements Iterator<Character> {
        private Stack<Noeud> st = new Stack<>();
        private Noeud last = null;
        public IterPost() { pG(racine); }
        private void pG(Noeud n) {
            while (n != null) { st.push(n); n = n.gauche; }
        }
        public boolean hasNext() { return !st.isEmpty(); }
        public Character next() {
            if (!hasNext()) throw new NoSuchElementException();
            while (true) {
                Noeud curr = st.peek();
                if (curr.droit == null || curr.droit == last) {
                    st.pop();
                    last = curr;
                    return curr.lettre;
                } else pG(curr.droit);
            }
        }
    }

    private class IterLarg implements Iterator<Character> {
        private Queue<Noeud> f = new LinkedList<>();
        private Queue<Character> att = new LinkedList<>();
        public IterLarg() { if (racine != null) pD(racine); }
        private void pD(Noeud n) {
            Noeud cur = n;
            while (cur != null) {
                att.add(cur.lettre);
                if (cur.gauche != null) f.add(cur.gauche);
                cur = cur.droit;
            }
        }
        public boolean hasNext() { return !att.isEmpty() || !f.isEmpty(); }
        public Character next() {
            if (!hasNext()) throw new NoSuchElementException();
            if (att.isEmpty()) pD(f.poll());
            char res = att.poll();
            return res;
        }
    }

    private class IterMots implements Iterator<String> {
        private Stack<Noeud> stN = new Stack<>();
        private Stack<StringBuilder> stM = new Stack<>();
        private String nxt = null;
        public IterMots() {
            if (racine != null) {
                stN.push(racine);
                stM.push(new StringBuilder().append(racine.lettre));
                up();
            }
        }
        private void up() {
            nxt = null;
            while (!stN.isEmpty() && nxt == null) {
                Noeud c = stN.pop();
                StringBuilder sb = stM.pop();
                if (c.estFinal) nxt = sb.toString();
                if (c.droit != null) {
                    stN.push(c.droit);
                    StringBuilder sD = new StringBuilder(sb);
                    sD.setCharAt(sD.length() - 1, c.droit.lettre);
                    stM.push(sD);
                }
                if (c.gauche != null) {
                    stN.push(c.gauche);
                    StringBuilder sG = new StringBuilder(sb);
                    sG.append(c.gauche.lettre);
                    stM.push(sG);
                }
            }
        }
        public boolean hasNext() { return nxt != null; }
        public String next() {
            if (!hasNext()) throw new NoSuchElementException();
            String r = nxt;
            up();
            return r;
        }
    }
}