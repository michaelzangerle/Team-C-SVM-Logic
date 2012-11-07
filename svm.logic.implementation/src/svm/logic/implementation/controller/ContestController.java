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
 * Date: 30.10.12
 */
public class ContestController implements IContestController {

    private IContest contest;
    private Integer sessionId;
    private ITransferContest transferContest;
    private ITransferAuth user;

    public ContestController(IContest contest, ITransferAuth user) {
        this.contest = contest;
        this.user = user;
    }

    @Override
    public void setContestName(String name) throws DomainAttributeException {
        this.contest.setName(name);
    }

    @Override
    public void setContestStartDate(Date start) throws DomainParameterCheckException {
        this.contest.setStart(start);
    }

    @Override
    public void setContestEndDate(Date end) throws DomainParameterCheckException {
        this.contest.setEnd(end);
    }

    @Override
    public void setContestFee(float val) throws DomainParameterCheckException, DomainAttributeException {
        this.contest.setFee(val);
    }

    @Override
    public void start() throws NoSessionFoundException, IllegalGetInstanceException {
        this.sessionId = DomainFacade.generateSessionId();
        DomainFacade.reattachObjectToSession(this.sessionId, contest);
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
    public void setPhone1(String val) throws DomainAttributeException {
        this.contest.getContactDetails().setPhone1(val);
    }

    @Override
    public void setPhone2(String val) throws DomainAttributeException {
        this.contest.getContactDetails().setPhone2(val);
    }

    @Override
    public void setEmail1(String val) throws DomainAttributeException {
        this.contest.getContactDetails().setEmail1(val);
    }

    @Override
    public void setEmail2(String val) throws DomainAttributeException {
        this.contest.getContactDetails().setEmail2(val);
    }

    @Override
    public void setFax(String val) throws DomainAttributeException {
        this.contest.getContactDetails().setFax(val);
    }

    @Override
    public void setStreet(String val) throws DomainAttributeException {
        this.contest.getContactDetails().setStreet(val);
    }

    @Override
    public void setStreetNumber(String val) throws DomainAttributeException {
        this.contest.getContactDetails().setStreetNumber(val);
    }

    @Override
    public void setLat(String val) throws DomainAttributeException {
        this.contest.getContactDetails().setCoordLat(val);
    }

    @Override
    public void setLong(String val) throws DomainAttributeException {
        this.contest.getContactDetails().setCoordLong(val);
    }

    @Override
    public void setLocation(ITransferLocation location) throws DomainAttributeException {
        ILocation l = ((IHasModel<ILocation>) location).getModel();
        this.contest.getContactDetails().setLocation(l);
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
            throw new LogicException("Neither internal nor external team!");
        }


        this.contest.addMatch(match);
    }

    @Override
    public void setDateForMatch(ITransferMatch match, Date start) {
        IMatch m = ((IHasModel<IMatch>) match).getModel();
        m.setStart(start);
    }

    @Override
    public void setResult(ITransferMatch match, Integer home, Integer away) throws NoSessionFoundException, DomainException, InstantiationException, IllegalAccessException {
        IMatch m = ((IHasModel<IMatch>) match).getModel();
        m.setResult(home, away);
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
            System.out.println(t.getName());
            result.add((ITransferInternalTeam) TransferObjectCreator.getInstance(TransferInternalTeam.class, t));
        }

        for (IExternalTeam t : externalTeams) {
            System.out.println(t.getName());
            result.add((ITransferExternalTeam) TransferObjectCreator.getInstance(TransferExternalTeam.class, t));
        }

        return result;
    }


    @Override
    public List<ITransferMatch> getMatches() throws RemoteException, IllegalGetInstanceException {
        List<IMatch> matches = this.contest.getMatches();
        List<ITransferMatch> result = new LinkedList<ITransferMatch>();

        for (IMatch m : matches) {
            System.out.println(m.getName());
            result.add((ITransferMatch) TransferObjectCreator.getInstance(TransferMatch.class, m));
        }

        return result;
    }
}
