import svm.domain.abstraction.exception.DomainException;
import svm.logic.abstraction.LogicFacade;
import svm.logic.abstraction.controller.IContestController;
import svm.logic.abstraction.controller.ILoginController;
import svm.logic.abstraction.controller.ISearchController;
import svm.logic.abstraction.controller.ISubTeamController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.exception.LogicException;
import svm.logic.abstraction.exception.NotAllowException;
import svm.logic.abstraction.transferobjects.ITransferAuth;
import svm.logic.abstraction.transferobjects.ITransferContest;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.abstraction.transferobjects.ITransferTeam;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;

import javax.transaction.NotSupportedException;
import java.rmi.RemoteException;

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
        ITransferTeam team = contestController.getTeams().get(0);
        ISubTeamController subTeamController = LogicFacade.getSubTeamController(user, team, contest);
        subTeamController.start();
        for (ITransferMember member : subTeamController.getMemberOfTeam()) {
            System.out.println(member.getFirstName());
        }
        ITransferMember m1 = subTeamController.getMemberOfTeam().get(0);
        ITransferMember m2 = subTeamController.getMemberOfTeam().get(1);
        subTeamController.addMember(m1);
        subTeamController.addMember(m2);
        subTeamController.commit();
        contestController.commit();
    }
}
