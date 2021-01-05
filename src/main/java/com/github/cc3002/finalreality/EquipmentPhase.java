package com.github.cc3002.finalreality;

/**
 * The Weapon Equipment Phase of the turns of the player characters.
 *
 * @author Ignacio Slater Muñoz
 * @author Vicente Daie Pinilla.
 *
 * @version 1.05
 * @since 1.05
 */
public class EquipmentPhase implements IPhase {

    /**
     * The main controller.
     */
    private final Controller controller;

    /**
     * The index of the current characters' turn.
     */
    private final int indexCharacter;

    /**
     * The index of the weapon to be equipped.
     */
    private int indexWeapon;

    /**
     * Creates a new equipment phase.
     *
     * @param theController
     *    'controller' parameter
     * @param index
     *    'index' parameter
     * @since 1.05
     */
    public EquipmentPhase(Controller theController, int index){
        controller = theController;
        indexCharacter = index;
    }

    /**
     * Checks if this phase is an equipment phase.
     *
     * @return true
     * @since 1.05
     */
    @Override
    public boolean isEquipmentPhase(){
        return true;
    }

    /**
     * Sets the weapon to be equipped.
     *
     * @param index
     *    index of the weapon to be equipped
     * @since 1.05
     */
    @Override
    public void setIndexWeapon(int index){
        indexWeapon = index;
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
     * Executes this phase.
     *
     * @since 1.05
     */
    @Override
    public void doPhase() {
        controller.equipWeapon(controller.getCharacters().get(indexCharacter), controller.getInventory().get(indexWeapon));
        endPhase();
    }

    /**
     * Ends this phase.
     *
     * @since 1.05
     */
    @Override
    public void endPhase() {
        controller.setCurrentPhase(new AttackPhase(controller, indexCharacter));
    }
}
