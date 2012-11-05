package svm.logic.implementation.controller;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.exception.DomainException;
import svm.domain.abstraction.modelInterfaces.IContest;
import svm.domain.abstraction.modelInterfaces.IExternalTeam;
import svm.domain.abstraction.modelInterfaces.IMatch;
import svm.domain.abstraction.modelInterfaces.ITeam;
import svm.logic.abstraction.controller.ITeamContestController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.*;
import svm.logic.implementation.tranferobjects.TransferContest;
import svm.logic.implementation.tranferobjects.TransferExternalTeam;
import svm.logic.implementation.tranferobjects.TransferInternalTeam;
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
        if (home instanceof ITransferExternalTeam) {
            IExternalTeam a = ((IHasModel<IExternalTeam>) home).getModel();

        } else if (home instanceof ITransferInternalTeam) {
            ITeam a = ((IHasModel<ITeam>) home).getModel();
        }

        if (away instanceof ITransferExternalTeam) {
            IExternalTeam b = ((IHasModel<IExternalTeam>) away).getModel();

        } else if (away instanceof ITransferInternalTeam) {
            ITeam b = ((IHasModel<ITeam>) away).getModel();
        }

        IMatchModelDAO matchDao = DomainFacade.getMatchModelDAO();
        IMatch match = matchDao.generateObject();
        match.setStart(start);

        match.setEnd(end);
        match.setContestants(a, b);
        this.contest.addMatch(match);
    }

    @Override
    public void addResult(ITransferMatch match, Integer home, Integer away) throws RemoteException {
        IMatch m = ((IHasModel<IMatch>) match).getModel();
        m.addResult(home, away);
    }

    @Override
    public void addTeam(ITransferTeam team) throws RemoteException, DomainException {

        if (team instanceof ITransferExternalTeam) {
            IExternalTeam t = ((IHasModel<IExternalTeam>) team).getModel();
            this.contest.addExternalTeam(t);

        } else if (team instanceof ITransferInternalTeam) {
            ITeam t = ((IHasModel<ITeam>) team).getModel();
            this.contest.addInternalTeam(t);
        }
    }

    @Override
    public void removeTeam(ITransferTeam team) throws RemoteException, DomainException {

        if (team instanceof ITransferExternalTeam) {
            IExternalTeam t = ((IHasModel<IExternalTeam>) team).getModel();
            this.contest.removeExternalTeam(t);

        } else if (team instanceof ITransferInternalTeam) {
            ITeam t = ((IHasModel<ITeam>) team).getModel();
            this.contest.removeInternalTeam(t);
        }
    }

    @Override
    public List<ITransferTeam> getTeams() throws RemoteException, IllegalGetInstanceException {
        List<ITransferTeam> result = new LinkedList<ITransferTeam>();
        List<ITeam> internalTeams = this.contest.getTeams();
        List<ITeam> externalTeams = this.contest.getExternalTeams();

        for (ITeam t : internalTeams) {
            result.add((ITransferInternalTeam) TransferObjectCreator.getInstance(TransferInternalTeam.class, t));
        }

        for (ITeam t : externalTeams) {
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
