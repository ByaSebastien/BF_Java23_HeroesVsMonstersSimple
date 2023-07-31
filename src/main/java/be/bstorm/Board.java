package be.bstorm;

import be.bstorm.models.Position;
import be.bstorm.models.heroes.Hero;
import be.bstorm.models.monsters.Monster;
import be.bstorm.models.monsters.MonsterFactory;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Board {

    private Hero hero;
    private List<Monster> monsters;
    private String[][] map;

    public Board(Hero hero) {
        this.hero = hero;
        this.monsters = MonsterFactory.generate(10);
        map = new String[15][15];
        init();
    }

    public void init() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = " ";
            }
        }
        hero.setPos(generatePosition());
        setCell(hero.getPos(), "H");
        for (Monster monster : monsters) {
            monster.setDieEvent(m -> hero.loot(m));
            monster.setPos(generatePosition());
            setCell(monster.getPos(), "M");
        }
    }

    public void setCell(Position pos, String value) {
        map[pos.x()][pos.y()] = value;
    }

    public String getCell(Position pos) {

        return map[pos.x()][pos.y()];
    }

    public Position generatePosition() {

        Random random = new Random();
        Position pos;
        int x;
        int y;
        do {
            x = random.nextInt(15);
            y = random.nextInt(15);
            pos = new Position(x, y);
        } while (!map[x][y].equals(" "));
        return pos;
    }

    public void displayMap() {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public void moove() {

        Scanner scanner = new Scanner(System.in);
        String dirrection = scanner.next().toLowerCase();

        switch (dirrection) {
            case "z":
                moove(new Position(hero.getPos().x() - 1, hero.getPos().y()));
                break;
            case "q":
                moove(new Position(hero.getPos().x(), hero.getPos().y() - 1));
                break;
            case "s":
                moove(new Position(hero.getPos().x() + 1, hero.getPos().y()));
                break;
            case "d":
                moove(new Position(hero.getPos().x(), hero.getPos().y() + 1));
                break;
        }
    }

    public void moove(Position pos) {

        if (!canMoove(pos)) {
            if (isOut(pos)) {
                return;
            }
            if (getCell(pos).equals("M")) {
                fight(pos);
            }
        }
        setCell(hero.getPos(), " ");
        hero.setPos(pos);
        setCell(hero.getPos(), "H");
    }

    public boolean canMoove(Position pos) {
        if (isOut(pos)) {
            return false;
        }
        return getCell(pos) == " ";
    }

    public boolean isOut(Position pos) {
        return pos.x() < 0 || pos.x() > map.length - 1 || pos.y() < 0 || pos.y() > map.length - 1;
    }

    public void fight(Position pos) {

        Monster target = getMonster(pos);

        while (hero.isAlive() && target.isAlive()) {

            hero.attack(target);
            System.out.println(hero.getName() + " attack " + target.getClass().getSimpleName());
            sleep(1);

            if (target.isAlive()) {
                target.attack(hero);
                System.out.println(target.getClass().getSimpleName() + " attack " + hero.getName());
                sleep(1);
            }

        }
        if(hero.isAlive()){
            System.out.println(hero.getName() + " WIN!!!");
        }
    }

    public void sleep(int time){
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Monster getMonster(Position pos) {
        for (Monster m : monsters) {
            if (m.getPos().equals(pos)) {
                return m;
            }
        }
        throw new RuntimeException();
    }
}
