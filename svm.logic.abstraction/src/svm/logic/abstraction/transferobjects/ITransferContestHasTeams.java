package svm.logic.abstraction.transferobjects;

/**
 * Projectteam: Team C
 * Date: 31.10.12
 */
public interface ITransferContestHasTeams extends ITransfer {

    boolean getConfirmed();

    String getComment();

    boolean getPaid();
}
