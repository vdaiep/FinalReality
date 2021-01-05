package com.github.cc3002.finalreality.model.character.player.classes;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import com.github.cc3002.finalreality.model.character.player.IPlayerCharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 *
 * @version 1.05
 * @since 1.03
 */
public class Thief extends AbstractPlayerCharacter {

    /**
     * Creates a new Thief with a name, maximum HP, defense, maximum mana and an associated turns queue.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the turns queue.
     * @param maxHP
     *     this character's maximum HP.
     * @param defense
     *     this character's defense.
     * @since 1.03
     */
    public Thief(@NotNull final String name, @NotNull final BlockingQueue<ICharacter> turnsQueue,
                  int maxHP, int defense, ArrayList<IPlayerCharacter> aList) {
        super(name, turnsQueue, maxHP, defense, aList);
    }

    /**
     * Equips a weapon to the character, if compatible.
     *
     * @param weapon
     *    Weapon to be equipped.
     * @since 1.0
     */
    public void equip(IWeapon weapon) {
        if(weapon.beEquippedByThief()){
            if(Objects.isNull(weapon.getBearer())){
                if(Objects.nonNull(this.getEquippedWeapon())){
                    this.getEquippedWeapon().beUnequipped();
                    this.unequip();
                }
                weapon.beEquipped(this);
                this.equippedWeapon = weapon;
            }
        }
    }

    /**
     * Checks if this character can equip a weapon.
     *
     * @param weapon
     *    weapon to check if compatible
     * @return true if it can, false otherwise
     */
    @Override
    public boolean canEquip(IWeapon weapon){
        return weapon.beEquippedByThief();
    }

    /**
     * Checks if this character is a knight.
     *
     * @return true if it is, false otherwise
     * @since 1.05
     */
    @Override
    public boolean isKnight(){
        return false;
    }

    /**
     * Checks if this character is an engineer.
     *
     * @return true if it is, false otherwise
     * @since 1.05
     */
    @Override
    public boolean isEngineer(){
        return false;
    }

    /**
     * Checks if this character is a thief.
     *
     * @return true if it is, false otherwise
     * @since 1.05
     */
    @Override
    public boolean isThief(){
        return true;
    }

    /**
     * Checks if this character is a black mage.
     *
     * @return true if it is, false otherwise
     * @since 1.05
     */
    @Override
    public boolean isBlackMage(){
        return false;
    }

    /**
     * Checks if this character is a white mage.
     *
     * @return true if it is, false otherwise
     * @since 1.05
     */
    @Override
    public boolean isWhiteMage(){
        return false;
    }

    /**
     * Compares two Thief objects for testing purposes.
     *
     * @param o
     *    object to compare with
     * @return true if equals, false otherwise
     * @since 1.0
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        return true;
    }

    /**
     * Hashes the Thief parameters for testing purposes.
     *
     * @return hash(super.hash())
     * @since 1.0
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

}
