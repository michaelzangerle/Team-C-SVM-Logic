package svm.logic.abstraction.jmsobjects.objects;

import svm.logic.abstraction.jmsobjects.IMemberMessage;
import svm.logic.abstraction.jmsobjects.MessageType;

/**
 * ProjectTeam: Team C
 * Date: 21.11.12
 */
public class MemberMessage implements IMemberMessage {
    private MessageType type;
    private int member;

    public MemberMessage(MessageType type, int member) {
        this.type = type;
        this.member = member;
    }

    @Override
    public MessageType getType() {
        return type;
    }

    @Override
    public int getMember() {
        return member;
    }
}
