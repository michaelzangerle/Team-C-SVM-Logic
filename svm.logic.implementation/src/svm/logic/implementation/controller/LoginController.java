package svm.logic.implementation.controller;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.logic.abstraction.controller.ILoginController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.implementation.tranferobjects.TransferMember;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Projectteam: Team C
 * Date: 02.11.12
 */
public class LoginController implements ILoginController {
    private IMember member;
    private Integer sessionId;
    private ITransferMember transferMember;

    public LoginController() {
    }

    @Override
    public void start() throws NoSessionFoundException, IllegalGetInstanceException {
        this.sessionId = DomainFacade.generateSessionId();
    }

    @Override
    public void commit() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException {
        DomainFacade.closeSession(this.sessionId);
    }

    @Override
    public void abort() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException {
        DomainFacade.closeSession(this.sessionId);
    }

    @Override
    public boolean login(String userName, String password) throws RemoteException, IllegalGetInstanceException, NoSessionFoundException {
        List<IMember> members = DomainFacade.getMemberModelDAO().get(sessionId, userName);
        if (members.size() == 1) {
            this.member = members.get(0);
            this.transferMember = (ITransferMember) TransferObjectCreator.getInstance(TransferMember.class, member);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ITransferMember getMember() throws RemoteException {
        return transferMember;
    }
}
