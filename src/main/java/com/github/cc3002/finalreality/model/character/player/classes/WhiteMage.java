package com.github.cc3002.finalreality.model.character.player.classes;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacter;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * @author Ignacio Slater Muñoz.
 */
public class WhiteMage extends AbstractPlayerCharacter {

    protected int mana;

    protected final int maxMana;

    /**
     * Creates a new character.
     *
     * @param name
     *     the character's name
     */
    public WhiteMage(@NotNull final String name, @NotNull final BlockingQueue<ICharacter> turnsQueue,
                     int maxHP, int defense, int maxMana) {
        super(name, turnsQueue, maxHP, defense);
        this.maxMana = maxMana;
        this.mana = maxMana;
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof WhiteMage && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), WhiteMage.class);
    }
}
