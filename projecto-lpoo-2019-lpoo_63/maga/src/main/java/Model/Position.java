package Model;

public class Position {
    private int xCord;
    private int yCord;

    public Position (int xCord, int yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
    }

    public Position getPosition() {
        return this;
    }

    public void setPosition(Position position) {
        this.xCord = position.xCord;
        this.yCord = position.yCord;
    }

    public int getxCord () {
        return xCord;
    }

    public int getyCord () {
        return yCord;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        Position position = (Position) obj;
        if (this.getxCord() == position.getxCord() && this.getyCord() == position.getyCord()) return true;
        else return false;
    }
}
