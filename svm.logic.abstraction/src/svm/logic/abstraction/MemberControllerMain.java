package svm.logic.abstraction;

import svm.domain.abstraction.exception.DomainAttributeException;
import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.logic.abstraction.controller.ILoginController;
import svm.logic.abstraction.controller.IMemberController;
import svm.logic.abstraction.controller.ISearchController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;

import java.rmi.RemoteException;

/**
 * Projectteam: Team C
 * Date: 03.11.12
 */
public class MemberControllerMain {

    public static void main(String[] args) throws NoSessionFoundException, IllegalAccessException, InstantiationException, IllegalGetInstanceException, DomainAttributeException, DomainParameterCheckException, ExistingTransactionException, NoTransactionException, RemoteException {

        ILoginController lc = LogicFacade.getLoginController();
        lc.start();
        lc.login("mary.sluis", "");
        ITransferMember user = lc.getMember();
        lc.abort();

        ISearchController searchController = LogicFacade.getSearchController(user);
        searchController.start();

        ITransferMember member = searchController.getMembers("Patrik", "Jost").get(0);
        searchController.abort();

        IMemberController memberController = LogicFacade.getMemberController(member, user);
        memberController.start();


//        memberController.setFirstName("Mike");
//        memberController.setLastName("Zangerle");
//        memberController.setBirthDate(new Date());
//
//        memberController.setEmail1("michael.zangerle@gmail.com");
//        memberController.setEmail2("michael.zangerle@outlook.com");
//
//        memberController.setGender("M");   // TODO Fails because of string / char
//
//        memberController.setSocialNumber("0123456789");
//
//        memberController.setStreet("Dorf");
//        memberController.setStreetNumber("46");
        memberController.setPaidCurrentYear();

        memberController.commit();

    }

}
