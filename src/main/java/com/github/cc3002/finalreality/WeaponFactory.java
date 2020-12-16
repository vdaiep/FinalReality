package com.github.cc3002.finalreality;

import com.github.cc3002.finalreality.model.weapon.*;

/**
 * A class that deals with the creation of Weapons, and holds their default attribute values.
 *
 * @author Ignacio Slater Muñoz
 * @author Vicente Daie Pinilla.
 *
 * @version 1.04
 * @since 1.04
 */
public class WeaponFactory {

    /**
     * Default values for each weapon class.
     */
    private final int SWORD_AD = 100;
    private final int SWORD_WEIGHT = 40;
    private final int AXE_AD = 150;
    private final int AXE_WEIGHT = 70;
    private final int KNIFE_AD = 70;
    private final int KNIFE_WEIGHT = 25;
    private final int STAFF_AD = 50;
    private final int STAFF_WEIGHT = 40;
    private final int STAFF_AP = 50;
    private final int BOW_AD = 90;
    private final int BOW_WEIGHT = 35;

    /**
     * Creates a new factory.
     *
     * @since 1.04
     */
    public WeaponFactory(){
    }

    /**
     * Creates a new Sword.
     *
     * @param name
     *    this Weapon's name
     * @since 1.04
     */
    public Sword createSword(String name){
        return new Sword(name, SWORD_AD, SWORD_WEIGHT);
    }

    /**
     * Creates a new Axe.
     *
     * @param name
     *    this Weapon's name
     * @since 1.04
     */
    public Axe createAxe(String name){
        return new Axe(name, AXE_AD, AXE_WEIGHT);
    }

    /**
     * Creates a new Knife.
     *
     * @param name
     *    this Weapon's name
     * @since 1.04
     */
    public Knife createKnife(String name){
        return new Knife(name, KNIFE_AD, KNIFE_WEIGHT);
    }

    /**
     * Creates a new Staff.
     *
     * @param name
     *    this Weapon's name
     * @since 1.04
     */
    public Staff createStaff(String name){
        return new Staff(name, STAFF_AD, STAFF_WEIGHT, STAFF_AP);
    }

    /**
     * Creates a new Bow.
     *
     * @param name
     *    this Weapon's name
     * @since 1.04
     */
    public Bow createBow(String name){
        return new Bow(name, BOW_AD, BOW_WEIGHT);
    }

}
