package Grafika;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import Logika.Logika;

/**
 * @version 12/2/2023
 * @author Anesa 
 */
public class GUI extends JPanel implements KeyListener, ActionListener {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean play = true;
	private int score = 0;
	private int totalbricks = 21;
    private Timer Timer;
    private int delay = 8;
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private Logika logika;
    private int ballXdir = -1;
    private int ballYdir = -2;
    public static matrica map;
    
    public GUI() {
    	logika = new Logika(77, 50);
    	map = new matrica(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        Timer = new Timer(delay, this);
        Timer.start();
    }
    
    /**
     * Ova funkcija "paint" se koristi za iscrtavanje grafičkog interfejsa u igri. Funkcija prima objekat "g" klase Graphics, koja se koristi za crtanje 
     * na prozoru igre. Prvo se postavlja boja pozadine na crnu boju i crta se pravougaonik koji pokriva cijeli prozor igre. Zatim se iscrtava mapa igre
     * korišćenjem metode "draw" u klasi "map".
     * Nakon toga se iscrtavaju ivice igre boje žute, a također se iscrtava i tekst koji prikazuje trenutni rezultat (score) igre. Dalje se iscrtava
     * igračevo veslo žute boje, a zatim se iscrtava loptica u bijeloj boji.
     * Na kraju, ispisuju se poruke  "You lost! Game Over Score: [rezultat]" ili "You win" zavisno od krajnjeg ishoda igre.
     * Na kraju se poziva metoda "dispose" na objektu "g", što znači da se svi resursi koji su korišćeni u ovoj funkciji oslobađaju.
     */
     public void paint(Graphics g) {
    	 
    	Graphics2D g2d = (Graphics2D) g;
 		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 		
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

       
		map.draw((Graphics2D) g);

        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);

        g.setColor(Color.yellow);
        g.fillRect(playerX, 550, 100, 8);

        //ball
        g.setColor(Color.white);
        g.fillOval(ballposX, ballposY, 20, 20);

        if (ballposY > 570) {
        	play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You lost! Game Over Score: " + score, 120, 300);

            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("   Press Enter to Restart", 170, 340);
        }
        if(totalbricks == 0){
        	play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.green);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("You win!",240,300);

            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("   Press Enter to Restart", 170, 340);


        }
        
        g.dispose();


    }

    /**
     * Ova funkcija "actionPerformed" je metoda iz interfesa ActionListener i predstavlja događaje koji se javlja kada se akcija izvrši.
     * Poziva funkciju ballPosition() iz klase Logika koja pomiče poziciju loptice u igri. Na kraju se poziva metoda repaint() koja će 
     * osvježiti prozor igre i prikazati nove pozicije loptice i ostale grafičke elemente.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Timer.start();
        if(play) {
        	 logika.ballPosition(map);
        	 connect(logika);
        }
        repaint();
    }
    /**
     * Funkcija koja prima logiku i inicijalizira varijable.
     * @param logika
     */
    public void connect(Logika logika) {
    	int[] arr1 = logika.getValues();
        playerX = arr1[4];
        ballposX = arr1[0];
        ballposY = arr1[1];
        score = arr1[6];
        totalbricks = arr1[5];
    }
    @Override
    public void keyTyped(KeyEvent e) {

       }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Ova funkcija opisuje akciju koja se izvodi kada se pritisne tipka na tastaturi. Funkcija sadrži niz if izjava koje provjeravaju
     * koji je tipka pritisnuta i izvode odgovarajuću akciju.
     * Funkcija provjerava da li je to taster sa desne strane (VK_RIGHT), taster sa lijve strane (VK_LEFT) ili enter taster (VK_ENTER).
     * Ukoliko je taster sa desne strane pritisnut, poziva se metoda moveR() iz klase Logika.
     * Ukoliko je taster sa lijeve strane pritisnut, poziva se metoda moveL() iz klase Logika.
     * Ukoliko je enter taster pritisnut i ako igra nije u toku tada se resetuju navedene vrijednosti. Zatim se poziva metoda repaint()
     * koja će ponovo iscrtati komponente na ekranu.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        	logika.moveR();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        	logika.moveL();
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                ballposX = 120;
                ballposY = 350;
                score = 0;
                playerX = 310;
                totalbricks = 21;
                map = new matrica(3, 7);
                repaint();
            }
        }
    }

    
}

