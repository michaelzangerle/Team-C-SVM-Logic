package svm.logic.implementation.controller;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.logic.abstraction.controller.ISearchController;
import svm.logic.abstraction.transferobjects.ITransferDepartment;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public class SearchController implements ISearchController {

    private Integer sessionId;

    @Override
    public void start() {
        this.sessionId = DomainFacade.generateSessionId();
    }

    @Override
    public void commit() {

    }

    @Override
    public void abort() {

    }

    @Override
    public List<ITransferMember> getMembers(String firstName, String lastName, ITransferDepartment department) throws NoSessionFoundException {
        // TODO ITransferDepartment to IDepartment
        DomainFacade.getMemberModelDAO().get(sessionId, firstName, lastName, null);
        return null;
    }

    @Override
    public List<ITransferMember> getMembers(Date birthDateFrom, Date birthDateTo) throws NoSessionFoundException {
        List<ITransferMember> result = new LinkedList<ITransferMember>();
        for (IMember member : DomainFacade.getMemberModelDAO().get(sessionId, birthDateFrom, birthDateTo)) {
            // TODO Wrappen
        }

        return result;
    }

    @Override
    public List<ITransferDepartment> getDepartments() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
