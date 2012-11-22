package svm.logic.abstraction.jmsobjects;

/**
 * ProjectTeam: Team C
 * Date: 22.11.12
 */
public interface IMessageObserver {
    void updateMemberMessage(IMemberMessage message);

    void updateSubTeamMessage(ISubTeamMessage message);
}
