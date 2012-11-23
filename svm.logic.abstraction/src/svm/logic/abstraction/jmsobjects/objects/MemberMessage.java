package svm.logic.abstraction.jmsobjects.objects;

import svm.logic.abstraction.jmsobjects.IMemberMessage;
import svm.logic.abstraction.jmsobjects.MessageType;
import svm.logic.abstraction.transferobjects.ITransferMember;

/**
 * ProjectTeam: Team C
 * Date: 21.11.12
 */
public class MemberMessage implements IMemberMessage {
    private MessageType type;
    private ITransferMember member;

    public MemberMessage(MessageType type, ITransferMember member) {
        this.type = type;
        this.member = member;
    }

    @Override
    public MessageType getType() {
        return type;
    }

    @Override
    public ITransferMember getMember() {
        return member;
    }
}
