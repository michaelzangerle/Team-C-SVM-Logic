package svm.logic.abstraction.controller;

import svm.logic.abstraction.transferobjects.ITransferMatch;
import svm.logic.abstraction.transferobjects.ITransferTeam;

import java.rmi.RemoteException;

/**
 * ProjectTeam: Team C
 * Date: 30.10.12
 */
public interface ITeamContestController extends IController {

    void addMatch(ITransferTeam home, ITransferTeam away) throws RemoteException;

    void addResult(ITransferMatch match, Integer home, Integer away) throws RemoteException;

    void addTeam(ITransferTeam team) throws RemoteException;

    void removeTeam(ITransferTeam team) throws RemoteException;

}
