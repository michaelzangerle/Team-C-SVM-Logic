import svm.domain.abstraction.exception.DomainAttributeException;
import svm.domain.abstraction.exception.DomainException;
import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.logic.abstraction.LogicFacade;
import svm.logic.abstraction.controller.*;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.exception.LogicException;
import svm.logic.abstraction.exception.NotAllowException;
import svm.logic.abstraction.jmsobjects.IMemberMessage;
import svm.logic.abstraction.jmsobjects.IMessageObserver;
import svm.logic.abstraction.jmsobjects.ISubTeamMessage;
import svm.logic.abstraction.transferobjects.*;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;

import javax.jms.JMSException;
import javax.transaction.NotSupportedException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Random;

/**
 * ProjectTeam: Team C
 * Date: 07.11.12
 */
public class MAIN_HANNES_NIX_AENDERN {
    public static void main(String[] args) throws RemoteException, IllegalGetInstanceException, NoSessionFoundException, ExistingTransactionException, NoTransactionException, InstantiationException, IllegalAccessException, LogicException, NotAllowException, DomainException, NotSupportedException, svm.persistence.abstraction.exceptions.NotSupportedException, JMSException, InterruptedException {
        System.out.println("Start");
        testSubTeamController();
        // testContestController();
        // testTeamController();
        testJMS();
        //testMessageController();
        System.out.println("End");
    }

    public static ITransferAuth login() throws IllegalGetInstanceException, NoSessionFoundException, RemoteException, svm.persistence.abstraction.exceptions.NotSupportedException, IllegalAccessException, InstantiationException, ExistingTransactionException, NoTransactionException {
        ILoginController lc = LogicFacade.getLoginController();
        lc.start();
        lc.loginWithoutLdap("tf-test", "Pak3bGEh");
        ITransferAuth user = lc.getMember();
        lc.abort();
        return user;
    }

    public static void testSubTeamController() throws svm.persistence.abstraction.exceptions.NotSupportedException, IllegalGetInstanceException, NoSessionFoundException, IllegalAccessException, InstantiationException, RemoteException, ExistingTransactionException, NoTransactionException, NotAllowException, LogicException, DomainException {
        ITransferAuth user = login();

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
        try {
            subTeamController.addMember(m1);
            subTeamController.addMember(m2);
        } catch (Exception ex) {
            subTeamController.removeMember(m1);
            subTeamController.removeMember(m2);
        }
        subTeamController.commit();
        contestController.commit();
    }

    public static void testContestController() throws svm.persistence.abstraction.exceptions.NotSupportedException, IllegalGetInstanceException, NoSessionFoundException, IllegalAccessException, InstantiationException, RemoteException, ExistingTransactionException, NoTransactionException, NotAllowException, LogicException, DomainException {
        ITransferAuth user = login();

        ISearchController searchController = LogicFacade.getSearchController(user);
        searchController.start();
        for (ITransferContest contest : searchController.getContests()) {
            System.out.println(contest.getName());
        }

        ITransferContest contest = searchController.getContests().get(0);
        searchController.abort();
        IContestController contestController = LogicFacade.getContestController(user, contest);
        contestController.start();
        /*
       ITransferTeam t1 = contestController.getTeams().get(0);
       ITransferTeam t2 = contestController.getTeams().get(1);
       ITransferTeam t3 = contestController.getTeams().get(0);
       ITransferTeam t4 = contestController.getTeams().get(1);

       contestController.addMatch(t1, t2, new Date(), new Date());
       contestController.addMatch(t3, t4, new Date(), new Date());
        */
        for (ITransferMatch match : contestController.getMatches()) {
            System.out.println(match.getName());

            contestController.setDateForMatch(match, new Date());
            contestController.setResult(match, new Random().nextInt(10), new Random().nextInt(10));
        }
        for (ITransferMatch match : contestController.getMatches()) {
            System.out.println(match.getName());

            contestController.setDateForMatch(match, new Date());
            contestController.setResult(match, new Random().nextInt(10), new Random().nextInt(10));
        }

        contestController.commit();
    }

    public static void testTeamController() throws svm.persistence.abstraction.exceptions.NotSupportedException, IllegalGetInstanceException, NoSessionFoundException, IllegalAccessException, InstantiationException, RemoteException, ExistingTransactionException, NoTransactionException, NotAllowException, LogicException, DomainException {
        ITransferAuth user = login();

        ISearchController searchController = LogicFacade.getSearchController(user);
        searchController.start();
        ITransferTeam team = searchController.getTeams().get(2);
        searchController.abort();

        ITeamController teamController = LogicFacade.getTeamController(user, team);
        teamController.start();
        for (ITransferContest t : teamController.getContests()) {
            System.out.println(t);
        }
        teamController.commit();
    }

    public static void testJMS() throws svm.persistence.abstraction.exceptions.NotSupportedException, ExistingTransactionException, IllegalGetInstanceException, NoSessionFoundException, NoTransactionException, InstantiationException, IllegalAccessException, RemoteException, DomainParameterCheckException, NotAllowException, DomainAttributeException {
        ITransferAuth user = login();

        ISearchController searchController = LogicFacade.getSearchController(user);
        searchController.start();
        ITransferSport sport = searchController.getSports().get(0);
        searchController.commit();

        IMemberController memberController = LogicFacade.getMemberController(user);
        memberController.start();
        memberController.setBirthDate(new Date());
        memberController.setEntryDate(new Date());
        memberController.setLat("");
        memberController.setLong("");
        memberController.setFirstName("asdf");
        memberController.setGender("M");
        memberController.setLastName("asdf");
        memberController.setSocialNumber("asdf");
        memberController.setTitle("asdf");
        memberController.setUsername("sadf");

        memberController.setSport(sport);
        memberController.commit();
    }

    public static void testMessageController() throws svm.persistence.abstraction.exceptions.NotSupportedException, ExistingTransactionException, IllegalGetInstanceException, NoSessionFoundException, NoTransactionException, InstantiationException, IllegalAccessException, RemoteException, JMSException, InterruptedException {
        ITransferAuth user = login();
        IMessageController messageController = LogicFacade.getMessageController(user);
        messageController.addObserver(new IMessageObserver() {
            @Override
            public void updateMemberMessage(IMemberMessage message) {
                System.out.println(message.getMember().getFirstName());
            }

            @Override
            public void updateSubTeamMessage(ISubTeamMessage message) {
                System.out.println(message.getSubTeam());
            }
        });
        messageController.start();
    }
}
