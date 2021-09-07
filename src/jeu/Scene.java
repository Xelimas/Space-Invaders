package jeu;

import javax.swing.JPanel;

import entites.Vaisseau;

import java.awt.*;
import ressources.Constantes;

public class Scene extends JPanel {
    
/**** VARIABLES ****/

    public Vaisseau vaisseau = new Vaisseau();

/**** CONSTRUCTEUR ****/

public Scene() {
    super();
}


/**** METHODES ****/
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    // Dessin du fond d'écran
    Graphics g2 = (Graphics2D) g;
    g2.setColor(Color.BLACK);
    g2.fillRect(0, 0, Constantes.LARGEUR_FENETRE, Constantes.HAUTEUR_FENETRE);

    // Dessin ligne verte
    g2.setColor(Color.GREEN);
    g2.fillRect(30, 530, 535, 5);

    // Dessin du vaisseau
    g2.drawImage(this.vaisseau.getImg(), this.vaisseau.getxPos(), this.vaisseau.getyPos(), null);
}

}