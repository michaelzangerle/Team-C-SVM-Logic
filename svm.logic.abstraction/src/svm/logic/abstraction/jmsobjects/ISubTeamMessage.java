package svm.logic.abstraction.jmsobjects;

/**
 * ProjectTeam: Team C
 * Date: 21.11.12
 */
public interface ISubTeamMessage extends IMessage {
    int getSubTeam();

    int getMember();

    MessageType getType();
}
