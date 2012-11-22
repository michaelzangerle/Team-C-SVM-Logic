package svm.logic.abstraction.controller;

import svm.logic.abstraction.jmsobjects.IMemberMessage;
import svm.logic.abstraction.jmsobjects.ISubTeamMessage;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;

import javax.jms.JMSException;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Projectteam : Team C
 * Date: 21.11.12
 */
public interface IMessageController extends Serializable, Remote, IController {

    List<IMemberMessage> updateMemberMessages() throws RemoteException, NoSessionFoundException, JMSException;

    List<ISubTeamMessage> updateSubTeamMessages() throws RemoteException;
}
