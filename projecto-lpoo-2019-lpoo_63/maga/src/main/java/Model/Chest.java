package Model;

import java.util.List;

public class Chest extends Container implements Solid, Lootable {

    public Chest (List<Item> items, int x, int y) {
        this.symbol = 'C';
        this.position = new Position(x,y);
        this.color = "#F5BE41";
        this.items = items;
    }

}
