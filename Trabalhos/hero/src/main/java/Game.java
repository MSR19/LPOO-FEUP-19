import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.ArrowUp;

public class Game {

    private Screen myScreen;
    private TextGraphics graphics;





    private Arena arena = new Arena(20,20, 10, 10);
    Game() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            myScreen = new TerminalScreen(terminal);

            myScreen.setCursorPosition(null);   // we don't need a cursor
            myScreen.startScreen();             // screens must be started
            myScreen.doResizeIfNecessary();     // resize screen if necessary
            graphics = myScreen.newTextGraphics();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        myScreen.clear();
        arena.draw(myScreen.newTextGraphics());
        myScreen.refresh();
    }


    public void run() {
        boolean eof = false;
        boolean screen = true;
        while (!eof) {
            try {
                draw();
                KeyStroke key = myScreen.readInput();
                if (!arena.processKey(key))
                    switch (key.getKeyType()) {
                        case Character:
                                myScreen.close();
                            break;
                        case EOF:
                            eof = true;
                            break;
                    }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
