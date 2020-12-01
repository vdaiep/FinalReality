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
public class BlackMage extends AbstractPlayerCharacter {

  /**
   * This character's current mana.
   */
  protected int mana;

  /**
   * This character's maximum mana.
   */
  protected final int maxMana;

  /**
   * Creates a new Thief with a name, maximum HP, defense, maximum mana and an associated turns queue.
   *
   * @param name
   *     the character's name
   * @param turnsQueue
   *     the turns queue.
   * @param maxHP
   *     this character's maximum HP.
   * @param defense
   *     this character's defense.
   * @param maxMana
   *     this character's maximum mana.
   * @since 1.03
   */
  public BlackMage(@NotNull final String name, @NotNull final BlockingQueue<ICharacter> turnsQueue,
                   int maxHP, int defense, int maxMana) {
    super(name, turnsQueue, maxHP, defense);
    this.maxMana = maxMana;
    this.mana = maxMana;
  }

  /**
   * Equips a weapon to the character, if compatible.
   *
   * @param weapon
   *    Weapon to be equipped.
   * @since 1.0
   */
  @Override
  public void equip(IWeapon weapon) {
    if(weapon.beEquippedByBlackMage()){
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
   * Gets this character's current mana.
   *
   * @return 'mana' parameter
   * @since 1.03
   */
  public int getMana() {
    return mana;
  }

  /**
   * Gets this character's maximum mana.
   *
   * @return 'maxMana' parameter
   * @since 1.03
   */
  public int getMaxMana() {
    return maxMana;
  }

  /**
   * Compares two Black Mage objects for testing purposes.
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
    BlackMage blackMage = (BlackMage) o;
    return getMana() == blackMage.getMana() &&
            getMaxMana() == blackMage.getMaxMana();
  }

  /**
   * Hashes the Black Mage parameters for testing purposes.
   *
   * @return hash(super.hash(), mana, maxMana)
   * @since 1.0
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getMana(), getMaxMana());
  }
}
