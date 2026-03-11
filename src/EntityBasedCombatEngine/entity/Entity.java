package EntityBasedCombatEngine.entity;


public class Entity {

    private static String name;
    private static int health;
    private static int attackDamage;

    public Entity(String name, int health, int attackDamage) {
        Entity.name = name;
        Entity.health = health;
        Entity.attackDamage = attackDamage;
    }

    public void takeDamage(int damage) {
        health = health - damage;
        if (health <= 0) {
            IO.println("Player " + name + " is dead");
            IO.println("\uD83D\uDC80DEFEAT\uD83D\uDC80");
        }
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
}
