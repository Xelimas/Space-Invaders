package entites;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import jeu.spaceInvadersMain;
import ressources.Chrono;
import ressources.Constantes;

public class TirAlien extends Entite {

    /**** VARIABLES ****/
    Random hasard = new Random();

    /**** CONSTRUCTEUR ****/
    public TirAlien(int[] tabPositionAlien) {

        // Initialisation des variables de la super classe
        super.xPos = tabPositionAlien[0] + Constantes.LARGEUR_ALIEN / 2 - 1;
        super.yPos = tabPositionAlien[1] + Constantes.HAUTEUR_ALIEN;
        super.largeur = Constantes.LARGEUR_ALIEN;
        super.hauteur = Constantes.HAUTEUR_ALIEN;
        super.dx = 0;
        super.dy = Constantes.DY_TIR_ALIEN;

        // Adresse des images du vaisseau
        super.strImg1 = "/images/tirAlien1.png";
        super.strImg2 = "/images/tirAlien2.png";
        super.strImg3 = "";

        // Chargement de l'image du vaisseau
        if (hasard.nextInt(2) == 0) {
            super.ico = new ImageIcon(getClass().getResource(super.strImg1));
        } else {
            super.ico = new ImageIcon(getClass().getResource(super.strImg2));
        }
        super.img = this.ico.getImage();
    }

    /**** METHODES ****/

    public int deplacementTirAlien() {
        if (Chrono.compteTours % 4 == 0) {
            if (this.yPos < 600) {
                this.yPos = this.yPos + Constantes.DY_TIR_ALIEN;
            }
        }
        return yPos;
    }

    public void dessinTirAlien(Graphics g) {
        g.drawImage(this.img, this.xPos,this.deplacementTirAlien(), null);
    }
}