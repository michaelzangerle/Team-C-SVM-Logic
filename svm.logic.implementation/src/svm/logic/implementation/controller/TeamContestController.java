package svm.logic.implementation.controller;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.exception.DomainException;
import svm.domain.abstraction.modelInterfaces.*;
import svm.domain.abstraction.modeldao.IMatchModelDAO;
import svm.logic.abstraction.controller.ITeamContestController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.exception.LogicException;
import svm.logic.abstraction.transferobjects.*;
import svm.logic.implementation.tranferobjects.TransferContest;
import svm.logic.implementation.tranferobjects.TransferExternalTeam;
import svm.logic.implementation.tranferobjects.TransferInternalTeam;
import svm.logic.implementation.tranferobjects.TransferMatch;
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
    private IMember user;

    public TeamContestController(IContest contest, IMember user) {
        this.contest = contest;
        this.user = user;
    }


    @Override
    public void addMatch(ITransferTeam home, ITransferTeam away, Date start, Date end) throws RemoteException, DomainException, NoSessionFoundException, InstantiationException, IllegalAccessException, LogicException {

        IMatchModelDAO matchDao = DomainFacade.getMatchModelDAO();
        IMatch match = matchDao.generateObject();
        match.setStart(start);
        match.setEnd(end);

        if (home instanceof ITransferExternalTeam && away instanceof ITransferExternalTeam) {
            IExternalTeam a = ((IHasModel<IExternalTeam>) home).getModel();
            IExternalTeam b = ((IHasModel<IExternalTeam>) away).getModel();
            match.setContestants(a, b);
        } else if (home instanceof ITransferInternalTeam && away instanceof ITransferInternalTeam) {
            ITeam a = ((IHasModel<ITeam>) home).getModel();
            ITeam b = ((IHasModel<ITeam>) away).getModel();
            match.setContestants(a, b);
        } else if (home instanceof ITransferInternalTeam && away instanceof ITransferExternalTeam) {
            ITeam a = ((IHasModel<ITeam>) home).getModel();
            IExternalTeam b = ((IHasModel<IExternalTeam>) away).getModel();
            match.setContestants(a, b);
        } else if (home instanceof ITransferExternalTeam && away instanceof ITransferInternalTeam) {
            IExternalTeam a = ((IHasModel<IExternalTeam>) home).getModel();
            ITeam b = ((IHasModel<ITeam>) away).getModel();
            match.setContestants(a, b);
        } else {
            throw new LogicException("Wehter internal nor external team!");
        }


        this.contest.addMatch(match);
    }

    @Override
    public void addResult(ITransferMatch match, Integer home, Integer away) throws RemoteException, NoSessionFoundException, DomainException, IllegalAccessException, InstantiationException {
        IMatch m = ((IHasModel<IMatch>) match).getModel();
        m.addResult(home, away);
    }

    @Override
    public void addTeam(ITransferTeam team) throws RemoteException, DomainException, NoSessionFoundException, InstantiationException, IllegalAccessException {

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
        List<IExternalTeam> externalTeams = this.contest.getExternalTeams();

        for (ITeam t : internalTeams) {
            result.add((ITransferInternalTeam) TransferObjectCreator.getInstance(TransferInternalTeam.class, t));
        }

        for (IExternalTeam t : externalTeams) {
            result.add((ITransferExternalTeam) TransferObjectCreator.getInstance(TransferExternalTeam.class, t));
        }

        return result;
    }


    @Override
    public List<ITransferMatch> getMatches() throws RemoteException, IllegalGetInstanceException {
        List<IMatch> matches = this.contest.getMatches();
        List<ITransferMatch> result = new LinkedList<ITransferMatch>();

        for (IMatch m : matches) {

            result.add((ITransferMatch) TransferObjectCreator.getInstance(TransferMatch.class, m));
        }

        return result;
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
