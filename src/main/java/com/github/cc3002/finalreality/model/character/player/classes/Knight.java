package com.github.cc3002.finalreality.model.character.player.classes;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacter;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * @author Ignacio Slater Muñoz.
 */
public class Knight extends AbstractPlayerCharacter {

  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name
   */
  public Knight(@NotNull final String name, @NotNull final BlockingQueue<ICharacter> turnsQueue,
                int maxHP, int defense) {
    super(name, turnsQueue, maxHP, defense);
  }
  @Override
  public boolean equals(final Object o) {
    return o instanceof Knight && super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), Knight.class);
  }
}
