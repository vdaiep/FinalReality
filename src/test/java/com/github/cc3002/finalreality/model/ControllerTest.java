package com.github.cc3002.finalreality.model;

import com.github.cc3002.finalreality.Controller;
import com.github.cc3002.finalreality.model.character.player.classes.BlackMage;
import com.github.cc3002.finalreality.model.character.player.classes.WhiteMage;
import com.github.cc3002.finalreality.model.weapon.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Set of tests for the Controller class and its helpers classes:
 * @code Controller
 * @code AttackPhase
 * @code EquipmentPhase
 * @code DeathHandler
 * @code WeaponFactory
 * @code CharacterFactory
 * @code EnemyPhase is ignored due to trivial methods.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 * @see Controller
 *
 * @version 1.05
 * @since 1.04
 */
public class ControllerTest {

    private Controller controller;
    private Controller controller2;
    private Controller controller3;
    private final ArrayList<String> knight_names = new ArrayList<>();
    private final ArrayList<String> engineer_names = new ArrayList<>();
    private final ArrayList<String> thief_names = new ArrayList<>();
    private final ArrayList<String> black_mage_names = new ArrayList<>();
    private final ArrayList<String> white_mage_names = new ArrayList<>();
    private final ArrayList<String> sword_names = new ArrayList<>();
    private final ArrayList<String> axe_names = new ArrayList<>();
    private final ArrayList<String> knife_names = new ArrayList<>();
    private final ArrayList<String> staff_names = new ArrayList<>();
    private final ArrayList<String> bow_names = new ArrayList<>();
    private final ArrayList<String> emptyArray = new ArrayList<>();
    private final ArrayList<String> strongKnights1 = new ArrayList<>();
    private final ArrayList<String> strongSwords1 = new ArrayList<>();
    private final ArrayList<String> strongKnights2 = new ArrayList<>();
    private final ArrayList<String> strongSwords2 = new ArrayList<>();

    /**
     * Setup method. Declares three different controllers for testing.
     *
     * @since 1.05
     */
    @BeforeEach
    public void setUp(){
        controller = new Controller();
        controller2 = new Controller();
        controller3 = new Controller();
        knight_names.add("TestKnight1");
        engineer_names.add("TestEngineer1");
        thief_names.add("TestThief1");
        black_mage_names.add("TestBlackMage1");
        white_mage_names.add("TestWhiteMage1");
        sword_names.add("TestSword1");
        axe_names.add("TestAxe1");
        knife_names.add("TestKnife1");
        staff_names.add("TestStaff1");
        bow_names.add("TestBow1");
        for(int i=0; i<2; i++){
            strongKnights1.add("Strong Knight " + i);
            strongSwords1.add("Strong Sword " + i);
        }
        for(int i=0; i<3; i++){
            strongKnights2.add("Strong Knight " + i);
            strongSwords2.add("Strong Sword " + i);
        }
    }

    /**
     * Checks that the controller's main set-up method and the getters work properly.
     *
     * @since 1.05
     */
    @Test
    public void controllerSetUpAndGettersTest(){
        controller.setUpGame(knight_names, engineer_names,thief_names,black_mage_names,white_mage_names,
                sword_names, axe_names,knife_names,staff_names,bow_names);
        assertEquals(5, controller.getCharacters().size());
        assertEquals(5, controller.getCharacterNames().size());
        assertEquals(5, controller.getCharacterDefense().size());
        assertEquals(5, controller.getCharacterHP().size());
        assertEquals(5, controller.getCharacterMaxHP().size());
        assertEquals(5, controller.getCharacterAttack().size());
        assertEquals(5, controller.getCharacterWeapons().size());
        assertEquals(5, controller.getCharacterWeight().size());
        assertEquals(5, controller.getEnemies().size());
        assertEquals(5, controller.getEnemyNames().size());
        assertEquals(5, controller.getEnemyDefense().size());
        assertEquals(5, controller.getEnemyHP().size());
        assertEquals(5, controller.getEnemyMaxHP().size());
        assertEquals(5, controller.getEnemyAD().size());
        assertEquals(5, controller.getEnemyWeight().size());
        assertEquals(5, controller.getInventory().size());
        assertEquals(5, controller.getWeaponAD().size());
        assertEquals(5, controller.getWeaponBearers().size());
        assertEquals(5, controller.getWeaponNames().size());
        assertEquals(5, controller.getWeaponWeight().size());
        for(int i=0;i<5;i++){
            assertEquals("None", controller.getCharacterWeapons().get(i));
            assertEquals("0", controller.getCharacterAttack().get(i).toString());
            assertEquals("0", controller.getCharacterWeight().get(i).toString());
        }
        assertEquals(50, controller.getMagicDamage((Staff) controller.getInventory().get(3)));
        assertEquals(100, controller.getMana((BlackMage) controller.getCharacters().get(3)));
        assertEquals(100, controller.getMaxMana((BlackMage) controller.getCharacters().get(3)));
        assertEquals(100, controller.getMana((WhiteMage) controller.getCharacters().get(4)));
        assertEquals(100, controller.getMaxMana((WhiteMage) controller.getCharacters().get(4)));
        assertTrue(controller.isKnight(0));
        assertFalse(controller.isKnight(1));
        assertFalse(controller.isKnight(2));
        assertFalse(controller.isKnight(3));
        assertFalse(controller.isKnight(4));
        assertFalse(controller.isEngineer(0));
        assertTrue(controller.isEngineer(1));
        assertFalse(controller.isEngineer(2));
        assertFalse(controller.isEngineer(3));
        assertFalse(controller.isEngineer(4));
        assertFalse(controller.isThief(0));
        assertFalse(controller.isThief(1));
        assertTrue(controller.isThief(2));
        assertFalse(controller.isThief(3));
        assertFalse(controller.isThief(4));
        assertFalse(controller.isBlackMage(0));
        assertFalse(controller.isBlackMage(1));
        assertFalse(controller.isBlackMage(2));
        assertTrue(controller.isBlackMage(3));
        assertFalse(controller.isBlackMage(4));
        assertFalse(controller.isWhiteMage(0));
        assertFalse(controller.isWhiteMage(1));
        assertFalse(controller.isWhiteMage(2));
        assertFalse(controller.isWhiteMage(3));
        assertTrue(controller.isWhiteMage(4));

    }

    /**
     * Checks that the phases of the game work properly.
     *
     * @since 1.05
     */
    @Test
    public void controllerPhasesTest(){
        controller.setUpGame(knight_names, emptyArray, emptyArray, emptyArray, emptyArray,
                emptyArray, axe_names,emptyArray, emptyArray, emptyArray);
        assertFalse(controller.getQueue().isEmpty());
        assertTrue(controller.getCurrentPhase().isEquipmentPhase());
        controller.getCurrentPhase().setIndexWeapon(0);
        controller.getCurrentPhase().setIndexEnemy(0);
        controller.getCurrentPhase().doPhase();
        assertEquals("TestAxe1", controller.getCharacterWeapons().get(0));
        assertEquals("150", controller.getCharacterAttack().get(0).toString());
        assertEquals("70", controller.getCharacterWeight().get(0).toString());
        assertEquals("TestKnight1", controller.getWeaponBearers().get(0));
        assertFalse(controller.getCurrentPhase().isEquipmentPhase());
        assertEquals(0, controller.getCurrentPlayer());
        controller.getCurrentPhase().setIndexEnemy(0);
        controller.getCurrentPhase().setIndexWeapon(0);
        controller.getCurrentPhase().doPhase();
        controller.setAttackPhase(0);
        controller.getCurrentPhase().setIndexEnemy(0);
        controller.getCurrentPhase().doPhase();
        controller.unequip(0);
        assertFalse(controller.getToLog().isEmpty());
        controller.cleanLog();
        assertEquals(0, controller.getToLog().size());
    }

    /**
     * Checks that the defeat condition can be achieved properly.
     *
     * @since 1.05
     */
    @Test
    public void controllerDefeatTest() {
        controller2.setUpGame(strongKnights1, emptyArray, emptyArray, emptyArray, emptyArray,
                strongSwords1, emptyArray, emptyArray, emptyArray, emptyArray);
        while(controller2.getGameStatus() == 0){
            controller2.getCurrentPhase().setIndexWeapon(controller2.getCurrentPlayer());
            controller2.getCurrentPhase().doPhase();
            controller2.getCurrentPhase().setIndexEnemy(0);
            controller2.getCurrentPhase().doPhase();
        }
        assertEquals(-1, controller2.getGameStatus());
    }

    /**
     * Checks that the victory condition can be achieved properly.
     *
     * @since 1.05
     */
    @Test
    public void controllerVictoryTest() {
        controller3.setUpGame(strongKnights2, emptyArray, emptyArray, emptyArray, emptyArray,
                strongSwords2, emptyArray, emptyArray, emptyArray, emptyArray);
        while(controller3.getGameStatus() == 0){
            controller3.getCurrentPhase().setIndexWeapon(controller3.getCurrentPlayer());
            controller3.getCurrentPhase().doPhase();
            controller3.getCurrentPhase().setIndexEnemy(0);
            controller3.getCurrentPhase().doPhase();
        }
        assertEquals(1, controller3.getGameStatus());
    }
}
