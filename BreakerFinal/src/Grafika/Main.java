package Grafika;

import javax.swing.JFrame;
/**
 * @author Anesa
 */
/**
 * 
 * Pokrecemo igricu BrickBreaker. 
 *
 */

public class Main extends JFrame {
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        GUI gameplay = new GUI();
        obj.setBounds(10,10,700,600);
        obj.setTitle("Brick Breaker Game!");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
    }
    
}