package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.player.CharacterClass;
import com.github.cc3002.finalreality.model.character.player.PlayerCharacter;
import com.github.cc3002.finalreality.model.weapon.SpellClass;
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
 * @version 1.02
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
   * Character's maximum HP
   */
  private final int maxHP;

  /**
   * Character's current HP
   */
  private int HP;

  /**
   * Character's maximum mana (set to 0 if not mage)
   */
  private final int maxMana;

  /**
   * Character's current mana (set to 0 if not mage)
   */
  private int mana;

  /**
   * Character's defense
   */
  private final int defense;

  /**
   * Current equipped Weapon by this Character
   */
  private Weapon equippedWeapon = null;

  /**
   * Character's current state of poisoning
   */
  private boolean poisoned = false;

  /**
   * Character's current state of paralysis
   */
  private boolean paralyzed = false;

  /**
   * Character's current state of burning
   */
  private boolean burnt = false;

  /**
   * How much damage does this Character take if poisoned
   */
  private int poisonDamage = 0;

  /**
   * How much damage does this Character take if burnt
   */
  private int burnDamage = 0;

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
   * @param maxHP
   *    Character's maximum HP
   * @param defense
   *    Character's defense
   * @param maxMana
   *    Character's maximum mana
   * @since 1.0
   */
  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name, CharacterClass characterClass, int maxHP, int defense, int maxMana) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.characterClass = characterClass;
    this.maxHP = maxHP;
    this.HP = maxHP;
    this.defense = defense;
    if(characterClass.isMage) {
      this.maxMana = maxMana;
      this.mana = maxMana;
    }
    else{
      this.maxMana = 0;
      this.mana = 0;
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
   * Waits for this Character's turn
   *
   * @since 1.0
   */
  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    if (this instanceof PlayerCharacter) {
      scheduledExecutor
          .schedule(this::addToQueue, getEquippedWeapon().getWeight() / 10, TimeUnit.SECONDS);
    } else {
      var enemy = (Enemy) this;
      scheduledExecutor
          .schedule(this::addToQueue, enemy.getWeight() / 10, TimeUnit.SECONDS);
    }
  }

  /**
   * Receives damage from another Character
   *
   * @param damage
   *    amount of damage to be received
   * @since 1.02
   */
  @Override
  public void receiveDamage(int damage, boolean true_damage){
    int defense;
    if(true_damage){
      defense = 0;
    }
    else{
      defense = this.getDefense();
    }
    this.HP = Math.max(this.getHP() - Math.max(damage - defense, 0), 0);
  }

  /**
   * Receives healing from another Character
   *
   * @param healing
   *    amount of healing to be received
   * @since 1.02
   */
  @Override
  public void receiveHealing(int healing){
    this.HP = Math.min(this.getHP() + healing , this.getMaxHP());
  }

  /**
   * Spends this Character mana
   *
   * @param mana
   *    mana to be spent
   * @since 1.02
   */
  @Override
  public void spendMana(int mana){
    this.mana = Math.max(this.getMana() - mana, 0);
  }

  /**
   * Refills this Character's mana
   *
   * @since 1.02
   */
  @Override
  public void refillMana(int mana){
    this.mana = Math.min(this.getMana() + mana, this.getMaxMana());
  }

  /**
   * Poisons this Character
   *
   * @param poisonDamage
   *    strength of the poisoning
   * @since 1.01
   */
  @Override
  public void getPoisoned(int poisonDamage){
    if(poisonDamage > 0) {
      this.poisoned = true;
      this.poisonDamage = this.poisonDamage + poisonDamage/3;
    }
  }

  /**
   * Paralyzes this Character
   *
   * @since 1.01
   */
  @Override
  public void getParalyzed(){
    this.paralyzed = true;
  }

  /**
   * Burns this Character
   *
   * @param burnDamage
   *    strength of the burning
   * @since 1.01
   */
  @Override
  public void getBurnt(int burnDamage){
    if(burnDamage > 0) {
      this.burnt = true;
      this.burnDamage = this.burnDamage + burnDamage/2;
    }
  }

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
  @Override
  public void getPurified(boolean poison, boolean paralysis, boolean burn){
    if(poison){
      this.poisoned = false;
      this.poisonDamage = 0;
    }
    if(paralysis){
      this.paralyzed = false;
    }
    if(burn){
      this.burnt = false;
      this.burnDamage = 0;
    }
  }

  /**
   * Applies burn and poison effects.
   *
   * @since 1.01
   */
  @Override
  public void applyStatusDamage() {
    if (this.isBurned()) {
      this.receiveDamage(this.getBurnDamage(), true);
    }
    if (this.isPoisoned()) {
      this.receiveDamage(this.getPoisonDamage(), true);
    }
  }

  /**
   * Attacks another Character
   *
   * @param that
   *    Character to be attacked
   * @return   0 if successful
   *          -1 if failed because 'this' is not alive
   *          -2 if failed because 'that' is not alive
   *          -4 if failed because 'this' has no equipped weapon
   *          -9 if successful but couldn't attack due to paralysis
   *          -10 if failed because 'this' is attacking itself
   * @since 1.01
   */
  @Override
  public int attack(AbstractCharacter that){
    if(!this.isAlive()){
      return -1;
    }
    if(!that.isAlive()){
      return -2;
    }
    if(this.getEquippedWeapon() == null){
      return -4;
    }
    if(this.isParalyzed()){
      this.getPurified(false, true, false);
      return -9;
    }
    if(that.equals(this)){
      return -10;
    }
    that.receiveDamage(this.getEquippedWeapon().getDamage(), false);
    return 0;
  }

  /**
   * Casts a spell on another Character
   *
   * @param that
   *    Character to cast the spell on
   * @return   0 if successful
   *          -1 if failed because 'this' is not alive
   *          -2 if failed because 'that' is not alive
   *          -3 if failed because 'this' is an Enemy (should never happen)
   *          -4 if failed because 'this' has no equipped weapon
   *          -5 if failed because 'this' is not a mage
   *          -6 if failed because 'this' has equipped a non-magical weapon
   *          -7 if failed because 'this' kind of mage can not use this spell
   *          -8 if failed because 'this' has not enough mana to cast this spell
   *          -9 if successful but couldn't use spell due to paralysis
   *          -10 if failed because 'this' is casting the spell on itself
   * @since 1.01
   */
  @Override
  public int castSpell(AbstractCharacter that, SpellClass spell){
    if(!this.isAlive()){
      return -1;
    }
    if(!that.isAlive()){
      return -2;
    }
    if(this.getCharacterClass() == CharacterClass.ENEMY){
      return -3;
    }
    if(this.getEquippedWeapon() == null){
      return -4;
    }
    if(!this.getCharacterClass().isMage){
      return -5;
    }
    if(!this.getEquippedWeapon().getType().isMagical){
      return -6;
    }
    if(!this.getCharacterClass().equals(spell.type)){
      return -7;
    }
    if(this.getMana() < spell.manaCost){
      return -8;
    }
    if(this.isParalyzed()){
      this.getPurified(false, true, false);
      return -9;
    }
    if(that.equals(this)){
      return -10;
    }
    this.spendMana(spell.manaCost);
    int damage = this.getEquippedWeapon().getMagicDamage();
    int damageDealt = (int) Math.round(this.getEquippedWeapon().getMagicDamage()*spell.damageRatio);
    int healApplied = (int) Math.round(that.getMaxHP()*spell.healRatio);
    that.receiveDamage(damageDealt, true);
    that.receiveHealing(healApplied);
    double random = Math.random();
    if(random < spell.poisonProbability){
      that.getPoisoned(damage);
    }
    if(random < spell.paralysisProbability){
      that.getParalyzed();
    }
    if(random < spell.burnProbability){
      that.getBurnt(damage);
    }
    return 0;
  }

  /**
   * Equips a weapon to this Character, modifying the 'equippedWeapon' parameter
   *
   * @param weapon
   *    Weapon to equip
   * @return 0 if successful,
   *        -1 if failed due to Character being an Enemy,
   *        -2 if failed due to this Character being unable to equip this Weapon type
   * @since 1.0
   */
  @Override
  public int equip(Weapon weapon) {
    if (this instanceof PlayerCharacter) {
      if(weapon.getType().usage.get(this.getCharacterClass()) == Boolean.TRUE){
        this.equippedWeapon = weapon;
        return 0;
      }
      return -2;
    }
    return -1;
  }

  /**
   * Removes this Character's equipped Weapon
   *
   * @since 1.02
   */
  @Override
  public void unequip(){
    this.equippedWeapon = null;
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
   * Gets the class of this Character
   *
   * @return 'characterClass' parameter
   * @since 1.0
   */
  @Override
  public CharacterClass getCharacterClass() {
    return characterClass;
  }

  /**
   * Gets the maximum HP of this Character
   *
   * @return 'maxHP' parameter
   * @since 1.0
   */
  @Override
  public int getMaxHP() {
    return maxHP;
  }

  /**
   * Gets the current HP of this Character
   *
   * @return 'HP' parameter
   * @since 1.0
   */
  @Override
  public int getHP() {
    return HP;
  }

  /**
   * Gets the defense of this Character
   *
   * @return 'defense' parameter
   * @since 1.0
   */
  @Override
  public int getDefense() {
    return defense;
  }
  /**
   * Gets the maximum mana of this Character
   *
   * @return 'maxMana' parameter
   * @since 1.0
   */
  @Override
  public int getMaxMana() {
    return maxMana;
  }

  /**
   * Gets the current mana of this Character
   *
   * @return 'mana' parameter
   * @since 1.0
   */
  @Override
  public int getMana() {
    return mana;
  }

  /**
   * Checks if Character is alive
   *
   * @return true if HP > 0, false otherwise
   * @since 1.02
   */
  @Override
  public boolean isAlive(){
    return this.getHP() > 0;
  }

  /**
   * Checks if Character is poisoned
   *
   * @return 'poisoned' parameter
   * @since 1.02
   */
  @Override
  public boolean isPoisoned(){
    return this.poisoned;
  }

  /**
   * Gets the current poisoning damage this Character is receiving
   *
   * @return 'poisonDamage' parameter
   * @since 1.02
   */
  @Override
  public int getPoisonDamage(){
    return this.poisonDamage;
  }

  /**
   * Checks if Character is paralyzed
   *
   * @return 'paralyzed' parameter
   * @since 1.02
   */
  @Override
  public boolean isParalyzed(){
    return this.paralyzed;
  }

  /**
   * Checks if Character is burnt
   *
   * @return 'burnt' parameter
   * @since 1.02
   */
  @Override
  public boolean isBurned(){
    return this.burnt;
  }

  /**
   * Gets the current burning damage this Character is receiving
   *
   * @return 'poisonDamage' parameter
   * @since 1.02
   */
  @Override
  public int getBurnDamage(){
    return this.burnDamage;
  }

}