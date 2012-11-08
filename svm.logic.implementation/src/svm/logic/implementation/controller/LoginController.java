package svm.logic.implementation.controller;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.logic.abstraction.controller.ILoginController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.ITransferAuth;
import svm.logic.implementation.tranferobjects.TransferAuth;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;
import svm.logic.implementation.ldap.*;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Projectteam: Team C
 * Date: 02.11.12
 */
public class LoginController implements ILoginController {
    private IMember member;
    private Integer sessionId;
    private ITransferAuth transferMember;

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
      if(!LdapChecker.authentication(userName,password))
      {
          System.out.println("Login false");
          return false;
      }
        System.out.println("Login ok");

        List<IMember> members = DomainFacade.getMemberModelDAO().get(sessionId, userName);
        if (members.size() == 1) {
            this.member = members.get(0);
            this.transferMember = (ITransferAuth) TransferObjectCreator.getInstance(TransferAuth.class, member);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean loginWithoutLdap(String userName, String password) throws RemoteException, IllegalGetInstanceException, NoSessionFoundException {
        List<IMember> members = DomainFacade.getMemberModelDAO().get(sessionId, userName);
        if (members.size() == 1) {
            this.member = members.get(0);
            this.transferMember = (ITransferAuth) TransferObjectCreator.getInstance(TransferAuth.class, member);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ITransferAuth getMember() throws RemoteException {
        return transferMember;
    }
}
