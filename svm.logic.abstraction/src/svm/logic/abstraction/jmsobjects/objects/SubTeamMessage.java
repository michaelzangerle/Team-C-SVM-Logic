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

    public SubTeamMessage(MessageType type, int member, int subTeam) {
        this.type = type;
        this.member = member;
        this.subTeam = subTeam;
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
}
