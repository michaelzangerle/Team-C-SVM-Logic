package svm.logic.abstraction;

import svm.domain.abstraction.exception.DomainAttributeException;
import svm.domain.abstraction.exception.DomainException;
import svm.logic.abstraction.controller.IContestController;
import svm.logic.abstraction.controller.ILoginController;
import svm.logic.abstraction.controller.ISearchController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.ITransferContest;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.abstraction.transferobjects.ITransferTeam;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;

import java.rmi.RemoteException;

/**
 * Projectteam: Team C
 * Date: 31.10.12
 */
public class ContestControllerMain {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, DomainException, DomainAttributeException, IllegalGetInstanceException, NoSessionFoundException, ExistingTransactionException, NoTransactionException, RemoteException {

        ILoginController login = LogicFacade.getLoginController();
        login.start();
        login.login("jwa8658", "");

        ITransferMember user = login.getMember();

        ISearchController searchController = LogicFacade.getSearchController(user);
        searchController.start();

        ITransferContest contest = searchController.getContests().get(0);
        ITransferTeam team = searchController.getTeams().get(0);
        searchController.abort();

        IContestController contestController = LogicFacade.getContestController(user, contest);

        contestController.start();

        contestController.removeTeam(team);

        contestController.commit();


    }

}
