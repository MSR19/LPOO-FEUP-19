import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element {
    private Position position;

    public Coin(int x, int y) {
        position = new Position();
        position.setX(x);
        position.setY(y);
    }

    public void draw(TextGraphics graphics) {
        super.draw(graphics,'C',position.getX(),position.getY());
    }
}
