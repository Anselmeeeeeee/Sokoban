import java.util.*;
public class ConcreteDictionary extends Dictionary {
	TreeSet<String> dico = new TreeSet<>();
	@Override
	public void add(String mot) {
		dico.add(mot);
	}

	@Override
	public boolean find(String mot) {
		return dico.contains(mot);
	}

	@Override
	public Iterator <String> wordIterator() {
		return dico.iterator();
	}
}
