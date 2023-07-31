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
    private Board board;

    public Game() {
        this.hero = selectHero();
        this.board = new Board(hero);
    }

    public void start() {
        while (hero.isAlive()) {
            board.displayMap();
            board.moove();
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
