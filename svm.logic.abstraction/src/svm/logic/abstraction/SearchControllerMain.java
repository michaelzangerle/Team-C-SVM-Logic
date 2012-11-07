package svm.logic.abstraction;

import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.logic.abstraction.controller.ILoginController;
import svm.logic.abstraction.controller.ISearchController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.exception.NotAllowException;
import svm.logic.abstraction.transferobjects.ITransferAuth;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public class SearchControllerMain {

    public static void main(String[] args) throws IllegalGetInstanceException, NoSessionFoundException, ExistingTransactionException, NoTransactionException, RemoteException, DomainParameterCheckException, NotAllowException {

        ILoginController lc = LogicFacade.getLoginController();
        lc.start();
        lc.login("mary.sluis", "");


        ITransferAuth user = lc.getMember();

        ISearchController search = LogicFacade.getSearchController(user);
        search.start();
        int date = 1;
        int month = Calendar.SEPTEMBER;
        int year = 1953;
        Date d = new GregorianCalendar(year, month, date).getTime();
        for (ITransferMember member : search.getMembers("Pa", "", search.getDepartments().get(1), false)) {
            System.out.println(member.toString());
        }

        search.commit();
    }

}
