package EntityBasedCombatEngine.entity;

import java.util.Random;

public class TestAlly extends Entity {
    public int healCounter = 0;

    public TestAlly(String name, int health, int attackDamage, EntityType entityType) {
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
    public int action(Random random) {
        int action = 0;

        System.out.println("Ally " + name + "'s turn");
        //Only Coded for combat right now
        if (random.nextInt(1, 3) == 1) {
            System.out.println("Ally " + name + " attacks!");
            action = 1;
        } else if (random.nextInt(1, 3) == 2) {
            System.out.println("Ally " + name + " Heals!");
            heal(20);
            healCounter++;
            System.out.println("Ally " + name + " Health: " + health);
            action = 2;
            // if out of heals then attack
            if (healCounter > 2) {
                action = 1;
            }
        }
        return action;
    }
}
