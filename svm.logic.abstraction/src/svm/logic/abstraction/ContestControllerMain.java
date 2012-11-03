package svm.logic.abstraction;

import svm.domain.abstraction.exception.DomainAttributeException;
import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.logic.abstraction.controller.IContestController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.implementation.controller.ContestController;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;

import java.util.Date;

/**
 * Projectteam: Team C
 * Date: 31.10.12
 */
public class ContestControllerMain {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, DomainParameterCheckException, DomainAttributeException, IllegalGetInstanceException, NoSessionFoundException, ExistingTransactionException, NoTransactionException {

        IContestController contestController = LogicFacade.getContestController();
        contestController.start();

        contestController.setContestStartDate(new Date());
        contestController.setContestEndDate(new Date());
        contestController.setContestName("Testcontest");
        contestController.setContestFee(150);

        contestController.commit();



    }

}
