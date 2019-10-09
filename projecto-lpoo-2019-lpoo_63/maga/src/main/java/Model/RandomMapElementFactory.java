package Model;

import java.util.ArrayList;
import java.util.List;

public class RandomMapElementFactory implements MapElementFactory {
    private List<Weapon> weapons = new ArrayList<>();
    private List<List<Item>> invectories = new ArrayList<>();
    private List<Integer> hps = new ArrayList<>();
    private List<String> phrases = new ArrayList<>();
    private RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    RandomMapElementFactory() {
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

        switch (randomNumberGenerator.createRandomNumber(4,0)) {
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
        return mapElement;
    }


    private void createWapons() {
        weapons.add(new Weapon("Death's sickle", randomNumberGenerator.createRandomNumber(300,20),randomNumberGenerator.createRandomNumber(100,0)));
        weapons.add(new Weapon("Thor's hammer", randomNumberGenerator.createRandomNumber(50,10),randomNumberGenerator.createRandomNumber(20,0)));
        weapons.add(new Weapon("RNGesus' gauntlet",  randomNumberGenerator.createRandomNumber(100,-100),randomNumberGenerator.createRandomNumber(10,0)));
        weapons.add(new Weapon("Nuke",1000, randomNumberGenerator.createRandomNumber(5,0)));
        weapons.add(new Weapon("passive aggressive", randomNumberGenerator.createRandomNumber(10,-10),50));
    }

    private void createInventories() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Boss' head"));
        items.add(new Item( "100 gp"));
        invectories.add(items);
        items.add(new Item("RNGesus first testament"));
        invectories.add(items);
        items.add(new Item("magic wand"));
        invectories.add(items);

    }

    private void createHps() {
        for (int i = 0; i != 20; i++) {
            hps.add(randomNumberGenerator.createRandomNumber(100,1));
        }
    }

    private void createPhrases() {
        phrases.add("the sword is stronger than the feather");
        phrases.add("this game is unbalanced");
        phrases.add("are you going to kill me?");
        phrases.add("go away");
        phrases.add("if at you don't succeed try again");
    }

}