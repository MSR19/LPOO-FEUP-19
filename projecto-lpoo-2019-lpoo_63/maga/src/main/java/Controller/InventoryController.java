package Controller;

import Model.Item;
import Model.Model;
import Model.Weapon;
import View.View;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class InventoryController implements GameState {
    private MasterController masterController;
    private Model data;
    private View view;

    public InventoryController(MasterController masterController, View view, Model data) {
        this.masterController = masterController;
        this.view = view;
        this.data = data;
    }


    @Override
    public boolean processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowUp:
                if (data.getInventoryPointer() > 0)
                    data.decrementInventoryPointer();
                break;
            case ArrowDown:
                if (data.getInventoryPointer() < data.getHero().getInventory().size()-1)
                    data.incrementInventoryPointer();
                break;

            case ArrowLeft:
                data.incrementInventoryDisplacement();
                break;

            case ArrowRight:
                data.decrementInventoryDisplacement();
                break;

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

            case 'e':
                Item item = data.getHero().getInventory().get(data.getInventoryPointer());
                if (Weapon.class.isInstance(item)) {
                    data.getHero().getInventory().remove(item);
                    data.getHero().getInventory().add(data.getHero().getWeapon());
                    data.getHero().setWeapon((Weapon)item);
                }
                break;

            case 'l':
                masterController.setToGlossaryController();
                break;

            case 'a':
                masterController.setToAchievementsController();
                break;
        }
        return true;
    }

    @Override
    public void processState() throws IOException {
        view.drawInventory(data.getHero(), data.getInventoryMenu(), data.getInventoryDisplacement(), data.getInventoryPointer());
    }
}
