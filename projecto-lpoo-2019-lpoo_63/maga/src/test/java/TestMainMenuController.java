import Controller.MainMenuController;
import Controller.MasterController;
import Model.Model;
import View.View;
import com.googlecode.lanterna.input.KeyStroke;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestMainMenuController {
    Model model;
    View view;
    MainMenuController mainMenuController;
    MasterController masterController;

    @Before
    public void setUp() {
        model = new Model(100, 100, 10, 10);
        masterController = new MasterController();
        view = new View(10, 10);

        mainMenuController = new MainMenuController( masterController, view, model);
    }

    @Test
    public void changeController() {
        if (masterController.getCurrentGameState() == masterController.getMainMenuController()) {
            mainMenuController.processKey(new KeyStroke('a', false, false, false));

            assertEquals(masterController.getCurrentGameState(), masterController.getGameController());
        }
    }
}

