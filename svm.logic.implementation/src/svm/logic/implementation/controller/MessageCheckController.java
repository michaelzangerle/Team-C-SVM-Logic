package svm.logic.implementation.controller;

import svm.logic.abstraction.controller.IMessageCheckController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.jmsobjects.IMemberMessage;
import svm.logic.abstraction.jmsobjects.ISubTeamMessage;
import svm.logic.abstraction.transferobjects.ITransferAuth;
import svm.logic.implementation.transferobject.TransferAuth;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;
import svm.persistence.abstraction.exceptions.NotSupportedException;

import java.rmi.RemoteException;

/**
 * ProjectTeam: Team C
 * Date: 24.11.12
 */
public class MessageCheckController implements IMessageCheckController {

    private ITransferAuth user;

    public MessageCheckController(ITransferAuth user) {
        this.user = user;
    }

    @Override
    public boolean myMemberMessage(IMemberMessage message) throws NoSessionFoundException {
        return (((TransferAuth) user).getModel().getUID() == message.getReceiverUID());
        /* ALT
        int sessionId = DomainFacade.generateSessionId();
        try {
            IMember messageMember = DomainFacade.getMemberModelDAO().getByUID(sessionId, message.getMember());
            DomainFacade.reattachObjectToSession(sessionId, messageMember.getSport());
            DomainFacade.reattachObjectToSession(sessionId, messageMember.getSport().getDepartment());
            messageMember = messageMember.getSport().getDepartment().getDepartmentHead();

            return (((TransferAuth) user).getModel().equals(messageMember));
        } finally {
            DomainFacade.closeSession(sessionId);
        }
        */
    }

    @Override
    public boolean mySubTeamMessage(ISubTeamMessage message) throws NoSessionFoundException {
        return (((TransferAuth) user).getModel().getUID() == message.getReceiverUID());
        /* ALT
        int sessionId = DomainFacade.generateSessionId();
        IMember member = DomainFacade.getMemberModelDAO().getByUID(sessionId, message.getMember());
        DomainFacade.closeSession(sessionId);
        return (((IHasModel<IMember>) member).getModel().equals(((TransferAuth) user).getModel()));
        */
    }

    @Override
    public void start() throws NoSessionFoundException, IllegalGetInstanceException, RemoteException, NotSupportedException, InstantiationException, IllegalAccessException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void commit() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException, RemoteException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void abort() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException, RemoteException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
