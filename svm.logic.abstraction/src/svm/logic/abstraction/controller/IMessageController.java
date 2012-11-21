package svm.logic.abstraction.controller;

import svm.logic.abstraction.jmsobjects.IMessage;
import svm.logic.abstraction.jmsobjects.ISubTeamMessage;
import svm.logic.abstraction.transferobjects.ITransferMember;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Projectteam : Team C
 * Date: 21.11.12
 */
public interface IMessageController extends Serializable, Remote, IController {

    List<IMessage> updateMemberMessages(ITransferMember member) throws RemoteException;

    List<ISubTeamMessage> updateSubTeamMessages(ITransferMember member) throws RemoteException;
}
