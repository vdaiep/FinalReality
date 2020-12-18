package com.github.cc3002.finalreality;

import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.IPlayerCharacter;
import com.github.cc3002.finalreality.model.character.player.classes.*;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.Staff;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The main controller, a class that links the user with the model.
 *
 * @author Ignacio Slater Muñoz
 * @author Vicente Daie Pinilla.
 *
 * @version 1.04
 * @since 1.04
 */
public class Controller {

    /**
     * Current turn phase.
     */
    private IPhase currentPhase;

    /**
     * Array with all the weapons the player has on its inventory.
     */
    private final ArrayList<IWeapon> inventory;

    /**
     * Array with all the characters the player has on its party.
     */
    private final ArrayList<IPlayerCharacter> characters;

    /**
     * Array with all the enemies controlled by the CPU.
     */
    private final ArrayList<Enemy> enemies;

    /**
     * Helper class that deals with the creation of enemies and player characters.
     */
    private final CharacterFactory characterFactory;

    /**
     * Helper class that deals with the creation of weapons.
     */
    private final WeaponFactory weaponFactory;

    /**
     * The turns queue containing all characters and enemies turns in order.
     */
    private final BlockingQueue<ICharacter> turnsQueue;

    /**
     * Helper class that deals with notifications of characters or enemies deaths.
     */
    private final DeathHandler deathHandler;

    /**
     * Indicator of the game status:
     * 0  : ongoing game
     * 1  : victory for the player
     * -1 : defeat for the player
     */
    private int gameStatus;

    /**
     * Creates the controller
     *
     * @since 1.04
     */
    public Controller(){
        inventory = new ArrayList<>();
        characters = new ArrayList<>();
        enemies = new ArrayList<>();
        characterFactory = new CharacterFactory();
        weaponFactory = new WeaponFactory();
        turnsQueue = new LinkedBlockingQueue<>();
        deathHandler = new DeathHandler(this);
        gameStatus = 0;
    }

    public void setCurrentPhase(IPhase phase){
        currentPhase = phase;
        currentPhase.doPhase();
    }

    /**
     * Method triggered by the listeners when a character dies. It checks if the game has come to an end.
     *
     * @since 1.04
     */
    public void onDeath(){
        if(characters.isEmpty()){
            defeat();
        }
        if(enemies.isEmpty()){
            victory();
        }
    }

    /**
     * Method that declares a defeat. To be completed on later releases.
     *
     * @since 1.04
     */
    public void defeat(){
        gameStatus = -1;
    }

    /**
     * Method that declares a victory. To be completed on later releases.
     *
     * @since 1.04
     */
    public void victory(){
        gameStatus = 1;
    }

    /**
     * Method that deals with enemies' turns. To be completed on later releases.
     *
     * @param playingEnemy
     *    enemy with the current turn.
     * @since 1.04
     */
    public void enemyTurn(Enemy playingEnemy){
        currentPhase = new AttackPhase(this, playingEnemy);
        currentPhase.doPhase();
    }

    /**
     * Method that deals with player characters' turns. To be completed on later releases.
     *
     * @param playingPlayerCharacter
     *    character with the current turn.
     * @since 1.04
     */
    public void playerTurn(IPlayerCharacter playingPlayerCharacter){
        currentPhase = new EquipmentPhase(this, playingPlayerCharacter);
        currentPhase.doPhase();
    }

    /**
     * Method that deals with turns, checks if the next turn is for an enemy or a player character and redirects to
     * the corresponding method.
     *
     * @since 1.04
     */
    public void turn(){
        try {
            ICharacter playingCharacter = turnsQueue.take();
            if (enemies.contains(playingCharacter)) {
                enemyTurn((Enemy) playingCharacter);
            }
            if (characters.contains(playingCharacter)) {
                playerTurn((IPlayerCharacter) playingCharacter);
            }
        }
        catch (InterruptedException i){
            turn();
        }
    }

    /**
     * Creates a new Knight on the character factory.
     *
     * @param name
     *    this Character's name
     * @since 1.04
     */
    public void createKnight(String name){
        IPlayerCharacter knight = characterFactory.createKnight(name, turnsQueue, characters);
        knight.addListener(deathHandler);
        knight.addToQueue();
        characters.add(knight);
    }

    /**
     * Creates a new Engineer on the character factory.
     *
     * @param name
     *    this Character's name
     * @since 1.04
     */
    public void createEngineer(String name){
        IPlayerCharacter engineer = characterFactory.createEngineer(name, turnsQueue, characters);
        engineer.addListener(deathHandler);
        engineer.addToQueue();
        characters.add(engineer);
    }

    /**
     * Creates a new Thief on the character factory.
     *
     * @param name
     *    this Character's name
     * @since 1.04
     */
    public void createThief(String name){
        IPlayerCharacter thief = characterFactory.createThief(name, turnsQueue, characters);
        thief.addListener(deathHandler);
        thief.addToQueue();
        characters.add(thief);
    }

    /**
     * Creates a new Black Mage on the character factory.
     *
     * @param name
     *    this Character's name
     * @since 1.04
     */
    public void createBlackMage(String name){
        IPlayerCharacter black_mage = characterFactory.createBlackMage(name, turnsQueue, characters);
        black_mage.addListener(deathHandler);
        black_mage.addToQueue();
        characters.add(black_mage);
    }

    /**
     * Creates a new White Mage on the character factory.
     *
     * @param name
     *    this Character's name
     * @since 1.04
     */
    public void createWhiteMage(String name){
        IPlayerCharacter white_mage = characterFactory.createWhiteMage(name, turnsQueue, characters);
        white_mage.addListener(deathHandler);
        white_mage.addToQueue();
        characters.add(white_mage);
    }

    /**
     * Creates a new Enemy on the character factory.
     *
     * @param name
     *    this Enemy's name
     * @since 1.04
     */
    public void createEnemy(String name){
        Enemy enemy = characterFactory.createEnemy(name, turnsQueue, enemies);
        enemy.addListener(deathHandler);
        enemy.addToQueue();
        enemies.add(enemy);
    }

    /**
     * Creates a new Sword on the weapon factory.
     *
     * @param name
     *    this Weapon's name
     * @since 1.04
     */
    public void createSword(String name){
        IWeapon sword = weaponFactory.createSword(name);
        inventory.add(sword);
    }

    /**
     * Creates a new Axe on the weapon factory.
     *
     * @param name
     *    this Weapon's name
     * @since 1.04
     */
    public void createAxe(String name){
        IWeapon axe = weaponFactory.createAxe(name);
        inventory.add(axe);
    }

    /**
     * Creates a new Knife on the weapon factory.
     *
     * @param name
     *    this Weapon's name
     * @since 1.04
     */
    public void createKnife(String name){
        IWeapon knife = weaponFactory.createKnife(name);
        inventory.add(knife);
    }

    /**
     * Creates a new Staff on the weapon factory.
     *
     * @param name
     *    this Weapon's name
     * @since 1.04
     */
    public void createStaff(String name){
        IWeapon staff = weaponFactory.createStaff(name);
        inventory.add(staff);
    }

    /**
     * Creates a new Bow on the weapon factory.
     *
     * @param name
     *    this Weapon's name
     * @since 1.04
     */
    public void createBow(String name){
        IWeapon bow = weaponFactory.createBow(name);
        inventory.add(bow);
    }

    /**
     * Makes a character to attack another.
     *
     * @param attacker
     *    character dealing the attack
     * @param victim
     *    character receiving the attack
     * @since 1.04
     */
    public void attack(ICharacter attacker, ICharacter victim){
        attacker.attack(victim);
    }

    /**
     * Equips a weapon to a character.
     *
     * @param character
     *    character to receive the weapon
     * @param weapon
     *    weapon to be equipped
     * @since 1.04
     */
    public void equipWeapon(IPlayerCharacter character, IWeapon weapon){
        character.equip(weapon);
    }

    /**
     * Gets the name of a character.
     *
     * @param character
     *    character to get the name from
     * @return 'name' parameter
     * @since 1.04
     */
    public String getName(ICharacter character){
        return character.getName();
    }

    /**
     * Gets the name of a weapon.
     *
     * @param weapon
     *    weapon to get the name from
     * @return 'name' parameter
     * @since 1.04
     */
    public String getName(IWeapon weapon){
        return weapon.getName();
    }

    /**
     * Gets the HP of a character.
     *
     * @param character
     *    character to get the HP from
     * @return 'HP' parameter
     * @since 1.04
     */
    public int getHP(ICharacter character){
        return character.getHP();
    }

    /**
     * Gets the maximum HP of a character.
     *
     * @param character
     *    character to get the MaxHP from
     * @return 'maxHP' parameter
     * @since 1.04
     */
    public int getMaxHP(ICharacter character){
        return character.getMaxHP();
    }

    /**
     * Gets the defense of a character.
     *
     * @param character
     *    character to get the defense from
     * @return 'defense' parameter
     * @since 1.04
     */
    public int getDefense(ICharacter character){
        return character.getDefense();
    }

    /**
     * Gets the mana of a white mage.
     *
     * @param character
     *    character to get the mana from
     * @return 'mana' parameter
     * @since 1.04
     */
    public int getMana(WhiteMage character){
        return character.getMana();
    }

    /**
     * Gets the maximum mana of a white mage.
     *
     * @param character
     *    character to get the MaxMana from
     * @return 'maxMana' parameter
     * @since 1.04
     */
    public int getMaxMana(WhiteMage character){
        return character.getMaxMana();
    }

    /**
     * Gets the mana of a black mage.
     *
     * @param character
     *    character to get the mana from
     * @return 'mana' parameter
     * @since 1.04
     */
    public int getMana(BlackMage character){
        return character.getMana();
    }

    /**
     * Gets the maximum mana of a black mage.
     *
     * @param character
     *    character to get the MaxMana from
     * @return 'maxMana' parameter
     * @since 1.04
     */
    public int getMaxMana(BlackMage character){
        return character.getMaxMana();
    }

    /**
     * Gets the weight of an enemy.
     *
     * @param enemy
     *    enemy to get the weight from
     * @return 'weight' parameter
     * @since 1.04
     */
    public int getWeight(Enemy enemy){
        return enemy.getWeight();
    }

    /**
     * Gets the weight of a weapon.
     *
     * @param weapon
     *    weapon to get the weight from
     * @return 'weight' parameter
     * @since 1.04
     */
    public int getWeight(IWeapon weapon){
        return weapon.getWeight();
    }

    /**
     * Gets the attack damage of an enemy.
     *
     * @param enemy
     *    enemy to get the AD from
     * @return 'attackDamage' parameter
     * @since 1.04
     */
    public int getAttackDamage(Enemy enemy){
        return enemy.getAttackDamage();
    }

    /**
     * Gets the attack damage of a weapon.
     *
     * @param weapon
     *    weapon to get the AD from
     * @return 'attackDamage' parameter
     * @since 1.04
     */
    public int getAttackDamage(IWeapon weapon){
        return weapon.getDamage();
    }

    /**
     * Gets the magic damage of a weapon.
     *
     * @param weapon
     *    weapon to get the AP from
     * @return 'magicDamage' parameter
     * @since 1.04
     */
    public int getMagicDamage(Staff weapon){
        return weapon.getMagicDamage();
    }

    /**
     * Gets the weapons list.
     *
     * @return 'inventory' parameter
     * @since 1.04
     */
    public ArrayList<IWeapon> getInventory(){
        return inventory;
    }

    /**
     * Gets the player characters list.
     *
     * @return 'characters' parameter
     * @since 1.04
     */
    public ArrayList<IPlayerCharacter> getCharacters(){
        return characters;
    }

    /**
     * Gets the enemies list.
     *
     * @return 'enemies' parameter
     * @since 1.04
     */
    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

    /**
     * Gets the turns queue
     *
     * @return 'turnsQueue' parameter
     * @since 1.04
     */
    public BlockingQueue<ICharacter> getQueue(){
        return turnsQueue;
    }

    /**
     * Gets the current game status.
     *
     * @return 'gameStatus' parameter
     * @since 1.04
     */
    public int getGameStatus() {
        return gameStatus;
    }
}
