package EntityBasedCombatEngine.entity.entities;

import EntityBasedCombatEngine.entity.Entity;
import EntityBasedCombatEngine.entity.EntityType;

public class TestAlly extends Entity {
    //Max of 2 Heals (Some different entities might have more in the future)
    public int healCounter = 0;

    public TestAlly(String name, int health, int attackDamage, EntityType entityType) {
        super(name, health, attackDamage, entityType);
    }

    @Override
    public void takeDamage(int damage) {
        health = health - damage;
        if (health <= 0) {
            System.out.println("Ally " + name + " is dead"); //Internally handles death message
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        healCounter++;
    }
}
