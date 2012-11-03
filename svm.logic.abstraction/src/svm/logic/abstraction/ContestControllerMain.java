package svm.logic.abstraction;

import svm.domain.abstraction.exception.DomainAttributeException;
import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.domain.abstraction.modelInterfaces.ILocation;
import svm.logic.abstraction.controller.IContestController;
import svm.logic.abstraction.controller.ISearchController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferLocation;
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

        //ISearchController searchController = LogicFacade.getSearchController();
        //ITransferLocation location = searchController.getLocations().get(0);

        IContestController contestController = LogicFacade.getContestController();
        contestController.start();

        contestController.setContestStartDate(new Date());
        contestController.setContestEndDate(new Date());
        contestController.setContestName("Testcontest");
        contestController.setContestFee(150);

        contestController.setEmail1("michael.zangerle@outlook.com");
        contestController.setEmail2("michael.zangerle@gmail.com");

        contestController.setFax("0654 123 789");

        contestController.setLat("4");
        contestController.setLong("10");

        contestController.setStreet("Dorf");
        contestController.setStreetNumber("45");

        contestController.setPhone1("0654 123 564 78");
        contestController.setPhone2("0654 123 564 79");

        //contestController.setLocation(location);

        contestController.commit();



    }

}
