import Controller.GameEndController;
import Controller.MasterController;
import Model.Model;
import View.View;
import com.googlecode.lanterna.input.KeyStroke;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TestGameEndController {
    Model model;
    View view;
    GameEndController gameEndController;
    MasterController masterController;

    @Before
    public void setUp() {
        masterController = new MasterController();

        view = new View(10,10);
        gameEndController = new GameEndController(masterController, view, model);
        masterController.setToGameOverController();
    }


    @Test
    public void changeController() {

        gameEndController.processKey(new KeyStroke('l', false, false, false));

        assertTrue(masterController.getCurrentGameState() != masterController.getGlossaryController());

        gameEndController.processKey(new KeyStroke('i', false, false, false));

        assertTrue(masterController.getCurrentGameState() != masterController.getInventoryController());

        gameEndController.processKey(new KeyStroke('a', false, false, false));

        assertTrue(masterController.getCurrentGameState() != masterController.getGameController());

    }
}
