package jeu;

import javax.swing.JFrame;

import ressources.Constantes;

public class spaceInvadersMain{

    public static Scene scene;
        
    
    public static void main(String[] args)throws Exception {
        // création de la fenêtre de l'application
        JFrame fenetre = new JFrame("Space Invaders");
        fenetre.setSize(Constantes.LARGEUR_FENETRE, Constantes.HAUTEUR_FENETRE);
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setAlwaysOnTop(true);

        scene = new Scene();
        fenetre.setContentPane(scene);
        fenetre.setVisible(true);

        
    }



}
