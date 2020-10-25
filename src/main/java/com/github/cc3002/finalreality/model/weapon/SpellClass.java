package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.player.CharacterClass;

/**
 * Enumeration of all the spell types.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 * @version 1.01
 * @since 1.01
 */
public enum SpellClass {
    THUNDER (CharacterClass.BLACK_MAGE, 15, 1, 0, 0, 0.3, 0),
    FIRE (CharacterClass.BLACK_MAGE, 15, 1, 0, 0, 0, 0.2),
    HEAL (CharacterClass.WHITE_MAGE, 15,0, 0.3, 0, 0, 0),
    POISON (CharacterClass.WHITE_MAGE, 40, 0, 0, 1,0,0),
    PARALYZE (CharacterClass.WHITE_MAGE, 25,0,0,0,1,0);

    /**
     * Indicates what kind of mages can use this spell
     */
    public final CharacterClass type;

    /**
     * Indicates how much mana does this spell cost
     */
    public final int manaCost;

    /**
     * Indicates how much damage deals this spell
     */
    public final double damageRatio;

    /**
     * Indicates how much healing applies this spell
     */
    public final double healRatio;

    /**
     * Indicates what is the probability for this spell to poison the enemy
     */
    public final double poisonProbability;

    /**
     * Indicates what is the probability for this spell to paralyze the enemy
     */
    public final double paralysisProbability;

    /**
     * Indicates what is the probability for this spell to burn the enemy
     */
    public final double burnProbability;

    /**
     * Incorporates parameters to each SpellClass
     *
     * @param type
     *    'type' parameter
     * @param damageRatio
     *    'damageRatio' parameter
     * @param healRatio
     *    'healRatio' parameter
     * @param poisonProbability
     *    'poisonProbability' parameter
     * @param paralysisProbability
     *    'paralysisProbability' parameter
     * @param burnProbability
     *    'burnProbability' parameter
     * @since 1.01
     */
    SpellClass(CharacterClass type, int manaCost, double damageRatio, double healRatio, double poisonProbability,
               double paralysisProbability, double burnProbability){
        this.type = type;
        this.manaCost = manaCost;
        this.damageRatio = damageRatio;
        this.healRatio = healRatio;
        this.poisonProbability = poisonProbability;
        this.paralysisProbability = paralysisProbability;
        this.burnProbability = burnProbability;
    }
}

