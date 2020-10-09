package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.CharacterClass;
import com.github.cc3002.finalreality.model.character.player.PlayerCharacter;
import com.github.cc3002.finalreality.model.weapon.Weapon;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractCharacter implements ICharacter {

  /**
   * Handles this Character's turn
   */
  protected final BlockingQueue<ICharacter> turnsQueue;
  /**
   * Character's name
   */
  protected final String name;
  /**
   * Character's class
   */
  private final CharacterClass characterClass;
  /**
   * Current equipped Weapon by this Character
   */
  private Weapon equippedWeapon = null;
  /**
   * Handles this Character's turn
   */
  private ScheduledExecutorService scheduledExecutor;

  /**
   * Creates an AbstractCharacter
   * @param turnsQueue
   *    Character's turn
   * @param name
   *    Character's name
   * @param characterClass
   *    Character's class
   * @since 1.0
   */
  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name, CharacterClass characterClass) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.characterClass = characterClass;
  }

  /**
   * Waits for this Character's turn
   *
   * @since 1.0
   */
  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    if (this instanceof PlayerCharacter) {
      scheduledExecutor
          .schedule(this::addToQueue, equippedWeapon.getWeight() / 10, TimeUnit.SECONDS);
    } else {
      var enemy = (Enemy) this;
      scheduledExecutor
          .schedule(this::addToQueue, enemy.getWeight() / 10, TimeUnit.SECONDS);
    }
  }

  /**
   * Adds this character to the turns queue.
   *
   * @since 1.0
   */
  private void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  /**
   * Gets the name of this Character
   *
   * @return 'name' parameter
   * @since 1.0
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Equips a weapon to this Character, modifying the 'equippedWeapon' parameter
   *
   * @param weapon
   *    Weapon to equip
   * @since 1.0
   */
  @Override
  public void equip(Weapon weapon) {
    if (this instanceof PlayerCharacter) {
      this.equippedWeapon = weapon;
    }
  }

  /**
   * Gets the equipped Weapon of this Character
   *
   * @return 'equippedWeapon' parameter
   * @since 1.0
   */
  @Override
  public Weapon getEquippedWeapon() {
    return equippedWeapon;
  }

  /**
   * Gets the class of this Character
   *
   * @return 'characterClass' parameter
   * @since 1.0
   */
  @Override
  public CharacterClass getCharacterClass() {
    return characterClass;
  }
}
