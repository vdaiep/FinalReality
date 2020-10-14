package com.github.cc3002.finalreality.model.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.github.cc3002.finalreality.model.character.player.CharacterClass;
import com.github.cc3002.finalreality.model.character.player.PlayerCharacter;
import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Set of tests for the {@code GameCharacter} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 * @see PlayerCharacter
 */
class PlayerCharacterTest extends AbstractCharacterTest {

  private static final String BLACK_MAGE_NAME = "Vivi";
  private static final String KNIGHT_NAME = "Adelbert";
  private static final String WHITE_MAGE_NAME = "Eiko";
  private static final String ENGINEER_NAME = "Cid";
  private static final String THIEF_NAME = "Zidane";
  private Map<CharacterClass, String> characterNames;
  private Map<CharacterClass, Integer> characterHPs;
  private Map<CharacterClass, Integer> characterDefenses;
  private Map<CharacterClass, Integer> characterManas;

  /**
   * Setup method.
   * Creates a new character named Vivi with 10 speed and links it to a turn queue.
   */
  @BeforeEach
  void setUp() {
    super.basicSetUp();

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
    characterManas.put(CharacterClass.KNIGHT, 0);
    characterManas.put(CharacterClass.WHITE_MAGE, 100);
    characterManas.put(CharacterClass.ENGINEER, 0);
    characterManas.put(CharacterClass.THIEF, 0);

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
   * Checks that the class' constructor and equals method works properly.
   */
  @Test
  void constructorTest() {
    var enemy = new Enemy("Enemy", 10, turns, 100, 20);
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

  @Test
  void equipWeaponTest() {
    for (var character :
        testCharacters) {
      assertNull(character.getEquippedWeapon());
      character.equip(testWeapon);
      assertEquals(testWeapon, character.getEquippedWeapon());
    }
  }
}
