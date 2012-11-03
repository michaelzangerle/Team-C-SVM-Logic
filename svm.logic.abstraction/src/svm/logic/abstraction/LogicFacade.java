package svm.logic.abstraction;

import svm.logic.abstraction.controller.*;
import svm.logic.abstraction.transferobjects.ITransferContest;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.abstraction.transferobjects.ITransferSubTeam;
import svm.logic.abstraction.transferobjects.ITransferTeam;
import svm.logic.implementation.ControllerFactory;
import svm.logic.implementation.controller.SearchController;
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
    public static IContestController getContestController() throws IllegalAccessException, InstantiationException, NoSessionFoundException {
        return ControllerFactory.getInstance().getContestController();
    }

    /**
     * Change Contest
     *
     * @param contest
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static IContestController getContestController(ITransferContest contest) throws IllegalAccessException, InstantiationException {
        return ControllerFactory.getInstance().getContestController(contest);
    }

    /**
     * Confirm a Contest for Member
     *
     * @param member
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static IContestConfirmationController getContestConfirmationController(ITransferMember member) {
        return ControllerFactory.getInstance().getContestConfirmationController(member);
    }

    /**
     * Sub Team Confirmation Controller
     * @return ISubTeamConfirmationController
     */
    public static ISubTeamConfirmationController getSubTeamConfirmationController (ITransferMember member)
    {
        return ControllerFactory.getInstance().getSubTeamConfirmationController(member);
    }

    /**
     * SubTeam Controller
     * @param subTeam  ITransferSubTeam
     * @return  ISubTeamController
     */
    public static ISubTeamController getSubTeamController (ITransferSubTeam subTeam){
        return ControllerFactory.getInstance().getSubTeamController(subTeam);
    }

    /**
     *    Subteam Controller
     * @param team     ITransferTeam
     * @param contest  ITransferContest
     * @return  ISubTeamController
     * @throws NoSessionFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static ISubTeamController getSubTeamController (ITransferTeam team,ITransferContest contest) throws NoSessionFoundException, IllegalAccessException, InstantiationException {
        return ControllerFactory.getInstance().getSubTeamController(team,contest);
    }

    public static IMemberController getMemberController(ITransferMember member){
        return ControllerFactory.getInstance().getMemberController( member);
    }

    public static ISearchController getSearchController(){
        return ControllerFactory.getInstance().getSearchController();
    }
}
