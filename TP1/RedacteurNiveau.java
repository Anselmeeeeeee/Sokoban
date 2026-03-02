public class RedacteurNiveau {
    void ecrit(Niveau niv) {
        for (int i = 0; i < 42; i++) {
            for (int j = 0; j < 42; j++) {
                System.out.print(niv.map[i][j]);
            }
            System.out.println();
        }
        if (niv.nom() != null) {
            System.out.println("; " + niv.nom());
        }
    }
}
