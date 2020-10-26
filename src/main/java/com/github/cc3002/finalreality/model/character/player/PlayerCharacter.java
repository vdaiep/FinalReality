package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.character.ICharacter;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 * @version 1.02
 * @since 1.0
 */
public class PlayerCharacter extends AbstractCharacter {

  /**
   * Creates a new character.
   *
   * @param name
   *    the character's name
   * @param turnsQueue
   *    the queue with the characters waiting for their turn
   * @param characterClass
   *    the class of this character
   * @param maxHP
   *    Character's maximum HP
   * @param defense
   *    Character's defense
   * @param maxMana
   *    Character's maximum mana
   * @see CharacterClass
   * @since 1.0
   */
  public PlayerCharacter(@NotNull String name,
                         @NotNull BlockingQueue<ICharacter> turnsQueue,
                         final CharacterClass characterClass,
                         int maxHP, int defense, int maxMana) {
    super(turnsQueue, name, characterClass, maxHP, defense, maxMana);
  }

  /**
   * Hash method for testing purposes
   *
   * @return hash(characterClass)
   * @since 1.0
   */
  @Override
  public int hashCode() {
    return Objects.hash(getCharacterClass());
  }

  /**
   * Compares two PlayerCharacter objects for testing purposes
   *
   * @param o
   *    object to compare with
   * @return true if equals, false otherwise (does not count current HP and mana)
   * @since 1.0
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PlayerCharacter)) {
      return false;
    }
    final PlayerCharacter that = (PlayerCharacter) o;
    return getCharacterClass() == that.getCharacterClass()
        && getName().equals(that.getName())
        && getMaxHP() == that.getMaxHP()
        && getDefense() == that.getDefense()
        && getMaxMana() == that.getMaxMana();
  }
}
