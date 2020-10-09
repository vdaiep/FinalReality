package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.CharacterClass;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author <Your name>
 * @version 1.0
 * @since 1.0
 */
public class Enemy extends AbstractCharacter {

  /**
   * Weight (speed indicator) of this Enemy
   */
  private final int weight;

  /**
   * Creates a new enemy with a name, a weight and the queue with the characters ready to
   * play.
   *
   * @param name
   *    name of this Enemy
   * @param weight
   *    weight of this Enemy
   * @param turnsQueue
   *    queue with the characters to play
   *
   * @since 1.0
   */
  public Enemy(@NotNull final String name, final int weight,
      @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(turnsQueue, name, CharacterClass.ENEMY);
    this.weight = weight;
  }

  /**
   * Returns the weight of this enemy.
   *
   * @return 'weight' parameter
   * @since 1.0
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Compares two Enemy objects for testing purposes
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
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy enemy = (Enemy) o;
    return getWeight() == enemy.getWeight();
  }

  /**
   * Hashes the Enemy parameters for testing purposes
   *
   * @return hash(weight)
   * @since 1.0
   */
  @Override
  public int hashCode() {
    return Objects.hash(getWeight());
  }
}
