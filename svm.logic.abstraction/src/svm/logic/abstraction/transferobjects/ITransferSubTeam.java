package svm.logic.abstraction.transferobjects;


import java.util.List;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public interface ITransferSubTeam extends ITransfer {
    List<ITransferMember> getSubTeamMembers();
}
