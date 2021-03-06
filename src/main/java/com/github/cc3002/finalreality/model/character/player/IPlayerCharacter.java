package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.DeathHandler;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;

/**
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 *
 * @version 1.05
 * @since 1.03
 */
public interface IPlayerCharacter extends ICharacter {

  /**
   * Adds a listener which notifies the controller when this Character has died.
   *
   * @param handler
   *    the listener
   * @since 1.04
   */
  void addListener(DeathHandler handler);

  /**
   * Equips a weapon to the character, if compatible.
   *
   * @param weapon
   *    Weapon to be equipped.
   * @since 1.0
   */
  void equip(IWeapon weapon);

  /**
   * Checks if this character can equip a weapon.
   *
   * @param weapon
   *    weapon to check if compatible
   * @return true if it can, false otherwise
   */
  boolean canEquip(IWeapon weapon);

  /**
   * Unequips the character's weapon.
   *
   * @since 1.0
   */
  void unequip();

  /**
   * Gets this character's equipped weapon.
   *
   * @return 'equippedWeapon' parameter.
   * @since 1.0
   */
  IWeapon getEquippedWeapon();

  /**
   * Checks if this character is a knight.
   *
   * @return true if it is, false otherwise
   * @since 1.05
   */
  boolean isKnight();

  /**
   * Checks if this character is an engineer.
   *
   * @return true if it is, false otherwise
   * @since 1.05
   */
  boolean isEngineer();

  /**
   * Checks if this character is a thief.
   *
   * @return true if it is, false otherwise
   * @since 1.05
   */
  boolean isThief();

  /**
   * Checks if this character is a black mage.
   *
   * @return true if it is, false otherwise
   * @since 1.05
   */
  boolean isBlackMage();

  /**
   * Checks if this character is a white mage.
   *
   * @return true if it is, false otherwise
   * @since 1.05
   */
  boolean isWhiteMage();
}
