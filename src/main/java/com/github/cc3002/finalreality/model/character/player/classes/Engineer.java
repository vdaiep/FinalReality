package com.github.islaterm.finalreality.model.character.player.classes;

import com.github.islaterm.finalreality.model.character.ICharacter;
import com.github.islaterm.finalreality.model.character.player.AbstractPlayerCharacter;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * @author Ignacio Slater Muñoz.
 */
public class Engineer extends AbstractPlayerCharacter {

  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name
   */
  public Engineer(@NotNull final String name,
      @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(name, turnsQueue);
  }
}
