package jeu;

import javax.swing.JPanel;

import entites.GroupeAliens;
import entites.TirVaisseau;
import entites.Vaisseau;

import java.awt.*;

import ressources.Chrono;
import ressources.Clavier;
import ressources.Constantes;

public class Scene extends JPanel {
    
/**** VARIABLES ****/

    public Vaisseau vaisseau = new Vaisseau();
    public GroupeAliens groupeAliens = new GroupeAliens();
    public TirVaisseau tirVaisseau = new TirVaisseau();
    
    
/**** CONSTRUCTEUR ****/

public Scene() {
    super();

    this.setFocusable(true);
    this.requestFocusInWindow();
    this.addKeyListener(new Clavier());
    Thread chronoEcran = new Thread(new Chrono());
    chronoEcran.start();
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
    this.vaisseau.dessinVaisseau(g2);

    // Dessin du groupe d'aliens
    this.groupeAliens.dessinAliens(g2);

    // Dessin du tir du vaisseau
    this.tirVaisseau.dessinTirVaisseau(g2);

    // détection contact tirVaisseau avec alien
    this.groupeAliens.tirVaisseauToucheAlien(this.tirVaisseau);
}

}