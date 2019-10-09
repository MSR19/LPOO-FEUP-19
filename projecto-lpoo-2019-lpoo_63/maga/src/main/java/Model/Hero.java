package Model;

public class Hero extends LivingBody implements LivingBeing, Aggressive {
    private Weapon weapon;

    public Hero (int x, int y) {
        this.symbol = 'H';
        this.position = new Position(x,y);
        this.color = "#FFFFFF";
        this.life = 20;
        weapon = new Weapon("Fist", 2, 50);
    }

    @Override
    public int attack() {
        if((int) (Math.random() * 101 + 1) > weapon.getLuck())
            return 0;
        return weapon.getDamage();
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position moveUp() {
        return new Position(position.getxCord(), position.getyCord() - 1);
    }

    @Override
    public Position moveDown() {
        return new Position(position.getxCord(), position.getyCord() + 1);
    }

    @Override
    public Position moveLeft() {
        return new Position(position.getxCord() - 1, position.getyCord());
    }

    @Override
    public Position moveRight() {
        return new Position(position.getxCord() +1, position.getyCord());
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

}
