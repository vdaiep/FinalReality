package com.github.cc3002.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that holds all the information of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla
 * @version 1.0
 * @since 1.0
 */
public class Weapon {

  /**
   * Name of the weapon
   */
  private final String name;
  /**
   * Physical damage of the weapon
   */
  private final int damage;
  /**
   * Magic damage of the weapon
   */
  private final int magicDamage;
  /**
   * Weight (speed indicator) of the weapon
   */
  private final int weight;
  /**
   * Type of the weapon
   */
  private final WeaponType type;

  /**
   * Creates a weapon with a name, a base damage, speed and it's type.
   *
   * @param name
   *    name of the weapon
   * @param damage
   *    physical damage of the weapon
   * @param magicDamage
   *    magic damage of the weapon
   * @param weight
   *    weight (speed indicator) of the weapon
   * @param type
   *    type of the weapon
   * @see WeaponType
   * @since 1.0
   */
  public Weapon(final String name, final int damage, final int magicDamage, final int weight,
      final WeaponType type) {
    this.name = name;
    this.damage = damage;
    this.weight = weight;
    this.type = type;
    if(type.isMagical){
      this.magicDamage = magicDamage;
    }
    else{
      this.magicDamage = 0;
    }
  }

  /**
   * Gets the name of the weapon
   *
   * @return 'name' parameter
   * @since 1.0
   */
  private String getName() {
    return name;
  }

  /**
   * Gets the physical damage of the weapon
   *
   * @return 'damage' parameter
   * @since 1.0
   */
  public int getDamage() {
    return damage;
  }

  /**
   * Gets the magic damage of the weapon
   *
   * @return 'magicDamage' parameter
   * @since 1.0
   */
  public int getMagicDamage() {
    return magicDamage;
  }

  /**
   * Gets the weight of the weapon
   *
   * @return 'weight' parameter
   * @since 1.0
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Gets the type damage of the weapon
   *
   * @return 'type' parameter
   * @since 1.0
   */
  public WeaponType getType() {
    return type;
  }

  /**
   * Compares two Weapon objects for testing purposes
   *
   * @param o
   *    object to compare with
   * @return true if equals, false otherwise
   * @since 1.0
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Weapon)) {
      return false;
    }
    final Weapon weapon = (Weapon) o;
    return getDamage() == weapon.getDamage() &&
            getMagicDamage() == weapon.getMagicDamage() &&
        getWeight() == weapon.getWeight() &&
        getName().equals(weapon.getName()) &&
        getType() == weapon.getType();
  }

  /**
   * Hashes the Weapon parameters for testing purposes
   *
   * @return hash(name, damage, magicDamage, weight, type)
   * @since 1.0
   */
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getDamage(), getMagicDamage(), getWeight(), getType());
  }
}
