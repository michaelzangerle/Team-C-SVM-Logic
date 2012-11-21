package svm.logic.jms.objects;

import svm.logic.abstraction.jmsobjects.IMemberMessage;
import svm.logic.abstraction.jmsobjects.MemberMessageType;
import svm.logic.abstraction.transferobjects.ITransferMember;

/**
 * ProjectTeam: Team C
 * Date: 21.11.12
 */
public class MemberMessage implements IMemberMessage {
    private MemberMessageType type;
    private ITransferMember member;

    public MemberMessage(MemberMessageType type, ITransferMember member) {
        this.type = type;
        this.member = member;
    }

    public MemberMessageType getType() {
        return type;
    }

    public ITransferMember getMember() {
        return member;
    }
}
