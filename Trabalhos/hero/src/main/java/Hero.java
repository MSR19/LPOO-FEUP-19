import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Hero extends  Element{
    private Position position;

    Hero (int x, int y) {
        position = new Position();
        position.setX(x);
        position.setY(y);
    }

    public void draw(TextGraphics graphics) {
        super.draw(graphics,'X',position.getX(),position.getY());
    }


    public void setPosition(Position position) {
        this.position = position;
    }

    public  Position getPosition() {
        return position;
    }

    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }

    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }

    public Position moveLeft() {
        return new Position(position.getX() -1, position.getY());
    }
}
