package com.github.cc3002.finalreality.model.weapon;

import java.util.Objects;

/**
 * @author Ignacio Slater Muñoz.
 */
public class Bow extends AbstractWeapon {

    /**
     * Creates a weapon with a name, a base damage, speed.
     */
    public Bow(final String name, final int damage, final int weight) {
        super(name, damage, weight);
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof Bow && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Bow.class);
    }
}
