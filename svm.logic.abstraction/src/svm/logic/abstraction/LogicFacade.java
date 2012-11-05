package svm.logic.abstraction;

import svm.logic.abstraction.controller.*;
import svm.logic.abstraction.transferobjects.ITransferContest;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.abstraction.transferobjects.ITransferSubTeam;
import svm.logic.abstraction.transferobjects.ITransferTeam;
import svm.logic.implementation.ControllerFactory;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;

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
    public static IContestController getContestController(ITransferMember user) throws IllegalAccessException, InstantiationException, NoSessionFoundException {
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
    public static IContestController getContestController(ITransferMember user, ITransferContest contest) throws IllegalAccessException, InstantiationException {
        return ControllerFactory.getInstance().getContestController(user, contest);
    }

    /**
     * Create new Contest
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static IMemberController getMemberController(ITransferMember user) throws NoSessionFoundException, InstantiationException, IllegalAccessException {
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
    public static IMemberController getMemberController(ITransferMember user, ITransferMember member) {
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
    public static IContestConfirmationController getContestConfirmationController(ITransferMember user, ITransferMember member) {
        return ControllerFactory.getInstance().getContestConfirmationController(user, member);
    }

    /**
     * Sub Team Confirmation Controller
     *
     * @return ISubTeamConfirmationController
     */
    public static ISubTeamConfirmationController getSubTeamConfirmationController(ITransferMember user, ITransferMember member) {
        return ControllerFactory.getInstance().getSubTeamConfirmationController(user, member);
    }

    /**
     * SubTeam Controller
     *
     * @param subTeam ITransferSubTeam
     * @return ISubTeamController
     */
    public static ISubTeamController getSubTeamController(ITransferMember user, ITransferSubTeam subTeam) {
        return ControllerFactory.getInstance().getSubTeamController(user, subTeam);
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
    public static ISubTeamController getSubTeamController(ITransferMember user, ITransferTeam team, ITransferContest contest) throws NoSessionFoundException, IllegalAccessException, InstantiationException {
        return ControllerFactory.getInstance().getSubTeamController(user, team, contest);
    }

    public static ISearchController getSearchController(ITransferMember user) {
        return ControllerFactory.getInstance().getSearchController(user);
    }

    public static ILoginController getLoginController() {
        return ControllerFactory.getInstance().getLoginController();
    }

    public static IContestController getTeamContestController(ITransferMember user, ITransferContest contest) throws InstantiationException, IllegalAccessException {
        return ControllerFactory.getInstance().getContestController(user, contest);
    }
}
