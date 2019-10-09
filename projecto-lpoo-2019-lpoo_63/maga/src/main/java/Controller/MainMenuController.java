package Controller;

import Model.Model;
import View.View;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class MainMenuController implements GameState {
    private MasterController masterController;
    private Model data;
    private View view;

    public MainMenuController(MasterController masterController, View view, Model data) {
        this.masterController = masterController;
        this.view = view;
        this.data = data;
    }

    @Override
    public boolean processKey(KeyStroke key) {
        if (key.getKeyType() != KeyType.EOF) {
            masterController.setToGameController();
            return true;
        }
        else return false;
    }

    @Override
    public void processState() throws IOException {
        view.drawMainMenu(data.getMainMenu());
    }
}
