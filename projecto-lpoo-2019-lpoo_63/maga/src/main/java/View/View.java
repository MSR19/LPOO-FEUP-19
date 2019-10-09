package View;

import Model.Model;
import Model.Hero;
import Model.MapElement;
import Model.TextLine;
import Model.Achievements;
import Model.Item;
import Model.Billboard;
import Model.Chest;
import Model.Corpse;
import Model.Warrior;
import Model.Villager;
import Model.Wall;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class View {
    private Screen myScreen;
    private TextGraphics graphics;
    private int screenWidth, screenHeight;
    private MapElementView mapElementView = new MapElementView();
    private TextLineView textLineView = new TextLineView();
    private AchievementsView achievementsView = new AchievementsView();

    public View(int screenWidth, int screenHeight) {
        try {
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(screenWidth, screenHeight)).createTerminal();
            myScreen = new TerminalScreen(terminal);

            myScreen.setCursorPosition(null);   // we don't need a cursor
            myScreen.startScreen();             // screens must be started
            myScreen.doResizeIfNecessary();     // resize screen if necessary
            graphics = myScreen.newTextGraphics();

            this.screenWidth = screenWidth;
            this.screenHeight = screenHeight;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeView() throws IOException {
        myScreen.close();
    }

    public KeyStroke readInput() throws IOException {
        return myScreen.readInput();
    }

    public void drawGame(Hero hero, List<MapElement> mapElements, List<TextLine> legend) throws IOException {
        myScreen.clear();
        this.fillScreen();

        for (MapElement element : mapElements) {
            mapElementView.draw(this.graphics, element.getSymbol(), element.getPosition().getxCord(), element.getPosition().getyCord(), element.getColor());
        }

        mapElementView.draw(this.graphics, hero.getSymbol(), hero.getPosition().getxCord(), hero.getPosition().getyCord(), hero.getColor());

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        this.drawTextLines(legend);
        graphics.putString(new TerminalPosition(screenWidth-10, 1), "HP: "+hero.getLife());
        myScreen.refresh();
    }

    public void drawGameEnd(List<TextLine> legend) throws IOException {
        myScreen.clear();
        this.fillScreen();
        this.drawTextLines(legend);
        myScreen.refresh();
    }

    public void drawInventory(Hero hero, List<TextLine> legend, int inventoryDisplacement, int inventoryPointer) throws IOException {
        myScreen.clear();
        this.fillScreen();
        this.drawInventoryList(hero, inventoryDisplacement, inventoryPointer);
        this.drawTextLines(legend);
        myScreen.refresh();
    }

    public void drawAchievements(List<TextLine> legend, List<Achievements> achievements) throws IOException {
        myScreen.clear();
        this.fillScreen();
        this.drawAchievementList();
        this.drawAchievementsList(achievements);
        this.drawTextLines(legend);
        myScreen.refresh();
    }

    public void drawGlossary(List<TextLine> legend) throws IOException {
        myScreen.clear();
        this.fillScreen();
        this.glossary();
        this.drawTextLines(legend);
        myScreen.refresh();
    }

    public void drawMainMenu(List<TextLine> legend) throws IOException {
        myScreen.clear();
        this.fillScreen();
        this.drawTextLines(legend);
        myScreen.refresh();
    }

    private void fillScreen() {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(screenWidth, screenHeight), ' ');
    }

    private void drawInventoryList(Hero hero, int inventoryDisplacement, int inventoryPointer) {
        int y = 2;
        int x = screenWidth /6;

        graphics.putString(new TerminalPosition(x, y), "INVENTORY:");
        y += 2;

        graphics.putString(new TerminalPosition(x, y), "EQUIPPED WEAPON:");
        graphics.putString(new TerminalPosition(x + 17, y), hero.getWeapon().getName());
        graphics.putString(new TerminalPosition(x+ 20 + hero.getWeapon().getName().length(), y), "DAMAGE:");
        graphics.putString(new TerminalPosition(x+ 28 + hero.getWeapon().getName().length(), y), String.valueOf(hero.getWeapon().getDamage()));

        x = x - inventoryDisplacement;
        for (int i = 0; i != hero.getInventory().size(); i++) {
            if (i%15 == 0 && i != 0) {
                y = 6;
                x = x +35;
            }
            else
                y += 2;
            graphics.putString(new TerminalPosition(x, y), hero.getInventory().get(i).getName());

            if (i == inventoryPointer) {
                graphics.putString(new TerminalPosition(x-2, y), ">");
            }
        }
    }

    private void drawAchievementList() {
        graphics.putString(new TerminalPosition(screenWidth / 6, 2), "ACHIEVEMENTS:");
    }

    private void glossary() {
        List<Item> items = new ArrayList<>();

        graphics.putString(new TerminalPosition(screenWidth / 6, 2), "GLOSSARY");

        Billboard billboard = new Billboard("", 0, 0);
        this.showClassSymbol(billboard, Billboard.class.getSimpleName(), 4, graphics);

        Chest chest = new Chest(null, 0, 0);
        this.showClassSymbol(chest, Chest.class.getSimpleName(), 6, graphics);

        Corpse corpse = new Corpse(null, 0, 0);
        this.showClassSymbol(corpse, Corpse.class.getSimpleName(), 8, graphics);

        Hero hero = new Hero(0, 0);
        this.showClassSymbol(hero, Hero.class.getSimpleName(), 10, graphics);

        Villager villager = new Villager("", 0, 0, 0, items);
        this.showClassSymbol(villager, Villager.class.getSimpleName(), 12, graphics);

        Wall wall = new Wall(0, 0);
        this.showClassSymbol(wall, Wall.class.getSimpleName(), 14, graphics);

        Warrior warrior = new Warrior(null, 0, 0, 0, items);
        this.showClassSymbol(warrior, Warrior.class.getSimpleName(), 16, graphics);
    }

    private void showClassSymbol(MapElement element, String className, int row, TextGraphics graphics) {
        graphics.putString(new TerminalPosition(screenWidth / 6, row), className);
        graphics.putString(new TerminalPosition(screenWidth / 6 + className.length(), row), ": ");
        graphics.putString(new TerminalPosition(screenWidth / 6 + className.length() + 2, row), String.valueOf(element.getSymbol()));
    }

    private void drawTextLines(List<TextLine> textLines) {
        for (TextLine textLine : textLines)
            textLineView.draw(this.graphics, textLine.getPosition().getxCord(), textLine.getPosition().getyCord(), textLine.getMessage());
    }

    private void drawAchievementsList(List<Achievements> achievements) {
        for (Achievements achievement : achievements)
            achievementsView.displayAchievement(this.graphics, achievement.getNumberOfTimes(), achievement.getScaleFactor(), achievement.getFirstForm(), achievement.getSecondForm(), achievement.getThirdForm(), achievement.getX(), achievement.getY());
    }
}

