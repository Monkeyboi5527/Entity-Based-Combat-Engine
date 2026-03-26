package EntityBasedCombatEngine.entity;

import java.util.Scanner;

public class TestPlayer extends Entity {
    public int healCounter = 0;

    public TestPlayer(String name, int health, int attackDamage, EntityType entityType) {
        super(name, health, attackDamage, entityType);
    }

    @Override
    public void takeDamage(int damage) {
        health = health - damage;
        if (health <= 0) {
            System.out.println("Player " + name + " is dead");
            System.out.println("\uD83D\uDC80DEFEAT\uD83D\uDC80");
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        healCounter++;
    }

    @Override
    public int choice(Scanner scanner) {
        System.out.println( "Player " + name + "'s turn");
        // Only Coded for combat right now
        System.out.println("1. Attack");
        System.out.println("2. Heal(2 Uses Max)");
        if (scanner.nextInt() == 2 && healCounter > 2) {
            System.out.println("Out of Heals!");
        } else {
            System.out.println("Player " + name + " Heals!");
            heal(20);
            healCounter++;
            System.out.println("Player " + name + " Health: " + health);
        }

        return scanner.nextInt();
    }
}
