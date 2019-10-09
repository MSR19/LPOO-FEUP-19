import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element{
    private Position position;

    public Wall(int x, int y) {
        position = new Position();
        position.setX(x);
        position.setY(y);
    }

    public void draw(TextGraphics graphics) {
        super.draw(graphics,'H',position.getX(),position.getY());
    }

}
