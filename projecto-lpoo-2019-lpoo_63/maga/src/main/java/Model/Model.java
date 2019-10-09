package Model;

import java.util.*;

public class Model {

    private int mapWidth;
    private int mapHeight;

    private Hero hero;

    private int inventoryDisplacement;
    private int inventoryPointer;

    private List<MapElement> gameElements = new ArrayList<>();
    private List<MapElement> solidElements = new ArrayList<>();
    private List<MapElement> interactiveElements = new ArrayList<>();

    private List<TextLine> mainMenu = new ArrayList<>();
    private List<TextLine> gameMenu = new ArrayList<>();
    private List<TextLine> inventoryMenu = new ArrayList<>();
    private List<TextLine> glossaryMenu = new ArrayList<>();
    private List<TextLine> achievementMenu = new ArrayList<>();
    private List<TextLine> gameOverMenu = new ArrayList<>();
    private List<TextLine> gameWinMenu = new ArrayList<>();

    private Achievements moveAchievement;
    private Achievements interactAchievement;
    public List<Achievements> achievements = new ArrayList<>();


    public Model(int width, int height, int heroX, int heroY) {

        this.hero = new Hero(heroX, heroY);
        this.mapWidth = width;
        this.mapHeight = height;

        this.inventoryDisplacement = 0;
        this.inventoryPointer = 0;

        this.createElements();
        this.createMenus();
        this.createAchievements();
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public Hero getHero() {
        return hero;
    }

    public List<MapElement> getGameElements() {
        return gameElements;
    }

    public List<MapElement> getSolidElements() {
        return solidElements;
    }

    public List<MapElement> getInteractiveElements() {
        return interactiveElements;
    }

    public List<TextLine> getMainMenu() {
        return mainMenu;
    }

    public List<TextLine> getGameMenu() {
        return gameMenu;
    }

    public List<TextLine> getGameOverMenu() {
        return gameOverMenu;
    }

    public List<TextLine> getInventoryMenu() {
        return inventoryMenu;
    }

    public List<TextLine> getGlossaryMenu() {
        return glossaryMenu;
    }

    public List<TextLine> getAchievementMenu() {
        return achievementMenu;
    }

    public List<TextLine> getGameWinMenu() { return gameWinMenu; }

    public Achievements getMoveAchievement() {
        return moveAchievement;
    }

    public Achievements getInteractAchievement() {
        return interactAchievement;
    }

    public List<Achievements> getAchievements() {
        return achievements;
    }

    public int getInventoryDisplacement() {return  inventoryDisplacement; }

    public void incrementInventoryDisplacement() {this.inventoryDisplacement = this.inventoryDisplacement + 35;}

    public void decrementInventoryDisplacement() {this.inventoryDisplacement = this.inventoryDisplacement - 35;}

    public int getInventoryPointer() {return  inventoryPointer; }

    public void incrementInventoryPointer() {this.inventoryPointer++;}

    public void decrementInventoryPointer() {this.inventoryPointer--;}
    private void createElements() {
        List<Integer> xCoords = createRandomCoord(mapWidth, 20);
        List<Integer> yCoords = createRandomCoord(mapHeight, 20);
        RandomMapElementFactory randomNumberGenerator = new RandomMapElementFactory();
        BalancedMapElementFactory balancedMapElementFactory = new BalancedMapElementFactory();

        for (int i = 0; i != 20; i++) {
            if (i%2 == 0)
                gameElements.add(randomNumberGenerator.createMapElement(xCoords.get(i),yCoords.get(i)));
            else
                gameElements.add(balancedMapElementFactory.createMapElement(xCoords.get(i),yCoords.get(i)));
        }



        for (MapElement element : gameElements) {
            if (element instanceof Solid)
                this.solidElements.add(element);
            if (element instanceof Interactive)
                this.interactiveElements.add(element);
        }
    }

    private void createMenus() {
        mainMenu.add(new TextLine(mapWidth / 2 - 5, mapWidth / 8, "ROGUE XXI"));
        mainMenu.add(new TextLine(mapWidth / 2 - 10, mapHeight / 2, "PRESS ANY KEY TO PLAY"));

        gameOverMenu.add(new TextLine(mapWidth / 2 - 4, mapWidth / 8, "YOU DIED!"));
        gameOverMenu.add(new TextLine(mapWidth / 2 - 7, mapWidth / 8 + 6, "PRESS Q TO EXIT"));

        gameWinMenu.add(new TextLine(mapWidth / 2 - 3, mapWidth / 8, "YOU WIN!"));
        gameWinMenu.add(new TextLine(mapWidth / 2 - 7, mapWidth / 8 + 6, "PRESS Q TO EXIT"));

        gameMenu.add(new TextLine(2 , 2, ""));
        gameMenu.add(new TextLine(2 , 4, ""));
        gameMenu.add(new TextLine(mapWidth / 6, mapHeight - 4, "(A) ACHIEVEMENTS"));
        gameMenu.add(new TextLine(mapWidth / 2, mapHeight - 2, "(E) INTERACT"));
        gameMenu.add(new TextLine(mapWidth / 2, mapHeight - 4, "(I) INVENTORY"));
        gameMenu.add(new TextLine((mapWidth / 6), mapHeight - 2, "(L) GLOSSARY"));
        gameMenu.add(new TextLine((mapWidth / 6) * 5, mapHeight - 2, "(Q) QUIT"));


        inventoryMenu.add(new TextLine(mapWidth / 6, mapHeight - 4, "(A) ACHIEVEMENTS"));
        inventoryMenu.add(new TextLine(mapWidth /2, mapHeight - 4, "UP/DOWN to change item, L/R to move inventory"));
        inventoryMenu.add(new TextLine(mapWidth/2, mapHeight-2, "(E) USE ITEM"));
        inventoryMenu.add(new TextLine((mapWidth / 6), mapHeight - 2, "(L) GLOSSARY"));
        inventoryMenu.add(new TextLine((mapWidth / 6) * 5, mapHeight - 2, "Q: GO BACK"));

        glossaryMenu.add(new TextLine(mapWidth / 6, mapHeight - 4, "(A) ACHIEVEMENTS"));
        glossaryMenu.add(new TextLine(mapWidth / 2, mapHeight - 4, "(I) INVENTORY"));
        glossaryMenu.add(new TextLine((mapWidth / 6) * 5, mapHeight - 2, "(Q) GO BACK"));

        achievementMenu.add(new TextLine(mapWidth / 2, mapHeight - 4, "(I) INVENTORY"));
        achievementMenu.add(new TextLine((mapWidth / 6), mapHeight - 2, "(L) GLOSSARY"));
        achievementMenu.add(new TextLine((mapWidth / 6) * 5, mapHeight - 2, "(Q) GO BACK"));
    }

    public void createAchievements() {
        moveAchievement = new Achievements(mapWidth / 6, 4, "1 - First Step", "2 - Explorer", "3 - The world is your oyster", 0.5);
        interactAchievement = new Achievements(mapWidth / 6, 10, "1 - Observer", "2 - Watcher", "3 - Eagle Eye", 0.2);
        achievements.add(moveAchievement);
        achievements.add(interactAchievement);
    }

    public List<Integer> createRandomCoord(int length, int numElements) {
        List<Integer> randomCoords = new ArrayList<>();
        int i = 0;
        int newCoord;
        int max = length - 10;
        int min = 5;
        int range = max - 5 +1;

        while (i < numElements) {
            newCoord = (int) (Math.random() * range) + min;

            if (!randomCoords.contains(newCoord)) {
                System.out.print("(Range, Coord): ");
                System.out.print(range);
                System.out.print(" ");
                System.out.println(newCoord);
                randomCoords.add(newCoord);
                i++;
            }
        }

        return randomCoords;
    }

}



