package com.github.cc3002.finalreality;

import com.github.cc3002.finalreality.model.character.Enemy;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.IPlayerCharacter;
import com.github.cc3002.finalreality.model.character.player.classes.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

/**
 * A class that deals with the creation of Characters, and holds their default attribute values.
 *
 * @author Ignacio Slater Muñoz
 * @author Vicente Daie Pinilla.
 *
 * @version 1.04
 * @since 1.04
 */
public class CharacterFactory {

    /**
     * Default values for each character class.
     */
    private final int KNIGHT_HP = 1000;
    private final int KNIGHT_DEFENSE = 25;
    private final int ENGINEER_HP = 700;
    private final int ENGINEER_DEFENSE = 30;
    private final int THIEF_HP = 600;
    private final int THIEF_DEFENSE = 20;
    private final int BLACK_MAGE_HP = 300;
    private final int BLACK_MAGE_DEFENSE = 20;
    private final int BLACK_MAGE_MANA = 100;
    private final int WHITE_MAGE_HP = 300;
    private final int WHITE_MAGE_DEFENSE = 20;
    private final int WHITE_MAGE_MANA = 100;
    private final int ENEMY_HP = 500;
    private final int ENEMY_DEFENSE = 20;
    private final int ENEMY_AD = 50;
    private final int ENEMY_WEIGHT = 20;

    /**
     * Creates a new factory.
     *
     * @since 1.04
     */
    public CharacterFactory(){
    }

    /**
     * Creates a new Knight.
     *
     * @param name
     *    this Character's name
     * @param turnsQueue
     *    the turns queue linked to the controller
     * @param list
     *    the list of characters linked to the controller
     * @since 1.04
     */
    public Knight createKnight(String name, BlockingQueue<ICharacter> turnsQueue, ArrayList<IPlayerCharacter> list){
        return new Knight(name, turnsQueue, KNIGHT_HP, KNIGHT_DEFENSE, list);
    }

    /**
     * Creates a new Engineer.
     *
     * @param name
     *    this Character's name
     * @param turnsQueue
     *    the turns queue linked to the controller
     * @param list
     *    the list of characters linked to the controller
     * @since 1.04
     */
    public Engineer createEngineer(String name, BlockingQueue<ICharacter> turnsQueue, ArrayList<IPlayerCharacter> list){
        return new Engineer(name, turnsQueue, ENGINEER_HP, ENGINEER_DEFENSE, list);
    }

    /**
     * Creates a new Thief.
     *
     * @param name
     *    this Character's name
     * @param turnsQueue
     *    the turns queue linked to the controller
     * @param list
     *    the list of characters linked to the controller
     * @since 1.04
     */
    public Thief createThief(String name, BlockingQueue<ICharacter> turnsQueue, ArrayList<IPlayerCharacter> list){
        return new Thief(name, turnsQueue, THIEF_HP, THIEF_DEFENSE, list);
    }

    /**
     * Creates a new Black Mage.
     *
     * @param name
     *    this Character's name
     * @param turnsQueue
     *    the turns queue linked to the controller
     * @param list
     *    the list of characters linked to the controller
     * @since 1.04
     */
    public BlackMage createBlackMage(String name, BlockingQueue<ICharacter> turnsQueue, ArrayList<IPlayerCharacter> list){
        return new BlackMage(name, turnsQueue, BLACK_MAGE_HP, BLACK_MAGE_DEFENSE, BLACK_MAGE_MANA, list);
    }

    /**
     * Creates a new White Mage.
     *
     * @param name
     *    this Character's name
     * @param turnsQueue
     *    the turns queue linked to the controller
     * @param list
     *    the list of characters linked to the controller
     * @since 1.04
     */
    public WhiteMage createWhiteMage(String name, BlockingQueue<ICharacter> turnsQueue, ArrayList<IPlayerCharacter> list){
        return new WhiteMage(name, turnsQueue, WHITE_MAGE_HP, WHITE_MAGE_DEFENSE, WHITE_MAGE_MANA, list);
    }

    /**
     * Creates a new Enemy.
     *
     * @param name
     *    this Enemy's name
     * @param turnsQueue
     *    the turns queue linked to the controller
     * @param list
     *    the list of enemies linked to the controller
     * @since 1.04
     */
    public Enemy createEnemy(String name, BlockingQueue<ICharacter> turnsQueue, ArrayList<Enemy> list){
        return new Enemy(name, turnsQueue, ENEMY_HP, ENEMY_DEFENSE, ENEMY_WEIGHT, ENEMY_AD, list);
    }
}
