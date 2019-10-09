package Controller;


import Model.Model;
import View.View;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class AchievementsController implements GameState {
    private MasterController masterController;
    private View view;
    private Model data;

    public AchievementsController(MasterController masterController, View view, Model data) {
        this.masterController = masterController;
        this.view = view;
        this.data = data;
    }

    @Override
    public boolean processKey(KeyStroke key) {
        switch (key.getKeyType()) {

            case Character:  return processCharacterKeys(key);

            case EOF:        return false;

        }
        return true;
    }

    public boolean processCharacterKeys(KeyStroke key){
        switch(key.getCharacter()){
            case 'q':
                masterController.setToGameController();
                break;

            case 'i':
                masterController.setToInventoryController();
                break;

            case 'l':
                masterController.setToGlossaryController();
                break;
        }
        return true;
    }

    @Override
    public void processState() throws IOException {
        view.drawAchievements(data.getAchievementMenu(),data.getAchievements());
    }
}
