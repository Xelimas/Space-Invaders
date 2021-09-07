package ressources;

public abstract class Constantes {

    // Dimension de la fenetre de jeu
    public static final int LARGEUR_FENETRE = 600;
    public static final int HAUTEUR_FENETRE = 600;
    public static final int MARGE_FENETRE = 50;



    // Dimension du vaisseau
    public static final int LARGEUR_VAISSEAU = 39;
    public static final int HAUTEUR_VAISSEAU = 24;

    // position initial du vaisseau
    public static final int X_POS_INIT_VAISSEAU = (LARGEUR_FENETRE - LARGEUR_VAISSEAU) / 2;
    public static final int Y_POS_VAISSEAU = 490;

    // unité de déplacement du vaisseau
    public static final int DX_VAISSEAU = 1;
}