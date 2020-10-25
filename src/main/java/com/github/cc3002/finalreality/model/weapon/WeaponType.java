package com.github.cc3002.finalreality.model.weapon;

/**
 * Enumeration of all the weapon types.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 * @version 1.0
 * @since 1.0
 */
public enum WeaponType {
  SWORD (false),
  AXE (false),
  KNIFE (false),
  STAFF (true),
  BOW (false);

  /**
   * Indicates if this Weapon can use magical properties: having magicDamage > 0
   * and being able to use spells (on mages only), true if it is, false otherwise
   */
  public final boolean isMagical;

  /**
   * Incorporates 'isMagical' parameter to each WeaponType
   *
   * @param isMagical
   *    'isMagical' parameter
   * @since 1.0
   */
  WeaponType(boolean isMagical){
    this.isMagical = isMagical;
  }

}
