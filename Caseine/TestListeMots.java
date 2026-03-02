public class TestListeMots extends TestDico {
	public static void main(String[] args) {
		new TestListeMots().run(args);
	}

	@Override
	<E> void traiteNoeud(E n) {
		System.out.println(n);
	}
}
