/* Die Klasse Physics wird genutzt, um die Physik der einzelnen Teile
  in unserem Spiel zu bewegen */


public class Physics {

    //Attribute
    float x, y;
    float vx, vy;
    final float g = 1f;

    //Konstruktor
    public Physics(float x, float y, float vx, float vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    /* Eine Methode für die Physik welche nur die Koordinaten kontinuierlich,
     damit es animiert erscheint bzw. es sich bewegt */

    void update() {
        x += vx;
        y += vy;

        vx += g;
        vy += g;
    }

    //Methode spezifisch für die Röhren, damit keine Gravitation g auf sie wirkt
    void updatePipe() {
        x += vx;
        y += vy;
    }


}