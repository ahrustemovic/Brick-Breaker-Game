package konzola;

import java.util.Scanner;
/**
 * 
 * @author Anesa
 * @version 12/2/2023
 */

/**
 * U ovoj klasi sam napravila da se Brick Breaker igra u konzoli.
 *
 */
class BrickBreaker {
    private static int width = 50;
    private static int height = 15;
    private static int x = width / 2;
    private static int y = height - 1;
    private static int ballXdir = 1;
    private static int ballYdir = -1;
    private static int paddleWidth = 8;
    private static int paddleX = width / 2 - paddleWidth / 2;
    private static int[][] bricks = new int[5][width];
    private static Scanner sc = new Scanner(System.in);

    
    /**
     * Ova funkcija "draw()" predstavlja metodu za iscrtavanje igre u konzolu. Iscrtava se igralište koje sadrži lopticu "o", veslo "=" i zidove "|".
     * Također, funkcija iscrtava i "brick" elemente u igri korištenjem switch petlje i ANSI kodova za bojenje.
     * Funkcija koristi dimenzije "width" i "height" za određivanje granica igrališta i za iscrtavanje zidova. PaddleX, paddleWidth, bricks, x i y
     * su varijable koje se koriste za određivanje položaja i dimenzija vesla i "brick" elemenata u igri. 
     * Dakle, ova funkcija iscrtava igru u konzolu pomoću ASCII znakova.
     */
    public static void draw() {
        for (int i = 0; i < width + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < height; i++) {
            System.out.print("|");
            for (int j = 0; j < width; j++) {
                if (j == x && i == y) {
                    System.out.print("o");
                } else if (i == height - 1 && j >= paddleX && j < paddleX + paddleWidth) {
                    System.out.print("=");
                } else {
                    int brick = 0;
                    for (int k = 0; k < 5; k++) {
                        if (bricks[k][j] == i) {
                            brick = k + 1;
                            break;
                        }
                    }
                    switch (brick) {
                        case 1:
                            System.out.print("\u001B[31m" + "#" + "\u001B[0m");
                            break;
                        case 2:
                            System.out.print("\u001B[33m" + "#" + "\u001B[0m");
                            break;
                        case 3:
                            System.out.print("\u001B[31m" + "#" + "\u001B[0m");
                            break;
                        case 4:
                            System.out.print("\u001B[33m" + "#" + "\u001B[0m");
                            break;
                        case 5:
                            System.out.print("\u001B[31m" + "#" + "\u001B[0m");
                            break;
                        default:
                            System.out.print(" ");
                            break;
                    }
                }
            }
            System.out.println("|");
        }
        for (int i = 0; i < width + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Funkcija sadrži logiku igre, poput promjene smjera kretanja lopte kada se sudari s rubovima ekrana ili kada se sudari s kockama, te 
     * promjene smjera kretanja vesla prema unesenom smjeru (lijevo ili desno).
     * Ukoliko lopta dolazi do donjeg ruba ekrana, a ne sudara se sa veslom, funkcija ispisuje poruku "Game Over" i završava program.
     * Funkcija također provjerava unos korisnika za smjer kretanja vesla, te mijenja poziciju vesla u skladu s unesenim smjerom. 
     */
    public static void update() {
        x += ballXdir;
        y += ballYdir;
        if (x < 0 || x >= width) {
        	ballXdir = -ballXdir;
        }
        if (y < 0) {
        	ballYdir = -ballYdir;
        } else if (y == height - 1) {
            if (x >= paddleX && x < paddleX + paddleWidth) {
            	ballYdir = -ballYdir;
            } else {
                System.out.println("Game Over");
                System.exit(0);
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < width; j++) {
                if (bricks[i][j] > 0 && y == bricks[i][j] + 1 && x == j) {
                	ballYdir = -ballYdir;
                    bricks[i][j] = 0;
                }
            }
        }
        int paddleDir = 0;
        if (sc.hasNextLine()) {
            String dir = sc.nextLine();
            if (dir.equals("left")) {
                paddleDir = -1;
            } else if (dir.equals("right")) {
                paddleDir = 1;
            }
        }
        paddleX += paddleDir;
        if (paddleX < 0) {
            paddleX = 0;
        } else if (paddleX + paddleWidth > width) {
            paddleX = width - paddleWidth;
        }
    }

    /**
     * 
     * @param args
     * @throws InterruptedException
     * Funkcija main je glavna funkcija aplikacije.
     * U svakom koraku petlje, funkcija draw se poziva za iscrtavanje trenutnog stanja igre na konzolu, zatim se poziva funkcija update za
     * ažuriranje stanja igre, i na kraju se funkcija Thread.sleep(100) poziva da pauzira izvršavanje petlje za 100 milisekundi.
     */
    
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < width; j++) {
                bricks[i][j] = i + 1;
            }
        }
        while (true) {
            draw();
            update();
            Thread.sleep(100);
        }
    }
    
}


