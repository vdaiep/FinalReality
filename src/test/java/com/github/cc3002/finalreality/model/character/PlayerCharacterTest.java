package com.github.cc3002.finalreality.model.character;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.github.cc3002.finalreality.model.character.player.CharacterClass;
import com.github.cc3002.finalreality.model.character.player.PlayerCharacter;
import java.util.EnumMap;
import java.util.Map;

import com.github.cc3002.finalreality.model.weapon.SpellClass;
import com.github.cc3002.finalreality.model.weapon.Weapon;
import com.github.cc3002.finalreality.model.weapon.WeaponType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Set of tests for the {@code PlayerCharacter} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 * @see PlayerCharacter
 *
 * @version 1.02
 * @since 1.0
 */
class PlayerCharacterTest extends AbstractCharacterTest {

  private static final String BLACK_MAGE_NAME = "Xerath";
  private static final String KNIGHT_NAME = "Garen";
  private static final String WHITE_MAGE_NAME = "Lulu";
  private static final String ENGINEER_NAME = "Heimerdinger";
  private static final String THIEF_NAME = "Shaco";
  private static final String SWORD_NAME = "Infinity Edge";
  private static final String AXE_NAME = "Black Cleaver";
  private static final String KNIFE_NAME = "Duskblade of Draktharr";
  private static final String STAFF_NAME = "Void Staff";
  private static final String BOW_NAME = "Runaan's Hurricane";
  private Map<CharacterClass, String> characterNames;
  private Map<CharacterClass, Integer> characterHPs;
  private Map<CharacterClass, Integer> characterDefenses;
  private Map<CharacterClass, Integer> characterManas;

  /**
   * Setup method.
   * Creates test Characters and Weapons
   *
   * @since 1.02
   */
  @BeforeEach
  void setUp() {
    super.basicSetUp();

    Map<WeaponType, String> weaponNames = new EnumMap<>(WeaponType.class);
    weaponNames.put(WeaponType.SWORD, SWORD_NAME);
    weaponNames.put(WeaponType.AXE, AXE_NAME);
    weaponNames.put(WeaponType.KNIFE, KNIFE_NAME);
    weaponNames.put(WeaponType.STAFF, STAFF_NAME);
    weaponNames.put(WeaponType.BOW, BOW_NAME);

    Map<WeaponType, Integer> weaponDamages = new EnumMap<>(WeaponType.class);
    weaponDamages.put(WeaponType.SWORD, 50);
    weaponDamages.put(WeaponType.AXE, 80);
    weaponDamages.put(WeaponType.KNIFE, 40);
    weaponDamages.put(WeaponType.STAFF, 15);
    weaponDamages.put(WeaponType.BOW, 50);

    Map<WeaponType, Integer> weaponMagicDamages = new EnumMap<>(WeaponType.class);
    weaponMagicDamages.put(WeaponType.SWORD, 15);
    weaponMagicDamages.put(WeaponType.AXE, 10);
    weaponMagicDamages.put(WeaponType.KNIFE, 25);
    weaponMagicDamages.put(WeaponType.STAFF, 60);
    weaponMagicDamages.put(WeaponType.BOW, 20);

    Map<WeaponType, Integer> weaponWeights = new EnumMap<>(WeaponType.class);
    weaponWeights.put(WeaponType.SWORD, 25);
    weaponWeights.put(WeaponType.AXE, 50);
    weaponWeights.put(WeaponType.KNIFE, 15);
    weaponWeights.put(WeaponType.STAFF, 30);
    weaponWeights.put(WeaponType.BOW, 20);

    testWeapons.add(new Weapon(
            weaponNames.get(WeaponType.SWORD),
            weaponDamages.get(WeaponType.SWORD),
            weaponMagicDamages.get(WeaponType.SWORD),
            weaponWeights.get(WeaponType.SWORD),
            WeaponType.SWORD
    ));

    testWeapons.add(new Weapon(
            weaponNames.get(WeaponType.AXE),
            weaponDamages.get(WeaponType.AXE),
            weaponMagicDamages.get(WeaponType.AXE),
            weaponWeights.get(WeaponType.AXE),
            WeaponType.AXE
    ));

    testWeapons.add(new Weapon(
            weaponNames.get(WeaponType.KNIFE),
            weaponDamages.get(WeaponType.KNIFE),
            weaponMagicDamages.get(WeaponType.KNIFE),
            weaponWeights.get(WeaponType.KNIFE),
            WeaponType.KNIFE
    ));

    testWeapons.add(new Weapon(
            weaponNames.get(WeaponType.STAFF),
            weaponDamages.get(WeaponType.STAFF),
            weaponMagicDamages.get(WeaponType.STAFF),
            weaponWeights.get(WeaponType.STAFF),
            WeaponType.STAFF
    ));

    testWeapons.add(new Weapon(
            weaponNames.get(WeaponType.BOW),
            weaponDamages.get(WeaponType.BOW),
            weaponMagicDamages.get(WeaponType.BOW),
            weaponWeights.get(WeaponType.BOW),
            WeaponType.BOW
    ));

    characterNames = new EnumMap<>(CharacterClass.class);
    characterNames.put(CharacterClass.BLACK_MAGE, BLACK_MAGE_NAME);
    characterNames.put(CharacterClass.KNIGHT, KNIGHT_NAME);
    characterNames.put(CharacterClass.WHITE_MAGE, WHITE_MAGE_NAME);
    characterNames.put(CharacterClass.ENGINEER, ENGINEER_NAME);
    characterNames.put(CharacterClass.THIEF, THIEF_NAME);

    characterHPs = new EnumMap<>(CharacterClass.class);
    characterHPs.put(CharacterClass.BLACK_MAGE, 70);
    characterHPs.put(CharacterClass.KNIGHT, 200);
    characterHPs.put(CharacterClass.WHITE_MAGE, 50);
    characterHPs.put(CharacterClass.ENGINEER, 120);
    characterHPs.put(CharacterClass.THIEF, 100);

    characterDefenses = new EnumMap<>(CharacterClass.class);
    characterDefenses.put(CharacterClass.BLACK_MAGE, 10);
    characterDefenses.put(CharacterClass.KNIGHT, 20);
    characterDefenses.put(CharacterClass.WHITE_MAGE, 10);
    characterDefenses.put(CharacterClass.ENGINEER, 10);
    characterDefenses.put(CharacterClass.THIEF, 15);

    characterManas = new EnumMap<>(CharacterClass.class);
    characterManas.put(CharacterClass.BLACK_MAGE, 100);
    characterManas.put(CharacterClass.KNIGHT, 100);
    characterManas.put(CharacterClass.WHITE_MAGE, 100);
    characterManas.put(CharacterClass.ENGINEER, 100);
    characterManas.put(CharacterClass.THIEF, 100);

    testCharacters.add(new PlayerCharacter(
            characterNames.get(CharacterClass.BLACK_MAGE),
            turns,
            CharacterClass.BLACK_MAGE,
            characterHPs.get(CharacterClass.BLACK_MAGE),
            characterDefenses.get(CharacterClass.BLACK_MAGE),
            characterManas.get(CharacterClass.BLACK_MAGE)
    ));

    testCharacters.add(new PlayerCharacter(
            characterNames.get(CharacterClass.KNIGHT),
            turns,
            CharacterClass.KNIGHT,
            characterHPs.get(CharacterClass.KNIGHT),
            characterDefenses.get(CharacterClass.KNIGHT),
            characterManas.get(CharacterClass.KNIGHT)
    ));

    testCharacters.add(new PlayerCharacter(
            characterNames.get(CharacterClass.WHITE_MAGE),
            turns,
            CharacterClass.WHITE_MAGE,
            characterHPs.get(CharacterClass.WHITE_MAGE),
            characterDefenses.get(CharacterClass.WHITE_MAGE),
            characterManas.get(CharacterClass.WHITE_MAGE)
    ));

    testCharacters.add(new PlayerCharacter(
            characterNames.get(CharacterClass.ENGINEER),
            turns,
            CharacterClass.ENGINEER,
            characterHPs.get(CharacterClass.ENGINEER),
            characterDefenses.get(CharacterClass.ENGINEER),
            characterManas.get(CharacterClass.ENGINEER)
    ));

    testCharacters.add(new PlayerCharacter(
            characterNames.get(CharacterClass.THIEF),
            turns,
            CharacterClass.THIEF,
            characterHPs.get(CharacterClass.THIEF),
            characterDefenses.get(CharacterClass.THIEF),
            characterManas.get(CharacterClass.THIEF)
    ));
  }

  /**
   * Checks that the class' constructor and equals method work properly.
   *
   * @since 1.02
   */
  @Test
  void constructorTest() {
    var enemy = new Enemy("Enemy", 10, 25, turns, 100, 20);
    for (var character :
        testCharacters) {
      var characterClass = character.getCharacterClass();
      var characterName = characterNames.get(characterClass);
      var characterHP = characterHPs.get(characterClass);
      var characterDefense = characterDefenses.get(characterClass);
      var characterMana = characterManas.get(characterClass);
      checkConstruction(
          new PlayerCharacter(
              characterName, turns, characterClass, characterHP, characterDefense, characterMana
          ),
          character,
          new PlayerCharacter(
                  "Test", turns, characterClass, characterHP, characterDefense, characterMana),
          new PlayerCharacter(characterName, turns,
              characterClass == CharacterClass.THIEF ? CharacterClass.BLACK_MAGE
                  : CharacterClass.THIEF, characterHP, characterDefense, characterMana));
      assertNotEquals(character, enemy);
    }

  }

  /**
   * Checks that the weapon equipment process works correctly with each kind of Character and Weapon
   *
   * @since 1.02
   */
  @Test
  void equipWeaponTest() {
    for (var character : testCharacters) {
      assertNull(character.getEquippedWeapon());
      for (var weapon : testWeapons) {
        CharacterClass characterClass = character.getCharacterClass();
        WeaponType weaponType = weapon.getType();
        character.equip(weapon);
        switch (characterClass){
          case KNIGHT:
            switch (weaponType){
              case SWORD:
              case AXE:
              case KNIFE:
                assertEquals(weapon, character.getEquippedWeapon());
                break;
              case STAFF:
              case BOW:
                assertNull(character.getEquippedWeapon());
                break;
            }
            break;
          case ENGINEER:
            switch (weaponType){
              case AXE:
              case BOW:
                assertEquals(weapon, character.getEquippedWeapon());
                break;
              case SWORD:
              case KNIFE:
              case STAFF:
                assertNull(character.getEquippedWeapon());
                break;
            }
            break;
          case THIEF:
            switch (weaponType){
              case SWORD:
              case STAFF:
              case BOW:
                assertEquals(weapon, character.getEquippedWeapon());
                break;
              case AXE:
              case KNIFE:
                assertNull(character.getEquippedWeapon());
                break;
            }
            break;
          case BLACK_MAGE:
            switch (weaponType){
              case KNIFE:
              case STAFF:
                assertEquals(weapon, character.getEquippedWeapon());
                break;
              case SWORD:
              case AXE:
              case BOW:
                assertNull(character.getEquippedWeapon());
                break;
            }
            break;
          case WHITE_MAGE:
            switch (weaponType){
              case STAFF:
                assertEquals(weapon, character.getEquippedWeapon());
                break;
              case SWORD:
              case AXE:
              case KNIFE:
              case BOW:
                assertNull(character.getEquippedWeapon());
                break;
            }
            break;
        }
        character.unequip();
      }
    }
  }

  /**
   * Checks if Characters' and Weapons' stats interact properly
   *
   * @since 1.02
   */
  @Test
  void interactionsTest() {
    /* Local set-up */
    AbstractCharacter black_mage = testCharacters.get(0);
    AbstractCharacter knight = testCharacters.get(1);
    AbstractCharacter white_mage = testCharacters.get(2);
    AbstractCharacter engineer = testCharacters.get(3);
    AbstractCharacter thief = testCharacters.get(4);
    Weapon sword = testWeapons.get(0);
    Weapon axe = testWeapons.get(1);
    Weapon knife = testWeapons.get(2);
    Weapon staff = testWeapons.get(3);
    Weapon bow = testWeapons.get(4);
    black_mage.equip(knife);
    knight.equip(sword);
    white_mage.equip(staff);
    engineer.equip(axe);
    thief.equip(bow);

    /* Non-mages should have no mana */
    assertEquals(knight.getMaxMana(), 0);
    assertEquals(engineer.getMaxMana(), 0);
    assertEquals(thief.getMaxMana(), 0);
    assertEquals(knight.getMana(), 0);
    assertEquals(engineer.getMana(), 0);
    assertEquals(thief.getMana(), 0);

    /* Non-magical Weapons should have no magic damage */
    assertEquals(sword.getMagicDamage(), 0);
    assertEquals(axe.getMagicDamage(), 0);
    assertEquals(knife.getMagicDamage(), 0);
    assertEquals(bow.getMagicDamage(), 0);

    /* Failed attack due to Character attacking itself
    *  Failed spell due to Character casting spell on itself */
    int atk10 = knight.attack(knight);
    assertEquals(atk10, -10);
    assertEquals(knight.getHP(), knight.getMaxHP());
    int sp10 = white_mage.castSpell(white_mage, SpellClass.HEAL);
    assertEquals(sp10, -10);

    /* Successful attack */
    int atk0 = engineer.attack(knight);
    assertEquals(atk0, 0);
    assertEquals(knight.getHP(), 140);

    /* Successful spell PARALYZE
     * Paralysis status
     * Failed attack due to paralysis, then successful attack */
    knight.attack(white_mage);
    int sp0 = white_mage.castSpell(knight, SpellClass.PARALYZE);
    assertEquals(sp0, 0);
    assertEquals(white_mage.getMana(), 75);
    assertTrue(knight.isParalyzed());
    int atk9 = knight.attack(white_mage);
    assertEquals(atk9, -9);
    assertFalse(knight.isParalyzed());
    assertEquals(white_mage.getHP(), 10);

    /* Failed spell due to wrong Weapon type */
    int sp6 = black_mage.castSpell(engineer, SpellClass.THUNDER);
    assertEquals(sp6, -6);
    assertEquals(black_mage.getMana(), 100);
    assertFalse(engineer.isParalyzed());
    assertEquals(engineer.getHP(), 120);

    /* Successful spell HEAL */
    thief.attack(knight);
    white_mage.castSpell(knight, SpellClass.HEAL);
    assertEquals(white_mage.getMana(), 60);
    assertEquals(knight.getHP(), 170);

    /* Burn status
     * Burn damage
     * Burn purification */
    engineer.getBurnt(staff.getMagicDamage());
    assertTrue(engineer.isBurned());
    assertEquals(engineer.getBurnDamage(), 30);
    engineer.applyStatusDamage();
    engineer.getPurified(false, false, true);
    assertEquals(engineer.getHP(), 90);

    /* Successful spell POISON
     * Poison status
     * Poison damage
     * Poison purification */
    white_mage.castSpell(thief, SpellClass.POISON);
    assertEquals(white_mage.getMana(), 20);
    assertTrue(thief.isPoisoned());
    assertEquals(thief.getPoisonDamage(), 20);
    thief.applyStatusDamage();
    thief.getPurified(true, false, false);
    assertFalse(thief.isPoisoned());
    assertEquals(thief.getHP(), 80);

    /* Failed spell due not enough mana */
    int sp8 = white_mage.castSpell(engineer, SpellClass.POISON);
    assertEquals(sp8, -8);
    assertEquals(white_mage.getMana(), 20);
    assertEquals(engineer.getHP(), 90);
    assertFalse(engineer.isPoisoned());
    assertEquals(engineer.getPoisonDamage(), 0);

    /* Mana refilled */
    white_mage.refillMana(99999);
    assertEquals(white_mage.getMana(), 100);

    /* Character dies */
    engineer.attack(white_mage);
    assertEquals(white_mage.getHP(), 0);
    assertFalse(white_mage.isAlive());

    /* Failed attack due to dead attacker
    *  Failed spell due to dead caster */
    int atk1 = white_mage.attack(thief);
    assertEquals(atk1, -1);
    int sp1 = white_mage.castSpell(thief, SpellClass.HEAL);
    assertEquals(sp1, -1);
    assertEquals(white_mage.getMana(), 100);
    assertFalse(white_mage.isAlive());
    assertEquals(thief.getHP(), 80);

    /* Failed attack due to dead target
    *  Failed spell due to dead target */
    int atk2 = knight.attack(white_mage);
    assertEquals(atk2, -2);
    int sp2 = black_mage.castSpell(white_mage, SpellClass.FIRE);
    assertEquals(sp2, -2);
    assertEquals(black_mage.getMana(), 100);
    assertEquals(white_mage.getHP(), 0);
    assertFalse(white_mage.isBurned());
    assertEquals(white_mage.getBurnDamage(), 0);

    /* Failed spell due to caster not being a mage */
    int sp5 = knight.castSpell(engineer, SpellClass.HEAL);
    assertEquals(sp5, -5);
    assertEquals(engineer.getHP(), 90);

    /* Failed attack due to null Weapon
    *  Failed spell due to null Weapon */
    black_mage.unequip();
    assertNull(black_mage.getEquippedWeapon());
    int atk4 = black_mage.attack(engineer);
    assertEquals(atk4, -4);
    int sp4 = black_mage.castSpell(engineer, SpellClass.FIRE);
    assertEquals(sp4, -4);
    assertEquals(engineer.getHP(), 90);
    assertFalse(engineer.isBurned());

    /* Failed spell due to wrong kind of mage */
    black_mage.equip(staff);
    int sp7 = black_mage.castSpell(engineer, SpellClass.HEAL);
    assertEquals(sp7, -7);
    assertEquals(engineer.getHP(), 90);

    /* Failed spell due to paralysis
    *  Paralysis status
    *  Paralysis purification */
    black_mage.getParalyzed();
    int sp9 = black_mage.castSpell(engineer, SpellClass.THUNDER);
    assertEquals(sp9, -9);
    assertFalse(engineer.isParalyzed());
    assertEquals(engineer.getHP(), 90);
    assertFalse(black_mage.isParalyzed());
    black_mage.getParalyzed();
    black_mage.getPurified(false, true, false);

    /* Successful spell FIRE
    *  Target got burnt
    *  Note: (3.507e-47)% chance of failing due to RNG */
    AbstractCharacter tank1 = new PlayerCharacter("Tank1", turns, CharacterClass.KNIGHT, 30000, 100, 100);
    while(!tank1.isBurned() && tank1.isAlive()){
      int result = black_mage.castSpell(tank1, SpellClass.FIRE);
      if(result == -8){
        black_mage.refillMana(99999);
      }
    }
    assertTrue(tank1.isBurned());
    tank1.getPurified(false, false, true);
    assertFalse(tank1.isBurned());

    /* Successful spell THUNDER
     *  Target got paralyzed
     *  Note: (3.540e-76)% chance of failing due to RNG */
    AbstractCharacter tank2 = new PlayerCharacter("Tank2", turns, CharacterClass.KNIGHT, 30000, 100, 100);
    while(!tank2.isParalyzed() && tank2.isAlive()){
      int result = black_mage.castSpell(tank2, SpellClass.THUNDER);
      if(result == -8){
        black_mage.refillMana(99999);
      }
    }
    assertTrue(tank2.isParalyzed());
    tank2.getPurified(false, true, false);
    assertFalse(tank2.isParalyzed());

    /* Stupid test to get 100% coverage */
    assertNotEquals(sword, knight);
  }

}
