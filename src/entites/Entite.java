package entites;

import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class Entite {

    /**** VARIABLES ****/

    protected int largeur, hauteur, xPos, yPos, dx, dy;
    protected boolean vivant;
    protected String strImg1, strImg2, strImg3;
    protected ImageIcon ico;
    protected Image img;

    /**** CONSTRUCTEUR ****/

    /**** METHODES ****/

    public ImageIcon getIco() {return ico;}

    public void setIco(ImageIcon ico) {this.ico = ico;}

    public Image getImg() {return img;}

    public void setImg(Image img) {this.img = img;}

    public String getStrImg1() {return strImg1;}

    public void setStrImg1(String strImg1) {this.strImg1 = strImg1;}

    public String getStrImg2() {return strImg2;}

    public void setStrImg2(String strImg2) {this.strImg2 = strImg2;}

    public String getStrImg3() {return strImg3;}

    public void setStrImg3(String strImg3) {this.strImg3 = strImg3;}

    public boolean isVivant() {return vivant;}

    public void setVivant(boolean vivant) {this.vivant = vivant;}

    public int getLargeur() {return largeur;}

    public void setLargeur(int largeur) {this.largeur = largeur;}

    public int getHauteur() {return largeur;}

    public void setHauteur(int hauteur) {this.hauteur = hauteur;}

    public int getxPos() {return xPos;}

    public void setxPos(int xPos) {this.xPos = xPos;}

    public int getyPos() {return yPos;}

    public void setyPos(int yPos) {this.yPos = yPos;}

    public int getDx() {return dx;}

    public void setDx(int dx) {this.dx = dx;}

    public int getDy() {return dy;}

    public void setDy(int dy) {this.dy = dy;}
}