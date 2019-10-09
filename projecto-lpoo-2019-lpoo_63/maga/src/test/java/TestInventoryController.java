import Controller.InventoryController;
import Controller.MasterController;
import Model.*;
import View.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TestInventoryController {
    Model model;
    View view;
    InventoryController inventoryController;
    MasterController masterController;

    @Before
    public void setUp() {
        model = new Model(100,100,10,10);
        model.getHero().getInventory().add(new Weapon("true",10, 10));
        model.getHero().getInventory().add(new Item("Cenas"));
        model.getHero().setWeapon(new Weapon("false", 10, 10));

        masterController = new MasterController();

        view = new View(10,10);
        inventoryController = new InventoryController(masterController, view, model);
    }

    @Test
    public void equipWeapon() {
        if (model.getHero().getInventory().size() <= 1 && model.getHero().getWeapon().getName().equals("false")) {
            if (model.getHero().getInventory().get(0).getName().equals("true") && Weapon.class.isInstance(model.getHero().getInventory().get(0))) {
                Item result = model.getHero().getInventory().get(0);
                inventoryController.processCharacterKeys(new KeyStroke('e', false, false, false));
                assertEquals(model.getHero().getWeapon(), result);


                inventoryController.processCharacterKeys(new KeyStroke('e', false, false, false));
                assertEquals(model.getHero().getWeapon(), result);
            }
        }
    }

    @Test
    public void changeController() {
        inventoryController.processKey(new KeyStroke('l', false, false, false));

        assertEquals(masterController.getCurrentGameState(), masterController.getGlossaryController());

        masterController.setToInventoryController();
        inventoryController.processKey(new KeyStroke('a', false, false, false));

        assertEquals(masterController.getCurrentGameState(),masterController.getAchievementsController());

        masterController.setToInventoryController();
        inventoryController.processKey(new KeyStroke('q', false, false, false));

        assertEquals(masterController.getCurrentGameState(),masterController.getGameController());
    }

    @Test
    public void inventoryMoves() {
        int beforePointer = model.getInventoryPointer();
        int beforeInventoryDisplacement = model.getInventoryDisplacement();

        inventoryController.processKey(new KeyStroke(KeyType.ArrowUp,false,false, false));

        System.out.println(model.getInventoryPointer());

        System.out.println(beforePointer);

        assertEquals(model.getInventoryPointer() , beforePointer);

        inventoryController.processKey(new KeyStroke(KeyType.ArrowDown,false,false, false));

        assertTrue(model.getInventoryPointer() > beforePointer);

        inventoryController.processKey(new KeyStroke(KeyType.ArrowUp,false,false, false));

        assertTrue(model.getInventoryPointer() == beforePointer);

        inventoryController.processKey(new KeyStroke(KeyType.ArrowLeft,false,false,false));

        assertTrue(model.getInventoryDisplacement() > beforeInventoryDisplacement);

        inventoryController.processKey(new KeyStroke(KeyType.ArrowRight,false,false, false));

        assertTrue(model.getInventoryDisplacement() == beforeInventoryDisplacement);

        inventoryController.processKey(new KeyStroke(KeyType.ArrowRight,false,false, false));

        assertTrue(model.getInventoryDisplacement() < beforeInventoryDisplacement);

    }

}
