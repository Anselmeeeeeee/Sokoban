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
        frame = new JFrame("Sokoban de ses morts");
        frame.add(new NiveauGraphique(jeuSokoban));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Passage automatique en plein écran
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        device.setFullScreenWindow(frame);

        // Mise à jour de l'état pour que toggleFullscreen fonctionne toujours
        maximized = true;
    }

    public static void demarrer(jeu j) {
        // Utilisation de invokeLater pour la sécurité des threads Swing
        SwingUtilities.invokeLater(new InterfaceGraphique(j));
    }
}