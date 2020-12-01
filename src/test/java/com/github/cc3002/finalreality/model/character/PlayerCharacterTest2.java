package com.github.cc3002.finalreality.model.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.cc3002.finalreality.model.character.player.IPlayerCharacter;
import com.github.cc3002.finalreality.model.character.player.classes.*;
import com.github.cc3002.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Set of tests for the {@code PlayerCharacter} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 * @see AbstractPlayerCharacter
 *
 * @version 1.03
 * @since 1.0
 */
public class PlayerCharacterTest2 extends AbstractCharacterTest2 {

    private static final String SWORD_NAME = "Infinity Edge";
    private static final String AXE_NAME = "Black Cleaver";
    private static final String KNIFE_NAME = "Duskblade of Draktharr";
    private static final String STAFF_NAME = "Void Staff";
    private static final String BOW_NAME = "Runaan's Hurricane";

    private static final int AXE_WEIGHT = 20;
    private static final int KNIFE_WEIGHT = 5;
    private static final int BOW_WEIGHT = 10;
    private static final int STAFF_WEIGHT = 10;
    private static final int SWORD_WEIGHT = 12;

    private static final int AXE_AD = 20;
    private static final int KNIFE_AD = 10;
    private static final int BOW_AD = 10;
    private static final int STAFF_AD = 5;
    private static final int SWORD_AD = 15;

    private static final int STAFF_AP = 25;

    private static final String BLACK_MAGE_NAME = "Xerath";
    private static final String KNIGHT_NAME = "Garen";
    private static final String WHITE_MAGE_NAME = "Lulu";
    private static final String ENGINEER_NAME = "Heimerdinger";
    private static final String THIEF_NAME = "Shaco";

    private static final int BLACK_MAGE_HP = 80;
    private static final int KNIGHT_HP = 150;
    private static final int WHITE_MAGE_HP = 50;
    private static final int ENGINEER_HP = 100;
    private static final int THIEF_HP = 80;

    private static final int BLACK_MAGE_DEFENSE = 2;
    private static final int KNIGHT_DEFENSE = 10;
    private static final int WHITE_MAGE_DEFENSE = 2;
    private static final int ENGINEER_DEFENSE = 5;
    private static final int THIEF_DEFENSE = 5;

    private static final int BLACK_MAGE_MANA = 100;
    private static final int WHITE_MAGE_MANA = 100;

    /**
     * Setup method.
     * Creates test Characters and Weapons.
     *
     * @since 1.02
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();
        testWeapons.add(new Axe(AXE_NAME, AXE_AD, AXE_WEIGHT));
        testWeapons.add(new Knife(KNIFE_NAME, KNIFE_AD, KNIFE_WEIGHT));
        testWeapons.add(new Bow(BOW_NAME, BOW_AD, BOW_WEIGHT));
        testWeapons.add(new Staff(STAFF_NAME, STAFF_AD, STAFF_WEIGHT, STAFF_AP));
        testWeapons.add(new Sword(SWORD_NAME, SWORD_AD, SWORD_WEIGHT));
        testCharacters.add(new Knight(KNIGHT_NAME, turns, KNIGHT_HP, KNIGHT_DEFENSE));
        testCharacters.add(new Engineer(ENGINEER_NAME, turns, ENGINEER_HP, ENGINEER_DEFENSE));
        testCharacters.add(new Thief(THIEF_NAME, turns, THIEF_HP, THIEF_DEFENSE));
        testCharacters.add(new WhiteMage(WHITE_MAGE_NAME, turns, WHITE_MAGE_HP, WHITE_MAGE_DEFENSE, BLACK_MAGE_MANA));
        testCharacters.add(new BlackMage(BLACK_MAGE_NAME, turns, BLACK_MAGE_HP, BLACK_MAGE_DEFENSE, WHITE_MAGE_MANA));
    }

    /**
     * Checks that the class' constructor and equals method work properly.
     *
     * @since 1.02
     */
    @Test
    void constructorTest() {
        ICharacter enemy = new Enemy("Elder Drake", turns, 250, 20, 10, 20);
        IPlayerCharacter knight = new Knight(KNIGHT_NAME, turns, KNIGHT_HP, KNIGHT_DEFENSE);
        ICharacter engineer = new Engineer(ENGINEER_NAME, turns, ENGINEER_HP, ENGINEER_DEFENSE);
        ICharacter thief = new Thief(THIEF_NAME, turns, THIEF_HP, THIEF_DEFENSE);
        ICharacter white_mage = new WhiteMage(WHITE_MAGE_NAME, turns, WHITE_MAGE_HP, WHITE_MAGE_DEFENSE, BLACK_MAGE_MANA);
        ICharacter black_mage = new BlackMage(BLACK_MAGE_NAME, turns, BLACK_MAGE_HP, BLACK_MAGE_DEFENSE, WHITE_MAGE_MANA);
        ICharacter knight_alt = new Knight(KNIGHT_NAME, turns, KNIGHT_HP+1, KNIGHT_DEFENSE);
        ICharacter engineer_alt = new Engineer(ENGINEER_NAME, turns, ENGINEER_HP+1, ENGINEER_DEFENSE);
        ICharacter thief_alt = new Thief(THIEF_NAME, turns, THIEF_HP+1, THIEF_DEFENSE);
        ICharacter white_mage_alt = new WhiteMage(WHITE_MAGE_NAME, turns, WHITE_MAGE_HP+1, WHITE_MAGE_DEFENSE, BLACK_MAGE_MANA);
        ICharacter black_mage_alt = new BlackMage(BLACK_MAGE_NAME, turns, BLACK_MAGE_HP+1, BLACK_MAGE_DEFENSE, WHITE_MAGE_MANA);
        checkConstruction(testCharacters.get(0), knight, knight_alt, engineer_alt);
        checkConstruction(testCharacters.get(1), engineer, engineer_alt, thief_alt);
        checkConstruction(testCharacters.get(2), thief, thief_alt, white_mage_alt);
        checkConstruction(testCharacters.get(3), white_mage, white_mage_alt, black_mage_alt);
        checkConstruction(testCharacters.get(4), black_mage, black_mage_alt, knight_alt);
        assertNotEquals(knight, enemy);
        assertNotEquals(engineer, enemy);
        assertNotEquals(thief, enemy);
        assertNotEquals(white_mage, enemy);
        assertNotEquals(black_mage, enemy);
    }

    /**
     * Checks that the Weapons are equipped correctly on each character class.
     *
     * @since 1.03
     */
    @Test
    void equipmentTest(){
        IPlayerCharacter knight = testCharacters.get(0);
        IPlayerCharacter engineer = testCharacters.get(1);
        IPlayerCharacter thief = testCharacters.get(2);
        IPlayerCharacter white_mage = testCharacters.get(3);
        IPlayerCharacter black_mage = testCharacters.get(4);
        IWeapon axe = testWeapons.get(0);
        IWeapon knife = testWeapons.get(1);
        IWeapon bow = testWeapons.get(2);
        IWeapon staff = testWeapons.get(3);
        IWeapon sword = testWeapons.get(4);
        IWeapon staff2 = new Staff("Luden's Echo", 20, 15, 40);

        knight.equip(axe);
        assertEquals(axe, knight.getEquippedWeapon());
        assertEquals(knight, axe.getBearer());
        knight.equip(sword);
        assertEquals(sword, knight.getEquippedWeapon());
        assertEquals(knight, sword.getBearer());
        assertNull(axe.getBearer());
        knight.equip(knife);
        assertEquals(knife, knight.getEquippedWeapon());
        assertEquals(knight, knife.getBearer());
        assertNull(sword.getBearer());
        knight.equip(staff);
        assertEquals(knife, knight.getEquippedWeapon());
        assertEquals(knight, knife.getBearer());
        assertNull(staff.getBearer());
        knight.equip(bow);
        assertEquals(knife, knight.getEquippedWeapon());
        assertEquals(knight, knife.getBearer());
        assertNull(bow.getBearer());
        knight.unequip();
        assertNull(knife.getBearer());
        assertNull(knight.getEquippedWeapon());

        engineer.equip(axe);
        assertEquals(axe, engineer.getEquippedWeapon());
        assertEquals(engineer, axe.getBearer());
        engineer.equip(sword);
        assertEquals(axe, engineer.getEquippedWeapon());
        assertEquals(engineer, axe.getBearer());
        assertNull(sword.getBearer());
        engineer.equip(knife);
        assertEquals(axe, engineer.getEquippedWeapon());
        assertEquals(engineer, axe.getBearer());
        assertNull(knife.getBearer());
        engineer.equip(staff);
        assertEquals(axe, engineer.getEquippedWeapon());
        assertEquals(engineer, axe.getBearer());
        assertNull(staff.getBearer());
        engineer.equip(bow);
        assertEquals(bow, engineer.getEquippedWeapon());
        assertEquals(engineer, bow.getBearer());
        assertNull(axe.getBearer());
        engineer.unequip();
        assertNull(bow.getBearer());
        assertNull(engineer.getEquippedWeapon());

        thief.equip(axe);
        assertNull(engineer.getEquippedWeapon());
        assertNull(axe.getBearer());
        thief.equip(sword);
        assertEquals(sword, thief.getEquippedWeapon());
        assertEquals(thief, sword.getBearer());
        thief.equip(knife);
        assertEquals(sword, thief.getEquippedWeapon());
        assertEquals(thief, sword.getBearer());
        assertNull(knife.getBearer());
        thief.equip(staff);
        assertEquals(staff, thief.getEquippedWeapon());
        assertEquals(thief, staff.getBearer());
        assertNull(sword.getBearer());
        thief.equip(bow);
        assertEquals(bow, thief.getEquippedWeapon());
        assertEquals(thief, bow.getBearer());
        assertNull(staff.getBearer());
        thief.unequip();
        assertNull(bow.getBearer());
        assertNull(thief.getEquippedWeapon());

        black_mage.equip(axe);
        assertNull(black_mage.getEquippedWeapon());
        assertNull(axe.getBearer());
        black_mage.equip(sword);
        assertNull(black_mage.getEquippedWeapon());
        assertNull(sword.getBearer());
        black_mage.equip(knife);
        assertEquals(knife, black_mage.getEquippedWeapon());
        assertEquals(black_mage, knife.getBearer());
        black_mage.equip(staff);
        assertEquals(staff, black_mage.getEquippedWeapon());
        assertEquals(black_mage, staff.getBearer());
        assertNull(knife.getBearer());
        black_mage.equip(bow);
        assertEquals(staff, black_mage.getEquippedWeapon());
        assertEquals(black_mage, staff.getBearer());
        assertNull(bow.getBearer());
        black_mage.unequip();
        assertNull(staff.getBearer());
        assertNull(black_mage.getEquippedWeapon());

        white_mage.equip(axe);
        assertNull(white_mage.getEquippedWeapon());
        assertNull(axe.getBearer());
        white_mage.equip(sword);
        assertNull(white_mage.getEquippedWeapon());
        assertNull(sword.getBearer());
        white_mage.equip(knife);
        assertNull(white_mage.getEquippedWeapon());
        assertNull(knife.getBearer());
        white_mage.equip(staff);
        assertEquals(staff, white_mage.getEquippedWeapon());
        assertEquals(white_mage, staff.getBearer());
        white_mage.equip(bow);
        assertEquals(staff, white_mage.getEquippedWeapon());
        assertEquals(white_mage, staff.getBearer());
        assertNull(bow.getBearer());
        white_mage.equip(staff2);
        assertNull(staff.getBearer());
        assertEquals(staff2, white_mage.getEquippedWeapon());
        assertEquals(white_mage, staff2.getBearer());
        white_mage.unequip();
        assertNull(staff.getBearer());
        assertNull(white_mage.getEquippedWeapon());
    }

    /**
     * Checks that the Characters attack each other correctly.
     *
     * @since 1.04
     */
    @Test
    void interactionsTest(){
        Enemy enemy = new Enemy("Rift Herald", turns, 45, 5, 10, 20);
        IPlayerCharacter knight = testCharacters.get(0);
        IPlayerCharacter engineer = testCharacters.get(1);
        IPlayerCharacter thief = testCharacters.get(2);
        IPlayerCharacter white_mage = testCharacters.get(3);
        IPlayerCharacter black_mage = testCharacters.get(4);
        IWeapon axe = testWeapons.get(0);
        IWeapon knife = testWeapons.get(1);
        IWeapon bow = testWeapons.get(2);
        IWeapon staff = testWeapons.get(3);
        IWeapon sword = testWeapons.get(4);

        knight.equip(axe);
        engineer.equip(bow);
        thief.equip(sword);
        white_mage.equip(staff);
        black_mage.equip(knife);

        knight.attack(white_mage);
        assertEquals(32, white_mage.getHP());
        knight.attack(white_mage);
        knight.attack(white_mage);
        assertEquals(0, white_mage.getHP());

        thief.attack(enemy);
        assertEquals(35, enemy.getHP());
        thief.attack(enemy);
        thief.attack(enemy);
        thief.attack(enemy);
        thief.attack(enemy);
        assertEquals(0, enemy.getHP());

        enemy.attack(knight);
        assertEquals(140, knight.getHP());
    }







}
