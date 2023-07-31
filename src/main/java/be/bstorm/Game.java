package be.bstorm;

import be.bstorm.models.heroes.Dwarf;
import be.bstorm.models.heroes.Hero;
import be.bstorm.models.heroes.Human;
import be.bstorm.models.monsters.Monster;
import be.bstorm.models.monsters.MonsterFactory;

import java.util.List;
import java.util.Scanner;

public class Game {

    private Hero hero;
    private List<Monster> monsters;

    public Game() {
        this.hero = selectHero();
        this.monsters = MonsterFactory.generate(10);
        for(Monster monster : monsters){
            monster.setDieEvent(m -> hero.loot(m));
        }
    }

    public void start() {

        while (hero.isAlive()) {

            for (Monster m : monsters) {

                while (hero.isAlive() && m.isAlive()) {

                    System.out.println("Hero attack");
                    hero.attack(m);
                    if (m.isAlive()) {
                        System.out.println("Monster attack");
                        m.attack(hero);
                    }

                }
                if(!hero.isAlive()){
                    System.out.println("Game Over");
                    return;
                }
                System.out.println("Next one");
            }
            System.out.println("You win!");
            return;
        }
    }

    public Hero selectHero() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("1 : Human\n2 : Dwarf");
        int choice;
        do {
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 2);
        System.out.print("Nom : ");
        String name = scanner.next();
        return switch (choice) {
            case 1 -> new Human(name);
            case 2 -> new Dwarf(name);
            default -> throw new RuntimeException();
        };
    }
}
