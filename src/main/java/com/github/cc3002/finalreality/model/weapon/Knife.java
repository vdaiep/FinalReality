package com.github.cc3002.finalreality.model.weapon;

import java.util.Objects;

/**
 * @author Ignacio Slater Muñoz.
 */
public class Knife extends AbstractWeapon {

    /**
     * Creates a weapon with a name, a base damage, speed.
     */
    public Knife(final String name, final int damage, final int weight) {
        super(name, damage, weight);
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof Knife && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Knife.class);
    }
}
