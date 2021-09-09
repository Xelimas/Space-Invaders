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
        super.largeur = Constantes.LARGEUR_TIR_ALIEN;
        super.hauteur = Constantes.HAUTEUR_TIR_ALIEN;
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
        g.drawImage(this.img, this.xPos, this.deplacementTirAlien(), null);
    }

    // Renvoie vrai si le tir de l'alien est à hauteur des châteaux
    private boolean tirAlienAHauteurDeChateau() {
        if (this.yPos < Constantes.Y_POS_CHATEAU + Constantes.HAUTEUR_CHATEAU
                && this.yPos + this.hauteur > Constantes.Y_POS_CHATEAU) {
            return true;
        } else {
            return false;
        }
    }

    // Renvoie le numéro du château (0,1,2 ou 3) dans la zone de tir du vaisseau
    private int chateauProche() {
        int numeroChateau = -1;
        int colonne = -1;
        while (numeroChateau == -1 && colonne < 4) {
            colonne++;
            if (this.xPos + this.largeur - 1 > Constantes.MARGE_FENETRE + Constantes.X_POS_INIT_CHATEAU
                    + colonne * (Constantes.LARGEUR_CHATEAU + Constantes.ECART_CHATEAU)
                    && this.xPos + 1 < Constantes.MARGE_FENETRE + Constantes.X_POS_INIT_CHATEAU
                            + Constantes.LARGEUR_CHATEAU
                            + colonne * (Constantes.LARGEUR_CHATEAU + Constantes.ECART_CHATEAU)) {
                numeroChateau = colonne;
            }
        }
        return numeroChateau;
    }

    // Renvoie l'abscisse du tir de l'alien lors du contact avec un château
    private int abscisseContactTirAlienChateau(Chateau chateau) {
        int xPosTirAlien = -1;
        if (this.xPos + this.largeur > chateau.getxPos()
                && this.xPos < chateau.getxPos() + Constantes.LARGEUR_CHATEAU) {
            xPosTirAlien = this.xPos;
        }
        return xPosTirAlien;
    }

    // Renvoie numéro château touché et abscisse du tir
    public int[] tirAlienToucheChateau() {
        int[] tabRep = { -1, -1 };
        if (this.tirAlienAHauteurDeChateau() == true) { // le tir est à hauteur du château
            tabRep[0] = this.chateauProche(); // enregistre le numéro du château touché dans tabRep[1]
            if (tabRep[0] != -1) {
                // Enregistre l'abscisse du tir du vaisseau lors du contact avec le château dans
                // tabRep[0]
                tabRep[1] = this.abscisseContactTirAlienChateau(spaceInvadersMain.scene.tabChateaux[tabRep[0]]);
            }
        }
        return tabRep;
    }

    public void tirAlienDetruitChateau(Chateau tabChateaux[]) {
        int[] tab = this.tirAlienToucheChateau(); // Contient (-1,-1) ou le numéro du château touché
        if (tab[0] != -1) { // un château est touché
            if (tabChateaux[tab[0]].trouveBriqueHaut(tabChateaux[tab[0]].trouveColonneChateau(tab[1])) != -1
                    && tabChateaux[tab[0]].trouveBriqueHaut(tabChateaux[tab[0]].trouveColonneChateau(tab[1])) != 27) {
                tabChateaux[tab[0]].casseBriquesHaut(tab[1]); // Détruit les briques du château touché
                this.yPos = 700; // On fait disparaitre le tir et on réactive le canon du vaisseau
            }
        }
    }

    // renvoie vrai si un tir touche le vaisseau
    public boolean toucheVaisseau(Vaisseau vaisseau) {
        if (this.yPos < vaisseau.getyPos() + vaisseau.getHauteur() && this.yPos + this.hauteur > vaisseau.getyPos()
                && this.xPos + this.largeur > vaisseau.getxPos()
                && this.xPos < vaisseau.getxPos() + vaisseau.getLargeur()) {
            this.yPos = 700;
            return true;
        } else {
            return false;
        }
    }

}