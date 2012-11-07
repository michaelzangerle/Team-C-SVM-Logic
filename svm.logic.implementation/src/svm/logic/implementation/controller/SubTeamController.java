package svm.logic.implementation.controller;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.exception.DomainException;
import svm.domain.abstraction.modelInterfaces.IContest;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.domain.abstraction.modelInterfaces.ISubTeam;
import svm.domain.abstraction.modelInterfaces.ITeam;
import svm.logic.abstraction.controller.ISubTeamController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.exception.LogicException;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferAuth;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.abstraction.transferobjects.ITransferSubTeam;
import svm.logic.implementation.tranferobjects.TransferSubTeam;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;

/**
 * ProjectTeam: Team C
 * Date: 01.11.12
 */
public class SubTeamController implements ISubTeamController {

    private ISubTeam subTeam;
    private ITransferSubTeam transferSubTeam;
    private Integer sessionId;
    private ITransferAuth user;

    public SubTeamController(ITeam team, IContest contest, ITransferAuth user) throws NoSessionFoundException, IllegalAccessException, InstantiationException {
        this.subTeam = DomainFacade.getSubTeamModelDAO().generateObject();
        this.subTeam.setContest(contest);
        this.subTeam.setTeam(team);
        this.user = user;
    }

    public SubTeamController(ISubTeam subTeam, ITransferAuth user) {
        this.subTeam = subTeam;
        this.user = user;
    }

    @Override
    public ITransferSubTeam getSubTeam() {
        return transferSubTeam;
    }

    @Override
    public void setName(String name) {
        subTeam.setName(name);
    }

    @Override
    public void addMember(ITransferMember member) throws LogicException, NoSessionFoundException, DomainException, IllegalAccessException, InstantiationException {
        this.subTeam.addMember(((IHasModel<IMember>) member).getModel());
    }

    @Override
    public void removeMember(ITransferMember member) {
        this.subTeam.removeMember(((IHasModel<IMember>) member).getModel());
    }

    @Override
    public void start() throws NoSessionFoundException, IllegalGetInstanceException {
        this.sessionId = DomainFacade.generateSessionId();
        DomainFacade.reattachObjectToSession(sessionId, ((IHasModel) this.subTeam).getModel());
        this.transferSubTeam = (ITransferSubTeam) TransferObjectCreator.getInstance(TransferSubTeam.class, subTeam);
    }

    @Override
    public void commit() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException {
        DomainFacade.startTransaction(sessionId);
        DomainFacade.getSubTeamModelDAO().saveOrUpdate(sessionId, this.subTeam);
        DomainFacade.commitTransaction(sessionId);
        DomainFacade.closeSession(sessionId);
    }

    @Override
    public void abort() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException {
        DomainFacade.closeSession(sessionId);
    }
}
