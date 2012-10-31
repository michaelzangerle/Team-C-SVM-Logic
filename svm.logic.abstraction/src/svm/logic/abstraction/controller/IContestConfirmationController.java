package svm.logic.abstraction.controller;

import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.ITransferContestHasTeams;

import java.util.List;

/**
 * Interface for the contest confirmation controller
 * Projectteam: Team C
 * Date: 30.10.12
 */
public interface IContestConfirmationController extends IController {

    /**
     * Gets all contests where a team could participate which has the specific contact person
     * (skips not confirmed contests in the past)
     *
     * @return
     */
    List<ITransferContestHasTeams> getTeamsForNotConfirmedContests() throws IllegalGetInstanceException;

    /**
     * Confirms or cancels a participation of a team in a contests
     *
     * @return
     */
    void confirmParticipationOfATeamInAContest(ITransferContestHasTeams transferTeamHasContest, boolean confirm, String comment, boolean paid);

}
