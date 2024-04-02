package Grafika;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
/**
 * 
 * @author Anesa
 *
 */
public class matrica {
    public int[][] map;
    public int brickWidth;
    public int brickHeight;
	

    public matrica(int row, int col) {
        map = new int[row][col];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }
        brickWidth = 540 / col;
        brickHeight = 150 / row;
    }

    public int[][] getMap() {
        return map;
    }
    /**
     * Funkcija nam sliži za crtanje kvadratica koje će loptica razbijati.
     * @param g
     */
    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g.setColor(Color.red);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

                   
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void drawMap() {
        for (int[] ints : map) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.println(ints[j] + " ");
            }
            System.out.println("\n");
        }
    }

    public void setBrickValue(int val, int row, int col) {
        map[row][col] = val;
    }
    
    public int getWidth() {
        return map[0].length * brickWidth + 80;
    }

}


