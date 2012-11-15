package svm.logic.abstraction;

import svm.logic.abstraction.controller.*;
import svm.logic.abstraction.transferobjects.ITransferAuth;
import svm.logic.abstraction.transferobjects.ITransferContest;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.abstraction.transferobjects.ITransferTeam;
import svm.logic.implementation.ControllerFactory;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NotSupportedException;

/**
 * ProjectTeam: Team C
 * Date: 31.10.12
 */
public class LogicFacade {

    /**
     * Create new Contest
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static IContestController getContestController(ITransferAuth user) throws IllegalAccessException, InstantiationException, NoSessionFoundException, NotSupportedException {
        return ControllerFactory.getInstance().getContestController(user);
    }

    /**
     * Change Contest
     *
     * @param contest
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static IContestController getContestController(ITransferAuth user, ITransferContest contest) throws IllegalAccessException, InstantiationException {
        return ControllerFactory.getInstance().getContestController(user, contest);
    }

    /**
     * Create new Contest
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static IMemberController getMemberController(ITransferAuth user) throws NoSessionFoundException, InstantiationException, IllegalAccessException, NotSupportedException {
        return ControllerFactory.getInstance().getMemberController(user);
    }

    /**
     * Change Contest
     *
     * @param member
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static IMemberController getMemberController(ITransferAuth user, ITransferMember member) {
        return ControllerFactory.getInstance().getMemberController(user, member);
    }

    /**
     * Confirm a Contest for Member
     *
     * @param member
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static IContestConfirmationController getContestConfirmationController(ITransferAuth user, ITransferMember member) {
        return ControllerFactory.getInstance().getContestConfirmationController(user, member);
    }

    /**
     * Sub Team Confirmation Controller
     *
     * @return ISubTeamConfirmationController
     */
    public static ISubTeamConfirmationController getSubTeamConfirmationController(ITransferAuth user, ITransferMember member) {
        return ControllerFactory.getInstance().getSubTeamConfirmationController(user, member);
    }

    /**
     * SubTeam Controller
     *
     * @param team    ITransferTeam
     * @param contest ITransferContest
     * @return ISubTeamController
     * @throws NoSessionFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static ISubTeamController getSubTeamController(ITransferAuth user, ITransferTeam team, ITransferContest contest) throws NoSessionFoundException, IllegalAccessException, InstantiationException, NotSupportedException {
        return ControllerFactory.getInstance().getSubTeamController(user, team, contest);
    }

    public static ISearchController getSearchController(ITransferAuth user) {
        return ControllerFactory.getInstance().getSearchController(user);
    }

    public static ILoginController getLoginController() {
        return ControllerFactory.getInstance().getLoginController();
    }

    public static IContestController getTeamContestController(ITransferAuth user, ITransferContest contest) throws InstantiationException, IllegalAccessException {
        return ControllerFactory.getInstance().getContestController(user, contest);
    }

    public static ITeamController getTeamController(ITransferAuth user, ITransferTeam team) {
        return ControllerFactory.getInstance().getTeamController(user, team);
    }
}
