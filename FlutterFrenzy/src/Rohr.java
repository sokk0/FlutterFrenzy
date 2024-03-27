/* Hier haben wir die Klasse Physics vererbt, damit wir nicht andauernd
 neue Physics schreiben mussten */
public class Rohr extends Physics{

    //Konstruktor
    public Rohr(int x, float y,float vy){

        /* Die horizontale Bewegung vx der Röhren wird mit -7 initialisiert
         und später durch verschiedene Methoden angepasst */
        super(x, y,-7, vy);
    }
}
