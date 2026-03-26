import EntityBasedCombatEngine.combat.CombatEngine;
import EntityBasedCombatEngine.entity.Entity;
import EntityBasedCombatEngine.entity.EntityType;
import EntityBasedCombatEngine.entity.TestAlly;
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
    // *Util* System.out.println("*TESTING* ");
    Random random = new Random();

    // *TESTING*
    TestPlayer testPlayer = new TestPlayer("testPlayer", 100, 15, EntityType.PLAYER);
    TestAlly testAlly = new TestAlly("testAlly", 100, 15, EntityType.ALLY);
    Entity testEnemy = new Entity("testEnemy", 100, 15, EntityType.ENEMY);
    Entity testEnemy2 = new Entity("testEnemy1", 100, 15, EntityType.ENEMY);
    Entity testBoss = new Entity("testBoss", 100, 15, EntityType.BOSS);

    if (testAlly.action(random) == 1) {
        testEnemy.takeDamage(testAlly.getAttackDamage());
        System.out.println("*TESTING* returned 1");
    } else  {
        System.out.println("*TESTING* returned 2");
    }


}

