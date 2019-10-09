package Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Achievements {
    private int numberOfTimes;
    private double scaleFactor;
    private int x, y;
    private String firstForm;
    private String secondForm;
    private String thirdForm;

    Achievements (int x, int y, String first,String second,String third,double scaleFactor) {
        this.x = x;
        this.y = y;
        this.numberOfTimes = 0;
        this.firstForm = first;
        this.secondForm = second;
        this.thirdForm = third;
        this.scaleFactor = scaleFactor;
    }

    public void incrementCounter () {
        numberOfTimes++;
    }

    public int getNumberOfTimes() {
        return numberOfTimes;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public String getFirstForm() {
        return firstForm;
    }

    public String getSecondForm() {
        return secondForm;
    }

    public String getThirdForm() {
        return thirdForm;
    }


}
