package EntityBasedCombatEngine.entity;

/**
 * A combat participant (player, enemy, NPC). Holds stats, components, and active effects.
 */

public class Entity implements Component {

    // Not Static so the variables don't share
    protected String name;
    protected int health;
    protected int attackDamage;
    EntityType entityType;

    public Entity(String name, int health, int attackDamage, EntityType entityType) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
        this.entityType = entityType;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    // For Debug Currently
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

    @Override
    public void heal(int amount) {
        health = health + amount;
    }

    @Override
    public void takeDamage(int damage) {
        health = health - damage;
        if (health <= 0) System.out.println(name + " is dead");
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public EntityType getEntityType() {
        return entityType;
    }
}
