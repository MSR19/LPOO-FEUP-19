package View;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public class AchievementsView {
    public String  achievementUnlockedDisplay(int numberOfTimes, double scaleFactor,String firstForm,String secondForm,String thirdForm) {
        if (numberOfTimes == 1) {
            return ("You have unlocked: " + firstForm);
        }
        else if (numberOfTimes == 10*scaleFactor) {
            return ("You have unlocked: " + secondForm);
        }
        else if (numberOfTimes == 100*scaleFactor) {
            return ("You have unlocked: " + thirdForm);
        }
        return "";
    }

    public void displayAchievement(TextGraphics graphics, int numberOfTimes, double scaleFactor, String firstForm, String secondForm, String thirdForm, int x, int y) {
            if (numberOfTimes >= 1)
                graphics.putString(new TerminalPosition(x, y), firstForm);
            if (numberOfTimes >= 10*scaleFactor)
                graphics.putString(new TerminalPosition(x, y+2), secondForm);
            if (numberOfTimes >= 100*scaleFactor)
                graphics.putString(new TerminalPosition(x, y+4), thirdForm);
    }
}
