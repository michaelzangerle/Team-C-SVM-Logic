import svm.domain.abstraction.exception.DomainException;
import svm.logic.abstraction.LogicFacade;
import svm.logic.abstraction.controller.IContestController;
import svm.logic.abstraction.controller.ILoginController;
import svm.logic.abstraction.controller.ISearchController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.exception.LogicException;
import svm.logic.abstraction.exception.NotAllowException;
import svm.logic.abstraction.transferobjects.ITransferAuth;
import svm.logic.abstraction.transferobjects.ITransferContest;
import svm.logic.abstraction.transferobjects.ITransferMatch;
import svm.logic.abstraction.transferobjects.ITransferTeam;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;

import javax.transaction.NotSupportedException;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * ProjectTeam: Team C
 * Date: 07.11.12
 */
public class MAIN_HANNES_NIX_AENDERN {
    public static void main(String[] args) throws RemoteException, IllegalGetInstanceException, NoSessionFoundException, ExistingTransactionException, NoTransactionException, InstantiationException, IllegalAccessException, LogicException, NotAllowException, DomainException, NotSupportedException, svm.persistence.abstraction.exceptions.NotSupportedException {
        ILoginController lc = LogicFacade.getLoginController();
        lc.start();
        lc.loginWithoutLdap("tf-test", "Pak3bGEh");
        ITransferAuth user = lc.getMember();
        lc.abort();

        ISearchController searchController = LogicFacade.getSearchController(user);
        searchController.start();
        for (ITransferContest contest : searchController.getContests()) {
            System.out.println(contest.getName());
        }

        ITransferContest contest = searchController.getContests().get(0);
        searchController.abort();

        IContestController contestController = LogicFacade.getContestController(user, contest);
        contestController.start();

        for (ITransferMatch t : contestController.getMatches()) {
            System.out.println(t.getName());
        }

        ITransferTeam t1 = contestController.getTeams().get(0);
        ITransferTeam t2 = contestController.getTeams().get(1);

        contestController.addMatch(t1, t2, new Date(), new Date());

        contestController.commit();

        contestController.start();
        contestController.commit();
    }
}
