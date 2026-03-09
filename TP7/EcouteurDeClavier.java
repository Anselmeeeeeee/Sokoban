import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class EcouteurDeClavier implements KeyListener {
    NiveauGraphique NG;
    jeu Sokoban;

    public EcouteurDeClavier(NiveauGraphique NG, jeu Sokoban) {
        this.NG = NG;
        this.Sokoban = Sokoban;
    }

    public void keyPressed(KeyEvent e) {
        int PousseurX = Sokoban.niveau().get_pousseurX();
        int PousseurY = Sokoban.niveau().get_pousseurY();
        JFrame frame = (JFrame) javax.swing.SwingUtilities.getWindowAncestor(NG);
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_Q:
                Sokoban.niveau().deplacePousseur(PousseurX,PousseurY-1);
                break;
                case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                    Sokoban.niveau().deplacePousseur(PousseurX,PousseurY+1);
                    break;
                    case KeyEvent.VK_UP:
                        case KeyEvent.VK_Z:
                        Sokoban.niveau().deplacePousseur(PousseurX-1,PousseurY);
                        break;
                        case KeyEvent.VK_DOWN:
                            case KeyEvent.VK_S:
                            Sokoban.niveau().deplacePousseur(PousseurX+1,PousseurY);
                            break;
                            case KeyEvent.VK_A:
                                System.exit(0);
                                break;
            case KeyEvent.VK_ESCAPE:
                if (device.getFullScreenWindow() != null) {
                    device.setFullScreenWindow(null);
                } else {
                    device.setFullScreenWindow(frame);
                }
                break;
        }
        NG.repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // On ne fait rien
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // On ne fait rien non plus
    }

}