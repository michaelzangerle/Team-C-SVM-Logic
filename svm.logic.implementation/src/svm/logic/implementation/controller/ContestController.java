package svm.logic.implementation.controller;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.exception.DomainAttributeException;
import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.domain.abstraction.modelInterfaces.IContest;
import svm.domain.abstraction.modelInterfaces.ILocation;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.logic.abstraction.controller.IContestController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferContest;
import svm.logic.abstraction.transferobjects.ITransferLocation;
import svm.logic.implementation.tranferobjects.TransferContest;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;

import java.util.Date;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public class ContestController implements IContestController {

    private IContest contest;
    private Integer sessionId;
    private ITransferContest transferContest;
    private IMember user;

    public ContestController(IContest contest, IMember user) {
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
}
