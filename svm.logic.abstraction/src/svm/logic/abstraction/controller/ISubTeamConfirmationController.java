package svm.logic.abstraction.controller;

import svm.logic.abstraction.transferobjects.interfaces.ITransferSubTeam;

import java.util.List;

/**
 * Interface for the subteam confirmation controller
 * Projectteam: Team C
 * Date: 30.10.12
 */
public interface ISubTeamConfirmationController extends IController {

    /**
     * Returns all subteams of a member
     *
     * @return
     */
    List<ITransferSubTeam> getSubTeamsOfMember();

    /**
     * Returns the subteams of a member which are not in the past
     *
     * @return
     */
    List<ITransferSubTeam> getCurrentSubTeamsOfMember();

    /**
     * Sets the confirmation flag and a comment for a specific subteam-contest relation
     *
     * @param subTeamID
     * @param confirmation
     * @param comment
     */
    void setConfirmationForSubTeam(int subTeamID, boolean confirmation, String comment);

}
