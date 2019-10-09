package Model;

import java.util.ArrayList;
import java.util.List;

public abstract class LivingBody extends MapElement implements LivingBeing{
    protected int life;
    protected List<Item> inventory = new ArrayList<>();

    @Override
    public boolean takeDamage(int damage) {
        if(damage == 0)
            return false;
        this.life = life - damage;
        return true;

    }

    public int getLife() {
        return life;
    }

    public List<Item> getInventory() {
        return inventory;
    }
}
