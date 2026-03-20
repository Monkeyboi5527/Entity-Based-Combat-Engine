package EntityBasedCombatEngine.entity;

/**
 * A combat participant (player, enemy, NPC). Holds stats, components, and active effects.
 */

public class Entity implements Component {

    // Not Static so the variables don't share
    private String name;
    private int health;
    private int attackDamage;

    public Entity(String name, int health, int attackDamage) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
    }

    public void takeDamage(int damage) {
        health = health - damage;
        if (health <= 0) {
            IO.println("Player " + name + " is dead");
            IO.println("\uD83D\uDC80DEFEAT\uD83D\uDC80");
        }
    }

    public void heal(int heal) {
        health = health + heal;
    }

    public boolean isAlive() {
        return health > 0;
    }

    // Getters and Display
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Health: " + health);
        System.out.println("Attack Damage: " + attackDamage);
    }

    //Component Implements
    @Override
    public void test() {
        System.out.println("test component");
    }
}
