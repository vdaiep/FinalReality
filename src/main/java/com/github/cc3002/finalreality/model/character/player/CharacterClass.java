package com.github.cc3002.finalreality.model.character.player;

/**
 * Enumeration of the classes a player character may have.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 * @version 1.0
 * @since 1.0
 */
public enum CharacterClass {
  KNIGHT (false),
  ENGINEER (false),
  THIEF (false),
  BLACK_MAGE (true),
  WHITE_MAGE (true),
  ENEMY (false);

  /**
   * Indicates if this CharacterClass can use magical properties: being able to use spells,
   * true if it is, false otherwise
   */
  protected final boolean isMage;

  /**
   * Incorporates 'isMage' parameter to each CharacterClass
   *
   * @param isMage
   *    'isMage' parameter
   * @since 1.0
   */
  CharacterClass(boolean isMage){
    this.isMage = isMage;
  }
}
