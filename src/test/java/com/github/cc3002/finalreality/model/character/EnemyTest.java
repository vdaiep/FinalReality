package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.CharacterClass;
import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.cc3002.finalreality.model.weapon.SpellClass;
import com.github.cc3002.finalreality.model.weapon.Weapon;
import com.github.cc3002.finalreality.model.weapon.WeaponType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


/**
 * Set of tests for the {@code Enemy} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 * @see Enemy
 *
 * @version 1.02
 * @since 1.0
 */
class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Baron Nashor";
  private static final int ENEMY_WEIGHT = 10;
  private static final int ENEMY_AD = 25;
  private static final int ENEMY_MAX_HP = 100;
  private static final int ENEMY_DEFENSE = 15;

  /**
   * Setup method.
   * Creates test Enemy
   *
   * @since 1.02
   */
  @BeforeEach
  void setUp() {
    basicSetUp();
    testCharacters.add(new Enemy(ENEMY_NAME, ENEMY_WEIGHT, ENEMY_AD, turns, ENEMY_MAX_HP, ENEMY_DEFENSE));
  }

  /**
   * Checks that the class' constructor and equals method work properly.
   *
   * @since 1.02
   */
  @Test
  void constructorTest() {
    checkConstruction(new Enemy(ENEMY_NAME, ENEMY_WEIGHT, ENEMY_AD, turns, ENEMY_MAX_HP, ENEMY_DEFENSE),
        testCharacters.get(0),
        new Enemy(ENEMY_NAME, 11, ENEMY_AD, turns, ENEMY_MAX_HP, ENEMY_DEFENSE),
        new AbstractPlayerCharacter(ENEMY_NAME, turns, CharacterClass.THIEF, ENEMY_MAX_HP, ENEMY_DEFENSE, 0));
  }

  /**
   * Checks that the Enemy waits the appropriate amount of time for its turn
   *
   * @since 1.0
   */
  @Test
  void waitTurnTest() {
    Assertions.assertTrue(turns.isEmpty());
    ICharacter enemy = new Enemy(ENEMY_NAME, ENEMY_WEIGHT, ENEMY_AD, turns, ENEMY_MAX_HP, ENEMY_DEFENSE);
    enemy.waitTurn();
    try {
      // Thread.sleep is not accurate so this values may be changed to adjust the
      // acceptable error margin.
      // We're testing that the character waits approximately 1 second.
      Thread.sleep(900);
      Assertions.assertEquals(0, turns.size());
      Thread.sleep(200);
      Assertions.assertEquals(1, turns.size());
      Assertions.assertEquals(enemy, turns.peek());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks if Enemies' stats interact properly with other Characters
   *
   * @since 1.02
   */
  @Test
  void interactionsTest() {
    /* Local set-up */
    AbstractCharacter enemy = testCharacters.get(0);
    AbstractCharacter player = new AbstractPlayerCharacter("Sylas", turns, CharacterClass.THIEF, 100, 20, 100);
    Weapon other_sword = new Weapon("Long sword", 100, 20, 10, WeaponType.SWORD);

    /* Enemies should have no mana */
    assertEquals(enemy.getMana(), 0);
    assertEquals(enemy.getMaxMana(), 0);

    /* Failed spell due to caster being an enemy */
    int sp3 = enemy.castSpell(player, SpellClass.THUNDER);
    assertEquals(sp3, -3);
    assertEquals(player.getHP(), player.getMaxHP());
    assertFalse(player.isParalyzed());

    /* Enemies can't equip weapons */
    enemy.equip(other_sword);
    assertNull(enemy.getEquippedWeapon());

    /* Successful attack */
    int atk0 = enemy.attack(player);
    assertEquals(atk0, 0);
    assertEquals(player.getHP(), 95);

    /* Failed attack due to target being dead */
    player.receiveDamage(99999, true);
    assertFalse(player.isAlive());
    assertEquals(player.getHP(), 0);
    int atk2 = enemy.attack(player);
    assertEquals(atk2, -2);
    player.receiveHealing(99999);

    /* Failed attack due to paralysis */
    enemy.getParalyzed();
    assertTrue(enemy.isParalyzed());
    int atk9 = enemy.attack(player);
    assertFalse(enemy.isParalyzed());
    assertEquals(atk9, -9);

    /* Failed attack due to attacker being dead */
    enemy.receiveDamage(99999, true);
    int atk1 = enemy.attack(player);
    assertEquals(atk1, -1);
  }

}
