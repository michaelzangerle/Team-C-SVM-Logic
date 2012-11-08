package svm.logic.implementation;

import svm.domain.abstraction.modelInterfaces.IContest;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.domain.abstraction.modelInterfaces.ITeam;
import svm.logic.abstraction.controller.*;
import svm.logic.abstraction.transferobjects.*;
import svm.logic.implementation.controller.*;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NotSupportedException;

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
    public IContestController getContestController(ITransferAuth user) throws IllegalAccessException, InstantiationException, NoSessionFoundException, NotSupportedException {
        return new ContestController(user);
    }

    /**
     * create new contest controller
     *
     * @param contest
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public IContestController getContestController(ITransferAuth user, ITransferContest contest) throws IllegalAccessException, InstantiationException {
        return new ContestController(((IHasModel<IContest>) contest).getModel(), user);
    }

    /**
     * Create new Contest
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public IMemberController getMemberController(ITransferAuth user) throws IllegalAccessException, InstantiationException, NoSessionFoundException, NotSupportedException {
        return new MemberController(user);
    }

    /**
     * Change Contest
     *
     * @param member
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public IMemberController getMemberController(ITransferAuth user, ITransferMember member) {
        return new MemberController(((IHasModel<IMember>) member).getModel(), user);
    }

    /**
     * Confirm a Contest for Member
     *
     * @param member
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public IContestConfirmationController getContestConfirmationController(ITransferAuth user, ITransferMember member) {
        return new ContestConfirmationController(((IHasModel<IMember>) member).getModel(), user);
    }

    public ISubTeamConfirmationController getSubTeamConfirmationController(ITransferAuth user, ITransferMember member) {
        return new SubTeamConfirmationController(((IHasModel<IMember>) member).getModel(), user);
    }

    public ISubTeamController getSubTeamController(ITransferAuth user, ITransferTeam team, ITransferContest contest) throws NoSessionFoundException, InstantiationException, IllegalAccessException, NotSupportedException {
        return new SubTeamController(((IHasModel<ITeam>) team).getModel(), ((IHasModel<IContest>) contest).getModel(), user);
    }

    public ISearchController getSearchController(ITransferAuth user) {
        return new SearchController(user);
    }

    public ILoginController getLoginController() {
        return new LoginController();
    }
}
