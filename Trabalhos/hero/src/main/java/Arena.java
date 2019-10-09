import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;

    Arena () {
        this.hero = new Hero(10, 10);
        width = 20;
        height = 20;
        this.walls = createWalls();
    }
    Arena (int width, int height) {
        this.hero = new Hero(10, 10);
        this.width = width;
        this.height = height;
        this.walls = createWalls();
    }
    Arena (int width, int height, int heroX, int heroY) {
        this.hero = new Hero(heroX, heroY);
        this.width = width;
        this.height = height;
        this.walls = createWalls();
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }

        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }

        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return coins;
    }






    public  void draw (TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Wall wall : walls)
            wall.draw(graphics);

        hero.draw(graphics);

        for (Coin coin : coins)
            coin.draw(graphics);
    }

    public boolean processKey(KeyStroke key) {
        System.out.println(key);
        Position newPosition;
        switch (key.getKeyType()) {
            case ArrowUp:
                newPosition = this.hero.moveUp();
                if (canMoveHero(newPosition))
                    this.hero.setPosition(newPosition);
                break;
            case ArrowDown:
                newPosition = this.hero.moveDown();
                if (canMoveHero(newPosition))
                    this.hero.setPosition(newPosition);
                break;
            case ArrowLeft:
                newPosition = this.hero.moveLeft();
                if (canMoveHero(newPosition))
                    this.hero.setPosition(newPosition);
                break;
            case ArrowRight:
                newPosition = this.hero.moveRight();
                if (canMoveHero(newPosition))
                    this.hero.setPosition(newPosition);
                break;
            case Character:
                if (key.getCharacter() == 'q') {
                    return false;
                }
                break;
            case EOF:
                return false;
        }
        return true;
    }

    private boolean canMoveHero (Position position) {
        if (position.getX() < 1 || position.getY() < 1) return false;
        else if (position.getX() > width-2 || position.getY() > height-2) return false;
        else return true;
    }

}
