package EntityBasedCombatEngine.entity;

import java.util.Random;
import java.util.Scanner;

/**
 * Interface that all components implement.
 */
// An interface is used when you have a class that you want it to do something, but you don't tell it how to do that thing. Kinda like an Abstract class
public interface Component {
    //Don't have any ideas for components at this time
        void test();
        void heal(int amount);
        void takeDamage(int damage);
        boolean isAlive();
        EntityType getEntityType();
        int choice(Scanner scanner);
        int action(Random random);
}
