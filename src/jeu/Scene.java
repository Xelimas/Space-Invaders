package jeu;

import javax.swing.JPanel;

import entites.Chateau;
import entites.GroupeAliens;
import entites.Soucoupe;
import entites.TirAlien;
import entites.TirVaisseau;
import entites.Vaisseau;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import ressources.Chrono;
import ressources.Clavier;
import ressources.Constantes;

public class Scene extends JPanel {

    /**** VARIABLES ****/

    public Vaisseau vaisseau = new Vaisseau();
    public GroupeAliens groupeAliens = new GroupeAliens();
    public TirVaisseau tirVaisseau = new TirVaisseau();
    public Chateau tabChateaux[] = new Chateau[4];

    public TirAlien tirAlien1, tirAlien2, tirAlien3;

    public Soucoupe soucoupe;

    private Font afficheScore = new Font("Arial", Font.PLAIN, 20);
    private Font afficheTexte = new Font("Arial", Font.PLAIN, 60);

    public int score = 0;

    /**** CONSTRUCTEUR ****/

    public Scene() {
        super();

        // instanciation des châteaux
        for (int colonne = 0; colonne < 4; colonne++) {
            this.tabChateaux[colonne] = new Chateau(Constantes.MARGE_FENETRE + Constantes.X_POS_INIT_CHATEAU
                    + colonne * (Constantes.LARGEUR_CHATEAU + Constantes.ECART_CHATEAU));
        }

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

        // Affichage du score
        g.setFont(afficheScore);
        g.drawString("SCORE : " + score, 400, 25);

        // Dessin du vaisseau
        this.vaisseau.dessinVaisseau(g2);

        // Dessin du groupe d'aliens
        this.groupeAliens.dessinAliens(g2);

        // Dessin du tir du vaisseau
        this.tirVaisseau.dessinTirVaisseau(g2);

        // détection contact tirVaisseau avec alien
        this.groupeAliens.tirVaisseauToucheAlien(this.tirVaisseau);

        // Dessin des châteaux
        for (int colonne = 0; colonne < 4; colonne++) {
            this.tabChateaux[colonne].dessinChateau(g2);
        }

        // Message de début de jeu
        if (Chrono.compteTours < 500) {
            g.setFont(afficheTexte);
            g.drawString("Bonne Chance !", 95, 100);
        }

        // Détection contact du tir du vaisseau avec le château
        this.tirVaisseau.tirVaisseauDetruitChateau(tabChateaux);

        // Dessin des tirs des aliens
        if (Chrono.compteTours % 500 == 0) {
            tirAlien1 = new TirAlien(this.groupeAliens.choixAlienQuiTire());
        }
        if (this.tirAlien1 != null) {
            this.tirAlien1.dessinTirAlien(g2);
            this.tirAlien1.tirAlienDetruitChateau(tabChateaux); // détection contact avec château
            if (this.tirAlien1.toucheVaisseau(vaisseau) == true) {
                this.vaisseau.setVivant(false);
            }
        }

        if (Chrono.compteTours % 750 == 0) {
            tirAlien2 = new TirAlien(this.groupeAliens.choixAlienQuiTire());
        }
        if (this.tirAlien2 != null) {
            this.tirAlien2.dessinTirAlien(g2);
            this.tirAlien2.tirAlienDetruitChateau(tabChateaux); // détection contact avec château
            if (this.tirAlien2.toucheVaisseau(vaisseau) == true) {
                this.vaisseau.setVivant(false);
            }
        }

        if (Chrono.compteTours % 900 == 0) {
            tirAlien3 = new TirAlien(this.groupeAliens.choixAlienQuiTire());
        }
        if (this.tirAlien3 != null) {
            this.tirAlien3.dessinTirAlien(g2);
            this.tirAlien3.tirAlienDetruitChateau(tabChateaux); // détection contact avec château
            if (this.tirAlien3.toucheVaisseau(vaisseau) == true) {
                this.vaisseau.setVivant(false);
            }
        }

        // dessin de la soucoupe
        if (Chrono.compteTours % 2500 == 0) {
            soucoupe = new Soucoupe();
        }
        if (this.soucoupe != null) {
            if (this.soucoupe.getxPos() > 0) {
                if (this.tirVaisseau.detruitSoucoupe(this.soucoupe) == true) {
                    if (this.soucoupe.getDx() != 0) {
                        this.score = this.score + Constantes.VALEUR_SOUCOUPE;
                    }
                    this.soucoupe.setDx(0);
                    this.soucoupe.setVivant(false);
                    this.soucoupe.musiqueSoucoupe.stop();
                    this.soucoupe.musiqueDestructionSoucoupe.play();
                }
                this.soucoupe.dessinSoucoupe(g2);
            } else {
                this.soucoupe = null;
            }
        }

        // Affichage de la fin du jeu
        if (this.vaisseau.isVivant() == false) {
            g.setFont(afficheTexte);
            g.drawString("Fin de partie !", 110, 100);
        }

        if (this.groupeAliens.getNombreAliens() == 0) {
            groupeAliens = new GroupeAliens();
        }

        if (this.groupeAliens.positionAlienLePlusBas() > Constantes.Y_POS_VAISSEAU) {
            this.vaisseau.destructionVaisseau();
        }
    }

}