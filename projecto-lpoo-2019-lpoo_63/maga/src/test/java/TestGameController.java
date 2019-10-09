import Controller.GameController;
import Controller.MasterController;
import Model.Model;
import Model.Wall;
import Model.Position;
import Model.MapElement;
import Model.Warrior;
import Model.Item;
import Model.Weapon;
import Model.Corpse;
import View.View;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static junit.framework.TestCase.assertEquals;

public class TestGameController {
    Model model;
    View view;
    GameController gameController;
    MasterController masterController;

    @Before
    public void setUp() {
        model = new Model(100,100,10,10);
        masterController = new MasterController();
        view = new View(10,10);

        gameController = new GameController(masterController, view, model);
        masterController.setToGameController();

        model.getHero().setWeapon(new Weapon("Mr.OP", 10000000, 1000000));
    }

    @Test
    public void moveThroughSolid() {
        Wall wall1 = new Wall(model.getHero().getPosition().getxCord()+1,model.getHero().getPosition().getyCord());
        Wall wall2 = new Wall(model.getHero().getPosition().getxCord()-1,model.getHero().getPosition().getyCord());
        Wall wall3 = new Wall(model.getHero().getPosition().getxCord(),model.getHero().getPosition().getyCord()+1);
        Wall wall4 = new Wall(model.getHero().getPosition().getxCord(),model.getHero().getPosition().getyCord()-1);

        model.getSolidElements().add(wall1);
        model.getSolidElements().add(wall2);
        model.getSolidElements().add(wall3);
        model.getSolidElements().add(wall4);

        Position oldPosition = model.getHero().getPosition();

        gameController.processKey(new KeyStroke(KeyType.ArrowUp,false,false, false));

        assertEquals(oldPosition, model.getHero().getPosition());

        gameController.processKey(new KeyStroke(KeyType.ArrowDown,false,false, false));

        assertEquals(oldPosition, model.getHero().getPosition());

        gameController.processKey(new KeyStroke(KeyType.ArrowRight,false,false, false));

        assertEquals(oldPosition, model.getHero().getPosition());

        gameController.processKey(new KeyStroke(KeyType.ArrowLeft,false,false, false));

        assertEquals(oldPosition, model.getHero().getPosition());
    }

    @Test
    public void move() {
        Position position1 = new Position(model.getHero().getPosition().getxCord()+1,model.getHero().getPosition().getyCord());
        Position position2 = new Position(model.getHero().getPosition().getxCord()-1,model.getHero().getPosition().getyCord());
        Position position3 = new Position(model.getHero().getPosition().getxCord(),model.getHero().getPosition().getyCord()+1);
        Position position4 = new Position(model.getHero().getPosition().getxCord(),model.getHero().getPosition().getyCord()-1);

        for (MapElement mapElement: model.getSolidElements()) {
            if (mapElement.getPosition().equals(position1)) model.getSolidElements().remove(mapElement);
            else if (mapElement.getPosition().equals(position2)) model.getSolidElements().remove(mapElement);
            else if (mapElement.getPosition().equals(position3)) model.getSolidElements().remove(mapElement);
            else if (mapElement.getPosition().equals(position4)) model.getSolidElements().remove(mapElement);
        }


        Position oldPosition = model.getHero().getPosition();

        gameController.processKey(new KeyStroke(KeyType.ArrowDown,false,false, false));

        assertEquals(position3, model.getHero().getPosition());

        gameController.processKey(new KeyStroke(KeyType.ArrowUp,false,false, false));

        assertEquals(oldPosition, model.getHero().getPosition());

        gameController.processKey(new KeyStroke(KeyType.ArrowRight,false,false, false));

        assertEquals(position1, model.getHero().getPosition());

        gameController.processKey(new KeyStroke(KeyType.ArrowLeft,false,false, false));

        assertEquals(oldPosition, model.getHero().getPosition());
    }

    @Test
    public void Wining () {
        for (int i = 0; i != model.getGameElements().size(); i++) {
            if (Warrior.class.isInstance(model.getGameElements().get(i))) {
                model.getSolidElements().remove(model.getGameElements().get(i));
                model.getGameElements().remove(model.getGameElements().get(i));
            }
        }
        for (int i = 0; i != model.getGameElements().size(); i++) {
            if (Warrior.class.isInstance(model.getGameElements().get(i))) {
                model.getSolidElements().remove(model.getGameElements().get(i));
                model.getGameElements().remove(model.getGameElements().get(i));
            }
        }
        for (int i = 0; i != model.getGameElements().size(); i++) {
            if (Warrior.class.isInstance(model.getGameElements().get(i))) {
                model.getSolidElements().remove(model.getGameElements().get(i));
                model.getGameElements().remove(model.getGameElements().get(i));
            }
        }


        List<Item> items = new ArrayList<>();

        Warrior warrior = new Warrior(new Weapon("...",1,1),model.getHero().getPosition().getxCord(),model.getHero().getPosition().getyCord()-1,10, items);

        model.getGameElements().add(warrior);
        model.getSolidElements().add(warrior);

        gameController.processKey(new KeyStroke(KeyType.ArrowUp,false,false, false));

        for (MapElement mapElement: model.getGameElements()) {
            if (Warrior.class.isInstance(mapElement)) {
                assertEquals(true, false);
            }
        }

        assertEquals(masterController.getCurrentGameState(), masterController.getGameEndController());
    }

    @Test
    public void Losing () {
        model.getHero().setWeapon(new Weapon("", 1, 1));

        for (int i = 0; i != model.getGameElements().size(); i++) {
            if (Warrior.class.isInstance(model.getGameElements().get(i))) {
                model.getSolidElements().remove(model.getGameElements().get(i));
                model.getGameElements().remove(model.getGameElements().get(i));
            }
        }

        List<Item> items = new ArrayList<>();

        Warrior warrior = new Warrior(new Weapon("...",10000,1000),model.getHero().getPosition().getxCord(),model.getHero().getPosition().getyCord()-1,10, items);

        model.getGameElements().add(warrior);
        model.getSolidElements().add(warrior);

        gameController.processKey(new KeyStroke(KeyType.ArrowUp,false,false, false));

        for (MapElement mapElement: model.getGameElements()) {
            if (Warrior.class.isInstance(mapElement)) {
                assertEquals(true, true);
            }
        }

        assertEquals(masterController.getCurrentGameState(), masterController.getGameEndController());

        assertTrue(masterController.getGameEndController().getWin() == false);
    }

    @Test
    public void InventoryLogic () {
        Position position1 = new Position(model.getHero().getPosition().getxCord()+1,model.getHero().getPosition().getyCord());
        Position position2 = new Position(model.getHero().getPosition().getxCord()-1,model.getHero().getPosition().getyCord());
        Position position3 = new Position(model.getHero().getPosition().getxCord(),model.getHero().getPosition().getyCord()+1);
        Position position4 = new Position(model.getHero().getPosition().getxCord(),model.getHero().getPosition().getyCord()-1);

        for (MapElement mapElement: model.getInteractiveElements()) {
            if (mapElement.getPosition().equals(position1)) model.getInteractiveElements().remove(mapElement);
            else if (mapElement.getPosition().equals(position2)) model.getInteractiveElements().remove(mapElement);
            else if (mapElement.getPosition().equals(position3)) model.getInteractiveElements().remove(mapElement);
            else if (mapElement.getPosition().equals(position4)) model.getInteractiveElements().remove(mapElement);
        }

        List<Item> items = new ArrayList<>();
        items.add(new Item("test result"));

        for (Item item: model.getHero().getInventory()) {
            System.out.println(item.getName());
        }

        Corpse corpse = new Corpse(items,position1.getxCord(), position1.getyCord());

        model.getInteractiveElements().add(corpse);

        assertEquals(model.getHero().getInventory().size(), 0);

        gameController.processKey(new KeyStroke('e',false,false, false));

        assertEquals(model.getHero().getInventory().size(), 1);

        gameController.processKey(new KeyStroke('e',false,false, false));

        assertEquals(model.getHero().getInventory().size(), 1);

    }

    @Test
    public void CorpseLogic () {
        Position position1 = new Position(model.getHero().getPosition().getxCord()+1,model.getHero().getPosition().getyCord());
        Position position2 = new Position(model.getHero().getPosition().getxCord()-1,model.getHero().getPosition().getyCord());
        Position position3 = new Position(model.getHero().getPosition().getxCord(),model.getHero().getPosition().getyCord()+1);
        Position position4 = new Position(model.getHero().getPosition().getxCord(),model.getHero().getPosition().getyCord()-1);

        for (MapElement mapElement: model.getSolidElements()) {
            if (mapElement.getPosition().equals(position1)) model.getSolidElements().remove(mapElement);
            else if (mapElement.getPosition().equals(position2)) model.getSolidElements().remove(mapElement);
            else if (mapElement.getPosition().equals(position3)) model.getSolidElements().remove(mapElement);
            else if (mapElement.getPosition().equals(position4)) model.getSolidElements().remove(mapElement);
        }

        List<Item> items = new ArrayList<>();

        Warrior warrior = new Warrior(new Weapon("",0,0), position1.getxCord(), position1.getyCord(),10, items);

        model.getGameElements().add(warrior);
        model.getSolidElements().add(warrior);

        for (MapElement mapElement: model.getSolidElements()) {
            if (mapElement.getPosition().equals(position1))
                assertTrue(Warrior.class.isInstance(mapElement));
        }

        gameController.processKey(new KeyStroke(KeyType.ArrowRight,false,false, false));

        for (MapElement mapElement: model.getSolidElements()) {
            if (mapElement.getPosition().equals(position1))
                assertTrue(Corpse.class.isInstance(mapElement));
        }

        Position oldPosition = model.getHero().getPosition();

        gameController.processKey(new KeyStroke('e',false,false, false));
        gameController.processKey(new KeyStroke(KeyType.ArrowRight,false,false, false));

        assertEquals (model.getHero().getInventory().size(), 1);
        assertTrue(oldPosition != model.getHero().getPosition());
    }

    @Test
    public void changeController () {
        gameController.processCharacterKeys(new KeyStroke('a', false, false, false));

        assertEquals(masterController.getCurrentGameState(), masterController.getAchievementsController());

        masterController.setToAchievementsController();
        gameController.processCharacterKeys(new KeyStroke('i', false, false, false));

        assertEquals(masterController.getCurrentGameState(),masterController.getInventoryController());

        masterController.setToAchievementsController();
        gameController.processCharacterKeys(new KeyStroke('l', false, false, false));

        assertEquals(masterController.getCurrentGameState(),masterController.getGlossaryController());

    }
    //Menus Logic
}
