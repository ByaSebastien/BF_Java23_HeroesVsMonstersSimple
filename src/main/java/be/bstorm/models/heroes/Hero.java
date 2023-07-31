package be.bstorm.models.heroes;

import be.bstorm.models.Entity;
import be.bstorm.models.Gold;
import be.bstorm.models.Leather;

public abstract  class Hero extends Entity implements Gold, Leather {

    private int gold;
    private int leather;
    private final String name;

    public Hero(String name,int strength,int stamina) {
        super(strength,stamina);
        this.name = name;
        if(name.equals("Dante")){
            cheat();
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public int getGold() {
        return gold;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    @Override
    public int getLeather() {
        return leather;
    }

    public void addLeather(int leather) {
        this.leather += leather;
    }

    public void loot(Entity target){

        System.out.println(name + " loot " + target.getClass().getSimpleName());
        if(target instanceof Gold g){
            addGold(g.getGold());
        }
        if(target instanceof Leather l){
            addLeather(l.getLeather());
        }
        regen();
    }
}
