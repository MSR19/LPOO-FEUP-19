package View;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public class TextLineView {

    public void draw (TextGraphics graphics, int x, int y, String message) {
        graphics.putString(new TerminalPosition(x, y), message);
    }
}
