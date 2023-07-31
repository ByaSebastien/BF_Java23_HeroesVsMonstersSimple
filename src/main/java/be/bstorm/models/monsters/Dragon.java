package be.bstorm.models.monsters;

import be.bstorm.models.Gold;
import be.bstorm.models.Leather;
import be.bstorm.utils.Dice;

public class Dragon extends Monster implements Gold, Leather {

    private final int gold;
    private final int leather;
    public Dragon() {
        super(2, 2);
        this.gold = Dice.D6.roll();
        this.leather = Dice.D6.roll();
    }

    @Override
    public int getGold() {
        return this.gold;
    }

    @Override
    public int getLeather() {
        return this.leather;
    }
}
