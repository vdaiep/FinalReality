package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.CharacterClass;
import com.github.cc3002.finalreality.model.weapon.Weapon;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 * @version 1.0
 * @since 1.0
 */
public interface ICharacter {

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   *
   * @since 1.0
   */
  void waitTurn();

  /**
   * Returns this character's name.
   *
   * @since 1.0
   */
  String getName();

  /**
   * Returns this character's maximum HP.
   *
   * @since 1.0
   */
  int getMaxHP();

  /**
   * Returns this character's current HP.
   *
   * @since 1.0
   */
  int getHP();

  /**
   * Returns this character's defense.
   *
   * @since 1.0
   */
  int getDefense();

  /**
   * Returns this character's maximum mana.
   *
   * @since 1.0
   */
  int getMaxMana();

  /**
   * Returns this character's current mana.
   *
   * @since 1.0
   */
  int getMana();

  /**
   * Equips a weapon to the character.
   *
   * @since 1.0
   */
  void equip(Weapon weapon);

  /**
   * Return this character's equipped weapon.
   *
   * @since 1.0
   */
  Weapon getEquippedWeapon();

  /**
   * Returns this character's class.
   *
   * @since 1.0
   */
  CharacterClass getCharacterClass();
}
