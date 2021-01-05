package com.github.cc3002.finalreality;

/**
 * Common interface with the phases of a turn.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 *
 * @version 1.05
 * @since 1.05
 */
public interface IPhase {

    /**
     * Checks if this phase is an equipment phase.
     *
     * @return true if it is, false otherwise
     * @since 1.05
     */
    boolean isEquipmentPhase();

    /**
     * Sets the weapon to be equipped.
     *
     * @param currentWeapon
     *    index of the weapon to be equipped
     * @since 1.05
     */
    void setIndexWeapon(int currentWeapon);

    /**
     * Sets the enemy to be attacked.
     *
     * @param currentEnemy
     *    index of the enemy to be attacked
     * @since 1.05
     */
    void setIndexEnemy(int currentEnemy);

    /**
     * Executes this phase.
     *
     * @since 1.05
     */
    void doPhase();

    /**
     * Ends this phase.
     *
     * @since 1.05
     */
    void endPhase();
}
