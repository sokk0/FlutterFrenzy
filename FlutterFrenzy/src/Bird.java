/* hier haben wir die Klasse Physics vererbt, damit wir nicht
 andauernd neue Physics schreiben mussten */

public class Bird extends Physics {
    //Attribute
    private boolean fly = false;

    //Konstruktor
    public Bird() {
        super(100, 100, 0, 0);
    }

    //Setter
    public void setFly(boolean fly) {
        this.fly = fly;
    }
    //Methode um den Vogel zum Fliegen zu bringen
    public void birdFly(Bird x){
        if (fly){
            vy=-10;
        }
    }
}


