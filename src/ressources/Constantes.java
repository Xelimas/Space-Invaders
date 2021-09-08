package ressources;

public abstract class Constantes {

    // Dimension de la fenetre de jeu
    public static final int LARGEUR_FENETRE = 600;
    public static final int HAUTEUR_FENETRE = 600;
    public static final int MARGE_FENETRE = 50;



    /******************************** VAISSEAU ********************************/
    // Dimension du vaisseau
    public static final int LARGEUR_VAISSEAU = 39;
    public static final int HAUTEUR_VAISSEAU = 24;

    // position initial du vaisseau
    public static final int X_POS_INIT_VAISSEAU = (LARGEUR_FENETRE - LARGEUR_VAISSEAU) / 2;
    public static final int Y_POS_VAISSEAU = 490;

    // unité de déplacement du vaisseau
    public static final int DX_VAISSEAU = 1;
    
    public static final int LIMITE_GAUCHE_VAISSEAU = 60;
    public static final int LIMITE_DROITE_VAISSEAU = 500;

    /******************************** ALIEN ********************************/
    // Dimension de l'alien
    public final static int LARGEUR_ALIEN = 33;
    public final static int HAUTEUR_ALIEN = 25;

    // Paramètres de position des aliens
    public final static int ALT_INIT_ALIEN = 120;
    public final static int X_POS_INIT_ALIEN = 29 + MARGE_FENETRE;
    public final static int ECART_LIGNES_ALIEN = 40;
    public final static int ECART_COLONNES_ALIEN = 10;

    // Unité de déplacement de l'alien
    public final static int DX_ALIEN = 2;
    public final static int DY_ALIEN = 20;
    public final static int VITESSE_ALIEN = 1;

    // Nombre total alien
    public final static int NOMBRE_ALIENS = 50;

    /******************************** TIR VAISSEAU ********************************/
    // Dimension du tir
    public final static int LARGEUR_TIR_VAISSEAU = 3;
    public final static int HAUTEUR_TIR_VAISSEAU = 13;

    // Unité de déplacement du tir
    public final static int DY_TIR_VAISSEAU = 2;

    /******************************** CHATEAU ********************************/
    // Dimension de la brique
    public final static int DIMENSION_BRIQUE = 2;

    // Dimension du château (multiple des dimensions de la brique)
    public final static int LARGEUR_CHATEAU = 72;
    public final static int HAUTEUR_CHATEAU = 54;

    // Paramètres de position des châteaux
    public final static int Y_POS_CHATEAU = 400;
    public final static int X_POS_INIT_CHATEAU = 39;
    public final static int ECART_CHATEAU = 42;

    /******************************** TIR ALIEN ********************************/
    // Dimension du tir
    public static final int LARGEUR_TIR_ALIEN = 5;
    public static final int HAUTEUR_TIR_ALIEN = 15;

    // Unité de déplacement du tir
    public final static int DY_TIR_ALIEN = 3;

}