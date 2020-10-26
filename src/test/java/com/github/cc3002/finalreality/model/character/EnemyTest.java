package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.CharacterClass;
import com.github.cc3002.finalreality.model.character.player.PlayerCharacter;
import com.github.cc3002.finalreality.model.weapon.SpellClass;
import com.github.cc3002.finalreality.model.weapon.Weapon;
import com.github.cc3002.finalreality.model.weapon.WeaponType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Watchable;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Baron Nashor";
  private static final int ENEMY_WEIGHT = 10;
  private static final int ENEMY_MAX_HP = 100;
  private static final int ENEMY_DEFENSE = 15;


  @BeforeEach
  void setUp() {
    basicSetUp();
    testCharacters.add(new Enemy(ENEMY_NAME, ENEMY_WEIGHT, turns, ENEMY_MAX_HP, ENEMY_DEFENSE));
  }

  @Test
  void constructorTest() {
    checkConstruction(new Enemy(ENEMY_NAME, ENEMY_WEIGHT, turns, ENEMY_MAX_HP, ENEMY_DEFENSE),
        testCharacters.get(0),
        new Enemy(ENEMY_NAME, 11, turns, ENEMY_MAX_HP, ENEMY_DEFENSE),
        new PlayerCharacter(ENEMY_NAME, turns, CharacterClass.THIEF, ENEMY_MAX_HP, ENEMY_DEFENSE, 0));
  }

  @Test
  void waitTurnTest() {
    Assertions.assertTrue(turns.isEmpty());
    ICharacter enemy = new Enemy(ENEMY_NAME, ENEMY_WEIGHT, turns, ENEMY_MAX_HP, ENEMY_DEFENSE);
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

  @Test
  void interactionsTest(){
    /* Local set-up */
    AbstractCharacter enemy = new Enemy(ENEMY_NAME, ENEMY_WEIGHT, turns, ENEMY_MAX_HP, ENEMY_DEFENSE);
    AbstractCharacter player = new PlayerCharacter("Sylas", turns, CharacterClass.THIEF, 100, 20, 100);
    Weapon other_sword = new Weapon("Long sword", 100, 20, 10, WeaponType.SWORD);

    /* Failed spell due to caster being an enemy */
    enemy.castSpell(player, SpellClass.THUNDER);
    assertEquals(player.getHP(), player.getMaxHP());
    assertFalse(player.isParalyzed());

    /* Enemies can't equip weapons */
    enemy.equip(other_sword);
    assertNull(enemy.getEquippedWeapon());
  }

}