package com.github.cc3002.finalreality.model.character;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 *
 * @version 1.04
 * @since 1.0
 */
public interface ICharacter {

  /**
   * Adds this character to the turns queue.
   *
   * @since 1.0
   */
  void addToQueue();

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   *
   * @since 1.0
   */
  void waitTurn();

  /**
   * Receives damage when attacked by another Character or Enemy.
   *
   * @param rawDamage
   *    Damage received.
   * @since 1.02
   */
  void getAttacked(int rawDamage);

  /**
   * Attacks another character, dealing 'attackDamage' damage if Enemy, or 'attackDamage' from its
   * equipped weapon if PlayerCharacter.
   *
   * @param that
   *    Character to be attacked.
   * @since 1.02
   */
  void attack(ICharacter that);

  /**
   * Gets the name of the character.
   *
   * @return 'name' parameter
   * @since 1.0
   */
  String getName();

  /**
   * Gets the current HP of the character.
   *
   * @return 'HP' parameter
   * @since 1.0
   */
  int getHP();

  /**
   * Gets the maximum HP of the character.
   *
   * @return 'maxHP' parameter
   * @since 1.0
   */
  int getMaxHP();

  /**
   * Gets the defense of the character.
   *
   * @return 'name' parameter
   * @since 1.0
   */
  int getDefense();

  /**
   * Gets the living condition of the character.
   *
   * @return 'alive' parameter
   * @since 1.0
   */
  boolean isAlive();
}

