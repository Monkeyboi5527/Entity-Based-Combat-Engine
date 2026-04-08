import EntityBasedCombatEngine.combat.CombatEngine;
import EntityBasedCombatEngine.entity.Entity;
import EntityBasedCombatEngine.entity.EntityType;
import EntityBasedCombatEngine.entity.TestAlly;
import EntityBasedCombatEngine.entity.TestPlayer;


void main() {
    // *Util* System.out.println("*TESTING* ");
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);


    // *TESTING*
    TestPlayer testPlayer = new TestPlayer("testPlayer", 100, 15, EntityType.PLAYER);
    TestAlly testAlly = new TestAlly("testAlly", 100, 15, EntityType.ALLY);
    TestAlly testAlly2 = new TestAlly("testAlly", 100, 15, EntityType.ALLY);
    Entity testEnemy = new Entity("testEnemy", 100, 15, EntityType.ENEMY);
    Entity testBoss = new Entity("testBoss", 100, 15, EntityType.BOSS);

    List<Entity> entities = new ArrayList<>();
    entities.add(testPlayer);
    entities.add(testAlly);
    entities.add(testAlly2);
    entities.add(testEnemy);
    entities.add(testBoss);


    CombatEngine combatEngine = new CombatEngine();
        combatEngine.startCombat(entities, scanner);


}

