package EntityBasedCombatEngine.combat;

import EntityBasedCombatEngine.entity.Entity;
import EntityBasedCombatEngine.entity.EntityType;
import EntityBasedCombatEngine.entity.TestAlly;
import EntityBasedCombatEngine.entity.TestPlayer;

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
    Entity enemy;
    Entity boss;





    /**
     *  Actually runs the combat
     *
     * @param entities
     * @param scanner
     */
    public void startCombat(List<Entity> entities, Scanner scanner, Random random) {
        initialize(entities);
        do {
            System.out.println("*TESTING* Starting Combat");
            System.out.print("*TESTING* Printing Entities: ");
            for (Entity entity : initialized) {
                System.out.print(entity.getName() + " ");
            }


            System.out.println("*TESTING* KILLING PLAYER");
            player.takeDamage(10000);
        } while (combatCondition());
    }

    /**
     * Function -> To check if the Player team or the Enemy team is alive.
     *     <P>
     *     Details: The loop goes through each {@link Entity} and sorts them into teams.
     *     Player team consists of {@link EntityType#PLAYER} and {@link EntityType#ALLY} while Enemy {@link EntityType#ENEMY} and or {@link EntityType#BOSS},
     *     Then checks if whether each entity in the team is alive or not.
     * */
    public boolean combatCondition() {
        int teamSize;
        int counter = 0;

        if (!player.isAlive()){
            return false;
        }

        // Checks each entity on enemy team
        for (Entity enemyTeamEntity : enemyTeam) {
            teamSize = enemyTeam.size();
            System.out.println("*TESTING* " + "Counter(after isAlive check(enemy)): " + counter);
            System.out.println("*TESTING* " + "Checking: " + enemyTeamEntity.getName());
            if (!enemyTeamEntity.isAlive()){
                counter++;
                System.out.println("*TESTING* " + "Counter(after isAlive check(enemy)): " + counter);
                if (counter == teamSize){
                    System.out.println("ENEMY TEAM DEAD");
                    return false;
                }
            }
        }

        return true;
    }

    /**
     *  Choices for the player
     *
     * @param scanner
     */
    private void choice(Scanner scanner) {
        System.out.println("Player " + player.getName()  + "'s turn");
        System.out.println("1. Attack");
        System.out.println("2. Heal(2 Uses Max)");

        switch (scanner.nextInt()) {
                case 1: {
                    System.out.println("Player " + player.getName() + " attacks " + enemyTeam.getFirst().getName() + "!");
                    enemyTeam.getFirst().takeDamage(player.getAttackDamage());
                    System.out.println( enemyTeam.getFirst().getName() + " health: " + enemyTeam.getFirst().getHealth());
                    break;
                }
                case 2: {
                    if (player.healCounter > 2){
                        System.out.println("Out of Heals!");
                        break;
                    }
                    System.out.println("Player " + player.getName() + " Heals!");
                    player.heal(20);
                    player.healCounter++;
                    System.out.println("Player " + player.getName() + " Health: " + player.getHealth());
                    break;
                }
                case 3: {
                    System.out.println("*TESTING* KILLING PLAYER");
                    player.takeDamage(10000);
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
     * @param entity
     * @param random
     */
    private void action(Entity entity, Random random) {
        System.out.println("Ally " + ally.getName() + "'s turn");
        if (entity.getEntityType() == EntityType.ALLY) {
           switch (random.nextInt(1, 3)) {
               case 2: {
                   if (ally.healCounter > 2){
                       System.out.println("Out of Heals!");
                       break;
                   }
                   System.out.println("Ally " + ally.getName() + " Heals!");
                   ally.heal(20);
                   ally.healCounter++;
                   System.out.println("Ally " + ally.getName() + " Health: " + ally.getHealth());
                   break;
               }
               default: {
                   System.out.println("Ally " + ally.getName() + " attacks!");
                   enemyTeam.getFirst().takeDamage(player.getAttackDamage());
                   System.out.println( enemyTeam.getFirst().getName() + " health: " + enemyTeam.getFirst().getHealth());
                   break;
               }
           }
        }
    }

    /**
     *  Actually runs the combat
     *
     * @param entities
     */
    public List<Entity> initialize(List<Entity> entities) {
        for (Entity entity : entities) {
            switch (entity.getEntityType()) {
                case PLAYER -> {
                    player = new TestPlayer(entity.getName(), entity.getHealth(), entity.getAttackDamage(), EntityType.PLAYER);
                    System.out.println("*TESTING* " + entity.getName() + " added!");
                    initialized.add(player);
                    playerTeam.add(player);
                }
                case ALLY -> {
                    ally = new TestAlly(entity.getName(), entity.getHealth(), entity.getAttackDamage(), EntityType.ALLY);
                    System.out.println("*TESTING* " + entity.getName() + " added!");
                    initialized.add(ally);
                    playerTeam.add(ally);
                }
                case ENEMY -> {
                    enemy = new Entity(entity.getName(), entity.getHealth(), entity.getAttackDamage(), EntityType.ENEMY);
                    System.out.println("*TESTING* " + entity.getName() + " added!");
                    initialized.add(entity);
                    enemyTeam.add(enemy);
                }
                case BOSS -> {
                    boss = new Entity(entity.getName(), entity.getHealth(), entity.getAttackDamage(), EntityType.BOSS);
                    System.out.println("*TESTING* " + entity.getName() + " added!");
                    initialized.add(boss);
                    enemyTeam.add(boss);
                }
                default -> System.out.println("*TESTING* SOMETHING WENT WRONG");
            }
        }
        System.out.println("*TESTING* Sorting complete! PlayerTeam: " + playerTeam.size() + " | EnemyTeam: " + enemyTeam.size());
        return initialized;
    }
}



