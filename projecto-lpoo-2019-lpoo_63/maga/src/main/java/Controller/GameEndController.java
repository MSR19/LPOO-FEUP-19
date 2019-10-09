package Controller;

import Model.Model;
import View.View;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;


public class GameEndController implements GameState {
    private MasterController masterController;
    private Model data;
    private View view;
    private boolean win;

    public GameEndController(MasterController masterController, View view, Model data) {
        this.masterController = masterController;
        this.view = view;
        this.data = data;
        this.win = false;
    }

    @Override
    public boolean processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowUp:
                break;
            case ArrowDown:
                break;

            case ArrowLeft:
                break;

            case ArrowRight:
                break;

            case Character:
                if (key.getCharacter() == 'q') return false;
                break;

            case EOF:        return false;

        }

        return true;
    }

    @Override
    public void processState()  throws IOException {
        if (win)
            view.drawGameEnd(data.getGameWinMenu());
        else
            view.drawGameEnd(data.getGameOverMenu());
    }

    public void setWin() {this.win = true; }

    public Boolean getWin() { return this.win; }
}
