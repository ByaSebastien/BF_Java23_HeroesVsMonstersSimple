package be.bstorm.models.monsters;

import be.bstorm.models.Entity;

public abstract class Monster extends Entity {
    public Monster(int stamina, int strength) {
        super(stamina, strength);
    }
}
