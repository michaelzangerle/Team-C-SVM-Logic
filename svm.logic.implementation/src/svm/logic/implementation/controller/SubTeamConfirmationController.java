package svm.logic.implementation.controller;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.domain.abstraction.modelInterfaces.ISubTeamsHasMembers;
import svm.logic.abstraction.controller.ISubTeamConfirmationController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.abstraction.transferobjects.ITransferSubTeamHasMember;
import svm.logic.implementation.tranferobjects.TransferMember;
import svm.logic.implementation.tranferobjects.TransferSubTeamHasMember;
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
    private Integer sessionId;
    private ITransferMember transferMember;
    private IMember user;

    public SubTeamConfirmationController(IMember member, IMember user) {
        this.member = member;
        this.user = user;
    }

    @Override
    public ITransferMember getMember() {
        return this.transferMember;
    }

    @Override
    public List<ITransferSubTeamHasMember> getSubTeamsOfMember() throws IllegalGetInstanceException {

        List<ITransferSubTeamHasMember> result = new LinkedList<ITransferSubTeamHasMember>();

        for (ISubTeamsHasMembers tmp : this.member.getSubTeamsHasMembersForPerson()) {
            result.add((ITransferSubTeamHasMember) TransferObjectCreator.getInstance(TransferSubTeamHasMember.class, tmp));
        }

        return result;

    }

    @Override
    public void setConfirmationForSubTeam(ITransferSubTeamHasMember subTeamHasMember, boolean confirmation, String comment) {
        ISubTeamsHasMembers tmp = ((IHasModel<ISubTeamsHasMembers>) subTeamHasMember).getModel();
        tmp.setComment(comment);
        tmp.setConfirmed(confirmation);
    }

    @Override
    public void start() throws NoSessionFoundException, IllegalGetInstanceException {
        this.sessionId = DomainFacade.generateSessionId();
        DomainFacade.reattachObjectToSession(this.sessionId, member);

        this.transferMember = (ITransferMember) TransferObjectCreator.getInstance(TransferMember.class, member);
    }

    @Override
    public void commit() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException {
        DomainFacade.startTransaction(this.sessionId);
        DomainFacade.getMemberModelDAO().saveOrUpdate(sessionId, member);
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
