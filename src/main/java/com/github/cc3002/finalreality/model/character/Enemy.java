package com.github.cc3002.finalreality.model.character;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import com.github.cc3002.finalreality.DeathHandler;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author Vicente Daie Pinilla.
 *
 * @version 1.04
 * @since 1.0
 */
public class Enemy extends AbstractCharacter {

  /**
   * List of enemies tied to the controller.
   */
  protected final ArrayList<Enemy> list;

  /**
   * Weight (speed indicator) of this enemy.
   */
  private final int weight;

  /**
   * Damage that deal this Enemy attacks.
   */
  private final int attackDamage;

  /**
   * Creates a new enemy with a name, a weight and an associated turns queue.
   *
   * @param name
   *   'name' parameter.
   * @param turnsQueue
   *    associated turns queue.
   * @param maxHP
   *    'maxHP' parameter.
   * @param defense
   *    'defense' parameter.
   * @param weight
   *    'weight' parameter.
   * @param attackDamage
   *    'attackDamage' parameter.
   * @since 1.0
   */
  public Enemy(@NotNull final String name, @NotNull final BlockingQueue<ICharacter> turnsQueue,
               int maxHP, int defense, int weight, int attackDamage, ArrayList<Enemy> aList) {
    super(turnsQueue, name, maxHP, defense);
    this.weight = weight;
    this.attackDamage = attackDamage;
    this.list = aList;
  }

  /**
   * Adds a listener which notifies the controller when this Enemy has died.
   *
   * @param handler
   *    the listener
   * @since 1.04
   */
  public void addListener(DeathHandler handler) {
    deathEvent.addPropertyChangeListener("alive", handler);
  }

  /**
   * Attacks another character, dealing 'attackDamage' damage.
   *
   * @param that
   *    Character to be attacked.
   * @since 1.02
   */
  public void attack(ICharacter that){
    if(that.isAlive()){
      that.getAttacked(this.getAttackDamage());
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
      this.list.remove(this);
      this.alive = false;
      this.deathEvent.firePropertyChange("alive", true, false);
    }
  }

  /**
   * Gets the weight of the Enemy.
   *
   * @return 'weight' parameter
   * @since 1.0
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Gets the physical damage of the Enemy.
   *
   * @return 'attackDamage' parameter
   * @since 1.0
   */
  public int getAttackDamage(){
    return attackDamage;
  }


  /**
   * Sets a scheduled executor to make this Enemy (thread) wait for {@code weight / 10}
   * seconds before adding the enemy to the queue.
   *
   * @since 1.0
   */
  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor
        .schedule(this::addToQueue, this.getWeight() / 10, TimeUnit.SECONDS);
  }

  /**
   * Compares two Enemy objects for testing purposes.
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
    Enemy enemy = (Enemy) o;
    return getWeight() == enemy.getWeight() &&
            getAttackDamage() == enemy.getAttackDamage();
  }

  /**
   * Hashes the Enemy parameters for testing purposes.
   *
   * @return hash(super.hash(), weight, attackDamage)
   * @since 1.0
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getWeight(), getAttackDamage());
  }
}
