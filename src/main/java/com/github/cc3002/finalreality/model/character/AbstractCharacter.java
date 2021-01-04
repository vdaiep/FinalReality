package com.github.cc3002.finalreality.model.character;

import java.beans.PropertyChangeSupport;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 *
 * @version 1.05
 * @since 1.0
 */
public abstract class AbstractCharacter implements ICharacter {

  /**
   * Handles the situation when this character dies (HP = 0)
   */
  protected final PropertyChangeSupport deathEvent = new PropertyChangeSupport(this);

  /**
   * Queue with all characters waiting for their turn.
   */
  protected final BlockingQueue<ICharacter> turnsQueue;

  /**
   * Name of this character.
   */
  protected final String name;

  /**
   * Handles this characters turn.
   */
  protected ScheduledExecutorService scheduledExecutor;

  /**
   * Current HP of this character.
   */
  protected int HP;

  /**
   * Maximum HP of this character.
   */
  protected final int maxHP;

  /**
   * Defense of this character.
   */
  protected final int defense;

  /**
   * Checks if this character has 0 HP.
   */
  protected boolean alive;

  /**
   * Creates a Character with a name, maximum HP and defense, and an associated turns queue.
   *
   * @param turnsQueue associated turns queue.
   * @param name       'name' parameter.
   * @param maxHP      'maxHP' parameter.
   * @param defense    'defense' parameter.
   * @since 1.0
   */
  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                              @NotNull String name, int maxHP, int defense) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.maxHP = maxHP;
    this.HP = maxHP;
    this.defense = defense;
    this.alive = true;
    this.scheduledExecutor = Executors.newScheduledThreadPool(1);
  }

  /**
   * Adds this character to the turns queue.
   *
   * @since 1.0
   */
  public void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  /**
   * Gets the name of the character.
   *
   * @return 'name' parameter
   * @since 1.0
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Gets the current HP of the character.
   *
   * @return 'HP' parameter
   * @since 1.0
   */
  public int getHP() {
    return HP;
  }

  /**
   * Gets the maximum HP of the character.
   *
   * @return 'maxHP' parameter
   * @since 1.0
   */
  public int getMaxHP() {
    return maxHP;
  }

  /**
   * Gets the defense of the character.
   *
   * @return 'name' parameter
   * @since 1.0
   */
  public int getDefense() {
    return defense;
  }

  /**
   * Gets the living condition of the character.
   *
   * @return 'alive' parameter
   * @since 1.0
   */
  public boolean isAlive(){
    return alive;
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
    AbstractCharacter that = (AbstractCharacter) o;
    return getHP() == that.getHP() &&
            getMaxHP() == that.getMaxHP() &&
            getDefense() == that.getDefense() &&
            Objects.equals(turnsQueue, that.turnsQueue) &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(alive, that.alive);
  }

  /**
   * Hashes the Character parameters for testing purposes.
   *
   * @return hash(turnsQueue, name, scheduledExecutor, HP, maxHP, defense)
   * @since 1.0
   */
  @Override
  public int hashCode() {
    return Objects.hash(turnsQueue, getName(), getHP(), getMaxHP(), getDefense(), alive);
  }
}
