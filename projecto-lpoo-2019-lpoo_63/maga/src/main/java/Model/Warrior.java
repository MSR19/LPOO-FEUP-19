package Model;

import java.util.List;

public class Warrior extends LivingBody implements LivingBeing, Aggressive {
    private Weapon weapon;

    public Warrior (Weapon weapon, int x, int y, int life, List<Item> items) {
        this.symbol = 'M';
        this.position = new Position(x,y);
        this.color = "#CF3721";
        this.weapon = weapon;
        this.life = life;
        this.inventory = items;
        if (weapon == null)
            this.weapon = new Weapon("Fist", 2, 60);

        if (items == null)
            this.inventory.add(new Item("Bag of gold"));
        this.inventory.add(this.weapon);

    }

    public Weapon getWeapon() {
        return weapon;
    }

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

    @Override
    public int attack() {
        if((int) (Math.random() * 101 + 1) > weapon.getLuck())
            return 0;
        return weapon.getDamage();
    }
}
