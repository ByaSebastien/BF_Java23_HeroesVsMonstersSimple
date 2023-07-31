package be.bstorm.models.monsters;

import be.bstorm.utils.Dice;

import java.util.ArrayList;
import java.util.List;

public class MonsterFactory {

    public static Monster generate(){

//        switch (Dice.D6.roll()){
//
//            case 1:
//            case 2:
//            case 3:
//                return new Wolf();
//            case 4:
//            case 5:
//                return new Troll();
//            case 6:
//                return new Dragon();
//            default:
//                throw new IllegalStateException();
//        }

        return switch (Dice.D6.roll()) {
            case 1, 2, 3 -> new Wolf();
            case 4, 5 -> new Troll();
            case 6 -> new Dragon();
            default -> throw new IllegalStateException();
        };
    }

    public static List<Monster> generate(int amount){

        List<Monster> monsters = new ArrayList<>();
        for(int i = 0; i < amount;i++){
            monsters.add(generate());
        }
        return monsters;
    }
}
