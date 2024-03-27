import processing.core.PImage;
import processing.core.PApplet;
// Eine Klasse um die vielen PNG ohne großen Aufwand zu verwenden
public class ImageVault {

    //Attribute
   public PImage image;
    final int pngWidth;
    final int pngHeight;

    //Konstruktor
    public ImageVault(PApplet p,String pngName, int pngWidth, int pngHeight) {
        image = p.loadImage(pngName);
        image.resize(pngWidth,pngHeight);
        this.pngWidth = pngWidth;
        this.pngHeight = pngHeight;
    }
}
