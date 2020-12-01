package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.player.IPlayerCharacter;
import java.util.Objects;

/**
 * A class that holds all the information of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 *
 * @version 1.03
 * @since 1.0
 */
public abstract class AbstractWeapon implements IWeapon {

  /**
   * Name of the weapon.
   */
  private final String name;

  /**
   * Damage of the weapon.
   */
  private final int damage;

  /**
   * Weight (speed indicator) of the weapon.
   */
  private final int weight;

  /**
   * Current character bearing the weapon, null if none.
   */
  private IPlayerCharacter bearer;

  /**
   * Creates a weapon with a name, a base damage and speed.
   *
   * @param name
   *    name of the weapon
   * @param damage
   *    physical damage of the weapon
   * @param weight
   *    weight (speed indicator) of the weapon
   * @since 1.0
   */
  public AbstractWeapon(final String name, final int damage, final int weight) {
    this.name = name;
    this.damage = damage;
    this.weight = weight;
    this.bearer = null;
  }

  /**
   * Method called when this weapon has been equipped by a character.
   *
   * @param character
   *    character to bear this weapon
   * @since 1.03
   */
  @Override
  public void beEquipped(IPlayerCharacter character){
    this.bearer = character;
  }

  /**
   * Method called when this weapon has been unequipped by a character.
   *
   * @since 1.03
   */
  @Override
  public void beUnequipped(){
    if(Objects.nonNull(this.getBearer())) {
      this.bearer = null;
    }
  }

  /**
   * Gets the name of the weapon.
   *
   * @return 'name' parameter
   * @since 1.0
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Gets the physical damage of the weapon.
   *
   * @return 'damage' parameter
   * @since 1.0
   */
  @Override
  public int getDamage() {
    return damage;
  }

  /**
   * Gets the weight of the weapon.
   *
   * @return 'weight' parameter
   * @since 1.0
   */
  @Override
  public int getWeight() {
    return weight;
  }

  /**
   * Gets the current bearer of the weapon.
   *
   * @return 'bearer' parameter
   * @since 1.03
   */
  @Override
  public IPlayerCharacter getBearer(){
    return bearer;
  }

  /**
   * Compares two Weapon objects for testing purposes.
   *
   * @param o
   *    object to compare with
   * @return true if equals, false otherwise
   * @since 1.0
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractWeapon that = (AbstractWeapon) o;
    return getDamage() == that.getDamage() &&
            getWeight() == that.getWeight() &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getBearer(), that.getBearer());
  }

  /**
   * Hashes the Weapon parameters for testing purposes.
   *
   * @return hash(name, damage, weight, bearer)
   * @since 1.0
   */
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getDamage(), getWeight(), getBearer());
  }
}
