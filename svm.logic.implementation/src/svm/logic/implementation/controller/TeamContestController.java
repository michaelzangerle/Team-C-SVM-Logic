package svm.logic.implementation.controller;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.exception.DomainException;
import svm.domain.abstraction.modelInterfaces.IContest;
import svm.domain.abstraction.modelInterfaces.IMatch;
import svm.domain.abstraction.modelInterfaces.ITeam;
import svm.logic.abstraction.controller.ITeamContestController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferContest;
import svm.logic.abstraction.transferobjects.ITransferMatch;
import svm.logic.abstraction.transferobjects.ITransferTeam;
import svm.logic.implementation.tranferobjects.TransferContest;
import svm.logic.implementation.tranferobjects.TransferTeam;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Projectteam: Team C
 * Date: 05.11.12
 */
public class TeamContestController implements ITeamContestController {

    private Integer sessionId;
    private IContest contest;
    private ITransferContest transferContest;

    public TeamContestController(IContest contest) {
        this.contest = contest;
    }


    @Override
    public void addMatch(ITransferTeam home, ITransferTeam away, Date start, Date end) throws RemoteException, DomainException {

        // TODO
        //ITeam team1 = ((IHasModel<ITeam>) home).getModel();
        //ITeam team2 = ((IHasModel<ITeam>) away).getModel();

        IMatchModelDAO matchDao = DomainFacade.getMatchModelDAO();
        IMatch match = matchDao.generateObject();
        match.setStart(start);
        match.setEnd(end);
        match.setContestants(team1, team2);

        this.contest.addMatch(match);
    }

    @Override
    public void addResult(ITransferMatch match, Integer home, Integer away) throws RemoteException {
        IMatch m = ((IHasModel<IMatch>) match).getModel();
        m.addResult(home, away);
    }

    @Override
    public void addTeam(ITransferTeam team) throws RemoteException {
        ITeam m = ((IHasModel<ITeam>) team).getModel();
        // TODO
        //this.contest.add
    }

    @Override
    public void removeTeam(ITransferTeam team) throws RemoteException {
        ITeam m = ((IHasModel<ITeam>) team).getModel();
        // TODO
        //this.contest.add
    }

    @Override
    public List<ITransferTeam> getTeams() throws RemoteException {
        List<ITransferTeam> result = new LinkedList<ITransferTeam>();
        List<ITeam> internalTeams = this.contest.getTeams();
        List<ITeam> externalTeams = this.contest.getExternalTeams();

        for (ITeam t : internalTeams) {
            result.add((ITransferInternalTeam) TransferObjectCreator.getInstance(TransferInternalTeam.class, t));
        }

        for (ITeam t : externalTeamsTeams) {
            result.add((ITransferExternalTeam) TransferObjectCreator.getInstance(TransferExternalTeam.class, t));
        }

        return result;
    }


    @Override
    public List<ITransferMatch> getMatches() throws RemoteException {
        return this.contest.getMatches();
    }


    @Override
    public void start() throws NoSessionFoundException, IllegalGetInstanceException {
        this.sessionId = DomainFacade.generateSessionId();
        DomainFacade.reattachObjectToSession(sessionId, ((IHasModel) this.contest).getModel());
        this.transferContest = (ITransferContest) TransferObjectCreator.getInstance(TransferContest.class, contest);
    }

    @Override
    public void commit() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException {
        DomainFacade.startTransaction(sessionId);
        DomainFacade.getContestModelDAO().saveOrUpdate(sessionId, this.contest);
        DomainFacade.commitTransaction(sessionId);
        DomainFacade.closeSession(sessionId);
    }

    @Override
    public void abort() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException {
        DomainFacade.closeSession(sessionId);
    }
}
