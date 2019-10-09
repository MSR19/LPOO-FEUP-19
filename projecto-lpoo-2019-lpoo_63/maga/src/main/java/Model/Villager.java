package Model;

import java.util.List;

public class Villager extends LivingBody implements LivingBeing, Readable{
    String message;

    public Villager(String message, int x, int y, int life, List<Item> items) {
        this.message = message;
        this.position = new Position(x,y);
        this.color = "#258039";
        this.symbol = 'V';
        this.life = life;
        this.inventory = items;
        if (inventory == null) {
            this.inventory.add(new Item("Bag of gold"));
            this.inventory.add(new Item("Family photo"));
        }
    }

    @Override
    public String interact() { return message;   }

    @Override
    public void setPosition(Position position) {

    }

    @Override
    public Position moveUp() {
        return null;
    }

    @Override
    public Position moveDown() {
        return null;
    }

    @Override
    public Position moveLeft() {
        return null;
    }

    @Override
    public Position moveRight() {
        return null;
    }

}
