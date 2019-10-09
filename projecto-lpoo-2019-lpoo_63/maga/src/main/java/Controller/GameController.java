package Controller;

import Model.Model;
import Model.Position;
import Model.Achievements;
import Model.MapElement;
import Model.LivingBeing;
import Model.LivingBody;
import Model.Corpse;
import Model.Interactive;
import Model.Warrior;
import Model.Lootable;
import Model.Readable;

import View.AchievementsView;
import View.View;
import com.googlecode.lanterna.input.KeyStroke;
import java.io.IOException;

public class GameController implements GameState {
    private MasterController masterController;
    private Model data;
    private View view;

    public GameController(MasterController masterController, View view, Model data) {
        this.masterController = masterController;
        this.view = view;
        this.data = data;
    }


    @Override
    public boolean processKey(KeyStroke key) {
        System.out.println(key);
        Position newPosition = null;

        switch (key.getKeyType()) {
            case ArrowUp:
                newPosition = data.getHero().moveUp();
                break;
            case ArrowDown:
                newPosition = data.getHero().moveDown();
                break;

            case ArrowLeft:
                newPosition = data.getHero().moveLeft();
                break;

            case ArrowRight:
                newPosition = data.getHero().moveRight();
                break;

            case Character:
                return processCharacterKeys(key);

            case EOF:
                return false;

        }

        if(newPosition != null) {
            if (canMove(newPosition)) {
                data.getHero().setPosition(newPosition);
                this.logicAchievement(data.getMoveAchievement());
                return true;
            }
            for (int i = 0; i < data.getSolidElements().size(); i++) {
                MapElement element = data.getSolidElements().get(i);
                if (element.getPosition().equals(newPosition))
                    if (element instanceof LivingBeing)
                        if (fightEnemy(element)) {
                            data.getGameMenu().get(0).setMessage("");
                            data.getGameMenu().get(1).setMessage("");
                            i--;
                        }
            }
        }
        return true;
    }

    public boolean processCharacterKeys(KeyStroke key) {
        switch(key.getCharacter()){
            case 'q':
                return false;

            case 'e':
                if (isInteractive(data.getHero().getPosition()))
                    this.logicAchievement(data.getInteractAchievement());
                break;

            case 'i':
                masterController.setToInventoryController();
                break;

            case 'l':
                masterController.setToGlossaryController();
                break;

            case 'a':
                masterController.setToAchievementsController();
                break;
        }
        return true;

    }

    @Override
    public void processState() throws IOException {
        this.view.drawGame(data.getHero(), data.getGameElements(), data.getGameMenu());
    }

    private boolean canMove (Position position) {
        for (MapElement element: data.getSolidElements()) {
            if (element.getPosition().equals(position))
                return false;
        }
        if (position.getxCord() < 0 || position.getyCord() < 0)
            return false;
        if (position.getxCord() > data.getMapWidth()-1 || position.getyCord() > data.getMapHeight()-6 || position.getyCord() < 5)
            return false;
        return true;
    }

    private boolean fightEnemy(MapElement element){

        LivingBody livingBody = (LivingBody) element;

        if (livingBody.takeDamage(data.getHero().attack())) {
            data.getGameMenu().get(0).setMessage("Enemy took damage, life remaining: " + livingBody.getLife());
            System.out.print("Enemy took damage, life remaining: ");
            System.out.println(livingBody.getLife());
        } else
            data.getGameMenu().get(0).setMessage("You missed!");

        if (livingBody.getLife() <= 0) {
            data.getGameMenu().get(0).setMessage("Enemy died!");
            MapElement corpse = new Corpse(livingBody.getInventory(), livingBody.getPosition().getxCord(), livingBody.getPosition().getyCord());
            data.getGameElements().add(corpse);
            data.getInteractiveElements().add(corpse);
            data.getGameElements().remove(element);
            data.getSolidElements().remove(element);
            if (element instanceof Interactive)
                data.getInteractiveElements().remove(element);

            if (this.win()) {
                masterController.setToGameWinController();
            }
            return true;
        }

        if (livingBody instanceof Warrior) {
            Warrior warrior = (Warrior) livingBody;
            if (data.getHero().takeDamage(warrior.attack())) {
                data.getGameMenu().get(1).setMessage("Model.Hero took "+ warrior.getWeapon().getDamage() + " damage!");
            } else
                data.getGameMenu().get(1).setMessage("The enemy missed!");

        }

        if(data.getHero().getLife() <= 0) {
            masterController.setToGameOverController();
            data.getGameMenu().get(0).setMessage("");
            data.getGameMenu().get(1).setMessage("You died!");
            return true;
        }

        return false;
    }

    private boolean isInteractive (Position position) {
        for (MapElement element: data.getInteractiveElements()) {
            if (isNextTo(position,element.getPosition())) {
                if (element instanceof Lootable){
                    Lootable lootable = (Lootable) element;
                    lootable.interact(data.getHero().getInventory());
                }
                else {
                    Readable readable = (Readable) element;
                    String string = readable.interact();
                    if (string != "") {
                        data.getGameMenu().get(0).setMessage(string);
                    }
                }
                return true;
            }
        }
        return false;
    }

    private boolean isNextTo(Position position1, Position position2){
        if(isRight(position1,position2)) return true;
        if(isLeft(position1,position2)) return true;
        if(isDown(position1,position2)) return true;
        if(isUp(position1,position2)) return true;

        return false;
    }

    private boolean isRight(Position position1, Position position2) {
        return (position1.getxCord() == (position2.getxCord()-1) && position1.getyCord() == position2.getyCord());
    }

    private boolean isLeft(Position position1, Position position2) {
        return (position1.getxCord() == (position2.getxCord()+1) && position1.getyCord() == position2.getyCord());
    }

    private boolean isDown(Position position1, Position position2) {
        return (position1.getxCord() == position2.getxCord() && position1.getyCord() == position2.getyCord()+1);
    }

    private boolean isUp(Position position1, Position position2) {
        return (position1.getxCord() == position2.getxCord() && position1.getyCord() == position2.getyCord()-1);
    }

    public void logicAchievement (Achievements achievement) {
        achievement.incrementCounter();
        AchievementsView achievementsView = new AchievementsView();

        String string = achievementsView.achievementUnlockedDisplay(achievement.getNumberOfTimes(), achievement.getScaleFactor(), achievement.getFirstForm(), achievement.getSecondForm(), achievement.getThirdForm());
        if (string != "") {
            data.getGameMenu().get(1).setMessage(string);

        }
    }

    private boolean win() {
        if (this.data.getHero().getLife() <= 0)
            return false;
        for (MapElement mapElement: data.getGameElements()) {
            if (Warrior.class.isInstance(mapElement))
                return false;
        }
        return true;
    }

}


