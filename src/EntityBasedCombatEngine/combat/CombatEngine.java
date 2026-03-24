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
     *     Details: The loop goes through each {@link Entity} and sorts them into teams(Haven't decided on this).
     *     Player team consists of {@link EntityType#PLAYER} and {@link EntityType#ALLY} while Enemy {@link EntityType#ENEMY} and or {@link EntityType#BOSS},
     *     Then checks if whether of the teams is alive or not.
     * */
    public boolean combatCondition(List<Entity> entities){
        List<Entity> enemyTeam = new ArrayList<>();
        List<Entity> playerTeam = new ArrayList<>();
        for(Entity entity : entities){

            if (entity.getEntityType() == EntityType.PLAYER || entity.getEntityType() == EntityType.ALLY){
                playerTeam.add(entity);
            } else {
                enemyTeam.add(entity);
            }


        }
        return false;
    }

    public void startCombat(List<Entity> entities) {
        do {

        } while (entities.getFirst().isAlive());
    }

}
