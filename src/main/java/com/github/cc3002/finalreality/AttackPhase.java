package com.github.cc3002.finalreality;

import com.github.cc3002.finalreality.model.character.player.IPlayerCharacter;

/**
 * The Attack Phase of the turns of the player characters.
 *
 * @author Ignacio Slater Muñoz
 * @author Vicente Daie Pinilla.
 *
 * @version 1.05
 * @since 1.05
 */
public class AttackPhase implements IPhase {

    /**
     * The main controller.
     */
    private final Controller controller;

    /**
     * The index of the current characters' turn.
     */
    private final int indexCharacter;

    /**
     * The index of the enemy to be attacked.
     */
    private int indexEnemy;

    /**
     * Creates a new attack phase.
     *
     * @param theController
     *    'controller' parameter
     * @param index
     *    'index' parameter
     * @since 1.05
     */
    public AttackPhase(Controller theController, int index){
        controller = theController;
        indexCharacter = index;
    }

    /**
     * Checks if this phase is an equipment phase.
     *
     * @return false
     * @since 1.05
     */
    @Override
    public boolean isEquipmentPhase(){
        return false;
    }

    /**
     * Dummy overridden method.
     * @param currentWeapon
     *    ignored
     * @since 1.05
     */
    @Override
    public void setIndexWeapon(int currentWeapon) {
    }

    /**
     * Sets the enemy to be attacked.
     *
     * @param currentEnemy
     *    index of the enemy to be attacked
     * @since 1.05
     */
    @Override
    public void setIndexEnemy(int currentEnemy) {
        indexEnemy = currentEnemy;
    }

    /**
     * Executes this phase.
     *
     * @since 1.05
     */
    @Override
    public void doPhase(){
        IPlayerCharacter attacker = controller.getCharacters().get(indexCharacter);
        controller.addToLog(attacker.getName() + " has attacked " + controller.getEnemies().get(indexEnemy).getName() + "!");
        controller.attack(attacker, controller.getEnemies().get(indexEnemy));
        attacker.waitTurn();
        endPhase();
    }

    /**
     * Ends this phase.
     *
     * @since 1.05
     */
    @Override
    public void endPhase(){
        controller.turn();
    }
}
