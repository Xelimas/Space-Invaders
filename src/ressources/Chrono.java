package ressources;

import jeu.spaceInvadersMain;

public class Chrono implements Runnable {

    private final int PAUSE = 5;
    public static int compteTours = 0;

    @Override
    public void run() {
        while (spaceInvadersMain.jeu == true) {
            compteTours++;
            spaceInvadersMain.scene.repaint();
            try {
                Thread.sleep(PAUSE);
            } catch (InterruptedException e) {

            }
        }

    }

}