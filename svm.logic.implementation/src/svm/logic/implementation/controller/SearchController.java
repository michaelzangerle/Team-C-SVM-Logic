package svm.logic.implementation.controller;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.domain.abstraction.modelInterfaces.*;
import svm.logic.abstraction.controller.ISearchController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.exception.NotAllowException;
import svm.logic.abstraction.transferobjects.*;
import svm.logic.implementation.tranferobjects.*;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;

import java.rmi.RemoteException;
import java.util.*;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public class SearchController implements ISearchController {

    private Integer sessionId;
    private ITransferAuth user;

    public SearchController(ITransferAuth user) {
        this.user = user;
    }

    @Override
    public void start() {
        this.sessionId = DomainFacade.generateSessionId();
    }

    @Override
    public void commit() throws NoSessionFoundException {
        DomainFacade.closeSession(sessionId);
    }

    @Override
    public void abort() throws NoSessionFoundException {
        DomainFacade.closeSession(sessionId);
    }

    /**
     * @param firstName
     * @param lastName
     * @param department
     * @return
     * @throws NoSessionFoundException
     * @throws IllegalGetInstanceException
     */
    @Override
    public List<ITransferMember> getMembers(String firstName, String lastName, ITransferDepartment department) throws NoSessionFoundException, IllegalGetInstanceException, NotAllowException {
        if(! user.isAllowedForSearching())
            throw new NotAllowException("Wrong privilege");

        List<ITransferMember> result = new LinkedList<ITransferMember>();
        for (IMember member : DomainFacade.getMemberModelDAO().get(sessionId, firstName, lastName, ((IHasModel<IDepartment>) department).getModel())) {
            result.add((ITransferMember) TransferObjectCreator.getInstance(TransferMember.class, member));
        }
        return result;

    }

    @Override
    public List<ITransferMember> getMembers(String firstName, String lastName, ITransferDepartment department, Boolean paid) throws NoSessionFoundException, IllegalGetInstanceException, RemoteException, DomainParameterCheckException, NotAllowException {
        if(! user.isAllowedForSearching())
            throw new NotAllowException("Wrong privilege");

        List<ITransferMember> result = new LinkedList<ITransferMember>();
        if (department != null) {
            for (IMember member : DomainFacade.getMemberModelDAO().get(sessionId, firstName, lastName, ((IHasModel<IDepartment>) department).getModel())) {
                if (member.hasPaidFee(new GregorianCalendar().get(Calendar.YEAR)) == paid) {
                    result.add((ITransferMember) TransferObjectCreator.getInstance(TransferMember.class, member));
                }
            }
        } else {
            for (IMember member : DomainFacade.getMemberModelDAO().get(sessionId, firstName, lastName)) {
                if (member.hasPaidFee(new GregorianCalendar().get(Calendar.YEAR)) == paid) {
                    result.add((ITransferMember) TransferObjectCreator.getInstance(TransferMember.class, member));
                }
            }
        }
        return result;
    }

    /**
     * @param firstName
     * @param lastName
     * @return
     * @throws NoSessionFoundException
     * @throws IllegalGetInstanceException
     */
    @Override
    public List<ITransferMember> getMembers(String firstName, String lastName) throws NoSessionFoundException, IllegalGetInstanceException, NotAllowException {
        if(! user.isAllowedForSearching())
            throw new NotAllowException("Wrong privilege");

        List<ITransferMember> result = new LinkedList<ITransferMember>();
        for (IMember member : DomainFacade.getMemberModelDAO().get(sessionId, firstName, lastName)) {
            result.add((ITransferMember) TransferObjectCreator.getInstance(TransferMember.class, member));
        }
        return result;
    }

    /**
     * @param birthDateFrom
     * @param birthDateTo
     * @return
     * @throws NoSessionFoundException
     * @throws IllegalGetInstanceException
     */
    @Override
    public List<ITransferMember> getMembers(Date birthDateFrom, Date birthDateTo) throws NoSessionFoundException, IllegalGetInstanceException, NotAllowException {
        if(! user.isAllowedForSearching())
            throw new NotAllowException("Wrong privilege");

        List<ITransferMember> result = new LinkedList<ITransferMember>();
        for (IMember member : DomainFacade.getMemberModelDAO().get(sessionId, birthDateFrom, birthDateTo)) {
            result.add((ITransferMember) TransferObjectCreator.getInstance(TransferMember.class, member));
        }
        return result;
    }

    /**
     * @return
     * @throws IllegalGetInstanceException
     */
    @Override
    public List<ITransferDepartment> getDepartments() throws IllegalGetInstanceException, NoSessionFoundException, NotAllowException {
        if(! user.isAllowedForSearching())
            throw new NotAllowException("Wrong privilege");

        List<ITransferDepartment> result = new LinkedList<ITransferDepartment>();
        for (IDepartment department : DomainFacade.getDepartmentModelDAO().getAll(sessionId)) {
            result.add((ITransferDepartment) TransferObjectCreator.getInstance(TransferDepartment.class, department));
        }
        return result;
    }

    /**
     * @return
     * @throws IllegalGetInstanceException
     */
    @Override
    public List<ITransferLocation> getLocations() throws IllegalGetInstanceException, NoSessionFoundException, NotAllowException {
        if(! user.isAllowedForSearching())
            throw new NotAllowException("Wrong privilege");

        List<ITransferLocation> result = new LinkedList<ITransferLocation>();

        for (ILocation location : DomainFacade.getLocationModelDAO().get(sessionId, "AT", "Vorarlberg")) {
            result.add((ITransferLocation) TransferObjectCreator.getInstance(TransferLocation.class, location));
        }
        return result;
    }

    @Override
    public List<ITransferContest> getContests() throws IllegalGetInstanceException, NoSessionFoundException, NotAllowException {
        if(! user.isAllowedForSearching())
            throw new NotAllowException("Wrong privilege");

        List<ITransferContest> result = new LinkedList<ITransferContest>();

        for (IContest contest : DomainFacade.getContestModelDAO().getAll(sessionId)) {
            result.add((ITransferContest) TransferObjectCreator.getInstance(TransferContest.class, contest));
        }
        return result;
    }

    @Override
    public List<ITransferTeam> getTeams() throws IllegalGetInstanceException, NoSessionFoundException, NotAllowException {
        if(! user.isAllowedForSearching())
            throw new NotAllowException("Wrong privilege");

        List<ITransferTeam> result = new LinkedList<ITransferTeam>();

        for (ITeam team : DomainFacade.getTeamModelDAO().getAll(sessionId)) {
            result.add((ITransferInternalTeam) TransferObjectCreator.getInstance(TransferInternalTeam.class, team));
        }

        for (IExternalTeam extTeam : DomainFacade.getExternalTeamModelDAO().getAll(sessionId)) {
            result.add((ITransferExternalTeam) TransferObjectCreator.getInstance(TransferExternalTeam.class, extTeam));
        }
        return result;
    }

    @Override
    public List<ITransferUserPrivilege> getUserPrivileges() throws NotAllowException, IllegalGetInstanceException, NoSessionFoundException {
        if(! user.isAllowedForSearching())
            throw new NotAllowException("Wrong privilege");

        List<ITransferUserPrivilege> result = new LinkedList<ITransferUserPrivilege>();


        for (IUserPrivilege priv : DomainFacade.getUserPrivilegeDAO().getAll(sessionId)) {
            result.add((ITransferUserPrivilege) TransferObjectCreator.getInstance(TransferUserPrivileges.class, priv));
        }

        return result;
    }
}
