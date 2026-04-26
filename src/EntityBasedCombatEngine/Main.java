import EntityBasedCombatEngine.combat.CombatEngine;
import EntityBasedCombatEngine.entity.*;
import EntityBasedCombatEngine.entity.entities.TestAlly;
import EntityBasedCombatEngine.entity.entities.TestBoss;
import EntityBasedCombatEngine.entity.entities.TestEnemy;
import EntityBasedCombatEngine.entity.entities.TestPlayer;


void main() {
    // *Util* System.out.println("*TESTING* ");
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);


    // *TESTING*
    TestPlayer testPlayer = new TestPlayer("testPlayer", 100000, 15, EntityType.PLAYER);
    TestAlly testAlly = new TestAlly("testAlly", 80, 10, EntityType.ALLY);
    TestAlly testAlly2 = new TestAlly("testAlly2", 100, 15, EntityType.ALLY);
    TestEnemy testEnemy = new TestEnemy("testEnemy", 80, 10, EntityType.ENEMY);
    TestEnemy testEnemy2 = new TestEnemy("testEnemy2", 80, 10, EntityType.ENEMY);
    TestEnemy testEnemy3 = new TestEnemy("testEnemy3", 80, 10, EntityType.ENEMY);
    TestEnemy testEnemy4 = new TestEnemy("testEnemy4", 80, 10, EntityType.ENEMY);
    TestEnemy testEnemy5 = new TestEnemy("testEnemy5", 80, 10, EntityType.ENEMY);
    TestEnemy testEnemy6 = new TestEnemy("testEnemy6", 80, 10, EntityType.ENEMY);
    TestBoss testBoss = new TestBoss("testBoss", 150, 15, EntityType.BOSS);

    List<Entity> entities = new ArrayList<>();
    entities.add(testPlayer);
    entities.add(testAlly);
    entities.add(testAlly2);
    entities.add(testEnemy);
    entities.add(testEnemy2);
    entities.add(testEnemy3);
    entities.add(testEnemy4);
    entities.add(testEnemy5);
    entities.add(testEnemy6);
    entities.add(testBoss);


    CombatEngine combatEngine = new CombatEngine();
    combatEngine.startCombatDebug(entities, scanner, random);


}

