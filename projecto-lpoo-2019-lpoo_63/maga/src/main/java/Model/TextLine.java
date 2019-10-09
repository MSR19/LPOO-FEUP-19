package Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public class TextLine {
    private Position position;
    private String message;

    TextLine(int x, int y, String message) {
        this.position = new Position(x,y);
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Position getPosition() {return position; }

    public String getMessage() {return message; }
}
