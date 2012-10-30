package svm.logic.abstraction.controller;

import svm.logic.abstraction.transferobjects.interfaces.ITransferTeam;

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
    List<ITransferTeam> getTeamForNotConfirmedContests(int personId);

    /**
     * Confirms or cancels a participation of a team in a contests
     *
     * @return
     */
    List<ITransferTeam> confirmParticipationOfATeamInAContest(int teamId, int contestId, boolean confirm, String comment, boolean paid);

}
