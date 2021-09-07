package entites;

import javax.swing.ImageIcon;
import ressources.Constantes;

public class Alien extends Entite {
    
    public Alien(int xPos, int yPos, String strImg1, String strImg2) {

        // Initialisation des variables de la super classe
        super.xPos = xPos;
        super.yPos = yPos;
        super.largeur = Constantes.LARGEUR_ALIEN;
        super.hauteur = Constantes.HAUTEUR_ALIEN;
        super.dx = 0;
        super.dy = 0;

        // Adresse des images du vaisseau
        super.strImg1 = strImg1;
        super.strImg2 = strImg2;
        super.strImg3 = "/images/alienMeurt.png";

        // Chargement de l'image du vaisseau
        super.ico = new ImageIcon(getClass().getResource(super.strImg1));
        super.img = this.ico.getImage();
    }
}