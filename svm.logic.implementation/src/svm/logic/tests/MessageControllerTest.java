package svm.logic.tests;

import svm.domain.abstraction.modelInterfaces.IDepartment;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.logic.abstraction.controller.ILoginController;
import svm.logic.abstraction.controller.ISearchController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.exception.NotAllowException;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferAuth;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.implementation.ControllerFactory;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;
import svm.persistence.abstraction.exceptions.NotSupportedException;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Projectteam : Team C
 * Date: 21.11.12
 */
public class MessageControllerTest{

    public static void main(String[] args) throws NotSupportedException, IllegalGetInstanceException, NoSessionFoundException, IllegalAccessException, InstantiationException, RemoteException, ExistingTransactionException, NoTransactionException, NotAllowException {

    ControllerFactory factory=ControllerFactory.getInstance();

    ILoginController loginController=factory.getLoginController();
    loginController.start();
    loginController.loginWithoutLdap("tf-test"," ");
    ITransferAuth user=loginController.getMember();
    System.out.println("Auth User: "+user.getUsername());
    loginController.commit();


    ISearchController searchController= factory.getSearchController(user);
       searchController.start();
       List<ITransferMember> memberList= searchController.getMembers("Thomas","Feilhauer");
        ITransferMember tm=memberList.get(0);
       IMember memberModel=(IMember)((IHasModel<IMember>)tm).getModel();
     IDepartment department=memberModel.getSport().getDepartment();
       IMember member=memberModel.getSport().getDepartment().getDepartmentHead();
       System.out.println("Departement Head of Department "+department.getName()+" is "+member.getFirstName()+" "+ member.getLastName());
       searchController.commit();
    }

}
