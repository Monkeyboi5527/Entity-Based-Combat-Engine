import EntityBasedCombatEngine.entity.Entity;
import EntityBasedCombatEngine.entity.TestPlayer;

void main() {

    Entity entity = new Entity("entity", 100, 10);
    Entity testPlayer = new TestPlayer("testPlayer", 100, 10);
    entity.takeDamage(10);
    testPlayer.display();
    entity.display();

}
