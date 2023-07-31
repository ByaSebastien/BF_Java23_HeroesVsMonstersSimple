package be.bstorm.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public enum Dice {
    D4(4),
    D6(6),
    D8(8),
    D10(10),
    D20(20),
    D100(100);

    private int nbFaces;

    Dice(int nbFaces){
        this.nbFaces = nbFaces;
    }

    public int roll(){
        return new Random().nextInt(nbFaces) + 1;
    }

    public int rolls(int nbThrow){

        int sum = 0;
        for (int i = 0; i < nbThrow; i++){
            sum += roll();
        }
        return sum;
    }

    public int rolls(int nbThrow, int nbToKeep){

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nbThrow; i++){
            result.add(roll());
        }

        result.sort(Comparator.reverseOrder());

        int sum = 0;
        for (int i = 0; i < nbToKeep; i++){
            sum += result.get(i);
        }
        return sum;
    }
}
