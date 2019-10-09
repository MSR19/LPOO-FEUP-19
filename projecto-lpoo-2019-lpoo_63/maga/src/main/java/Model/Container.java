package Model;

import java.util.ArrayList;
import java.util.List;

public abstract class Container extends MapElement implements Lootable {
    protected List<Item> items;

    public void interact(List<Item> items) {
        for (Item item: this.items) {
            items.add(item);
        }
        this.items = new ArrayList<>();
    }
}
