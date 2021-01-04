package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.DeathHandler;
import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 *
 * @version 1.05
 * @since 1.03
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements
    IPlayerCharacter {

  /**
   * Current weapon equipped by the character, null if none.
   */
  protected IWeapon equippedWeapon;

  /**
   * List of characters tied to the controller.
   */
  protected final ArrayList<IPlayerCharacter> list;

  /**
   * Creates a new character with a name, maximum HP, defense, and an associated turns queue.
   *
   * @param name
   *     the character's name
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   * @param maxHP
   *    this character's maximum HP.
   * @param defense
   *    this character's defense.
   * @since 1.03
   */
  public AbstractPlayerCharacter(@NotNull String name,
      @NotNull BlockingQueue<ICharacter> turnsQueue, int maxHP, int defense, ArrayList<IPlayerCharacter> aList) {
    super(turnsQueue, name, maxHP, defense);
    equippedWeapon = null;
    list = aList;
  }

  /**
   * Adds a listener which notifies the controller when this Character has died.
   *
   * @param handler
   *    the listener
   * @since 1.04
   */
  public void addListener(DeathHandler handler) {
    deathEvent.addPropertyChangeListener("alive", handler);
  }

  /**
   * Checks if this object is an Enemy
   *
   * @return false
   * @since 1.05
   */
  public boolean isEnemy(){
    return false;
  }

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code weapon weight / 10}
   * seconds before adding the character to the queue.
   *
   * @since 1.0
   */
  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    int delay = equippedWeapon.getWeight();
    scheduledExecutor.schedule(this::addToQueue, delay / 10, TimeUnit.SECONDS);
  }

  /**
   * Unequips the character's weapon.
   *
   * @since 1.0
   */
  @Override
  public void unequip(){
    if(Objects.nonNull(this.getEquippedWeapon())){
      this.getEquippedWeapon().beUnequipped();
      this.equippedWeapon = null;
    }
  }

  /**
   * Receives damage when attacked by another Character or Enemy.
   *
   * @param rawDamage Damage received.
   * @since 1.02
   */
  @Override
  public void getAttacked(int rawDamage) {
    int damage = Math.max(0, rawDamage - this.defense);
    this.HP = Math.max(0, this.getHP() - damage);
    if (this.getHP() == 0) {
      this.unequip();
      this.alive = false;
      this.deathEvent.firePropertyChange("alive", true, false);
    }
  }

  /**
   * Gets this character's equipped weapon.
   *
   * @return 'equippedWeapon' parameter.
   * @since 1.0
   */
  @Override
  public IWeapon getEquippedWeapon() {
    return equippedWeapon;
  }

  /**
   * Attacks another character, dealing 'attackDamage' from its equipped weapon damage if PlayerCharacter.
   *
   * @param that
   *    Character to be attacked.
   * @since 1.02
   */
  public void attack(ICharacter that){
    if(that.isAlive()){
      that.getAttacked(this.getEquippedWeapon().getDamage());
    }
  }

  /**
   * Compares two Character objects for testing purposes.
   *
   * @param o
   *    object to compare with
   * @return true if equals, false otherwise.
   * @since 1.0
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    AbstractPlayerCharacter that = (AbstractPlayerCharacter) o;
    return Objects.equals(getEquippedWeapon(), that.getEquippedWeapon());
  }

  /**
   * Hashes the Character parameters for testing purposes.
   *
   * @return hash(super.hash(), equippedWeapon)
   * @since 1.0
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getEquippedWeapon());
  }
}
