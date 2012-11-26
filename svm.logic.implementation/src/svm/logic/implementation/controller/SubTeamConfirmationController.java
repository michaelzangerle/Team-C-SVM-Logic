package svm.logic.implementation.controller;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.domain.abstraction.modelInterfaces.ISubTeam;
import svm.domain.abstraction.modelInterfaces.ISubTeamsHasMembers;
import svm.logic.abstraction.controller.ISubTeamConfirmationController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.*;
import svm.logic.implementation.transferobject.TransferMember;
import svm.logic.implementation.transferobject.TransferSubTeam;
import svm.logic.implementation.transferobject.TransferSubTeamHasMember;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;

import java.util.LinkedList;
import java.util.List;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public class SubTeamConfirmationController implements ISubTeamConfirmationController {

    private IMember member;
    private ISubTeam subteam;
    private Integer sessionId;

    private ITransferMember transferMember;
    private ITransferAuth user;
    private ITransferSubTeam transferSubTeam;

    public SubTeamConfirmationController(ITransferAuth user, IMember member, ISubTeam subteam) {
        this.subteam = subteam;
        this.member = member;
        this.user = user;
    }

    @Override
    public ITransferMember getMember() {
        return this.transferMember;
    }

    @Override
    public void setConfirmation(boolean confirm, String comment) {

        for (ISubTeamsHasMembers tmp : this.member.getSubTeamsHasMembersForPerson()) {
            if(tmp.getSubTeam().equals(this.subteam)){
                tmp.setComment(comment);
                tmp.setConfirmed(confirm);
                return;
            }
        }
    }

    @Override
    public void start() throws NoSessionFoundException, IllegalGetInstanceException {
        this.sessionId = DomainFacade.generateSessionId();
        DomainFacade.reattachObjectToSession(this.sessionId, member);
        DomainFacade.reattachObjectToSession(this.sessionId, subteam);

        this.transferMember = (ITransferMember) TransferObjectCreator.getInstance(TransferMember.class, member);
        this.transferSubTeam = (ITransferSubTeam) TransferObjectCreator.getInstance(TransferSubTeam.class, subteam);
    }

    @Override
    public void commit() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException {
        DomainFacade.startTransaction(this.sessionId);
        DomainFacade.getMemberModelDAO().saveOrUpdate(sessionId, member);
        DomainFacade.getSubTeamModelDAO().saveOrUpdate(sessionId, subteam);
        DomainFacade.commitTransaction(this.sessionId);
        DomainFacade.closeSession(this.sessionId);
    }

    @Override
    public void abort() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException {
        DomainFacade.startTransaction(this.sessionId);
        DomainFacade.abortTransaction(this.sessionId);
        DomainFacade.closeSession(this.sessionId);
    }
}
