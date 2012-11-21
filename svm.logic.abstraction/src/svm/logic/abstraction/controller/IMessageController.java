package svm.logic.abstraction.controller;

import svm.logic.abstraction.jmsobjects.IMessage;
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

    List<IMessage> update(ITransferMember member) throws RemoteException;
}
