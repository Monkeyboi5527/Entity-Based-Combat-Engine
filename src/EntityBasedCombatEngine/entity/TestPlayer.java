package EntityBasedCombatEngine.entity;

public class TestPlayer extends Entity {


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
}
