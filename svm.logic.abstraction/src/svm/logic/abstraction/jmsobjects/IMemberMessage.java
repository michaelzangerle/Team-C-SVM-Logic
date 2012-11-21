package svm.logic.abstraction.jmsobjects;

import svm.logic.abstraction.transferobjects.ITransferMember;

/**
 * ProjectTeam: Team C
 * Date: 21.11.12
 */
public interface IMemberMessage extends IMessage {

    MessageType getType();

    ITransferMember getMember();
}
