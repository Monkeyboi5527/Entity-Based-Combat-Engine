import EntityBasedCombatEngine.combat.CombatEngine;
import EntityBasedCombatEngine.entity.Entity;
import EntityBasedCombatEngine.entity.EntityType;
import EntityBasedCombatEngine.entity.TestPlayer;
/**
 *  Game Function <p>
 *   -Entity, Stats, & Components stored of the Data <p>
 *   -DamageSystem & TurnManger handles the logic of combat <p>
 *   -CombatEngine & CombatContext combine all those together and displays them <p>
 * <p>
 *   *NOTE* <p>
 *   Since this was given by ChatGPT and Claude I don't know what everything does <p>
 *   There will be alot of changes
 */

void main() {

    // *TESTING*
    TestPlayer testPlayer = new TestPlayer("testPlayer", 100, 15, EntityType.PLAYER);
    TestPlayer testAlly = new TestPlayer("testAlly", 100, 15, EntityType.ALLY);
    Entity testEnemy = new Entity("testEnemy", 100, 15, EntityType.ENEMY);
    Entity testEnemy1 = new Entity("testEnemy1", 100, 15, EntityType.ENEMY);
    Entity testBoss = new Entity("testBoss", 100, 15, EntityType.BOSS);

    List<Entity> entities = new ArrayList<>();
    entities.add(testPlayer);
    entities.add(testAlly);
    entities.add(testEnemy);
    entities.add(testEnemy1);
    entities.add(testBoss);
    CombatEngine combatEngine = new CombatEngine();
    if (combatEngine.combatCondition(entities)){
        System.out.println("*TESTING*" + "idk!");
    } else{
        System.out.println("*TESTING*" + " IT WORKS!");
    }


}

