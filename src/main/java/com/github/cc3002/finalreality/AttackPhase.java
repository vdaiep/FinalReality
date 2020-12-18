package com.github.cc3002.finalreality;

import com.github.cc3002.finalreality.model.character.ICharacter;

public class AttackPhase implements IPhase {

    private final Controller controller;
    private final ICharacter attacker;
    private ICharacter victim;

    public AttackPhase(Controller theController, ICharacter anAttacker){
        controller = theController;
        attacker = anAttacker;
        doPhase();
    }

    public void setVictim(ICharacter aVictim){
        victim = aVictim;
    }

    @Override
    public void doPhase(){
        /* esperar inputs */
        controller.attack(attacker, victim);
        attacker.waitTurn();
        endPhase();
    }

    @Override
    public void endPhase(){
        controller.turn();
    }
}
