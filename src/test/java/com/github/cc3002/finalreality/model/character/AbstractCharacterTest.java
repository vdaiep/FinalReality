package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.IPlayerCharacter;
import com.github.cc3002.finalreality.model.character.player.classes.Knight;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.Sword;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * Abstract class containing the common tests for all the types of characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 * @see ICharacter
 *
 * @version 1.04
 * @since 1.0
 */
public class AbstractCharacterTest {

    protected BlockingQueue<ICharacter> turns;
    protected ArrayList<IPlayerCharacter> testCharacters;
    protected ArrayList<IWeapon> testWeapons;

    /**
     * Setup method. Declares turns queue and test lists.
     *
     * @since 1.02
     */
    @BeforeEach
    protected void basicSetUp() {
        turns = new LinkedBlockingQueue<>();
        testWeapons = new ArrayList<>();
        testCharacters = new ArrayList<>();
    }

    /**
     * Checks that the character waits the appropriate amount of time for it's turn.
     *
     * @since 1.0
     */
    @Test
    void waitTurnTest() {
        Assertions.assertTrue(turns.isEmpty());
        IPlayerCharacter character = new Knight("Darius", turns, 100, 20, testCharacters);
        IWeapon weapon = new Sword("BF Sword", 30, 15);
        character.equip(weapon);
        character.waitTurn();
        try {
            // Thread.sleep is not accurate so this values may be changed to adjust the
            // acceptable error margin.
            // We're testing that the character waits approximately 1 second.
            Thread.sleep(500);
            Assertions.assertEquals(0, turns.size());
            Thread.sleep(800);
            Assertions.assertEquals(1, turns.size());
            Assertions.assertEquals(character, turns.peek());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks that the class' constructor and equals method work properly.
     *
     * @since 1.0
     */
    protected void checkConstruction(final ICharacter expectedCharacter,
                                     final ICharacter testEqualCharacter,
                                     final ICharacter sameClassDifferentCharacter,
                                     final ICharacter differentClassCharacter) {
        assertEquals(expectedCharacter, testEqualCharacter);
        assertNotEquals(sameClassDifferentCharacter, testEqualCharacter);
        assertNotEquals(testEqualCharacter, differentClassCharacter);
        assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode());
    }

}
