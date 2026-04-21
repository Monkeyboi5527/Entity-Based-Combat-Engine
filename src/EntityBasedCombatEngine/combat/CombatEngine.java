package EntityBasedCombatEngine.combat;

import EntityBasedCombatEngine.entity.*;
import EntityBasedCombatEngine.entity.entities.TestAlly;
import EntityBasedCombatEngine.entity.entities.TestBoss;
import EntityBasedCombatEngine.entity.entities.TestEnemy;
import EntityBasedCombatEngine.entity.entities.TestPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * The main controller. Runs the combat loop start to finish.
 */
public class CombatEngine {
    List<Entity> enemyTeam = new ArrayList<>();
    List<Entity> playerTeam = new ArrayList<>();

    List<Entity> initialized = new ArrayList<>();
    TestPlayer player;
    TestAlly ally;
    TestEnemy enemy;
    TestBoss boss;





    /**
     *  Actually runs the combat
     *
     * @param entities Entities in the Game
     * @param scanner Used for inputs
     * @param random Random Number Gen
     */
    public void startCombat(List<Entity> entities, Scanner scanner, Random random) {
        initialize(entities);

        while (true) {

            // PLAYER TURN
            for (Entity entity : playerTeam) {
                if (isCombatOver()) break;

                if (entity.isAlive()) {
                    if (entity.getEntityType() == EntityType.PLAYER) {
                        choice(scanner);
                    } else {
                        action(entity, random);
                    }
                }
            }

            removeDeadEntities();
            if (isCombatOver()) break;

            // ENEMY TURN
            for (Entity entity : enemyTeam) {
                if (isCombatOver()) break;

                if (entity.isAlive()) {
                    action(entity, random);
                }
            }

            removeDeadEntities();

            if (isCombatOver()) break;
        }

        printCombatResult();
    }

    /**
     *  Choices for the player
     *
     * @param scanner Used for inputs
     */
    private void choice(Scanner scanner) {
        Entity target = getFirstAliveEnemy();
        System.out.println(player.getName()  + "'s turn");
        System.out.println("1. Attack");
        System.out.println("2. Heal(2 Uses Max)");

        switch (scanner.nextInt()) {
                case 1: {
                    if (target == null) { System.out.println("No enemies left"); return; }
                    System.out.println(player.getName() + " attacks " + target.getName() + "!");
                    target.takeDamage(player.getAttackDamage());
                    System.out.println(target.getName() + " health: " + target.getHealth());
                    System.out.println("----------------------------------");
                    break;
                }
                case 2: {
                    if (player.healCounter > 2){
                        System.out.println("Out of Heals!");
                        System.out.println("----------------------------------");
                        break;
                    }
                    System.out.println(player.getName() + " Heals!");
                    player.heal(20);
                    player.healCounter++;
                    System.out.println(player.getName() + " Health: " + player.getHealth());
                    System.out.println("----------------------------------");
                    break;
                }
                case 3: {
                    System.out.println("*TESTING* INSTANT KILLING");
                    if (target == null) { System.out.println("No enemies left"); return; }
                    target.takeDamage(150);
                    System.out.println("----------------------------------");
                    break;
                }
                default: {
                    System.out.println("INVALID CHOICE");
                }
            }
        }

    /**
     *  A Random int decides if to attack or to heal
     *
     * @param entity Used for who is it?
     * @param random Random Number Gen
     */
    private void action(Entity entity, Random random) {
        Entity playerTarget = getFirstAlivePlayer();
        switch (entity.getEntityType()) {
            case ALLY -> {
                Entity target = getFirstAliveEnemy();
                System.out.println(entity.getName() + "'s turn");
                if (random.nextInt(1, 3) == 2) {
                    if (entity.healCounter > 2) {
                        System.out.println("Out of Heals!");
                        System.out.println("----------------------------------");
                        break;
                    }
                    System.out.println(entity.getName() + " Heals!");
                    entity.heal(20);
                    entity.healCounter++;
                    System.out.println(entity.getName() + " Health: " + entity.getHealth());
                    System.out.println("----------------------------------");
                } else {
                    System.out.println(entity.getName() + " attacks!");
                    if (target == null) { System.out.println("No enemies left"); return; }
                    target.takeDamage(entity.getAttackDamage());
                    System.out.println(target.getName() + " health: " + target.getHealth());
                    System.out.println("----------------------------------");
                }
            }
            case ENEMY -> {
                System.out.println(entity.getName() + "'s turn");
                if (random.nextInt(1, 3) == 2) {
                    if (entity.healCounter > 2) {
                        System.out.println("Out of Heals!");
                        System.out.println("----------------------------------");
                        break;
                    }
                    System.out.println(entity.getName() + " Heals!");
                    entity.heal(20);
                    entity.healCounter++;
                    System.out.println(entity.getName() + " Health: " + entity.getHealth());
                    System.out.println("----------------------------------");
                } else {
                    System.out.println(entity.getName() + " attacks!");
                    if (playerTarget == null) { System.out.println("No players left");return; }
                    playerTarget.takeDamage(entity.getAttackDamage());
                    System.out.println(playerTarget.getName() + " health: " + playerTarget.getHealth());
                    System.out.println("----------------------------------");
                }
            }
            case BOSS -> {
                // MORE IMPLEMENTATIONS LATER
                System.out.println(entity.getName() + "'s turn");
                if (random.nextInt(1, 3) == 2) {
                    if (entity.healCounter > 2) {
                        System.out.println("Out of Heals!");
                        System.out.println("----------------------------------");
                        break;
                    }
                    System.out.println(entity.getName() + " Heals!");
                    entity.heal(20);
                    entity.healCounter++;
                    System.out.println(entity.getName() + " Health: " + entity.getHealth());
                    System.out.println("----------------------------------");
                } else {
                    System.out.println(entity.getName() + " attacks!");
                    if (playerTarget == null) { System.out.println("No players left");return; }
                    playerTarget.takeDamage(entity.getAttackDamage());
                    System.out.println(playerTarget.getName() + " health: " + playerTarget.getHealth());
                    System.out.println("----------------------------------");
                }

            }
            case null, default -> {
            }
        }
    }

    /**
     * Initializes and sorts entities
     *
     * @param entities Entities in the Game
     */
    public void initialize(List<Entity> entities) {
        for (Entity entity : entities) {
            switch (entity.getEntityType()) {
                case PLAYER -> {
                    player = new TestPlayer(entity.getName(), entity.getHealth(), entity.getAttackDamage(), entity.getEntityType());
                    initialized.add(entity);
                    playerTeam.add(entity);
                }
                case ALLY -> {
                    ally = new TestAlly(entity.getName(), entity.getHealth(), entity.getAttackDamage(), entity.getEntityType());
                    initialized.add(entity);
                    playerTeam.add(entity);
                }
                case ENEMY -> {
                    enemy = new TestEnemy(entity.getName(), entity.getHealth(), entity.getAttackDamage(), entity.getEntityType());
                    initialized.add(entity);
                    enemyTeam.add(entity);
                }
                case BOSS -> {
                    boss = new TestBoss(entity.getName(), entity.getHealth(), entity.getAttackDamage(), entity.getEntityType());
                    initialized.add(entity);
                    enemyTeam.add(entity);
                }
                default -> throw new IllegalStateException("Unknown entity type: " + entity.getEntityType());
            }
        }
    }

    /**
     * Function -> To check if the Player team or the Enemy team is alive.
     *     <P>
     *     Details: The loop goes through each {@link Entity} and sorts them into teams.
     *     Player team consists of {@link EntityType#PLAYER} and {@link EntityType#ALLY} while Enemy {@link EntityType#ENEMY} and or {@link EntityType#BOSS},
     *     Then checks if whether each entity in the team is alive or not.
     * */
    public boolean isCombatOver() {

        boolean anyPlayerAlive = playerTeam.stream().anyMatch(Entity::isAlive);
        boolean anyEnemyAlive = enemyTeam.stream().anyMatch(Entity::isAlive);

        return !anyPlayerAlive || !anyEnemyAlive;
    }

    private void printCombatResult() {
        boolean anyPlayerAlive = playerTeam.stream().anyMatch(Entity::isAlive);
        boolean anyEnemyAlive = enemyTeam.stream().anyMatch(Entity::isAlive);

        if (!anyPlayerAlive) System.out.println("PLAYER TEAM DEAD");
        if (!anyEnemyAlive) System.out.println("ENEMY TEAM DEAD");
    }

    private List<Entity> getAliveEnemies() {
        return enemyTeam.stream()
                .filter(Entity::isAlive)
                .toList();
    }

    private Entity getFirstAlivePlayer() {
        return playerTeam.stream()
                .filter(Entity::isAlive)
                .findFirst()
                .orElse(null);
    }

    private Entity getFirstAliveEnemy() {
        return getAliveEnemies().stream().findFirst().orElse(null);
    }

    private void removeDeadEntities() {

        initialized.removeIf(entity -> !entity.isAlive());

        playerTeam.removeIf(entity -> !entity.isAlive());
        enemyTeam.removeIf(entity -> !entity.isAlive());
    }

    // TESTING METHODS

    /**
     *  {@link CombatEngine#startCombat(List, Scanner, Random)} but withing TESTING messages
     *
     * @param entities Entities in the Game
     * @param scanner Used for inputs
     * @param random Random Number Gen
     */
    public void startCombatDebug(List<Entity> entities, Scanner scanner, Random random) {
        initializeDebug(entities);

        while (true) {

            // PLAYER TURN
            for (Entity entity : playerTeam) {
                if (isCombatOver()) break;

                if (entity.isAlive()) {
                    if (entity.getEntityType() == EntityType.PLAYER) {
                        choice(scanner);
                    } else {
                        action(entity, random);
                    }
                }
            }

            removeDeadEntities();
            if (isCombatOver()) break;

            // ENEMY TURN
            for (Entity entity : enemyTeam) {
                if (isCombatOver()) break;

                if (entity.isAlive()) {
                    action(entity, random);
                }
            }

            removeDeadEntities();

            if (isCombatOver()) break;
        }

        printCombatResult();
    }

    /**
     * {@link CombatEngine#initialize(List)} but with TESTING messages
     *
     * @param entities Entities in the Game
     */
    public void initializeDebug(List<Entity> entities) {
        for (Entity entity : entities) {
            switch (entity.getEntityType()) {
                case PLAYER -> {
                    player = new TestPlayer(entity.getName(), entity.getHealth(), entity.getAttackDamage(), entity.getEntityType());
                    System.out.println("*TESTING* " + entity.getName() + " added!");
                    initialized.add(entity);
                    playerTeam.add(entity);
                }
                case ALLY -> {
                    ally = new TestAlly(entity.getName(), entity.getHealth(), entity.getAttackDamage(), entity.getEntityType());
                    System.out.println("*TESTING* " + entity.getName() + " added!");
                    initialized.add(entity);
                    playerTeam.add(entity);
                }
                case ENEMY -> {
                    enemy = new TestEnemy(entity.getName(), entity.getHealth(), entity.getAttackDamage(), entity.getEntityType());
                    System.out.println("*TESTING* " + entity.getName() + " added!");
                    initialized.add(entity);
                    enemyTeam.add(entity);
                }
                case BOSS -> {
                    boss = new TestBoss(entity.getName(), entity.getHealth(), entity.getAttackDamage(), entity.getEntityType());
                    System.out.println("*TESTING* " + entity.getName() + " added!");
                    initialized.add(entity);
                    enemyTeam.add(entity);
                }
                default -> System.out.println("*TESTING* SOMETHING WENT WRONG");
            }
        }
        System.out.println("*TESTING* Sorting complete! PlayerTeam: " + playerTeam.size() + " | EnemyTeam: " + enemyTeam.size());
    }

}



