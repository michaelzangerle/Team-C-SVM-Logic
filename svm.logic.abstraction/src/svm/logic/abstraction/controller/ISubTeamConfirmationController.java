package svm.logic.abstraction.controller;

import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.abstraction.transferobjects.ITransferSubTeamHasMember;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Interface for the subteam confirmation controller
 * Projectteam: Team C
 * Date: 30.10.12
 */
public interface ISubTeamConfirmationController extends IController {

    ITransferMember getMember() throws RemoteException;

    void setConfirmation(boolean confirm, String comment);
}
