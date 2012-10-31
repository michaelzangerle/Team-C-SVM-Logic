package svm.logic.abstraction.controller;

import svm.logic.abstraction.transferobjects.ITransferDepartment;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;

import java.util.Date;
import java.util.List;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public interface ISearchController extends IController {

    List<ITransferMember> getMembers(String firstName, String lastName, ITransferDepartment department) throws NoSessionFoundException;

    List<ITransferMember> getMembers(Date birthDateFrom, Date birthDateTo) throws NoSessionFoundException;

    List<ITransferDepartment> getDepartments();
}
