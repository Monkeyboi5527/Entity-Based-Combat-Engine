package EntityBasedCombatEngine.entity.entities;

import EntityBasedCombatEngine.entity.Entity;
import EntityBasedCombatEngine.entity.EntityType;

public class TestBoss extends Entity {

    public TestBoss(String name, int health, int attackDamage, EntityType entityType) {
        super(name, health, attackDamage, entityType);
    }

    @Override
    public void takeDamage(int damage) {
        health = health - damage;
        if (health <= 0) {
            System.out.println("You Defeated " + name + '!');
            System.out.println("The Rest of their Minions Scatter!");
        }
    }

}
