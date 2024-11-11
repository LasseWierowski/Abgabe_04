import java.lang.annotation.Documented;

/**
 * Repräsentiert ein Rechteck, definiert durch seine Position (x, y) und seine Dimensionen (Breite, Höhe).
 */
public class Rectangle {

    int x = 0;
    int y = 0;
    int width = 0;
    int height = 0;

    /**
     * Konstruktor: Erstellt ein Rechteck mit den angegebenen x- und y-Koordinaten sowie der angegebenen Breite und Höhe.
     * Gibt "Invalid Input" aus, wenn eine Eingabe ungültig ist (nicht positiv).
     *
     * @param xInput      die x-Koordinate der oberen linken Ecke des Rechtecks
     * @param yInput      die y-Koordinate der oberen linken Ecke des Rechtecks
     * @param widthInput  die Breite des Rechtecks
     * @param heightInput die Höhe des Rechtecks
     */
    public Rectangle(int xInput, int yInput, int widthInput, int heightInput) {
        if (xInput > 0 && yInput > 0 && widthInput > 0 && heightInput > 0) {
            this.x = xInput;
            this.y = yInput;
            this.width = widthInput;
            this.height = heightInput;
        } else {
            System.out.println("Invalid Input");
        }
    }

    /**
     * Konstruktor: Erstellt ein quadratisches Rechteck (Quadrat) mit den angegebenen x- und y-Koordinaten
     * und einer Seitenlänge, die sowohl Breite als auch Höhe des Rechtecks bestimmt.
     *
     * @param xInput          die x-Koordinate der oberen linken Ecke des Rechtecks
     * @param yInput          die y-Koordinate der oberen linken Ecke des Rechtecks
     * @param sideLengthInput die Seitenlänge des Quadrats
     */
    public Rectangle(int xInput, int yInput, int sideLengthInput) {
        x = xInput;
        y = yInput;
        width = sideLengthInput;
        height = sideLengthInput;
    }

    /**
     * Erstellt eine Kopie eines gegebenen Rechtecks und gibt ein neues Rectangle-Objekt zurück.
     *
     * @param r das Rechteck, das kopiert werden soll
     * @return eine neue Rectangle-Instanz mit denselben Eigenschaften wie das übergebene Rechteck
     */
    public Rectangle copy(Rectangle r) {
        return new Rectangle(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    /**
     * Gibt die x-Koordinate des Rechtecks zurück.
     *
     * @return die x-Koordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gibt die y-Koordinate des Rechtecks zurück.
     *
     * @return die y-Koordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Gibt die Breite des Rechtecks zurück.
     *
     * @return die Breite
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gibt die Höhe des Rechtecks zurück.
     *
     * @return die Höhe
     */
    public int getHeight() {
        return height;
    }

    /**
     * Überprüft, ob alle Rechtecke in einem Array Quadrate sind (Breite gleich Höhe).
     *
     * @param r ein Array von Rechtecken, das überprüft wird
     * @return true, wenn alle Rechtecke Quadrate sind, sonst false
     */
    public boolean areSquares(Rectangle[] r) {
        for (int i = 0; i < r.length; i++) {
            if (r[i].getHeight() != r[i].getWidth()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Berechnet die Fläche des Rechtecks.
     *
     * @return die Fläche des Rechtecks (Breite * Höhe)
     */
    public int area() {
        return width * height;
    }

    /**
     * Berechnet den Schnittbereich von mehreren Rechtecken.
     *
     * @param r eine variable Anzahl von Rechtecken, deren Schnittbereich berechnet werden soll
     * @return ein neues Rechteck, das den Schnittbereich repräsentiert, oder null, falls kein Schnittbereich existiert
     */
    public static Rectangle intersection(Rectangle... r) {
        Rectangle intersect = r[0];

        for (int i = 1; i < r.length; i++) {
            intersect = intersectTest(intersect, r[i]);
            if (intersect == null) {
                return null;
            }
        }
        return intersect;
    }

    /**
     * Berechnet den Schnittbereich zwischen zwei Rechtecken.
     *
     * @param r1 das erste Rechteck
     * @param r2 das zweite Rechteck
     * @return ein neues Rechteck, das den Schnittbereich repräsentiert, oder null, falls kein Schnittbereich existiert
     */
    public static Rectangle intersectTest(Rectangle r1, Rectangle r2) {
        int x = Math.max(r1.getX(), r2.getX());
        int y = Math.min(r1.getY(), r2.getY());

        int width = Math.min(r1.getX() + r1.getWidth(), r2.getX() + r2.getWidth()) - x;
        int height = Math.min(r2.getHeight() - (r2.getY() - r1.getY()), r1.getHeight() - (r1.getY() - r2.getY()));

        if (height <= 0 || width <= 0) {
            return null;
        }

        return new Rectangle(x, y, width, height);
    }

    /**
     * Beispielprogramm zur Demonstration der Schnittbereichberechnung zwischen mehreren Rechtecken.
     *
     * @param args Eingabeargumente für die Kommandozeile
     */
    public static void main(String[] args) {
        System.out.println(intersection(new Rectangle(1, 4, 2, 3), new Rectangle(2, 5, 3, 3), new Rectangle(1, 4, 2, 3)).toString());
    }

    /**
     * Gibt die Koordinaten aller vier Ecken des Rechtecks als String zurück.
     *
     * @return eine String-Repräsentation der vier Ecken des Rechtecks
     */
    public String toString() {
        String p1 = "(" + x + " | " + y + ")";                  // Oben links
        String p2 = "(" + (x + width) + " | " + y + ")";        // Oben rechts
        String p3 = "(" + x + " | " + (y - height) + ")";       // Unten links
        String p4 = "(" + (x + width) + " | " + (y - height) + ")"; // Unten rechts

        return p1 + ", " + p2 + ", " + p3 + ", " + p4;
    }
}