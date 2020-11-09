package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;

/**
 * @author Ignacio Slater Muñoz.
 */
public interface IPlayerCharacter {

  /**
   * Equips a weapon to the character.
   */
  void equip(IWeapon weapon);

  void attack(ICharacter that);

  /**
   * Return this character's equipped weapon.
   * @return
   */
  IWeapon getEquippedWeapon();
}
