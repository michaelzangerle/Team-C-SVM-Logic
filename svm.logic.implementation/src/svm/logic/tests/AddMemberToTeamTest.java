package svm.logic.tests;

import svm.logic.abstraction.controller.ILoginController;
import svm.logic.abstraction.controller.IMemberController;
import svm.logic.abstraction.controller.ISearchController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.exception.NotAllowException;
import svm.logic.abstraction.transferobjects.ITransferAuth;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.abstraction.transferobjects.ITransferTeam;
import svm.logic.implementation.ControllerFactory;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;
import svm.persistence.abstraction.exceptions.NotSupportedException;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Projectteam : Team C
 * Date: 26.11.12
 */
public class AddMemberToTeamTest {

    public static void main(String[] args) throws IllegalGetInstanceException, NoSessionFoundException, RemoteException, NotSupportedException, IllegalAccessException, InstantiationException, ExistingTransactionException, NoTransactionException, NotAllowException {

        ControllerFactory factory=ControllerFactory.getInstance();

        ILoginController loginController=factory.getLoginController();
        loginController.start();
        loginController.loginWithoutLdap("tf-test"," ");
        ITransferAuth user=loginController.getMember();
        System.out.println("Auth User: "+user.getUsername());
        loginController.commit();



        ISearchController searchController= factory.getSearchController(user);
        searchController.start();
        List<ITransferMember> memberList= searchController.getMembers("Michael","Zangerle");
        ITransferMember tm=memberList.get(0);
        List<ITransferTeam> teams=searchController.getTeams();
        ITransferTeam team=teams.get(0);
        searchController.commit();

        IMemberController memberController=factory.getMemberController(user,tm);
        memberController.start();
        memberController.addMemberToTeam(team);
        memberController.commit();






    }





}
