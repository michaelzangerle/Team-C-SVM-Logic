package svm.logic.abstraction.controller;

import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.abstraction.transferobjects.ITransferSubTeam;
import svm.logic.abstraction.transferobjects.ITransferSubTeamHasMember;

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
     * Sets the confirmation flag and a comment for a specific subteam-contest relation
     *
     * @param subTeamID
     * @param confirmation
     * @param comment
     */
    void setConfirmationForSubTeam(ITransferSubTeamHasMember subTeam, boolean confirmation, String comment);

    ITransferMember getMember();
}
