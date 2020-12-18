package com.github.cc3002.finalreality;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.player.IPlayerCharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;

public class EquipmentPhase implements IPhase {

    private final Controller controller;
    private final IPlayerCharacter character;
    private IWeapon weapon;

    public EquipmentPhase(Controller theController, ICharacter playerCharacter){
        controller = theController;
        character = (IPlayerCharacter) playerCharacter;
    }

    public void setWeapon(IWeapon aWeapon){
        weapon = aWeapon;
    }

    @Override
    public void doPhase() {
        /* esperar inputs */
        controller.equipWeapon(character, weapon);
        endPhase();
    }

    @Override
    public void endPhase() {
        controller.setCurrentPhase(new AttackPhase(controller, character));
    }
}
