package be.bstorm.models.monsters;

import be.bstorm.models.Leather;
import be.bstorm.utils.Dice;

public class Wolf extends Monster implements Leather {

    private final int leather;
    public Wolf() {
        super(0, 0);
        this.leather = Dice.D4.roll();
    }

    @Override
    public int getLeather() {
        return leather;
    }
}
