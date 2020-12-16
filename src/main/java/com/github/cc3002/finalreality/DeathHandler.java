package com.github.cc3002.finalreality;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A listener class that deals with the death of Characters and notifications to the controller.
 *
 * @author Ignacio Slater Muñoz
 * @author Vicente Daie Pinilla.
 *
 * @version 1.04
 * @since 1.04
 */
public class DeathHandler implements PropertyChangeListener {

    /**
     * Associated controller.
     */
    private final Controller controller;

    /**
     * Creates a new listener.
     *
     * @param theController
     *    'controller' attribute
     * @since 1.04
     */
    public DeathHandler(Controller theController){
        controller = theController;
    }

    /**
     * Reports a death to the controller.
     *
     * @param evt
     *    inherited, ignored
     * @since 1.04
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        controller.onDeath();
    }

}
