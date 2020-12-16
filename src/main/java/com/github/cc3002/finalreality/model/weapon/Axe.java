package com.github.cc3002.finalreality.model.weapon;

import java.util.Objects;

/**
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 *
 * @version 1.04
 * @since 1.03
 */
public class Axe extends AbstractWeapon {

  /**
   * Creates a weapon with a name, a base damage, speed.
   */
  public Axe(final String name, final int damage, final int weight) {
    super(name, damage, weight);
  }

  /**
   * Checks if this kind of weapon can be equipped by a Knight character.
   *
   * @return true.
   * @since 1.03
   */
  public boolean beEquippedByKnight(){
    return true;
  }

  /**
   * Checks if this kind of weapon can be equipped by an Engineer character.
   *
   * @return true.
   * @since 1.03
   */
  public boolean beEquippedByEngineer(){
    return true;
  }

  /**
   * Checks if this kind of weapon can be equipped by a Thief character.
   *
   * @return false.
   * @since 1.03
   */
  public boolean beEquippedByThief(){
    return false;
  }

  /**
   * Checks if this kind of weapon can be equipped by a Black Mage character.
   *
   * @return false.
   * @since 1.03
   */
  public boolean beEquippedByBlackMage(){
    return false;
  }

  /**
   * Checks if this kind of weapon can be equipped by a White Mage character.
   *
   * @return false.
   * @since 1.03
   */
  public boolean beEquippedByWhiteMage(){
    return false;
  }

  /**
   * Compares two Axe objects for testing purposes.
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
    return true;
  }

  /**
   * Hashes the Axe parameters for testing purposes.
   *
   * @return hash(super.hash())
   * @since 1.0
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }
}
