import java.util.Random;
public class Logic {

    // Globale Variablen
    public static boolean gameOver = false;

    public static int score = 0;
    public static int hellScore = 0;
    private static int highScore = 0;
    private static int hellHighScore=0;

    //passieren im Sinne von vorbeigehen bzw. fliegen
    public static boolean pipePassieren = true;
    public static boolean pipePassieren2 = false;
    public static boolean pipePassieren3 = false;

    static Random r1 = new Random();

    public static Bird b1;

    public static Rohr pUpwards;
    public static Rohr pDownwards;

    public static Rohr pUpwards2;
    public static Rohr pDownwards2;

    public static Rohr pUpwards3;
    public static Rohr pDownwards3;

    public static Rohr pExUpwards;
    public static Rohr pExDownwards;

    public static Rohr pExUpwards2;
    public static Rohr pExDownwards2;

    public static Rohr pExUpwards3;
    public static Rohr pExDownwards3;


    // Die Update-Methode aus Physics wird nun auf die Rohre angewendet
    public static void movePipe(Rohr up, Rohr down){
       up.updatePipe();
       down.updatePipe();
    }

    //hier werden zufällige (mit bestimmter Abgrenzung) Floats berechnet durch Random
    public static float randomizePipePosition() {
        return r1.nextFloat(350, 750);
    }

    /*
    hier werden die Röhren Paare gecheckt, ob sie aus dem Bild sind, falls ja,
    sollen sie wieder auf das andere Ende des Bildschirms springen.
    Als Parameter nutzen wir ein Rohr Paar (oben + unten)
     */
    public static void spawnPipe(Rohr x, Rohr y){

        if(x.x<-300 && y.x<-300){
            x.x=1500;
            y.x=1500;
            x.y = randomizePipePosition();
            y.y = x.y -800;
        }
    }

    /* Wenn die Rohre wieder auf der rechten Seite des Bildschirms "teleportiert" wurden,
    wird nun die Länge (y-Wert) der Rohre wieder Zufällig zugewiesen. Das immer nur für das Untere
    bzw. "u1", das Obere bzw. "d1" wird dafür immer um 800 niedriger gehalten, um einen bestimmten Abstand
    zu sichern */
    public static void endlessRandomizing(Rohr u1, Rohr d1, Rohr u2, Rohr d2, Rohr u3, Rohr d3){
        if(u1.x == 1500){
            u1.y = randomizePipePosition();
            d1.y = u1.y -800;
        }else if(u2.x == 1500){
            u2.y = randomizePipePosition();
            d2.y = u2.y -800;
        }else if(u3.x == 1500){
            u3.y = randomizePipePosition();
            d3.y = u3.y -800;
        }
    }


    //der vx-Wert aus Physics für die Rohre wird nur erhöht, damit diese schneller werden
    public static void pipeBoost(int x, float y, Rohr rUp1, Rohr rDown1, Rohr rUp2, Rohr rDown2, Rohr rUp3, Rohr rDown3){
        if( score > x){
            rUp1.vx = y;
            rDown1.vx = y;
            rUp2.vx = y;
            rDown2.vx = y;
            rUp3.vx = y;
            rDown3.vx = y;
        }
    }
    // Hier werden die Rohre in bestimmten Inkrementen hochgeschraubt
    public static void boost(Rohr rUp1, Rohr rDown1, Rohr rUp2, Rohr rDown2, Rohr rUp3, Rohr rDown3){
        pipeBoost(10, -8, rUp1, rDown1, rUp2, rDown2, rUp3, rDown3);
        pipeBoost(15, -9, rUp1, rDown1, rUp2, rDown2, rUp3, rDown3);
        pipeBoost(20, -9.5f, rUp1, rDown1, rUp2, rDown2, rUp3, rDown3);
        pipeBoost(25, -10, rUp1, rDown1, rUp2, rDown2, rUp3, rDown3);
    }

    /* alle Kollisionen in eine Methode zusammengeführt, um diese nur einmal in der GUI
    aufzurufen */
    public static void collision(Bird bird, Rohr rUp1, Rohr rDown1, Rohr rUp2, Rohr rDown2, Rohr rUp3, Rohr rDown3){
        floorCollision(bird);
        skyCollision(bird);
        pipeCollisionDown(bird, rUp1, rUp2, rUp3);
        pipeCollisionUp(bird, rUp1, rDown1, rDown2, rDown3);
    }

    //hier wird geguckt, ob man den Boden berührt hat, falls ja, dann GameOver!!!
    public static boolean floorCollision(Bird bird){
        if(bird.y >= 900){
            bird.y = 1550;
            gameOver = true;
        }
        return gameOver;
    }

    //hier wird geguckt, ob man den Himmel berührt hat, falls ja, dann darf man nicht höher
    public static void skyCollision(Bird bird){
        if(bird.y <= 5){
            bird.y= 5;
        }
    }

    //hier wird geguckt, ob man ein unters Rohr berührt hat, falls ja, dann GameOver!!!
    public static boolean pipeCollisionDown(Bird bird, Rohr rUp1, Rohr rUp2, Rohr rUp3){

        if (bird.y - 120 > rUp1.y -160 && bird.x > rUp1.x && bird.x + 10  < rUp1.x + 280){
            gameOver = true;
        }else if (bird.y - 120 > rUp2.y -160 && bird.x > rUp2.x && bird.x + 10 < rUp2.x + 280){
            gameOver = true;
        }else if (bird.y - 120 > rUp3.y -160 && bird.x > rUp3.x && bird.x + 10 < rUp3.x + 280){
            gameOver = true;
        }

        return gameOver;
    }

    //hier wird geguckt, ob man ein oberes Rohr berührt hat, falls ja, dann GameOver!!!
     public static boolean pipeCollisionUp(Bird bird, Rohr rUp1, Rohr rDown1, Rohr rDown2, Rohr rDown3){

         if (bird.y < rDown1.y + 540 && bird.x > rUp1.x && bird.x + 10  < rUp1.x + 280){
             gameOver = true;
         }else if (bird.y < rDown2.y + 540 && bird.x > rDown2.x && bird.x + 120 < rDown2.x + 280){
             gameOver = true;
         }else if (bird.y < rDown3.y + 540 && bird.x > rDown3.x && bird.x + 120 < rDown3.x + 280){
             gameOver = true;
         }
         return gameOver;
     }


     /* Hier wird eine Methode gameOver angelegt, die das Spiel neu startet und den persönlichen Score wieder auf 0 setzt,
    dass nicht nach einem Tod weiter gezählt wird */
    public static void gameOver(){
        if(gameOver){
            startGame();
            gameOver = false;
            score = 0;
            hellScore = 0;
        }
    }

    /* Hier werden alle Objekte angelegt, welche für das Spiel gebraucht werden, außerdem werden die Booleans
    welche für das ScoreCounting benötigt werden wieder auf ihren Originalen Stand zurückgesetzt */
    public static void startGame(){

        b1 = new Bird();

        pUpwards = new Rohr(1000,randomizePipePosition(), 0);
        pDownwards = new Rohr(1000,pUpwards.y-800, 0);

        pUpwards2 = new Rohr(1500,randomizePipePosition(), 0);
        pDownwards2 = new Rohr(1500,pUpwards2.y-800, 0);


        pUpwards3 = new Rohr(2300, randomizePipePosition(), 0);
        pDownwards3 = new Rohr(2300, pUpwards3.y - 800, 0);


        pExUpwards = new Rohr(1000, randomizePipePosition(), -2f);
        pExDownwards = new Rohr(1000, pExUpwards.y - 800, -2f);

        pExUpwards2 = new Rohr(1500, randomizePipePosition(), -2f);
        pExDownwards2 = new Rohr(1500, pExUpwards2.y - 800, -2f);

        pExUpwards3 = new Rohr(2300, randomizePipePosition(), -2f);
        pExDownwards3 = new Rohr(2300, pExUpwards3.y - 800, -2f);




        pipePassieren = true;
        pipePassieren2 = false;
        pipePassieren3 = false;
    }

    // Diese Methode ist für das Experten-Level, welches jedes Rohr vertikal bewegt
    public static void hellPipeVert(Rohr rUp, Rohr rDown){
        if(rUp.y <= 350){
            rUp.vy =  2f;
            rDown.vy = 2f;

        }else if(rUp.y >= 750){
            rUp.vy = -2f;
            rDown.vy = -2f;
        }

    }

    /*
    hier wird der Score berechnet, indem geguckt wird, ob der Vogel rechts vom Rohr ist.
    Die Booleans werden als an und ausschalter der if-Abfragen genutzt, da diese sich in der
    Draw wie Schleifen verhalten und sonst, solange hoch zählen bis das Rohr wieder auf
    der rechten Seite des Bildschirms erscheint
     */

    public static int score(Bird bird, Rohr rUp1, Rohr rUp2, Rohr rUp3){

        if(bird.x > rUp1.x + 280 && pipePassieren){
            score++;
            pipePassieren = false;
            pipePassieren2 = true;
            System.out.println(score);

        }else if(bird.x > rUp2.x + 280 && pipePassieren2){
            score = score + 1;
            pipePassieren2 = false;
            pipePassieren3 = true;
            System.out.println(score);

        }else if(bird.x > rUp3.x + 280 && pipePassieren3){
            score = score + 1;
            pipePassieren3 = false;
            pipePassieren = true;
            System.out.println(score);

        }
        return score;
    }

    public static int hellScore(Bird bird, Rohr rUp1, Rohr rUp2, Rohr rUp3){

        if(bird.x > rUp1.x + 280 && pipePassieren){
            hellScore++;
            pipePassieren = false;
            pipePassieren2 = true;
            System.out.println(hellScore);

        }else if(bird.x > rUp2.x + 280 && pipePassieren2){
            hellScore++;
            pipePassieren2 = false;
            pipePassieren3 = true;
            System.out.println(hellScore);

        }else if(bird.x > rUp3.x + 280 && pipePassieren3){
            hellScore++;
            pipePassieren3 = false;
            pipePassieren = true;
            System.out.println(hellScore);

        }
        return hellScore;
    }

    /* Hier wird der höchste Score nach Start der Applikation in einer Variable gespeichert,
    diese wird wiederum mit dem neuen Score verglichen */
    public static int highScore(){
        if(highScore < score){
            highScore = score;
        }
        return highScore;
    }

    public static int hellHighScore(){
        if(hellHighScore < hellScore){
            hellHighScore = hellScore;
        }
        return hellHighScore;
    }






}
