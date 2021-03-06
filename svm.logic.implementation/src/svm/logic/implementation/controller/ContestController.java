package svm.logic.implementation.controller;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.exception.DomainAttributeException;
import svm.domain.abstraction.exception.DomainException;
import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.domain.abstraction.modelInterfaces.*;
import svm.domain.abstraction.modeldao.IMatchModelDAO;
import svm.logic.abstraction.controller.IContestController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.exception.LogicException;
import svm.logic.abstraction.exception.NotAllowException;
import svm.logic.abstraction.transferobjects.*;
import svm.logic.implementation.transferobject.TransferContest;
import svm.logic.implementation.transferobject.TransferExternalTeam;
import svm.logic.implementation.transferobject.TransferInternalTeam;
import svm.logic.implementation.transferobject.TransferMatch;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;
import svm.persistence.abstraction.exceptions.NotSupportedException;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public class ContestController implements IContestController {

    private IContest contest = null;
    private Integer sessionId;
    private ITransferContest transferContest;
    private ITransferAuth user;

    public ContestController(IContest contest, ITransferAuth user) {
        this.contest = contest;
        this.user = user;
    }

    public ContestController(ITransferAuth user) {
        this.user = user;
    }

    @Override
    public void setContestName(String name) throws DomainAttributeException, NotAllowException {
        if (!user.isAllowedForContestDetailsChanging())
            throw new NotAllowException("Wrong privileges");

        this.contest.setName(name);

    }

    @Override
    public void setContestStartDate(Date start) throws DomainParameterCheckException, NotAllowException {
        if (!user.isAllowedForContestDetailsChanging())
            throw new NotAllowException("Wrong privileges");

        this.contest.setStart(start);
    }

    @Override
    public void setContestEndDate(Date end) throws DomainParameterCheckException, NotAllowException {
        if (!user.isAllowedForContestDetailsChanging())
            throw new NotAllowException("Wrong privileges");

        this.contest.setEnd(end);
    }

    @Override
    public void setContestFee(float val) throws DomainParameterCheckException, DomainAttributeException, NotAllowException {
        if (!user.isAllowedForContestDetailsChanging())
            throw new NotAllowException("Wrong privileges");

        this.contest.setFee(val);
    }

    @Override
    public void start() throws NoSessionFoundException, IllegalGetInstanceException, NotSupportedException, InstantiationException, IllegalAccessException {
        this.sessionId = DomainFacade.generateSessionId();
        if (this.contest == null) {
            contest = DomainFacade.getContestModelDAO().generateObject(sessionId);
        }
        DomainFacade.reattachObjectToSession(this.sessionId, contest);
        for (IMatch m : contest.getMatches()) {
            DomainFacade.reattachObjectToSession(sessionId, m);
        }
        this.transferContest = (ITransferContest) TransferObjectCreator.getInstance(TransferContest.class, contest);
    }

    @Override
    public void commit() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException {
        DomainFacade.startTransaction(this.sessionId);
        DomainFacade.getContestModelDAO().saveOrUpdate(sessionId, contest);
        DomainFacade.commitTransaction(this.sessionId);
        DomainFacade.closeSession(this.sessionId);
    }

    @Override
    public void abort() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException {
        DomainFacade.startTransaction(this.sessionId);
        DomainFacade.abortTransaction(this.sessionId);
        DomainFacade.closeSession(this.sessionId);
    }

    @Override
    public ITransferContest getTransferContest() {
        return transferContest;
    }

    @Override
    public void setPhone1(String val) throws DomainAttributeException, NotAllowException {
        if (!user.isAllowedForContestDetailsChanging())
            throw new NotAllowException("Wrong privileges");

        this.contest.getContactDetails().setPhone1(val);
    }

    @Override
    public void setPhone2(String val) throws DomainAttributeException, NotAllowException {
        if (!user.isAllowedForContestDetailsChanging())
            throw new NotAllowException("Wrong privileges");

        this.contest.getContactDetails().setPhone2(val);
    }

    @Override
    public void setEmail1(String val) throws DomainAttributeException, NotAllowException {
        if (!user.isAllowedForContestDetailsChanging())
            throw new NotAllowException("Wrong privileges");

        this.contest.getContactDetails().setEmail1(val);
    }

    @Override
    public void setEmail2(String val) throws DomainAttributeException, NotAllowException {
        if (!user.isAllowedForContestDetailsChanging())
            throw new NotAllowException("Wrong privileges");

        this.contest.getContactDetails().setEmail2(val);
    }

    @Override
    public void setFax(String val) throws DomainAttributeException, NotAllowException {
        if (!user.isAllowedForContestDetailsChanging())
            throw new NotAllowException("Wrong privileges");

        this.contest.getContactDetails().setFax(val);
    }

    @Override
    public void setStreet(String val) throws DomainAttributeException, NotAllowException {
        if (!user.isAllowedForContestDetailsChanging())
            throw new NotAllowException("Wrong privileges");

        this.contest.getContactDetails().setStreet(val);
    }

    @Override
    public void setStreetNumber(String val) throws DomainAttributeException, NotAllowException {
        if (!user.isAllowedForContestDetailsChanging())
            throw new NotAllowException("Wrong privileges");

        this.contest.getContactDetails().setStreetNumber(val);
    }

    @Override
    public void setLat(String val) throws DomainAttributeException, NotAllowException {
        if (!user.isAllowedForContestDetailsChanging())
            throw new NotAllowException("Wrong privileges");

        this.contest.getContactDetails().setCoordLat(val);
    }

    @Override
    public void setLong(String val) throws DomainAttributeException, NotAllowException {
        if (!user.isAllowedForContestDetailsChanging())
            throw new NotAllowException("Wrong privileges");

        this.contest.getContactDetails().setCoordLong(val);
    }

    @Override
    public void setLocation(ITransferLocation location) throws DomainAttributeException, NotAllowException {
        if (!user.isAllowedForContestDetailsChanging())
            throw new NotAllowException("Wrong privileges");

        ILocation l = ((IHasModel<ILocation>) location).getModel();
        this.contest.getContactDetails().setLocation(l);
    }

    @Override
    public void addMatch(ITransferTeam home, ITransferTeam away, Date start, Date end) throws RemoteException, DomainException, NoSessionFoundException, InstantiationException, IllegalAccessException, LogicException, NotAllowException, NotSupportedException {
        if (!user.isAllowedForContestMatchAdding())
            throw new NotAllowException("Wrong privileges");

        IMatchModelDAO matchDao = DomainFacade.getMatchModelDAO();
        IMatch match = matchDao.generateObject(sessionId);
        match.setStart(start);
        match.setEnd(end);
        match.setName(home.getName() + " - " + away.getName());

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
            throw new LogicException("Neither internal nor external team!");
        }

        match.setContest(this.contest);
        this.contest.addMatch(match);
    }

    @Override
    public void setDateForMatch(ITransferMatch match, Date start) throws NotAllowException {
        if (!user.isAllowedForContestResultChanging())
            throw new NotAllowException("Wrong privileges");

        IMatch m = null;
        IMatch toSearch = ((IHasModel<IMatch>) match).getModel();
        for (IMatch x : contest.getMatches()) {
            if (x.equals(toSearch)) m = x;
        }

        if (m != null) {
            m.setStart(start);
            m.setEnd(start);
        }
    }

    @Override
    public void setResult(ITransferMatch match, Integer home, Integer away) throws NoSessionFoundException, DomainException, InstantiationException, IllegalAccessException, NotAllowException {
        if (!user.isAllowedForContestResultChanging())
            throw new NotAllowException("Wrong privileges");

        IMatch m = null;
        IMatch toSearch = ((IHasModel<IMatch>) match).getModel();
        for (IMatch x : contest.getMatches()) {
            if (x.equals(toSearch)) m = x;
        }

        if (m != null) {
            m.setResult(home, away);
            System.out.println(m.getAwayInternal().getName() + " - " + m.getHomeInternal().getName() + ": " +
                    m.getHomeResult() + " : " + m.getAwayResult());
        }
    }

    @Override
    public void setSport(ITransferSport sport) throws NotAllowException {
        if (!user.isAllowedForContestDetailsChanging())
            throw new NotAllowException("Wrong privileges");

        this.contest.setSport(((IHasModel<ISport>) sport).getModel());

    }

    @Override
    public void setFinished(boolean finished) {
        this.contest.setFinished(finished);
    }

    @Override
    public void addTeam(ITransferTeam team) throws RemoteException, DomainException, NoSessionFoundException, InstantiationException, IllegalAccessException, NotAllowException {
        if (!user.isAllowedForContestTeamsChanging())
            throw new NotAllowException("Wrong privileges");


        if (team instanceof ITransferExternalTeam) {
            IExternalTeam t = ((IHasModel<IExternalTeam>) team).getModel();
            this.contest.addExternalTeam(t);

        } else if (team instanceof ITransferInternalTeam) {
            ITeam t = ((IHasModel<ITeam>) team).getModel();
            this.contest.addInternalTeam(t);
        }
    }

    @Override
    public void removeTeam(ITransferTeam team) throws RemoteException, DomainException, NotAllowException {

        if (!user.isAllowedForContestTeamsChanging())
            throw new NotAllowException("Wrong privileges");


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
    public List<ITransferTeam> getPossibleTeams() throws NoSessionFoundException, IllegalGetInstanceException {
        List<ITransferTeam> result = new LinkedList<ITransferTeam>();

        for (ITeam team : DomainFacade.getTeamModelDAO().getAll(sessionId)) {
            if (isPossible(team)) {
                result.add((ITransferInternalTeam) TransferObjectCreator.getInstance(TransferInternalTeam.class, team));
            }
        }

        for (IExternalTeam extTeam : DomainFacade.getExternalTeamModelDAO().getAll(sessionId)) {
            result.add((ITransferExternalTeam) TransferObjectCreator.getInstance(TransferExternalTeam.class, extTeam));
        }
        return result;
    }

    private boolean isPossible(ITeam team) {
        if (contest.getSport().isNull()) return true;
        return team.getSport().equals(contest.getSport());
    }


}
