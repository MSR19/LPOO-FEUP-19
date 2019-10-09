package Model;

public class Wall extends MapElement implements Solid {

    public Wall(int x, int y) {
        this.symbol = 'T';
        this.position = new Position(x, y);
        this.color = "#FFFFFF";
    }

}