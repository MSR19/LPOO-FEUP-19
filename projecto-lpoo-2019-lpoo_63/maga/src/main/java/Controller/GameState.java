package Controller;

import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public interface GameState {
    public boolean processKey(KeyStroke key);

    public void processState() throws IOException;
}
