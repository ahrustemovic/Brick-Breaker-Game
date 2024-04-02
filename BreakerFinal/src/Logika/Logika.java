package Logika;

import java.awt.Rectangle;

import Grafika.GUI;
import Grafika.matrica;
/**
 * 
 * @author Anesa
 *
 */
public class Logika {
	private boolean play = true;
	private int totalbricks = 21;
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    int score = 0;
    
    
    private int brickWidth;
    private int brickHeight;
    public Logika(int brickWidth, int brickHeight) {
    	this.brickWidth = brickWidth;
    	this.brickHeight = brickHeight;
    }
    
    
    /**
     * Ova funkcija pomjera veslo desno.
     */
    public void moveRight(int playerX) {
    	play = true;
    	this.playerX = playerX + 20;
    }
	/**
	 * Ova funkcija pomjera veslo lijevo.
	 */
    public void moveLeft(int playerX) {
    	play = true;
    	this.playerX = playerX - 20;
    }
    
    
    /**
     * Ova funkcija služi za pomjeranje loptice i razbijanje pravougaonika. Ukoliko igra nije u toku (play nije true), funkcija se ne izvrsava. 
     * Na početku imamo 21 pravougaonik, funkcija prolazi kroz sve elemente matrice i provjerava da li se loptica sudarila sa nekim od pravougaonika.
     * Ukoliko se sudarila, pravougaonik se uništava, ukupan broj pravougaonika se smanjuje za 1, ukupan score se povecava za 5 i mijenja se smijer 
     * kretanje loptice zavisno od toga gdje se desila kolizija. 
     * Pozicija loptice se ažurira koristeci smijer kretanja u X i Y osi (ballXdir i ballYdir). Ukoliko se loptica sudara sa ivicama igre, smijer
     * kretanja u X osi se mijenja. Ukoliko se loptica sudara sa gornjom ivicom igre, smijer kretanja u Y osi se mijenja.
     */
    
    
    public void ballPosition(matrica gameBoard) {
        if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
            ballYdir = -ballYdir;
        }

        A:
        for (int i = 0; i < gameBoard.map.length; i++) {
            for (int j = 0; j < gameBoard.map[0].length; j++) {
                if (gameBoard.map[i][j] > 0) {
                    int brickX = j * gameBoard.brickWidth + 80;
                    int brickY = i * gameBoard.brickHeight + 50;
                    int bricksWidth = gameBoard.brickWidth;
                    int bricksHeight = gameBoard.brickHeight;

                    Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);
                    Rectangle ballrect = new Rectangle(ballposX, ballposY, 20, 20);

                    if (ballrect.intersects(rect)) {
                        gameBoard.setBrickValue(0, i, j);
                        totalbricks--;
                        score += 5;
                        if (ballposX + 19 <= rect.x || ballposX + 1 >= rect.x + rect.width) {
                            ballXdir = -ballXdir;
                        } else {
                            ballYdir = -ballYdir;
                        }
                        break A;
                    }
                }
            }
        }

        ballposX += ballXdir;
        ballposY += ballYdir;
        if (ballposX < 0) {
            ballXdir = -ballXdir;
        }
        if (ballposY < 0) {
            ballYdir = -ballYdir;
        }
        if (ballposX > gameBoard.getWidth() - 20) {
            ballXdir = -ballXdir;
        }
    }

  
    /**
     * Funkcija kontroliše kretanje igrača desno. Ako je pozicija vesla veća ili jednaka 600, onda se pozicija vesla postavlja na 600, što znači
     * da igrač ne može da se kreće dalje desno. Ukoliko nije, poziva se funkcija "moveRight".
     */
    public void moveR() {
	    if (playerX >= 600) {
	        playerX = 600;
	    } else {
	        moveRight(playerX);
	    }
    }
    
    
    /**
     * Funkcija kontrolise kretanje vesla lijevo. Ako je pozicija vesla manja ili jednaka 10, onda se pozicija vesla postavlja na 10, što znači
     * da igrač ne može da se kreće dalje desno. Ukoliko nije, poziva se funkcija "moveLeft".
     */
    public void moveL() {
    	if (playerX < 10) {
            playerX = 10;
        } else {
            moveLeft(playerX);
        }
    }
    
    public int[] getValues() {
        int[] arr = new int[7];
        arr[0] = ballposX;
        arr[1] = ballposY;
        arr[2] = ballXdir;
        arr[3] = ballYdir;
        arr[4] = playerX;
        arr[5] = totalbricks;
        arr[6] = score;
        return arr;
    }
    
    
}

