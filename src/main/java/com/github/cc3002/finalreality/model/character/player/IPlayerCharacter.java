package com.github.islaterm.finalreality.model.character.player;

import com.github.islaterm.finalreality.model.weapon.IWeapon;

/**
 * @author Ignacio Slater Muñoz.
 */
public interface IPlayerCharacter {

  /**
   * Equips a weapon to the character.
   */
  void equip(IWeapon weapon);

  /**
   * Return this character's equipped weapon.
   * @return
   */
  IWeapon getEquippedWeapon();
}
