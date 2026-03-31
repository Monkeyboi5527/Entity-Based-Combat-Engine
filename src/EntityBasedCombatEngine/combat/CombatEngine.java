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

    // Hard Coded for 1 Player, 1 Ally, 2 Enemies, and 1 Boss currently
    TestPlayer player;
    TestAlly ally;
    Entity enemy;
    Entity boss;

    Random random = new Random();

    /**
     * Function -> To check if the Player team or the Enemy team is alive.
     *     <P>
     *     Details: The loop goes through each {@link Entity} and sorts them into teams.
     *     Player team consists of {@link EntityType#PLAYER} and {@link EntityType#ALLY} while Enemy {@link EntityType#ENEMY} and or {@link EntityType#BOSS},
     *     Then checks if whether each entity in the team is alive or not.
     * */
    public boolean combatCondition(List<Entity> entities){

        int teamSize;
        int counter = 0;
        for(Entity entity : entities) {

            // First run. Sorts into Player and Enemy Teams
            if (entity.getEntityType() == EntityType.PLAYER || entity.getEntityType() == EntityType.ALLY) {
                playerTeam.add(entity);
                System.out.println("*TESTING* " + entity.getName() + " added!");
            } else if (entity.getEntityType() == EntityType.ENEMY || entity.getEntityType() == EntityType.BOSS) {
                enemyTeam.add(entity);
                System.out.println("*TESTING* " + entity.getName() + " added!");
            }
        }
        System.out.println("*TESTING* Sorting complete! Players: " + playerTeam.size() + " | Enemies: " + enemyTeam.size());

            // Checks each entity on player team
        // Might not need this cause if player dead then game over
//            for (Entity playerTeamEntity : playerTeam){
//                teamSize = playerTeam.size();
//                System.out.println("*TESTING* " + "Counter(before isAlive check(player)): " + counter);
//                System.out.println("*TESTING* " + "Checking: " + playerTeamEntity.getName());
//                if (!playerTeamEntity.isAlive()){
//                    counter++;
//                    System.out.println("*TESTING* " + "Counter(after isAlive check(player)): " + counter);
//                    if (counter == teamSize){
//                        System.out.println("PLAYER TEAM DEAD");
//                        return false;
//                    }
//                }
//            }

        if (!player.isAlive()){
            return false;
        }

            // Checks each entity on enemy team
            for (Entity enemyTeamEntity : enemyTeam){
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

    public void startCombat(List<Entity> entities, Scanner scanner) {
        do {
            System.out.println("*TESTING*" + " Starting Combat");
            for(Entity entity : entities) {
               // Sorts into player and enemy teams
                if (entity.getEntityType() == EntityType.PLAYER || entity.getEntityType() == EntityType.ALLY) {
                    playerTeam.add(entity);
                    if (entity.getEntityType() == EntityType.PLAYER) {
                        player = (TestPlayer) entity;
                    } else ally = (TestAlly) entity;
                    System.out.println("*TESTING* (startCombat) " + entity.getName() + " added!");
                } else if (entity.getEntityType() == EntityType.ENEMY || entity.getEntityType() == EntityType.BOSS) {
                    enemyTeam.add(entity);
                    if (entity.getEntityType() == EntityType.ENEMY) {
                        enemy = entity;
                    } else boss = entity;
                    System.out.println("*TESTING* (startCombat) " + entity.getName() + " added!");
                }
            }



            System.out.println("*TESTING* KILLING PLAYER");
            player.takeDamage(10000);


        } while (combatCondition(entities));
    }

    public void choice(Scanner scanner) {
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
                }
                default: {
                    System.out.println("INVALID CHOICE");
                }
            }
        }


    public void action(Entity entity, Random random) {
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
}



