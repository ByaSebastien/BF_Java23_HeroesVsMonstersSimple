package be.bstorm.models.monsters;

import be.bstorm.models.Gold;
import be.bstorm.utils.Dice;

public class Troll extends Monster implements Gold {

    private final int gold;

    public Troll() {
        super(1, 1);
        this.gold = Dice.D4.roll();
    }

    @Override
    public int getGold() {
        return this.gold;
    }
}
