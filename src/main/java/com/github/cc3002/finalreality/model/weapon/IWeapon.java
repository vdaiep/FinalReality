package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.player.IPlayerCharacter;

/**
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 *
 * @version 1.03
 * @since 1.03
 */
public interface IWeapon {

  /**
   * Gets the name of the weapon.
   *
   * @return 'name' parameter
   * @since 1.0
   */
  String getName();

  /**
   * Gets the physical damage of the weapon.
   *
   * @return 'damage' parameter
   * @since 1.0
   */
  int getDamage();

  /**
   * Gets the weight of the weapon.
   *
   * @return 'weight' parameter
   * @since 1.0
   */
  int getWeight();

  /**
   * Gets the magic damage of the weapon.
   *
   * @return 'magicDamage' parameter if Weapon is a Staff, 0 otherwise.
   * @since 1.0
   */
  int getMagicDamage();

  /**
   * Gets the current bearer of the weapon.
   *
   * @return 'bearer' parameter
   * @since 1.03
   */
  IPlayerCharacter getBearer();

  /**
   * Checks if this kind of weapon can be equipped by a Knight character.
   *
   * @return true if it is, false otherwise.
   * @since 1.03
   */
  boolean beEquippedByKnight();

  /**
   * Checks if this kind of weapon can be equipped by an Engineer character.
   *
   * @return true if it is, false otherwise.
   * @since 1.03
   */
  boolean beEquippedByEngineer();

  /**
   * Checks if this kind of weapon can be equipped by a Thief character.
   *
   * @return true if it is, false otherwise.
   * @since 1.03
   */
  boolean beEquippedByThief();

  /**
   * Checks if this kind of weapon can be equipped by a Black Mage character.
   *
   * @return true if it is, false otherwise.
   * @since 1.03
   */
  boolean beEquippedByBlackMage();

  /**
   * Checks if this kind of weapon can be equipped by a White Mage character.
   *
   * @return true if it is, false otherwise.
   * @since 1.03
   */
  boolean beEquippedByWhiteMage();

  /**
   * Method called when this weapon has been equipped by a character.
   *
   * @param character
   *    character to bear this weapon
   * @since 1.03
   */
  void beEquipped(IPlayerCharacter character);

  /**
   * Method called when this weapon has been unequipped by a character.
   *
   * @since 1.03
   */
  void beUnequipped();
}
