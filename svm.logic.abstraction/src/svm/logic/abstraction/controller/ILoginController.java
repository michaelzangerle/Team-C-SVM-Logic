package svm.logic.abstraction.controller;

import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;

import java.rmi.RemoteException;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public interface ILoginController extends IController {

    boolean login(String userName, String password) throws RemoteException, IllegalGetInstanceException, NoSessionFoundException;

    ITransferMember getMember() throws RemoteException;
}
