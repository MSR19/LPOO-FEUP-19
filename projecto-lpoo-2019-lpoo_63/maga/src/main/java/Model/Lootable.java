package Model;

import java.util.List;

public interface Lootable extends  Interactive{
    void interact(List<Item> items);
}
