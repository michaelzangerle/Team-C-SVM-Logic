package svm.logic.abstraction.controller;

/**
 * Basis Interface for the controllers
 * Projectteam: Team C
 * Date: 30.10.12
 */
public interface IController {

    /**
     * Inits the controller (open session, ...)
     */
    void start();

    /**
     * Saves changes to db (save and close session, ...)
     */
    void commit();

    /**
     * Reset changes to objects (rollback and close session, ...)
     */
    void abort();

}
