package com.github.cc3002.finalreality;

import com.github.cc3002.finalreality.model.character.ICharacter;
import java.util.Random;

/**
 * The phase of the turns of the enemies.
 *
 * @author Ignacio Slater Muñoz
 * @author Vicente Daie Pinilla.
 *
 * @version 1.05
 * @since 1.05
 */
public class EnemyPhase implements IPhase {

    /**
     * The main controller.
     */
    private final Controller controller;

    /**
     * The index of the current enemy's turn.
     */
    private final int index;

    /**
     * Creates a new enemy phase.
     *
     * @param theController
     *    'controller' parameter
     * @param theIndex
     *    'index' parameter
     * @since 1.05
     */
    public EnemyPhase(Controller theController, int theIndex){
        controller = theController;
        index = theIndex;
    }

    /**
     * Checks if this phase is an equipment phase.
     *
     * @return false
     * @since 1.05
     */
    @Override
    public boolean isEquipmentPhase() {
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
     * Dummy overridden method.
     * @param currentEnemy
     *    ignored
     * @since 1.05
     */
    @Override
    public void setIndexEnemy(int currentEnemy) {
    }

    /**
     * Executes this phase, attacking a random character.
     *
     * @since 1.05
     */
    public void doPhase(){
        Random r = new Random();
        int randomIndex = r.nextInt(controller.getCharacters().size());
        ICharacter attacker = controller.getEnemies().get(index);
        controller.addToLog("--------------------------------------------");
        controller.addToLog(attacker.getName() + " has attacked " + controller.getCharacters().get(randomIndex).getName() + "!");
        controller.attack(attacker, controller.getCharacters().get(randomIndex));
        attacker.waitTurn();
        endPhase();
    }

    /**
     * Ends this phase.
     *
     * @since 1.05
     */
    public void endPhase(){
        controller.turn();
    }
}
