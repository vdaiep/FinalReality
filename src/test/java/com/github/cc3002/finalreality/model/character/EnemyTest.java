package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.CharacterClass;
import com.github.cc3002.finalreality.model.character.player.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Mountain Troll";
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
}