package svm.logic.abstraction.controller;

import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
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

    List<ITransferMember> getMembers(String firstName, String lastName, ITransferDepartment department) throws NoSessionFoundException, IllegalGetInstanceException, RemoteException;

    List<ITransferMember> getMembers(String firstName, String lastName, ITransferDepartment department, Boolean paid) throws NoSessionFoundException, IllegalGetInstanceException, RemoteException, DomainParameterCheckException;

    List<ITransferMember> getMembers(String firstName, String lastName) throws NoSessionFoundException, IllegalGetInstanceException, RemoteException;

    List<ITransferMember> getMembers(Date birthDateFrom, Date birthDateTo) throws NoSessionFoundException, IllegalGetInstanceException, RemoteException;

    List<ITransferDepartment> getDepartments() throws IllegalGetInstanceException, NoSessionFoundException, RemoteException;

    List<ITransferLocation> getLocations() throws IllegalGetInstanceException, NoSessionFoundException, RemoteException;

    List<ITransferContest> getContests() throws IllegalGetInstanceException, NoSessionFoundException;

    List<ITransferTeam> getTeams() throws IllegalGetInstanceException, NoSessionFoundException;
}
