package Model;

public class Weapon extends Item{
    private int damage;
    private int luck;

    public Weapon(String name, int damage, int luck) {
        super(name);
        this.damage = damage;
        this.luck = luck;
    }

    public int getDamage() {
        return damage;
    }

    public int getLuck() { return luck; }

}
