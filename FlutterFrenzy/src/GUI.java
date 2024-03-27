import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;

public class GUI extends PApplet {

    /**
     * Author : Sleman Kakar and Ricardo Erdmann
     */

    // Globale Variablen
    public static int play = 0;
    private boolean gameMode = true;
    PImage birdUp;
    PImage birdDown;

    PImage hellBirdUp;
    PImage hellBirdDown;

    PImage pipeUp;
    PImage pipeDown;
    PImage pipeHellUp;
    PImage pipeHellDown;

    PImage background;
    PImage hell;
    PImage lobbyBackground;

    PFont font;
    PFont fontSmall;
    PFont hellFont;
    PFont blockFont;

    ImageVault ivBirdD;
    ImageVault ivBirdU;
    ImageVault ivPipeU;
    ImageVault ivPipeD;

    ImageVault ivHellBirdUp;
    ImageVault ivHellBirdDown;
    ImageVault ivPipeHellU;
    ImageVault ivPipeHellD;


    public void settings() {
        size(1500, 900);

        Logic.startGame();

        ivBirdD = new ImageVault(this,"Down1.png",120,120);

        ivBirdU = new ImageVault(this,"Up1.png",120,120);

        ivPipeU = new ImageVault(this,"PipeUpwards.png",300,600);
        ivPipeD = new ImageVault(this,"PipeDownwards.png",300  ,600);

        ivHellBirdUp = new ImageVault(this,"HellBirdUp.png",120,120);
        ivHellBirdDown = new ImageVault(this,"HellBirdDown.png",120,120);
        ivPipeHellU = new ImageVault(this,"PipeUpwardsH.png",300,600);
        ivPipeHellD = new ImageVault(this,"PipeDownwardsH.png",300,600);


        background=loadImage("Background.png");
        background.resize(1500,900);

        hell = loadImage("Hell3.gif");
        hell.resize(1500,900);

        lobbyBackground=loadImage("LobbyBackground.png");
        lobbyBackground.resize(1500,900);

        birdDown = ivBirdD.image;
        birdUp =ivBirdU.image;

        hellBirdUp = ivHellBirdUp.image;
        hellBirdDown = ivHellBirdDown.image;

        pipeUp= ivPipeU.image;
        pipeDown = ivPipeD.image;

        pipeHellUp = ivPipeHellU.image;
        pipeHellDown = ivPipeHellD.image;



    }
    public void setup(){

        font = createFont("PixelFont.ttf", 50);
        fontSmall = createFont("PixelFont.ttf", 40);
        hellFont = createFont("scary.TTF",50);
        blockFont = createFont("BlockFont.ttf",70);
    }

    public void draw() {

        drawStartMenu();

        /* Wenn der Integer play auf 0 gesetzt ist, dann kehrt man zum Hauptbildschirm zurück,
         wenn play auf 1 gesetzt ist dann wird das Basic-Level gestartet und wenn play auf 2 gesetzt ist,
          dann wird das Expert-Level gestartet */
        if (play == 1) {
            image(background, 0, 0);

            drawBird();
            birdFly();
            drawPipe();


            Logic.movePipe(Logic.pUpwards, Logic.pDownwards);
            Logic.spawnPipe(Logic.pUpwards, Logic.pDownwards);

            Logic.movePipe(Logic.pUpwards2, Logic.pDownwards2);
            Logic.spawnPipe(Logic.pUpwards2, Logic.pDownwards2);

            Logic.movePipe(Logic.pUpwards3, Logic.pDownwards3);
            Logic.spawnPipe(Logic.pUpwards3, Logic.pDownwards3);

            Logic.endlessRandomizing(Logic.pUpwards, Logic.pDownwards, Logic.pUpwards2, Logic.pDownwards2, Logic.pUpwards3, Logic.pDownwards3);

            Logic.collision(Logic.b1, Logic.pUpwards, Logic.pDownwards, Logic.pUpwards2, Logic.pDownwards2, Logic.pUpwards3, Logic.pDownwards3);

            Logic.gameOver();

            Logic.boost(Logic.pUpwards, Logic.pDownwards, Logic.pUpwards2, Logic.pDownwards2, Logic.pUpwards3, Logic.pDownwards3);

            Logic.score(Logic.b1, Logic.pUpwards, Logic.pUpwards2, Logic.pUpwards3);
            Logic.highScore();

            drawScore();
            drawHighScore();

        }else if(play == 2){

            image(hell,0,0);

            drawHellBird();
            birdFly();
            drawHellPipe();


            Logic.movePipe(Logic.pExUpwards, Logic.pExDownwards);
            Logic.hellPipeVert(Logic.pExUpwards, Logic.pExDownwards);
            Logic.spawnPipe(Logic.pExUpwards, Logic.pExDownwards);

            Logic.movePipe(Logic.pExUpwards2, Logic.pExDownwards2);
            Logic.hellPipeVert(Logic.pExUpwards2, Logic.pExDownwards2);
            Logic.spawnPipe(Logic.pExUpwards2, Logic.pExDownwards2);

            Logic.movePipe(Logic.pExUpwards3, Logic.pExDownwards3);
            Logic.hellPipeVert(Logic.pExUpwards3, Logic.pExDownwards3);
            Logic.spawnPipe(Logic.pExUpwards3, Logic.pExDownwards3);

            Logic.endlessRandomizing(Logic.pExUpwards, Logic.pExDownwards, Logic.pExUpwards2, Logic.pExDownwards2, Logic.pExUpwards3, Logic.pExDownwards3);

            Logic.collision(Logic.b1, Logic.pExUpwards, Logic.pExDownwards, Logic.pExUpwards2, Logic.pExDownwards2, Logic.pExUpwards3, Logic.pExDownwards3);

            Logic.gameOver();

            Logic.boost(Logic.pUpwards, Logic.pDownwards, Logic.pUpwards2, Logic.pDownwards2, Logic.pUpwards3, Logic.pDownwards3);

            Logic.hellScore(Logic.b1, Logic.pExUpwards, Logic.pExUpwards2, Logic.pExUpwards3);
            Logic.hellHighScore();

            drawHellScore();
            drawHellHighScore();
        }
    }
        // Methode um den Vogel auf den Canvis zu bekommen und die Physik auf ihn einwirken zu lassen via .update()
        public void drawBird () {
            if (keyPressed||mousePressed) {
                image(birdDown, Logic.b1.x, Logic.b1.y);
            } else {
                image(birdUp, Logic.b1.x, Logic.b1.y);
            }
            Logic.b1.update();
            Logic.b1.vx = 0;

        }

    public void drawHellBird () {
        if (keyPressed||mousePressed) {
            image(hellBirdUp, Logic.b1.x, Logic.b1.y);
        } else {
            image(hellBirdDown, Logic.b1.x, Logic.b1.y);
        }
        Logic.b1.update();
        Logic.b1.vx = 0;

    }
        // Methode um beim Tastendruck den Vogel fliegen zu lassen
        public void birdFly () {
            if (keyPressed||mousePressed) {
                Logic.b1.setFly(true);
                Logic.b1.birdFly(Logic.b1);
            }
        }

        // Hier werden die Rohre für das Standard-Level gezeichnet
        public void drawPipe () {
            image(pipeDown, Logic.pDownwards.x - 2, Logic.pDownwards.y);
            image(pipeUp, Logic.pUpwards.x, Logic.pUpwards.y);


            image(pipeUp, Logic.pUpwards2.x - 2, Logic.pUpwards2.y);
            image(pipeDown, Logic.pDownwards2.x, Logic.pDownwards2.y);


            image(pipeUp, Logic.pUpwards3.x - 2, Logic.pUpwards3.y);
            image(pipeDown, Logic.pDownwards3.x, Logic.pDownwards3.y);
        }

    // Hier werden die Rohre für das Expert-Level gezeichnet
    public void drawHellPipe () {
        image(pipeHellDown, Logic.pExDownwards.x - 2, Logic.pExDownwards.y);
        image(pipeHellUp, Logic.pExUpwards.x, Logic.pExUpwards.y);


        image(pipeHellUp, Logic.pExUpwards2.x - 2, Logic.pExUpwards2.y);
        image(pipeHellDown, Logic.pExDownwards2.x, Logic.pExDownwards2.y);


        image(pipeHellDown, Logic.pExDownwards3.x - 2, Logic.pExDownwards3.y);
        image(pipeHellUp, Logic.pExUpwards3.x, Logic.pExUpwards3.y);



    }
        // Methoden um den aktuellen Score im Basic-Level anzuzeigen
        public void drawScore () {
            textFont(font);
            fill(255);
            text(Logic.score, 750, 200);
        }

    // Methode um den aktuellen Score um Expert-Level anzuzeigen
    public void drawHellScore () {
        fill(205,0,0);
        textFont(hellFont);
        text(Logic.hellScore, 750, 200);
    }
    // Methoden um den aktuellen HighScore im Basic-Level anzuzeigen
        public void drawHighScore () {
            textFont(font);
            text("HighScore: " + Logic.highScore(), 1100, 55);
        }
    // Methode um den aktuellen HighScore um Expert-Level anzuzeigen
    public void drawHellHighScore () {
        textFont(hellFont);
        text("HIGHSCORE" + " " + Logic.hellHighScore(), 1000, 55);
    }

        // Methode um das Startmenü zu zeichnen
        public void drawStartMenu () {
            background(lobbyBackground);
            textFont(blockFont);
            fill(0);
            textSize(100);
            fill(255, 191, 0);
            text("Flutter Frenzy", 120, 200);

            textFont(blockFont);
            fill( 255, 191, 70);
            text("For Basic Mode press MOUSE 1 ", 120,450);
            text("For Expert Mode press MOUSE 2 ",120, 550);
            text("hit enter to return  home",120, 750);

            fill(0, 128, 0);
            text("HighScore " + Logic.highScore(),1100,150);

            fill(136, 8, 8);
            text("HighScore " + Logic.hellHighScore(),1100,250);



                if (mousePressed && (mouseButton == LEFT) && gameMode){
                    play = 1;
                    gameMode = false;
                } else if(mousePressed && (mouseButton == RIGHT) && gameMode){
                    play = 2;
                    gameMode = false;
                    // Bei Trackpads könnten Probleme auftauchen und das returnen zum Home ist eventuell nicht möglich
                }else if(mousePressed && (mouseButton == CENTER)){
                    play = 0;
                    gameMode = true;
                    Logic.pipePassieren = true;
                    Logic.pipePassieren2 = false;
                    Logic.pipePassieren3 = false;
                    Logic.score = 0;

                }

        }


        public static void main (String[]passedArgs){
            String[] appletArgs = new String[]{"GUI"};
            PApplet.main(appletArgs);
        }
    }