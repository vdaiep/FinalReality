package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.CharacterClass;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author Vicente Daie Pinilla
 * @version 1.02
 * @since 1.0
 */
public class Enemy extends AbstractCharacter {

  /**
   * Weight (speed indicator) of this Enemy
   */
  private final int weight;

  /**
   * How much damage this Enemy's attacks deal
   */
  private final int attackDamage;

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
   * @param maxHP
   *    Maximum HP of this Enemy
   * @param defense
   *    Defense of this Enemy
   *
   * @since 1.0
   */
  public Enemy(@NotNull final String name, final int weight, final int attackDamage,
      @NotNull final BlockingQueue<ICharacter> turnsQueue, int maxHP,
               int defense) {
    super(turnsQueue, name, CharacterClass.ENEMY, maxHP, defense, 0);
    this.weight = weight;
    this.attackDamage = attackDamage;
  }

  /**
   * Returns the weight of this enemy.
   *
   * @return 'weight' parameter
   * @since 1.0
   */
  public int getWeight() {
    return this.weight;
  }

  /**
   * Returns the damage this Enemy's attacks deal
   *
   * @return 'attackDamage' parameter
   */
  public int getAttackDamage() {
    return this.attackDamage;
  }

  /**
   * Attacks another Character
   *
   * @param that
   *    Character to be attacked.
   * @return   0 if successful,
   *          -1 if failed because 'this' is not alive,
   *          -2 if failed because 'that' is not alive,
   *          -9 if successful but couldn't attack due to paralysis.
   * @since 1.01
   */
  public int attack(AbstractCharacter that){
    if(!this.isAlive()){
      return -1;
    }
    if(!that.isAlive()){
      return -2;
    }
    if(this.isParalyzed()){
      this.getPurified(false, true, false);
      return -9;
    }
    int damage = this.getAttackDamage();
    that.receiveDamage(damage, false);
    return 0;
  }

  /**
   * Compares two Enemy objects for testing purposes
   *
   * @param o
   *    object to compare with
   * @return true if equals, false otherwise (does not count current HP and mana)
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
    final Enemy that = (Enemy) o;
    return getWeight() == that.getWeight()
            && getCharacterClass() == that.getCharacterClass()
            && getName().equals(that.getName())
            && getMaxHP() == that.getMaxHP()
            && getDefense() == that.getDefense()
            && getMaxMana() == that.getMaxMana();
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
