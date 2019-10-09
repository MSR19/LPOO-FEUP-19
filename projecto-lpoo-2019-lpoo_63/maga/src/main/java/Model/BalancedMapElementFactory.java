package Model;

import java.util.ArrayList;
import java.util.List;

public class BalancedMapElementFactory implements MapElementFactory {
    private int iteration;
    private List<Weapon> weapons = new ArrayList<>();
    private List<List<Item>> invectories = new ArrayList<>();
    private List<Integer> hps = new ArrayList<>();
    private List<String> phrases = new ArrayList<>();
    private RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    BalancedMapElementFactory () {
        this.createWapons();
        this.createInventories();
        this.createHps();
        this.createPhrases();
    }


    @Override
    public MapElement createMapElement(int x, int y) {
        MapElement mapElement = null;

        int weaponId;
        int liveId;
        int pharseId;
        int inventoryId;

        switch (iteration%5) {
            case 0:
                weaponId = randomNumberGenerator.createRandomNumber(weapons.size()-1,0);
                liveId = randomNumberGenerator.createRandomNumber(hps.size()-1,0);
                inventoryId = randomNumberGenerator.createRandomNumber(invectories.size()-1,0);
                mapElement = new Warrior(weapons.get(weaponId), x, y, hps.get(liveId), invectories.get(inventoryId));
                break;
            case 1:
                pharseId = randomNumberGenerator.createRandomNumber(phrases.size()-1, 0);
                liveId = randomNumberGenerator.createRandomNumber(hps.size()-1,0);
                inventoryId = randomNumberGenerator.createRandomNumber(invectories.size()-1,0);
                mapElement = new Villager(phrases.get(pharseId),x,y,hps.get(liveId), invectories.get(inventoryId));
                break;
            case 2:
                inventoryId = randomNumberGenerator.createRandomNumber(invectories.size()-1,0);
                mapElement = new Corpse(invectories.get(inventoryId),x,y);
                break;
            case 3:
                inventoryId = randomNumberGenerator.createRandomNumber(invectories.size()-1,0);
                mapElement = new Chest(invectories.get(inventoryId),x,y);
                break;
            case 4:
                pharseId = randomNumberGenerator.createRandomNumber(phrases.size()-1, 0);
                mapElement = new Billboard(phrases.get(pharseId),x,y);
                break;
        }
        if (mapElement == null) {
            mapElement = new Wall(x,y);
        }
        iteration++;
        return mapElement;
    }


    private void createWapons() {
        weapons.add(new Weapon("Machado", 3, 40));
        weapons.add(new Weapon("BrokenSwors",0, 100));
        weapons.add(new Weapon( "Rapier",2, 45));
        weapons.add(new Weapon( "Great Club", 20, 2));
        weapons.add(new Weapon("Sickle", 1, 100));
        weapons.add(new Weapon("Pen", 10, 15));
    }

    private void createInventories() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("purse of gp"));
        items.add(new Item( "lucky charm"));
        invectories.add(items);
        items.add(new Item("Hammer"));
        invectories.add(items);
        items.add(new Item("The book of truths"));
        invectories.add(items);

    }

    private void createHps() {
        hps.add(20);
        hps.add(10);
        hps.add(1);
        hps.add(24);
    }

     private void createPhrases() {
        phrases.add("in April, waters thousand");
        phrases.add("A bird in the hand is better than two to fly");
        phrases.add("Quickly and well does not anyone");
        phrases.add("...");
        phrases.add("In doubt, stick your finger");
     }
}
