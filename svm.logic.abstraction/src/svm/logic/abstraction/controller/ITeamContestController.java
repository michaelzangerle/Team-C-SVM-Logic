package svm.logic.abstraction.controller;

import svm.domain.abstraction.exception.DomainException;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.ITransferMatch;
import svm.logic.abstraction.transferobjects.ITransferTeam;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * ProjectTeam: Team C
 * Date: 30.10.12
 */
public interface ITeamContestController extends IController {

    void addResult(ITransferMatch match, Integer home, Integer away) throws RemoteException;

    void addTeam(ITransferTeam team) throws RemoteException, DomainException, NoSessionFoundException, InstantiationException, IllegalAccessException;

    void removeTeam(ITransferTeam team) throws RemoteException, DomainException;

    List<ITransferMatch> getMatches() throws RemoteException, IllegalGetInstanceException;

    List<ITransferTeam> getTeams() throws RemoteException, IllegalGetInstanceException;

    void addMatch(ITransferTeam home, ITransferTeam away, Date start, Date end) throws RemoteException, DomainException, NoSessionFoundException, InstantiationException, IllegalAccessException;
}
