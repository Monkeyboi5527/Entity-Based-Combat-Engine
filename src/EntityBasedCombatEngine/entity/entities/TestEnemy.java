package EntityBasedCombatEngine.entity.entities;

import EntityBasedCombatEngine.entity.Entity;
import EntityBasedCombatEngine.entity.EntityType;

public class TestEnemy extends Entity {

    public TestEnemy(String name, int health, int attackDamage, EntityType entityType) {
        super(name, health, attackDamage, entityType);
    }

    @Override
    public void takeDamage(int damage) {
        health = health - damage;
        if (health <= 0) {
            System.out.println("Enemy " + name + " is dead");
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
    }
}
