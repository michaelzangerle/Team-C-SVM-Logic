package svm.logic.abstraction;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.domain.abstraction.modelInterfaces.ISubTeam;
import svm.logic.abstraction.controller.ILoginController;
import svm.logic.abstraction.controller.ISearchController;
import svm.logic.abstraction.controller.ISubTeamConfirmationController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.ITransferAuth;
import svm.logic.implementation.controller.SubTeamConfirmationController;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;
import svm.persistence.abstraction.exceptions.NotSupportedException;

import java.rmi.RemoteException;

/**
 * Projectteam: Team C
 * Date: 26.11.12
 */
public class SubteamConfirmationControllerMain {

    public static void main(String[] args) throws NotSupportedException, IllegalGetInstanceException, NoSessionFoundException, IllegalAccessException, InstantiationException, RemoteException, ExistingTransactionException, NoTransactionException {

        ILoginController lc = LogicFacade.getLoginController();
        lc.start();
        lc.login("tf-test", "Pak3bGEh");
        ITransferAuth user = lc.getMember();
        lc.abort();


        ISearchController search = LogicFacade.getSearchController(user);
        search.start();
        Integer sessionId = DomainFacade.generateSessionId();
        IMember m = DomainFacade.getMemberModelDAO().getByUID(sessionId,10378);
        ISubTeam s = DomainFacade.getSubTeamModelDAO().getByUID(sessionId, 6);
        DomainFacade.closeSession(sessionId);


        ISubTeamConfirmationController controller = new SubTeamConfirmationController(user,m, s);
        controller.start();
        controller.setConfirmation(true, "testkommentar");
        controller.commit();


    }

}

