package entites;

import javax.swing.ImageIcon;

import ressources.Constantes;

public class Vaisseau extends Entite {

    /**** VARIABLES ****/

    /**** CONSTRUCTEUR ****/
    public Vaisseau() {

        // Initialisation des variables de la super classe
        super.xPos = Constantes.X_POS_INIT_VAISSEAU;
        super.yPos = Constantes.Y_POS_VAISSEAU;
        super.largeur = Constantes.LARGEUR_VAISSEAU;
        super.hauteur = Constantes.HAUTEUR_VAISSEAU;
        super.dx = 0;
        super.dy = 0;

        // Adresse des images du vaisseau
        super.strImg1 = "/images/vaisseau.png";
        super.strImg2 = "/images/vaisseauDetruit1.png";
        super.strImg3 = "/images/vaisseauDetruit2.png";

        // Chargement de l'image du vaisseau
        super.ico = new ImageIcon(getClass().getResource(super.strImg1));
        super.img = this.ico.getImage();
    }

    /**** METHODES ****/

    public int deplacementVaisseau() {

        if (this.dx < 0) {

            if (this.xPos > Constantes.LIMITE_GAUCHE_VAISSEAU) {
                this.xPos = this.xPos + this.dx;
            } 
 
        }else if (dx > 0) {
                if (this.xPos + this.dx < Constantes.LIMITE_DROITE_VAISSEAU) {
                    this.xPos = this.xPos + this.dx;
                }
            }
        return this.xPos;
    }
}