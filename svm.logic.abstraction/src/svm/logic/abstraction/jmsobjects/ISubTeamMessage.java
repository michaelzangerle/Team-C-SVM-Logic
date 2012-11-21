package svm.logic.abstraction.jmsobjects;

import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.abstraction.transferobjects.ITransferSubTeam;

/**
 * ProjectTeam: Team C
 * Date: 21.11.12
 */
public interface ISubTeamMessage extends IMessage {
    ITransferSubTeam getSubTeam();

    ITransferMember getMember();

    MessageType getType();
}
