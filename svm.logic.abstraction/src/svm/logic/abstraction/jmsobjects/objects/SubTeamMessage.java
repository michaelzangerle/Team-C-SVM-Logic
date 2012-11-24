package svm.logic.abstraction.jmsobjects.objects;

import svm.logic.abstraction.jmsobjects.ISubTeamMessage;
import svm.logic.abstraction.jmsobjects.MessageType;

/**
 * ProjectTeam: Team C
 * Date: 21.11.12
 */
public class SubTeamMessage implements ISubTeamMessage {

    private MessageType type;
    private int member;
    private int subTeam;
    private int receiver;

    public SubTeamMessage(MessageType type, int member, int subTeam) {
        this.type = type;
        this.member = member;
        this.subTeam = subTeam;
        this.receiver = member;
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
    public int getSubTeam() {
        return subTeam;
    }

    @Override
    public int getReceiverUID() {
        return receiver;
    }
}
