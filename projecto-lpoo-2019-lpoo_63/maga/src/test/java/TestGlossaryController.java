import Controller.GlossaryController;
import Controller.MasterController;
import Model.Model;
import View.View;
import com.googlecode.lanterna.input.KeyStroke;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestGlossaryController {
    Model model;
    View view;
    GlossaryController glossaryController;
    MasterController masterController;

    @Before
    public void setUp() {
        masterController = new MasterController();

        view = new View(10,10);
        glossaryController = new GlossaryController(masterController, view, model);
        masterController.setToGlossaryController();
    }

    @Test
    public void changeController() {
        glossaryController.processCharacterKeys(new KeyStroke('a', false, false, false));

        assertEquals(masterController.getCurrentGameState(), masterController.getAchievementsController());

        masterController.setToAchievementsController();
        glossaryController.processCharacterKeys(new KeyStroke('i', false, false, false));

        assertEquals(masterController.getCurrentGameState(),masterController.getInventoryController());

        masterController.setToAchievementsController();
        glossaryController.processCharacterKeys(new KeyStroke('q', false, false, false));

        assertEquals(masterController.getCurrentGameState(),masterController.getGameController());

    }
}
