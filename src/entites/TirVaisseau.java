package entites;

import java.awt.Graphics;
import javax.swing.ImageIcon;

import jeu.spaceInvadersMain;
import ressources.Audio;
import ressources.Constantes;

public class TirVaisseau extends Entite {

    /**** VARIABLES ****/

    private boolean vaisseauTire = false;

    /**** CONSTRUCTEUR ****/
    public TirVaisseau() {

        // Initialisation des variables de la super classe
        super.xPos = 0;
        super.yPos = Constantes.Y_POS_VAISSEAU - Constantes.HAUTEUR_TIR_VAISSEAU;
        super.largeur = Constantes.LARGEUR_TIR_VAISSEAU;
        super.hauteur = Constantes.HAUTEUR_TIR_VAISSEAU;
        super.dx = 0;
        super.dy = Constantes.DY_TIR_VAISSEAU;

        // Adresse des images du vaisseau
        super.strImg1 = "/images/tirVaisseau.png";
        super.strImg2 = "";
        super.strImg3 = "";

        // Chargement de l'image du vaisseau
        super.ico = new ImageIcon(getClass().getResource(super.strImg1));
        super.img = this.ico.getImage();
    }

    /**** METHODES ****/

    public boolean isVaisseauTire() {
        return vaisseauTire;
    }

    public void setVaisseauTire(boolean vaisseauTire) {
        this.vaisseauTire = vaisseauTire;
    }

    public int deplacementTirVaisseau() {
        if (this.vaisseauTire == true) {
            if (this.yPos > 0) {
                this.yPos = this.yPos - Constantes.DY_TIR_VAISSEAU;
            } else {
                this.vaisseauTire = false;
            }
        }
        return yPos;
    }

    public void dessinTirVaisseau(Graphics g) {
        if (this.vaisseauTire == true) {
            g.drawImage(this.img, this.xPos, this.deplacementTirVaisseau(), null);
        }
    }

    public boolean tueAlien(Alien alien) {
        if (this.yPos < alien.getyPos() + alien.getHauteur() && this.yPos + this.hauteur > alien.getyPos()
                && this.xPos + this.largeur > alien.getxPos() && this.xPos < alien.getxPos() + alien.getLargeur()) {
            Audio.playSound("/sons/sonAlienMeurt.wav");
            return true;
        } else {
            return false;
        }
    }

    // Renvoie vrai si le tir du vaisseau est ?? hauteur des ch??teaux
    private boolean tirVaisseauAHauteurDeChateau() {
        if (this.yPos < Constantes.Y_POS_CHATEAU + Constantes.HAUTEUR_CHATEAU
                && this.yPos + this.hauteur > Constantes.Y_POS_CHATEAU) {
            return true;
        } else {
            return false;
        }
    }

    // Renvoie le num??ro du ch??teau (0,1,2 ou 3) dans la zone de tir du vaisseau
    private int chateauProche() {
        int numeroChateau = -1;
        int colonne = -1;
        while (numeroChateau == -1 && colonne < 4) {
            colonne++;
            if (this.xPos + this.largeur > Constantes.MARGE_FENETRE + Constantes.X_POS_INIT_CHATEAU
                    + colonne * (Constantes.LARGEUR_CHATEAU + Constantes.ECART_CHATEAU)
                    && this.xPos < Constantes.MARGE_FENETRE + Constantes.X_POS_INIT_CHATEAU + Constantes.LARGEUR_CHATEAU
                            + colonne * (Constantes.LARGEUR_CHATEAU + Constantes.ECART_CHATEAU)) {
                numeroChateau = colonne;
            }
        }
        return numeroChateau;
    }

    // Renvoie l'abscisse du tir du vaisseau lors du contact avec un ch??teau
    private int abscisseContactTirChateau(Chateau chateau) {
        int xPosTirVaisseau = -1;
        if (this.xPos + this.largeur > chateau.getxPos()
                && this.xPos < chateau.getxPos() + Constantes.LARGEUR_CHATEAU) {
            xPosTirVaisseau = this.xPos;
        }
        return xPosTirVaisseau;
    }

    // Renvoie num??ro ch??teau touch?? et abscisse du tir
    public int[] tirVaisseauToucheChateau() {
        int[] tabRep = { -1, -1 };
        if (this.tirVaisseauAHauteurDeChateau() == true) { // le tir est ?? hauteur du ch??teau
            tabRep[0] = this.chateauProche(); // enregistre le num??ro du ch??teau touch?? dans tabRep[1]
            if (tabRep[0] != -1) {
                // Enregistre l'abscisse du tir du vaisseau lors du contact avec le ch??teau dans
                // tabRep[1]
                tabRep[1] = this.abscisseContactTirChateau(spaceInvadersMain.scene.tabChateaux[tabRep[0]]);
            }
        }
        return tabRep;
    }

    public void tirVaisseauDetruitChateau(Chateau tabChateaux[]) {
        int[] tab = this.tirVaisseauToucheChateau(); // Contient (-1,-1) ou le num??ro du ch??teau touch??
        if (tab[0] != -1) { // un ch??teau est touch??
            if (tabChateaux[tab[0]].trouveBrique(tabChateaux[tab[0]].trouveColonneChateau(tab[1])) != -1) {
                tabChateaux[tab[0]].casseBriques(tab[1]); // D??truit les briques du ch??teau touch??
                this.yPos = -1; // On fait disparaitre le tir et on r??active le canon du vaisseau
            }
        }
    }

    public boolean detruitSoucoupe(Soucoupe soucoupe) {
        if (this.yPos < soucoupe.getyPos() + soucoupe.getHauteur() && this.yPos + this.hauteur > soucoupe.getyPos()
                && this.xPos + this.largeur > soucoupe.getxPos()
                && this.xPos < soucoupe.getxPos() + soucoupe.getLargeur()) {
            this.vaisseauTire = false;
            return true;
        } else {
            return false;
        }
    }

}