package Model;

import java.util.List;

public class Corpse extends Container {

    public Corpse (List<Item> items, int x, int y) {
        this.symbol = 'c';
        this.position = new Position(x,y);
        this.color = "#777777";
        this.items = items;
    }
}
