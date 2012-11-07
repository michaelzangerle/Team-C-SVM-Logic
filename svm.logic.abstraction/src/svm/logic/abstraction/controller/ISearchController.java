package svm.logic.abstraction.controller;

import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.exception.NotAllowException;
import svm.logic.abstraction.transferobjects.*;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public interface ISearchController extends IController {

    List<ITransferMember> getMembers(String firstName, String lastName, ITransferDepartment department) throws NoSessionFoundException, IllegalGetInstanceException, RemoteException, NotAllowException;

    List<ITransferMember> getMembers(String firstName, String lastName, ITransferDepartment department, Boolean paid) throws NoSessionFoundException, IllegalGetInstanceException, RemoteException, DomainParameterCheckException, NotAllowException;

    List<ITransferMember> getMembers(String firstName, String lastName) throws NoSessionFoundException, IllegalGetInstanceException, RemoteException, NotAllowException;

    List<ITransferMember> getMembers(Date birthDateFrom, Date birthDateTo) throws NoSessionFoundException, IllegalGetInstanceException, RemoteException, NotAllowException;

    List<ITransferDepartment> getDepartments() throws IllegalGetInstanceException, NoSessionFoundException, RemoteException, NotAllowException;

    List<ITransferLocation> getLocations() throws IllegalGetInstanceException, NoSessionFoundException, RemoteException, NotAllowException;

    List<ITransferContest> getContests() throws IllegalGetInstanceException, NoSessionFoundException, NotAllowException;

    List<ITransferTeam> getTeams() throws IllegalGetInstanceException, NoSessionFoundException, NotAllowException;

    List<ITransferUserPrivilege> getUserPrivileges() throws NotAllowException, IllegalGetInstanceException, NoSessionFoundException;
}
