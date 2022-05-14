public class LegacyUnitAdapter implements IUnit {

    private LegacyUnit legacyUnit;
    private String name;
    private int hp = 20;

    public LegacyUnitAdapter(String name) {
        legacyUnit = new LegacyUnit();
        this.name = name;
    }

    @Override
    public void move() {
        legacyUnit.walk();
        // if (legacyUnit.getPositionX() >= Game.SIZE) {
        //     hp--;
        // }
        // if (legacyUnit.getPositionY() >= Game.SIZE) {
        //     hp--;
        // }
        int x = getX();
        int y = getY();
        if (x <= 0 || x >= Game.SIZE) {
			hp--;
		}
		if (y <= 0 || y >= Game.SIZE) {
			hp--;
		}
    }

    @Override
    public int getX() {
        return legacyUnit.getPositionX();
    }

    @Override
    public int getY() {
        return legacyUnit.getPositionY();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return hp;
    }

    @Override
    public boolean dead() {
        return false;
    }
}
