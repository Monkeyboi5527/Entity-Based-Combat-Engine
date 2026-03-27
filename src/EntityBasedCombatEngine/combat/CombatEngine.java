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
            for (Entity playerTeamEntity : playerTeam){
                teamSize = playerTeam.size();
                System.out.println("*TESTING* " + "Counter(before isAlive check(player)): " + counter);
                System.out.println("*TESTING* " + "Checking: " + playerTeamEntity.getName());
                if (!playerTeamEntity.isAlive()){
                    counter++;
                    System.out.println("*TESTING* " + "Counter(after isAlive check(player)): " + counter);
                    if (counter == teamSize){
                        System.out.println("PLAYER TEAM DEAD");
                        return false;
                    }
                }
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

            // PLAYER TEAM TURN
            // Player:
            while (player.isAlive()){
                if (playerTeam.getFirst().getEntityType() == EntityType.PLAYER){
                    player.choice(scanner);
                    if (player.choice(scanner) == 1){
                        enemyTeam.getFirst().takeDamage(player.getAttackDamage());
                    }
                } else {
                    // Ally:
                    ally.action(random);
                }
            }


        } while (combatCondition(entities));
    }

}
