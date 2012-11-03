package svm.logic.abstraction;

import svm.domain.abstraction.exception.DomainAttributeException;
import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.logic.abstraction.LogicFacade;
import svm.logic.abstraction.controller.IMemberController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.ITransfer;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;

import java.util.Date;

/**
 * Projectteam: Team C
 * Date: 03.11.12
 */
public class MemberControllerMain {

    public static void main(String[] args) throws NoSessionFoundException, IllegalAccessException, InstantiationException, IllegalGetInstanceException, DomainAttributeException, DomainParameterCheckException, ExistingTransactionException, NoTransactionException {


        IMemberController memberController =  LogicFacade.getMemberController();
        memberController.start();

        memberController.setFirstName("Michael");
        memberController.setLastName("Zangerle");
        memberController.setBirthDate(new Date());

        memberController.setEmail1("michael.zangerle@gmail.com");
        memberController.setEmail2("michael.zangerle@outlook.com");

        memberController.setGender("M");

        memberController.setSocialNumber("0123456789");

        memberController.setStreet("Dorf");
        memberController.setStreetNumber("46");

        memberController.commit();

    }

}
