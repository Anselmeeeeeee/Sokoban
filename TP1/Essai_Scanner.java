import java.util.*;

class Essai_Scanner {
    public static void main(String [] args) {
        Scanner my_scanner;
        int ligne;
        try {
        my_scanner = new Scanner(System.in);
        System.out.println("Saisissez une ligne");
        ligne = my_scanner.nextInt();
        System.out.println("Vous avez saisi la ligne : " + ligne);

        }
        catch (NoSuchElementException e){
            System.err.println("Veuillez rentrez un entier FDP");
        }
    }
}