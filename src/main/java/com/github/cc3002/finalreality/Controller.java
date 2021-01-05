package com.github.cc3002.finalreality;

import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.IPlayerCharacter;
import com.github.cc3002.finalreality.model.character.player.classes.*;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.Staff;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The main controller, a class that links the user with the model.
 *
 * @author Ignacio Slater Muñoz
 * @author Vicente Daie Pinilla.
 *
 * @version 1.05
 * @since 1.04
 */
public class Controller {

    /**
     * Current turn phase.
     */
    private IPhase currentPhase;

    /**
     * Log data to be sent to the GUI, to be shown during battle.
     */
    private final ArrayList<String> toLog;

    /**
     * Current character's turn.
     */
    private int currentCharacter;

    /**
     * Container for the characters before being randomly included in the queue.
     */
    private final ArrayList<ICharacter> charactersBeforeQueue;

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
    public Controller() {
        charactersBeforeQueue = new ArrayList<>();
        inventory = new ArrayList<>();
        characters = new ArrayList<>();
        enemies = new ArrayList<>();
        characterFactory = new CharacterFactory();
        weaponFactory = new WeaponFactory();
        turnsQueue = new LinkedBlockingQueue<>();
        deathHandler = new DeathHandler(this);
        toLog = new ArrayList<>();
        currentCharacter = 0;
        gameStatus = 0;
    }

    /**
     * Receives names from the user to create the characters and weapons. Puts enemies and characters randomly
     * on the turns queue.
     *
     * @param knights    names of the knights
     * @param engineers  names of the engineers
     * @param thieves    names of the thieves
     * @param blackMages names of the black mages
     * @param whiteMages names of the white mages
     * @param swords     names of the swords
     * @param axes       names of the axes
     * @param knives     names of the knives
     * @param staffs     names of the staffs
     * @param bows       names of the bows
     * @since 1.05
     */
    public void setUpGame(ArrayList<String> knights,
                          ArrayList<String> engineers,
                          ArrayList<String> thieves,
                          ArrayList<String> blackMages,
                          ArrayList<String> whiteMages,
                          ArrayList<String> swords,
                          ArrayList<String> axes,
                          ArrayList<String> knives,
                          ArrayList<String> staffs,
                          ArrayList<String> bows) {
        ArrayList<String> theEnemies = new ArrayList<>();
        theEnemies.add("Enemy 1");
        theEnemies.add("Enemy 2");
        theEnemies.add("Enemy 3");
        theEnemies.add("Enemy 4");
        theEnemies.add("Enemy 5");
        for (String knight : knights) {
            createKnight(knight);
        }
        for (String engineer : engineers) {
            createEngineer(engineer);
        }
        for (String thief : thieves) {
            createThief(thief);
        }
        for (String blackMage : blackMages) {
            createBlackMage(blackMage);
        }
        for (String whiteMage : whiteMages) {
            createWhiteMage(whiteMage);
        }
        for (String enemy : theEnemies) {
            createEnemy(enemy);
        }
        for (String sword : swords) {
            createSword(sword);
        }
        for (String axe : axes) {
            createAxe(axe);
        }
        for (String knife : knives) {
            createKnife(knife);
        }
        for (String staff : staffs) {
            createStaff(staff);
        }
        for (String bow : bows) {
            createBow(bow);
        }
        Collections.shuffle(charactersBeforeQueue);
        for (ICharacter character : charactersBeforeQueue) {
            character.addToQueue();
        }
        turn();
    }

    /**
     * Method triggered by the listeners when a character dies. It checks if the game has come to an end and removes
     * dead characters and enemies.
     *
     * @since 1.04
     */
    public void onDeath() {
        IPlayerCharacter deadCharacter = null;
        for (IPlayerCharacter character : characters) {
            if (!character.isAlive()) {
                toLog.add(character.getName() + " is out of combat!");
                deadCharacter = character;
            }
        }
        if (deadCharacter != null) {
            characters.remove(deadCharacter);
        }
        if (characters.isEmpty()) {
            defeat();
        }
        Enemy deadEnemy = null;
        for (Enemy enemy : enemies) {
            if (!enemy.isAlive()) {
                toLog.add(enemy.getName() + " is out of combat!");
                deadEnemy = enemy;
            }
        }
        if (deadEnemy != null) {
            enemies.remove(deadEnemy);
        }
        if (enemies.isEmpty()) {
            victory();
        }
    }

    /**
     * Method that declares a defeat.
     *
     * @since 1.04
     */
    public void defeat() {
        gameStatus = -1;
    }

    /**
     * Method that declares a victory.
     *
     * @since 1.04
     */
    public void victory() {
        gameStatus = 1;
    }

    /**
     * Method that deals with enemies' turns.
     *
     * @param index enemy with the current turn.
     * @since 1.04
     */
    public void enemyTurn(int index) {
        currentPhase = new EnemyPhase(this, index);
        currentPhase.doPhase();
    }

    /**
     * Method that deals with player characters' turns.
     *
     * @param index character with the current turn.
     * @since 1.04
     */
    public void playerTurn(int index) {
        setCurrentCharacter(index);
        addToLog("--------------------------------------------");
        addToLog("It's " + characters.get(index).getName() + "'s turn...");
        currentPhase = new EquipmentPhase(this, index);
    }

    /**
     * Method that deals with turns, checks if the next turn is for an enemy or a player character and redirects to
     * the corresponding method.
     *
     * @since 1.04
     */
    public void turn() {
        if(gameStatus == 0) {
            try {
                ICharacter playingCharacter = turnsQueue.take();
                if (!playingCharacter.isEnemy()) {
                    int index = getCharacters().indexOf(playingCharacter);
                    if (index == -1) {
                        turn();
                    } else {
                        playerTurn(index);
                    }
                } else {
                    int index = getEnemies().indexOf(playingCharacter);
                    if (index == -1) {
                        turn();
                    } else {
                        enemyTurn(index);
                    }
                }
            } catch (InterruptedException ignored) {}
        }
    }

    /**
     * Adds some text to the log, to be shown on the GUI.
     *
     * @param text some text
     * @since 1.05
     */
    public void addToLog(String text) {
        toLog.add(text);
    }

    /**
     * Creates a new Knight on the character factory.
     *
     * @param name this Character's name
     * @since 1.04
     */
    public void createKnight(String name) {
        IPlayerCharacter knight = characterFactory.createKnight(name, turnsQueue, characters);
        knight.addListener(deathHandler);
        charactersBeforeQueue.add(knight);
        characters.add(knight);
    }

    /**
     * Creates a new Engineer on the character factory.
     *
     * @param name this Character's name
     * @since 1.04
     */
    public void createEngineer(String name) {
        IPlayerCharacter engineer = characterFactory.createEngineer(name, turnsQueue, characters);
        engineer.addListener(deathHandler);
        charactersBeforeQueue.add(engineer);
        characters.add(engineer);
    }

    /**
     * Creates a new Thief on the character factory.
     *
     * @param name this Character's name
     * @since 1.04
     */
    public void createThief(String name) {
        IPlayerCharacter thief = characterFactory.createThief(name, turnsQueue, characters);
        thief.addListener(deathHandler);
        charactersBeforeQueue.add(thief);
        characters.add(thief);
    }

    /**
     * Creates a new Black Mage on the character factory.
     *
     * @param name this Character's name
     * @since 1.04
     */
    public void createBlackMage(String name) {
        IPlayerCharacter black_mage = characterFactory.createBlackMage(name, turnsQueue, characters);
        black_mage.addListener(deathHandler);
        charactersBeforeQueue.add(black_mage);
        characters.add(black_mage);
    }

    /**
     * Creates a new White Mage on the character factory.
     *
     * @param name this Character's name
     * @since 1.04
     */
    public void createWhiteMage(String name) {
        IPlayerCharacter white_mage = characterFactory.createWhiteMage(name, turnsQueue, characters);
        white_mage.addListener(deathHandler);
        charactersBeforeQueue.add(white_mage);
        characters.add(white_mage);
    }

    /**
     * Creates a new Enemy on the character factory.
     *
     * @param name this Enemy's name
     * @since 1.04
     */
    public void createEnemy(String name) {
        Enemy enemy = characterFactory.createEnemy(name, turnsQueue, enemies);
        enemy.addListener(deathHandler);
        charactersBeforeQueue.add(enemy);
        enemies.add(enemy);
    }

    /**
     * Creates a new Sword on the weapon factory.
     *
     * @param name this Weapon's name
     * @since 1.04
     */
    public void createSword(String name) {
        IWeapon sword = weaponFactory.createSword(name);
        inventory.add(sword);
    }

    /**
     * Creates a new Axe on the weapon factory.
     *
     * @param name this Weapon's name
     * @since 1.04
     */
    public void createAxe(String name) {
        IWeapon axe = weaponFactory.createAxe(name);
        inventory.add(axe);
    }

    /**
     * Creates a new Knife on the weapon factory.
     *
     * @param name this Weapon's name
     * @since 1.04
     */
    public void createKnife(String name) {
        IWeapon knife = weaponFactory.createKnife(name);
        inventory.add(knife);
    }

    /**
     * Creates a new Staff on the weapon factory.
     *
     * @param name this Weapon's name
     * @since 1.04
     */
    public void createStaff(String name) {
        IWeapon staff = weaponFactory.createStaff(name);
        inventory.add(staff);
    }

    /**
     * Creates a new Bow on the weapon factory.
     *
     * @param name this Weapon's name
     * @since 1.04
     */
    public void createBow(String name) {
        IWeapon bow = weaponFactory.createBow(name);
        inventory.add(bow);
    }

    /**
     * Makes a character to attack another.
     *
     * @param attacker character dealing the attack
     * @param victim   character receiving the attack
     * @since 1.04
     */
    public void attack(ICharacter attacker, ICharacter victim) {
        attacker.attack(victim);
    }

    /**
     * Equips a weapon to a character.
     *
     * @param character character to receive the weapon
     * @param weapon    weapon to be equipped
     * @since 1.04
     */
    public void equipWeapon(IPlayerCharacter character, IWeapon weapon) {
        character.equip(weapon);
    }

    /**
     * Unequips a weapon.
     *
     * @param currentPlayer
     *    character bearing the weapon to be unequipped
     * @since 1.05
     */
    public void unequip(int currentPlayer) {
        getCharacters().get(currentPlayer).unequip();
    }

    /**
     * Gets the name of a character.
     *
     * @param character character to get the name from
     * @return 'name' parameter
     * @since 1.04
     */
    public String getName(ICharacter character) {
        return character.getName();
    }

    /**
     * Gets the name of a weapon.
     *
     * @param weapon weapon to get the name from
     * @return 'name' parameter
     * @since 1.04
     */
    public String getName(IWeapon weapon) {
        return weapon.getName();
    }

    /**
     * Gets the HP of a character.
     *
     * @param character character to get the HP from
     * @return 'HP' parameter
     * @since 1.04
     */
    public int getHP(ICharacter character) {
        return character.getHP();
    }

    /**
     * Gets the maximum HP of a character.
     *
     * @param character character to get the MaxHP from
     * @return 'maxHP' parameter
     * @since 1.04
     */
    public int getMaxHP(ICharacter character) {
        return character.getMaxHP();
    }

    /**
     * Gets the defense of a character.
     *
     * @param character character to get the defense from
     * @return 'defense' parameter
     * @since 1.04
     */
    public int getDefense(ICharacter character) {
        return character.getDefense();
    }

    /**
     * Gets the mana of a white mage.
     *
     * @param character character to get the mana from
     * @return 'mana' parameter
     * @since 1.04
     */
    public int getMana(WhiteMage character) {
        return character.getMana();
    }

    /**
     * Gets the maximum mana of a white mage.
     *
     * @param character character to get the MaxMana from
     * @return 'maxMana' parameter
     * @since 1.04
     */
    public int getMaxMana(WhiteMage character) {
        return character.getMaxMana();
    }

    /**
     * Gets the mana of a black mage.
     *
     * @param character character to get the mana from
     * @return 'mana' parameter
     * @since 1.04
     */
    public int getMana(BlackMage character) {
        return character.getMana();
    }

    /**
     * Gets the maximum mana of a black mage.
     *
     * @param character character to get the MaxMana from
     * @return 'maxMana' parameter
     * @since 1.04
     */
    public int getMaxMana(BlackMage character) {
        return character.getMaxMana();
    }

    /**
     * Gets the weight of an enemy.
     *
     * @param enemy enemy to get the weight from
     * @return 'weight' parameter
     * @since 1.04
     */
    public int getWeight(Enemy enemy) {
        return enemy.getWeight();
    }

    /**
     * Gets the weight of a weapon.
     *
     * @param weapon weapon to get the weight from
     * @return 'weight' parameter
     * @since 1.04
     */
    public int getWeight(IWeapon weapon) {
        return weapon.getWeight();
    }

    /**
     * Gets the attack damage of an enemy.
     *
     * @param enemy enemy to get the AD from
     * @return 'attackDamage' parameter
     * @since 1.04
     */
    public int getAttackDamage(Enemy enemy) {
        return enemy.getAttackDamage();
    }

    /**
     * Gets the attack damage of a weapon.
     *
     * @param weapon weapon to get the AD from
     * @return 'attackDamage' parameter
     * @since 1.04
     */
    public int getAttackDamage(IWeapon weapon) {
        return weapon.getDamage();
    }

    /**
     * Gets the magic damage of a weapon.
     *
     * @param weapon weapon to get the AP from
     * @return 'magicDamage' parameter
     * @since 1.04
     */
    public int getMagicDamage(Staff weapon) {
        return weapon.getMagicDamage();
    }

    /**
     * Gets the current bearer of a weapon.
     *
     * @param weapon weapon to get the bearer from
     * @return 'bearer' parameter, or None if null
     * @since 1.04
     */
    public String getBearer(IWeapon weapon) {
        if (weapon.getBearer() == null) {
            return "None";
        } else {
            return weapon.getBearer().getName();
        }
    }

    /**
     * Gets the weapons list.
     *
     * @return 'inventory' parameter
     * @since 1.04
     */
    public ArrayList<IWeapon> getInventory() {
        return inventory;
    }

    /**
     * Gets the player characters list.
     *
     * @return 'characters' parameter
     * @since 1.04
     */
    public ArrayList<IPlayerCharacter> getCharacters() {
        return characters;
    }

    /**
     * Gets the enemies list.
     *
     * @return 'enemies' parameter
     * @since 1.04
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Gets the characters' names for display on the GUI.
     *
     * @return an array with the names of the characters.
     * @since 1.05
     */
    public ArrayList<String> getCharacterNames() {
        ArrayList<String> output = new ArrayList<>();
        for (IPlayerCharacter character : characters) {
            output.add(getName(character));
        }
        return output;
    }

    /**
     * Gets the characters' HP for display on the GUI.
     *
     * @return an array with the current HP of the characters.
     * @since 1.05
     */
    public ArrayList<Integer> getCharacterHP() {
        ArrayList<Integer> output = new ArrayList<>();
        for (IPlayerCharacter character : characters) {
            output.add(getHP(character));
        }
        return output;
    }

    /**
     * Gets the characters' maximum HP for display on the GUI.
     *
     * @return an array with the maximum HP of the characters.
     * @since 1.05
     */
    public ArrayList<Integer> getCharacterMaxHP() {
        ArrayList<Integer> output = new ArrayList<>();
        for (IPlayerCharacter character : characters) {
            output.add(getMaxHP(character));
        }
        return output;
    }

    /**
     * Gets the characters' defense for display on the GUI.
     *
     * @return an array with the defense of the characters.
     * @since 1.05
     */
    public ArrayList<Integer> getCharacterDefense() {
        ArrayList<Integer> output = new ArrayList<>();
        for (IPlayerCharacter character : characters) {
            output.add(getDefense(character));
        }
        return output;
    }

    /**
     * Gets the characters' weapons for display on the GUI.
     *
     * @return an array with the names of the weapons held by the characters, None if null
     * @since 1.05
     */
    public ArrayList<String> getCharacterWeapons() {
        ArrayList<String> output = new ArrayList<>();
        for (IPlayerCharacter character : characters) {
            if (character.getEquippedWeapon() == null) {
                output.add("None");
            } else {
                output.add(character.getEquippedWeapon().getName());
            }
        }
        return output;
    }

    /**
     * Gets the characters' attack for display on the GUI.
     *
     * @return an array with the attack of the weapons held by the characters, 0 if null
     * @since 1.05
     */
    public ArrayList<Integer> getCharacterAttack() {
        ArrayList<Integer> output = new ArrayList<>();
        for (IPlayerCharacter character : characters) {
            if (character.getEquippedWeapon() == null) {
                output.add(0);
            } else {
                output.add(character.getEquippedWeapon().getDamage());
            }
        }
        return output;
    }

    /**
     * Gets the characters' weight for display on the GUI.
     *
     * @return an array with the weight of the weapons held by the characters, 0 if null
     * @since 1.05
     */
    public ArrayList<Integer> getCharacterWeight() {
        ArrayList<Integer> output = new ArrayList<>();
        for (IPlayerCharacter character : characters) {
            if (character.getEquippedWeapon() == null) {
                output.add(0);
            } else {
                output.add(character.getEquippedWeapon().getWeight());
            }
        }
        return output;
    }

    /**
     * Gets the enemies' names for display on the GUI.
     *
     * @return an array with the names of the enemies
     * @since 1.05
     */
    public ArrayList<String> getEnemyNames() {
        ArrayList<String> output = new ArrayList<>();
        for (Enemy enemy : enemies) {
            output.add(getName(enemy));
        }
        return output;
    }

    /**
     * Gets the enemies' HP for display on the GUI.
     *
     * @return an array with the current HP of the enemies
     * @since 1.05
     */
    public ArrayList<Integer> getEnemyHP() {
        ArrayList<Integer> output = new ArrayList<>();
        for (Enemy enemy : enemies) {
            output.add(getHP(enemy));
        }
        return output;
    }

    /**
     * Gets the enemies' maximum HP for display on the GUI.
     *
     * @return an array with the maximum HP of the enemies
     * @since 1.05
     */
    public ArrayList<Integer> getEnemyMaxHP() {
        ArrayList<Integer> output = new ArrayList<>();
        for (Enemy enemy : enemies) {
            output.add(getMaxHP(enemy));
        }
        return output;
    }

    /**
     * Gets the enemies' defense for display on the GUI.
     *
     * @return an array with the defense of the enemies
     * @since 1.05
     */
    public ArrayList<Integer> getEnemyDefense() {
        ArrayList<Integer> output = new ArrayList<>();
        for (Enemy enemy : enemies) {
            output.add(getDefense(enemy));
        }
        return output;
    }

    /**
     * Gets the enemies' attack for display on the GUI.
     *
     * @return an array with the attack of the enemies
     * @since 1.05
     */
    public ArrayList<Integer> getEnemyAD() {
        ArrayList<Integer> output = new ArrayList<>();
        for (Enemy enemy : enemies) {
            output.add(getAttackDamage(enemy));
        }
        return output;
    }

    /**
     * Gets the enemies' weight for display on the GUI.
     *
     * @return an array with the weight of the enemies
     * @since 1.05
     */
    public ArrayList<Integer> getEnemyWeight(){
        ArrayList<Integer> output = new ArrayList<>();
        for(Enemy enemy : enemies){
            output.add(getWeight(enemy));
        }
        return output;
    }

    /**
     * Gets the weapons' names for display on the GUI.
     *
     * @return an array with the names of the weapons
     * @since 1.05
     */
    public ArrayList<String> getWeaponNames(){
        ArrayList<String> output = new ArrayList<>();
        for(IWeapon weapon : inventory){
            output.add(getName(weapon));
        }
        return output;
    }

    /**
     * Gets the weapons' attack for display on the GUI.
     *
     * @return an array with the attack of the weapons
     * @since 1.05
     */
    public ArrayList<Integer> getWeaponAD(){
        ArrayList<Integer> output = new ArrayList<>();
        for(IWeapon weapon : inventory){
            output.add(getAttackDamage(weapon));
        }
        return output;
    }

    /**
     * Gets the weapons' weight for display on the GUI.
     *
     * @return an array with the weight of the weapons
     * @since 1.05
     */
    public ArrayList<Integer> getWeaponWeight(){
        ArrayList<Integer> output = new ArrayList<>();
        for(IWeapon weapon : inventory){
            output.add(getWeight(weapon));
        }
        return output;
    }

    /**
     * Gets the weapons' bearers for display on the GUI.
     *
     * @return an array with the names of the weapons bearers
     * @since 1.05
     */
    public ArrayList<String> getWeaponBearers(){
        ArrayList<String> output = new ArrayList<>();
        for(IWeapon weapon : inventory){
            output.add(getBearer(weapon));
        }
        return output;
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

    /**
     * Sets the current character's turn.
     * @param index
     *   index of the character
     * @since 1.05
     */
    public void setCurrentCharacter(int index){
        currentCharacter = index;
    }

    /**
     * Gets the current character's turn.
     *
     * @since 1.05
     */
    public int getCurrentPlayer() {
        return currentCharacter;
    }

    /**
     * Gets the logs container.
     *
     * @return
     *    the log container
     * @since 1.05
     */
    public ArrayList<String> getToLog() {
        return toLog;
    }

    /**
     * Clears the logs container.
     *
     * @since 1.05
     */
    public void cleanLog(){
        toLog.clear();
    }

    /**
     * Gets the current phase of the turn.
     *
     * @since 1.05
     */
    public IPhase getCurrentPhase(){
        return currentPhase;
    }

    /**
     * Sets the current phase of the turn.
     * @param phase
     *    current phase
     * @since 1.05
     */
    public void setCurrentPhase(IPhase phase){
        currentPhase = phase;
    }

    /**
     * Sets the current phase of the turn as an attack phase.
     * @param index
     *    index of the character whose turn this is
     * @since 1.05
     */
    public void setAttackPhase(int index){
        currentPhase = new AttackPhase(this, index);
    }

    /**
     * Checks if this character is a knight.
     *
     * @param index
     *    index of the character
     * @return true if it is, false otherwise
     * @since 1.05
     */
    public boolean isKnight(int index){
        return getCharacters().get(index).isKnight();
    }

    /**
     * Checks if this character is an engineer.
     *
     * @param index
     *    index of the character
     * @return true if it is, false otherwise
     * @since 1.05
     */
    public boolean isEngineer(int index){
        return getCharacters().get(index).isEngineer();
    }

    /**
     * Checks if this character is a thief
     *
     * @param index
     *    index of the character
     * @return true if it is, false otherwise
     * @since 1.05
     */
    public boolean isThief(int index){
        return getCharacters().get(index).isThief();
    }

    /**
     * Checks if this character is a black mage.
     *
     * @param index
     *    index of the character
     * @return true if it is, false otherwise
     * @since 1.05
     */
    public boolean isBlackMage(int index){
        return getCharacters().get(index).isBlackMage();
    }

    /**
     * Checks if this character is a white mage.
     *
     * @param index
     *    index of the character
     * @return true if it is, false otherwise
     * @since 1.05
     */
    public boolean isWhiteMage(int index){
        return getCharacters().get(index).isWhiteMage();
    }

}
