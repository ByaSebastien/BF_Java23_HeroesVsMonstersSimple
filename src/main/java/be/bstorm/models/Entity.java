package be.bstorm.models;

import be.bstorm.utils.Dice;

import java.util.List;
import java.util.function.Consumer;

public abstract class Entity {

    private Consumer<Entity> dieEvent;
    private int stamina;
    private int strength;
    private int hp;
    private int currentHp;
    private Position pos;

    public Entity(int stamina, int strength) {
        this.stamina = Dice.D6.rolls(4,3) + stamina;
        this.strength = Dice.D6.rolls(4,3) + strength;
        hp = this.stamina + modifier(stamina);
        regen();
    }

    public int getStamina() {
        return stamina;
    }

    public int getStrength() {
        return strength;
    }

    public int getHp() {
        return hp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp < 0 ? 0 : (currentHp > hp ? hp : currentHp);
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public boolean isAlive(){
        return currentHp > 0;
    }

    protected void regen(){
        currentHp = hp;
    }

    public void attack(Entity target){
        int damage = modifier(strength) + Dice.D4.roll();
        target.takeDamage(damage);
        if(!target.isAlive()){
            if(target.dieEvent != null){
                target.raiseDieEvent();
            }
        }
    }

    protected void takeDamage(int amount){
        currentHp -= amount;
    }

    private int modifier(int statValue){

        if(statValue > 15){
            return 3;
        }
        if(statValue > 10){
            return 2;
        }
        if (statValue > 5) {
            return 1;
        }
        return 0;
    }

    public void setDieEvent(Consumer<Entity> consumer){
        dieEvent = consumer;
    }

    public void raiseDieEvent(){
        dieEvent.accept(this);
    }

    protected void cheat(){

        strength = 1000;
        stamina = 1000;
        currentHp = 1000;
    }
}
