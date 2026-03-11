package Vue;

import Modele.*;
import Global.Configuration;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public class InterfaceGraphique implements Runnable {
    jeu jeuSokoban;
    JFrame frame;
    NiveauGraphique aire;

    public InterfaceGraphique(jeu j) {
        this.jeuSokoban = j;
        this.aire = new NiveauGraphique(j);

    }

    public NiveauGraphique getAire() {
        return aire;
    }

    @Override
    public void run() {
        frame = new JFrame("Sokoban DIOOOOOOO");
        frame.add(aire);
        aire.setFocusable(true);
        aire.requestFocusInWindow();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setVisible(true);

    }

    public void toggleFullscreen() {
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (device.getFullScreenWindow() != null) {
            device.setFullScreenWindow(null);
        } else {
            device.setFullScreenWindow(frame);
        }
    }

    public NiveauGraphique getNiveauGraphique() {
        return aire;
    }
    /*
    public static void demarrer(jeu j) {
        // Utilisation de invokeLater pour la sécurité des threads Swing
        SwingUtilities.invokeLater(new InterfaceGraphique(j));
    }

     */
}