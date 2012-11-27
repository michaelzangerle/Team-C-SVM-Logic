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
    private String text;

    public SubTeamMessage(MessageType type, int member, int subTeam, String text) {
        this.type = type;
        this.member = member;
        this.subTeam = subTeam;
        this.receiver = member;
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
    public int getSubTeam() {
        return subTeam;
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
