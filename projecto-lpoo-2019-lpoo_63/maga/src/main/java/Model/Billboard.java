package Model;

public class Billboard extends MapElement implements Solid, Readable{
    private String message;

    public Billboard (String message, int x, int y) {
        this.symbol = 'B';
        this.message = message;
        this.position = new Position(x,y);
        this.color = "#31A9B8";
    }

    @Override
    public String interact() { return message;  }
}
