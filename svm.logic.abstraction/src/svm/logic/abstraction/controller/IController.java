package svm.logic.abstraction.controller;

import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Basis Interface for the controllers
 * Projectteam: Team C
 * Date: 30.10.12
 */
public interface IController extends Serializable, Remote {

    /**
     * Inits the controller (open session, ...)
     */
    void start() throws NoSessionFoundException, IllegalGetInstanceException, RemoteException;

    /**
     * Saves changes to db (save and close session, ...)
     */
    void commit() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException, RemoteException;

    /**
     * Reset changes to objects (rollback and close session, ...)
     */
    void abort() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException, RemoteException;

}
