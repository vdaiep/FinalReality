package com.github.cc3002.finalreality.model.character;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public abstract class AbstractCharacter implements ICharacter {

  protected final BlockingQueue<ICharacter> turnsQueue;
  protected final String name;
  protected ScheduledExecutorService scheduledExecutor;
  protected int HP;
  protected final int maxHP;
  protected final int defense;

  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name, int maxHP, int defense) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.maxHP = maxHP;
    this.HP = maxHP;
    this.defense = defense;
  }

  /**
   * Adds this character to the turns queue.
   */
  protected void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  @Override
  public int hashCode() {
    return Objects.hash(AbstractCharacter.class, getName());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractCharacter)) {
      return false;
    }
    final AbstractCharacter that = (AbstractCharacter) o;
    return getName().equals(that.getName());
  }

  @Override
  public void getAttacked(int rawDamage){
    int damage = Math.max(0, rawDamage - this.defense);
    this.HP = Math.max(0, this.getHP() - damage);
  }

  @Override
  public String getName() {
    return name;
  }

  public int getHP(){
    return HP;
  }

  public int getMaxHP(){
    return maxHP;
  }

  public int getDefense() {
    return defense;
  }

}
