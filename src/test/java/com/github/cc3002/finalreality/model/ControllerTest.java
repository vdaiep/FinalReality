package com.github.cc3002.finalreality.model;

import com.github.cc3002.finalreality.Controller;
import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.IPlayerCharacter;
import com.github.cc3002.finalreality.model.character.player.classes.BlackMage;
import com.github.cc3002.finalreality.model.character.player.classes.WhiteMage;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Set of tests for the {@code Controller} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 * @see Controller
 *
 * @version 1.04
 * @since 1.04
 */
public class ControllerTest {

    private Controller controller;
    private Controller controller2;

    /**
     * Setup method. Declares two different controllers for testing.
     *
     * @since 1.04
     */
    @BeforeEach
    public void setUp(){
        controller = new Controller();
        controller2 = new Controller();
    }

    /**
     * Checks that the controller works properly. Part 1.
     *
     * @since 1.04
     */
    @Test
    public void controllerTest1(){
        controller.createEnemy("TestEnemy1");
        controller.createEnemy("TestEnemy2");
        controller.createEnemy("TestEnemy3");
        controller.createKnight("TestKnight");
        controller.createEngineer("TestEngineer");
        controller.createThief("TestThief");
        controller.createBlackMage("TestBlackMage");
        controller.createWhiteMage("TestWhiteMage");
        controller.createAxe("TestAxe");
        controller.createBow("TestBow");
        controller.createStaff("TestStaff");
        controller.createKnife("TestKnife");
        controller.createSword("TestSword");
        assertEquals(5, controller.getInventory().size());
        assertEquals(5, controller.getCharacters().size());
        assertEquals(3, controller.getEnemies().size());
        assertEquals(8, controller.getQueue().size());
        ICharacter someEnemy = controller.getQueue().poll();
        assertNotNull(someEnemy);
        assertTrue(controller.getEnemies().contains(someEnemy));
        assertEquals(controller.getAttackDamage((Enemy) someEnemy), 50);
        assertEquals(controller.getDefense(someEnemy), 20);
        assertEquals(controller.getHP(someEnemy), 500);
        assertEquals(controller.getMaxHP(someEnemy), 500);
        assertEquals(controller.getWeight((Enemy) someEnemy), 20);
        IWeapon someWeapon = controller.getInventory().get(0);
        IPlayerCharacter somePlayerCharacter = controller.getCharacters().get(0);
        controller.equipWeapon(somePlayerCharacter, someWeapon);
        assertEquals(controller.getAttackDamage(someWeapon), 150);
        assertEquals(controller.getWeight(someWeapon), 70);
        assertEquals(somePlayerCharacter.getEquippedWeapon(), someWeapon);
        for(int i=0;i<10;i++){
            controller.attack(somePlayerCharacter, someEnemy);
        }
        assertEquals(controller.getHP(someEnemy), 0);
        assertEquals(controller.getEnemies().size(), 2);
        ICharacter anotherEnemy = controller.getQueue().poll();
        assertNotNull(anotherEnemy);
        assertTrue(controller.getEnemies().contains(anotherEnemy));
        for(int i=0;i<10;i++){
            controller.attack(somePlayerCharacter, anotherEnemy);
        }
        ICharacter theLastEnemy = controller.getQueue().poll();
        assertNotNull(theLastEnemy);
        assertTrue(controller.getEnemies().contains(theLastEnemy));
        for(int i=0;i<10;i++) {
            controller.attack(somePlayerCharacter, theLastEnemy);
        }
        assertEquals(controller.getEnemies().size(), 0);
        assertEquals(controller.getGameStatus(), 1);
    }

    /**
     * Checks that the controller works properly. Part 2.
     *
     * @since 1.04
     */
    @Test
    public void controllerTest2(){
        controller2.createEnemy("TestEnemy4");
        controller2.createBlackMage("TestBlackMage2");
        controller2.createWhiteMage("TestWhiteMage2");
        controller2.createStaff("TestStaff2");
        controller2.createKnife("TestKnife2");
        Enemy enemy = controller2.getEnemies().get(0);
        IPlayerCharacter blackMage = controller2.getCharacters().get(0);
        IPlayerCharacter whiteMage = controller2.getCharacters().get(1);
        IWeapon weapon1 = controller2.getInventory().get(0);
        IWeapon weapon2 = controller2.getInventory().get(1);
        assertEquals(controller2.getName(enemy), "TestEnemy4");
        assertEquals(controller2.getName(weapon2), "TestKnife2");
        assertEquals(controller2.getMagicDamage((Staff) weapon1), 50);
        assertEquals(controller2.getMana((WhiteMage) whiteMage), 100);
        assertEquals(controller2.getMaxMana((WhiteMage) whiteMage), 100);
        assertEquals(controller2.getMana((BlackMage) blackMage), 100);
        assertEquals(controller2.getMaxMana((BlackMage) blackMage), 100);
        controller2.equipWeapon(blackMage, weapon2);
        controller2.equipWeapon(whiteMage, weapon1);
        assertEquals(whiteMage.getEquippedWeapon(), weapon1);
        controller2.turn();
        controller2.turn();
        for(int i=0; i<10; i++){
            controller2.attack(enemy, blackMage);
            controller2.attack(enemy, whiteMage);
        }
        assertEquals(controller2.getCharacters().size(), 0);
        assertEquals(controller2.getGameStatus(), -1);

    }

}
