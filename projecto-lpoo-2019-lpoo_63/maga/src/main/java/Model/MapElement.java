package Model;

public abstract class MapElement{
    protected Character symbol;
    protected Position position;
    protected String color;

    public Character getSymbol() {return symbol; }
    public Position getPosition() {return position; }
    public String getColor() {return color; }
}
