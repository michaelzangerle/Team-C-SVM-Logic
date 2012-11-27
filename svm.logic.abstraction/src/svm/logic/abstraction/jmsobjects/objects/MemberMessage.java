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
    private int receiver;
    private String text;

    public MemberMessage(MessageType type, int member, int receiver,String text) {
        this.type = type;
        this.member = member;
        this.receiver = receiver;
        this.text = text;
    }

    @Override
    public MessageType getType() {
        return type;
    }

    @Override
    public int getMember() {
        return member;
    }

    @Override
    public int getReceiverUID() {
        return receiver;
    }


    @Override
    public String toString(){
        return this.text;
    }
}
