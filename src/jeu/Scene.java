package jeu;

import javax.swing.JPanel;

import javafx.scene.canvas.Canvas;
import java.awt.*;

import javafx.scene.transform.Transform;
import ressources.Constantes;

public class Scene extends JPanel {
    
/**** VARIABLES ****/

/**** CONSTRUCTEUR ****/

public Scene() {
    super();
}


/**** METHODES ****/
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    Graphics g2 = (Graphics2D) g;
    g2.setColor(Color.BLACK);
    g2.fillRect(0, 0, Constantes.LARGEUR_FENETRE, Constantes.HAUTEUR_FENETRE);

    g2.setColor(Color.GREEN);
    g2.fillRect(30, 530, 535, 5);
}

}