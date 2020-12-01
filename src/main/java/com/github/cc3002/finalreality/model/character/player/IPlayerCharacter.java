package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;

/**
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 *
 * @version 1.03
 * @since 1.03
 */
public interface IPlayerCharacter extends ICharacter {

  /**
   * Equips a weapon to the character, if compatible.
   *
   * @param weapon
   *    Weapon to be equipped.
   * @since 1.0
   */
  void equip(IWeapon weapon);

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
}
