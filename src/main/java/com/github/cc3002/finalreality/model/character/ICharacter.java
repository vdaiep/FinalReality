package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.CharacterClass;
import com.github.cc3002.finalreality.model.weapon.SpellClass;
import com.github.cc3002.finalreality.model.weapon.Weapon;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author Vicente Daie Pinilla.
 * @version 1.02
 * @since 1.0
 */
public interface ICharacter {

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   *
   * @since 1.0
   */
  void waitTurn();

  /**
   * Receives damage from another Character
   *
   * @param damage
   *    amount of damage to be received
   * @since 1.02
   */
  void receiveDamage(int damage, boolean true_damage);

  /**
   * Receives healing from another Character
   *
   * @param healing
   *    amount of healing to be received
   * @since 1.02
   */
  void receiveHealing(int healing);

  /**
   * Spends this Character mana
   *
   * @param mana
   *    mana to be spent
   * @since 1.02
   */
  void spendMana(int mana);

  /**
   * Refills this Character's mana
   *
   * @since 1.02
   */
  void refillMana(int mana);

  /**
   * Poisons this Character
   *
   * @param poisonDamage strength of the poisoning
   * @since 1.0
   */
  void getPoisoned(int poisonDamage);

  /**
   * Paralyzes this Character
   *
   * @since 1.01
   */
  void getParalyzed();

  /**
   * Burns this Character
   *
   * @param burnDamage strength of the burning
   * @since 1.01
   */
  void getBurnt(int burnDamage);

  /**
   * Clears adverse effects on this Character
   *
   * @param poison
   *    clear poison?
   * @param paralysis
   *    clear paralysis?
   * @param burn
   *    clear burn?
   */
  void getPurified(boolean poison, boolean paralysis, boolean burn);

  /**
   * Applies burn and poison effects.
   *
   * @since 1.01
   */
  void applyStatusDamage();

  /**
   * Attacks another Character
   *
   * @param that Character to be attacked.
   * @return 0 if successful,
   * -1 if failed because 'this' is not alive,
   * -2 if failed because 'that' is not alive,
   * -3 if failed because 'this' has no equipped weapon,
   * -9 if successful but couldn't attack due to paralysis.
   * @since 1.01
   */
  int attack(AbstractCharacter that);

  /**
   * Casts a spell on another Character
   *
   * @param that Character to cast the spell on.
   * @return 0 if successful,
   * -1 if failed because 'this' has 0 HP,
   * -2 if failed because 'that' has 0 HP,
   * -3 if failed because 'this' has no equipped weapon,
   * -4 if failed because 'this' is not a mage,
   * -5 if failed because 'this' has equipped a non-magical weapon,
   * -6 if failed because 'this' kind of mage can not use this spell,
   * -7 if failed because 'this' has not enough mana to cast this spell,
   * -9 if successful but couldn't use spell due to paralysis.
   * @since 1.01
   */
  int castSpell(AbstractCharacter that, SpellClass spell);

  /**
   * Equips a weapon to the character.
   *
   * @since 1.01
   */
  int equip(Weapon weapon);

  /**
   * Removes this Character's equipped Weapon.
   *
   * @since 1.02
   */
  void unequip();

  /**
   * Return this character's equipped weapon.
   *
   * @since 1.0
   */
  Weapon getEquippedWeapon();

  /**
   * Returns this character's name.
   *
   * @since 1.0
   */
  String getName();

  /**
   * Returns this character's class.
   *
   * @since 1.0
   */
  CharacterClass getCharacterClass();

  /**
   * Returns this character's maximum HP.
   *
   * @since 1.0
   */
  int getMaxHP();

  /**
   * Returns this character's current HP.
   *
   * @since 1.0
   */
  int getHP();

  /**
   * Returns this character's defense.
   *
   * @since 1.0
   */
  int getDefense();

  /**
   * Returns this character's maximum mana.
   *
   * @since 1.0
   */
  int getMaxMana();

  /**
   * Returns this character's current mana.
   *
   * @since 1.0
   */
  int getMana();

  /**
   * Checks if Character is alive
   *
   * @return 'alive' parameter
   * @since 1.02
   */
  boolean isAlive();

  /**
   * Checks if Character is poisoned
   *
   * @return 'poisoned' parameter
   * @since 1.02
   */
  boolean isPoisoned();

  /**
   * Gets the current poisoning damage this Character is receiving
   *
   * @return 'poisonDamage' parameter
   * @since 1.02
   */
  int getPoisonDamage();

  /**
   * Checks if Character is paralyzed
   *
   * @return 'paralyzed' parameter
   * @since 1.02
   */
  boolean isParalyzed();

  /**
   * Checks if Character is burnt
   *
   * @return 'burnt' parameter
   * @since 1.02
   */
  boolean isBurned();

  /**
   * Gets the current burning damage this Character is receiving
   *
   * @return 'poisonDamage' parameter
   * @since 1.02
   */
  int getBurnDamage();

}