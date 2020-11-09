package com.github.cc3002.finalreality.model.character;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 */
public class Enemy extends AbstractCharacter {

  private final int weight;
  private final int attackDamage;

  /**
   * Creates a new enemy with a name, a weight and the queue with the characters ready to
   * play.
   */
  public Enemy(@NotNull final String name, @NotNull final BlockingQueue<ICharacter> turnsQueue,
               int maxHP, int defense, int weight, int attackDamage) {
    super(turnsQueue, name, maxHP, defense);
    this.weight = weight;
    this.attackDamage = attackDamage;
  }

  public void attack(ICharacter that){
    that.getAttacked(this.getAttackDamage());
  }

  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() {
    return weight;
  }

  public int getAttackDamage(){
    return attackDamage;
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor
        .schedule(this::addToQueue, this.getWeight() / 10, TimeUnit.SECONDS);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getWeight());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy enemy = (Enemy) o;
    return getWeight() == enemy.getWeight();
  }
}
