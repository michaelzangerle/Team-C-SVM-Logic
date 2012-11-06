package svm.logic.implementation;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.modelInterfaces.IContest;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.domain.abstraction.modelInterfaces.ISubTeam;
import svm.domain.abstraction.modelInterfaces.ITeam;
import svm.logic.abstraction.controller.*;
import svm.logic.abstraction.transferobjects.*;
import svm.logic.implementation.controller.*;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;

/**
 * ProjectTeam: Team C
 * Date: 31.10.12
 */
public class ControllerFactory {

    private static ControllerFactory instance;

    public static ControllerFactory getInstance() {
        if (instance == null) instance = new ControllerFactory();
        return instance;
    }

    private ControllerFactory() {
    }

    /**
     * Create new Contest controller
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public IContestController getContestController(ITransferMember user) throws IllegalAccessException, InstantiationException, NoSessionFoundException {
        return new ContestController(DomainFacade.getContestModelDAO().generateObject(), ((IHasModel<IMember>) user).getModel());
    }

    /**
     * create new contest controller
     *
     * @param contest
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public IContestController getContestController(ITransferMember user, ITransferContest contest) throws IllegalAccessException, InstantiationException {
        return new ContestController(((IHasModel<IContest>) contest).getModel(), ((IHasModel<IMember>) user).getModel());
    }

    /**
     * Create new Contest
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public IMemberController getMemberController(ITransferMember user) throws IllegalAccessException, InstantiationException, NoSessionFoundException {
        return new MemberController(DomainFacade.getMemberModelDAO().generateObject(), ((IHasModel<IMember>) user).getModel());
    }

    /**
     * Change Contest
     *
     * @param member
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public IMemberController getMemberController(ITransferMember user, ITransferMember member) {
        return new MemberController(((IHasModel<IMember>) member).getModel(), ((IHasModel<IMember>) user).getModel());
    }

    /**
     * Confirm a Contest for Member
     *
     * @param member
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public IContestConfirmationController getContestConfirmationController(ITransferMember user, ITransferMember member) {
        return new ContestConfirmationController(((IHasModel<IMember>) member).getModel(), ((IHasModel<IMember>) user).getModel());
    }

    public ISubTeamConfirmationController getSubTeamConfirmationController(ITransferMember user, ITransferMember member) {
        return new SubTeamConfirmationController(((IHasModel<IMember>) member).getModel(), ((IHasModel<IMember>) user).getModel());
    }

    public ISubTeamController getSubTeamController(ITransferMember user, ITransferSubTeam subTeam) {
        return new SubTeamController(((IHasModel<ISubTeam>) subTeam).getModel(), ((IHasModel<IMember>) user).getModel());
    }

    public ISubTeamController getSubTeamController(ITransferMember user, ITransferTeam team, ITransferContest contest) throws NoSessionFoundException, InstantiationException, IllegalAccessException {
        return new SubTeamController(((IHasModel<ITeam>) team).getModel(), ((IHasModel<IContest>) contest).getModel(), ((IHasModel<IMember>) user).getModel());
    }

    public ISearchController getSearchController(ITransferMember user) {
        return new SearchController(((IHasModel<IMember>) user).getModel());
    }

    public ILoginController getLoginController() {
        return new LoginController();
    }
}
