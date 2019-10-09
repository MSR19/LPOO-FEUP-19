package Model;

public interface Mobile {
    void setPosition(Position position);

    Position moveUp();
    Position moveDown();
    Position moveLeft();
    Position moveRight();
}
