package Controller;

import Model.Model;
import View.View;
import com.googlecode.lanterna.input.KeyStroke;
import java.io.IOException;

public class MasterController {
    GameState currentGameState;
    AchievementsController achievementsController;
    GameController gameController;
    GameEndController gameEndController;
    GlossaryController glossaryController;
    InventoryController inventoryController;
    MainMenuController mainMenuController;
    View view;
    Model data;

    public MasterController() {
        data = new Model(130, 40, 10, 10);
        view = new View(130, 40);
        achievementsController = new AchievementsController(this, view, data);
        gameController = new GameController(this, view, data);
        gameEndController = new GameEndController(this, view, data);
        glossaryController = new GlossaryController(this, view, data);
        inventoryController = new InventoryController(this, view, data);
        mainMenuController = new MainMenuController(this, view, data);


        this.setToMainMenuController();
    }

    public void run () {
        while (true) {
            try {
                currentGameState.processState();
                KeyStroke key = view.readInput();
                if (!currentGameState.processKey(key)) {
                    view.closeView();
                    return;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setToAchievementsController() {
        this.currentGameState = achievementsController;
    }

    public void setToGameController() {
        this.currentGameState = gameController;
    }

    public void setToGameOverController() { this.currentGameState = gameEndController; }

    public void setToGlossaryController() {
        this.currentGameState = glossaryController;
    }

    public void setToInventoryController() {
        this.currentGameState = inventoryController;
    }

    public void setToMainMenuController() {
        this.currentGameState = mainMenuController;
    }

    public void setToGameWinController() {
        this.gameEndController.setWin();
        this.currentGameState = gameEndController;
    }

    public GameState getCurrentGameState() {return currentGameState; }

    public AchievementsController getAchievementsController() {return achievementsController; }

    public GameController getGameController() {return  gameController; }

    public GameEndController getGameEndController() { return gameEndController; }

    public GlossaryController getGlossaryController() { return  glossaryController; }

    public InventoryController getInventoryController()  {return  inventoryController; }

    public MainMenuController getMainMenuController() {return mainMenuController; }
}
