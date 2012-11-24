package svm.logic.abstraction.controller;

import svm.logic.abstraction.jmsobjects.IMessageObserver;

import javax.jms.JMSException;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Projectteam : Team C
 * Date: 21.11.12
 */
public interface IMessageController extends Serializable, Remote, IController {

    void addObserver(IMessageObserver o) throws RemoteException;

    void removeObserver(IMessageObserver o) throws RemoteException;

    void updateMessages() throws JMSException;
}
