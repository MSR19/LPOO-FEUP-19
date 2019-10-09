package Model;

public class Item {
    protected  String name;

    public Item (String name) {
        this.name = name;
    }

    public String getName() {return name; }

    @Override
    public boolean equals(java.lang.Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        Item item = (Item) obj;
        if (this.name.equals(item.name)) return true;
        else return false;
    }


}
