package com.github.cc3002.finalreality.model.weapon;

import java.util.Objects;

/**
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 *
 * @version 1.05
 * @since 1.03
 */
public class Staff extends AbstractWeapon {

  /**
   * Magic damage of the weapon.
   */
  private final int magicDamage;

  /**
   * Creates a weapon with a name, a base damage, speed and it's type.
   */
  public Staff(final String name, final int damage, final int weight, final int magicDamage) {
    super(name, damage, weight);
    this.magicDamage = magicDamage;
  }

  /**
   * Checks if this kind of weapon can be equipped by a Knight character.
   *
   * @return false.
   * @since 1.03
   */
  public boolean beEquippedByKnight(){
    return false;
  }

  /**
   * Checks if this kind of weapon can be equipped by an Engineer character.
   *
   * @return false.
   * @since 1.03
   */
  public boolean beEquippedByEngineer(){
    return false;
  }

  /**
   * Checks if this kind of weapon can be equipped by a Thief character.
   *
   * @return true.
   * @since 1.03
   */
  public boolean beEquippedByThief(){
    return true;
  }

  /**
   * Checks if this kind of weapon can be equipped by a Black Mage character.
   *
   * @return true.
   * @since 1.03
   */
  public boolean beEquippedByBlackMage(){
    return true;
  }

  /**
   * Checks if this kind of weapon can be equipped by a White Mage character.
   *
   * @return true.
   * @since 1.03
   */
  public boolean beEquippedByWhiteMage(){
    return true;
  }

  /**
   * Gets the magic damage of the weapon.
   *
   * @return 'magicDamage' parameter.
   * @since 1.0
   */
  public int getMagicDamage(){
    return this.magicDamage;
  }

  /**
   * Compares two Staff objects for testing purposes.
   *
   * @param o
   *    object to compare with
   * @return true if equals, false otherwise.
   * @since 1.0
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Staff staff = (Staff) o;
    return getMagicDamage() == staff.getMagicDamage();
  }

  /**
   * Hashes the Staff parameters for testing purposes.
   *
   * @return hash(super.hash(), magicDamage)
   * @since 1.0
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getMagicDamage());
  }
}
