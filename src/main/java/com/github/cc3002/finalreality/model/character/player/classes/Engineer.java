package com.github.cc3002.finalreality.model.character.player.classes;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacter;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 *
 * @version 1.03
 * @since 1.03
 */
public class Engineer extends AbstractPlayerCharacter {

  /**
   * Creates a new Engineer with a name, maximum HP, defense, maximum mana and an associated turns queue.
   *
   * @param name
   *     the character's name
   * @param turnsQueue
   *     the turns queue.
   * @param maxHP
   *     this character's maximum HP.
   * @param defense
   *     this character's defense.
   * @since 1.03
   */
  public Engineer(@NotNull final String name, @NotNull final BlockingQueue<ICharacter> turnsQueue,
                  int maxHP, int defense) {
    super(name,turnsQueue, maxHP, defense);
  }

  /**
   * Equips a weapon to the character, if compatible.
   *
   * @param weapon
   *    Weapon to be equipped.
   * @since 1.0
   */
  public void equip(IWeapon weapon) {
    if(weapon.beEquippedByEngineer()){
      if(Objects.isNull(weapon.getBearer())){
        if(Objects.nonNull(this.getEquippedWeapon())){
          this.getEquippedWeapon().beUnequipped();
          this.unequip();
        }
        weapon.beEquipped(this);
        this.equippedWeapon = weapon;
      }
    }
  }

  /**
   * Compares two Engineer objects for testing purposes.
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
    if (!super.equals(o)) return false;
    return true;
  }

  /**
   * Hashes the Engineer parameters for testing purposes.
   *
   * @return hash(super.hash())
   * @since 1.0
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }
}
