import Global.Configuration;
import javax.swing.*;
import java.awt.*;

public class InterfaceGraphique implements Runnable {
    jeu jeuSokoban;
    boolean maximized;
    JFrame frame;

    public InterfaceGraphique(jeu j) {
        this.jeuSokoban = j;
    }

    @Override
    public void run() {
        frame = new JFrame("Sokoban DIOOOOOOO");
        NiveauGraphique aire = new NiveauGraphique(jeuSokoban);
        frame.add(aire);
        aire.setFocusable(true);
        aire.requestFocusInWindow();

        // ON AJOUTE L'ÉCOUTEUR ICI
        aire.addMouseListener(new EcouteurDeSouris(aire, jeuSokoban));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        EcouteurDeClavier edc = new EcouteurDeClavier(aire, jeuSokoban);
        aire.addKeyListener(edc);

        // Passage automatique en plein écran
        //GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //GraphicsDevice device = env.getDefaultScreenDevice();
        //device.setFullScreenWindow(frame);

        // Mise à jour de l'état pour que toggleFullscreen fonctionne toujours
        maximized = true;
    }

    public static void demarrer(jeu j) {
        // Utilisation de invokeLater pour la sécurité des threads Swing
        SwingUtilities.invokeLater(new InterfaceGraphique(j));
    }
}