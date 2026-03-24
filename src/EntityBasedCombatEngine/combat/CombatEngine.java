package EntityBasedCombatEngine.combat;

import EntityBasedCombatEngine.entity.Entity;
import EntityBasedCombatEngine.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

/**
 * The main controller. Runs the combat loop start to finish.
 */
public class CombatEngine {

    /**
     * Function -> To check if the Player team or the Enemy team is alive.
     *     <P>
     *     Details: The loop goes through each {@link Entity} and sorts them into teams.
     *     Player team consists of {@link EntityType#PLAYER} and {@link EntityType#ALLY} while Enemy {@link EntityType#ENEMY} and or {@link EntityType#BOSS},
     *     Then checks if whether each entity in the team is alive or not.
     * */
    public boolean combatCondition(List<Entity> entities){
        List<Entity> enemyTeam = new ArrayList<>();
        List<Entity> playerTeam = new ArrayList<>();
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

    public void startCombat(List<Entity> entities) {
        do {
            System.out.println("*TESTING*" + " Starting Combat");
        } while (combatCondition(entities));
    }

}
