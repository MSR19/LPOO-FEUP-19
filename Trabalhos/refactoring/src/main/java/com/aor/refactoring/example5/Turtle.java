package com.aor.refactoring.example5;

public class Turtle {
    private int row;
    private int column;
    private char direction;

    public Turtle(int row, int column, char direction) {
        this.row = row;
        this.column = column;
        this.direction = direction;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public char getDirection() {
        return direction;
    }


    public void execute(char command) {
        switch (command) {
            case 'L':
                rotateLefth();
                break;
            case 'R':
                rotateRigth();
                break;
            case 'F':
                move();
                break;
        }
    }

    private void move() {
        if (direction == 'N') row--;
        else if (direction == 'S') row++;
        else if (direction == 'W') column--;
        else if (direction == 'E') column++;
    }

    private void rotateRigth() {
        if (direction == 'N') direction = 'E';
        else if (direction == 'E') direction = 'S';
        else if (direction == 'S') direction = 'W';
        else if (direction == 'W') direction = 'N';
    }

    private void rotateLefth() {
        if (direction == 'N') direction = 'W';
        else if (direction == 'W') direction = 'S';
        else if (direction == 'S') direction = 'E';
        else if (direction == 'E') direction = 'N';
    }

}
