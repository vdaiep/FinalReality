package com.github.cc3002.finalreality.model.character;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 */
public interface ICharacter {

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  void waitTurn();


  void getAttacked(int rawDamage);

  void attack(ICharacter that);

  /**
   * Returns this character's name.
   */
  String getName();

  int getHP();

  int getMaxHP();

  int getDefense();


}

