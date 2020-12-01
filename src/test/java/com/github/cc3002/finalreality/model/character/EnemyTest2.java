package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.classes.BlackMage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Set of tests for the {@code Enemy} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 * @see Enemy
 *
 * @version 1.03
 * @since 1.0
 */
public class EnemyTest2 extends AbstractCharacterTest2 {

    private static final String ENEMY_NAME = "Baron Nashor";
    private static final int ENEMY_WEIGHT = 10;
    private static final int ENEMY_AD = 25;
    private static final int ENEMY_MAX_HP = 100;
    private static final int ENEMY_DEFENSE = 15;


    /**
     * Checks that the class' constructor and equals method work properly.
     *
     * @since 1.02
     */
    @Test
    void constructorTest() {
        Enemy enemy = new Enemy(ENEMY_NAME, turns, ENEMY_MAX_HP, ENEMY_DEFENSE, ENEMY_WEIGHT, ENEMY_AD);
        checkConstruction(new Enemy(ENEMY_NAME, turns, ENEMY_MAX_HP, ENEMY_DEFENSE, ENEMY_WEIGHT, ENEMY_AD),
                enemy,
                new Enemy(ENEMY_NAME, turns, ENEMY_MAX_HP, ENEMY_DEFENSE, 11, ENEMY_AD),
                new BlackMage("Veigar", turns, ENEMY_MAX_HP, ENEMY_DEFENSE, 0));
    }

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     *
     * @since 1.0
     */
    @Test
    void waitTurnTest() {
        Assertions.assertTrue(turns.isEmpty());
        Enemy enemy = new Enemy(ENEMY_NAME, turns, ENEMY_MAX_HP, ENEMY_DEFENSE, ENEMY_WEIGHT, ENEMY_AD);
        enemy.waitTurn();
        try {
            // Thread.sleep is not accurate so this values may be changed to adjust the
            // acceptable error margin.
            // We're testing that the character waits approximately 1 second.
            Thread.sleep(500);
            Assertions.assertEquals(0, turns.size());
            Thread.sleep(800);
            Assertions.assertEquals(1, turns.size());
            Assertions.assertEquals(enemy, turns.peek());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
