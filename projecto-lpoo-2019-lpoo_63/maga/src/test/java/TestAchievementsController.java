import Controller.AchievementsController;
import Controller.MasterController;
import Model.Model;
import View.View;
import com.googlecode.lanterna.input.KeyStroke;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestAchievementsController {
    Model model;
    View view;
    AchievementsController achievementsController;
    MasterController masterController;

    @Before
    public void setUp() {
        masterController = new MasterController();

        view = new View(10,10);
        achievementsController = new AchievementsController(masterController, view, model);
        masterController.setToAchievementsController();
    }

    @Test
    public void changeController() {
        achievementsController.processKey(new KeyStroke('l', false, false, false));

        assertEquals(masterController.getCurrentGameState(), masterController.getGlossaryController());

        masterController.setToAchievementsController();
        achievementsController.processKey(new KeyStroke('i', false, false, false));

        assertEquals(masterController.getCurrentGameState(),masterController.getInventoryController());

        masterController.setToAchievementsController();
        achievementsController.processKey(new KeyStroke('q', false, false, false));

        assertEquals(masterController.getCurrentGameState(),masterController.getGameController());

    }
}
