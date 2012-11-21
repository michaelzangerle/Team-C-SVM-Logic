package svm.logic.jms.objects;

import svm.logic.abstraction.jmsobjects.ISubTeamMessage;
import svm.logic.abstraction.jmsobjects.MessageType;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.abstraction.transferobjects.ITransferSubTeam;

/**
 * ProjectTeam: Team C
 * Date: 21.11.12
 */
public class SubTeamMessage implements ISubTeamMessage {

    private MessageType type;
    private ITransferMember member;
    private ITransferSubTeam subTeam;

    public SubTeamMessage(MessageType type, ITransferMember member, ITransferSubTeam subTeam) {
        this.type = type;
        this.member = member;
        this.subTeam = subTeam;
    }

    @Override
    public MessageType getType() {
        return type;
    }

    @Override
    public ITransferMember getMember() {
        return member;
    }

    @Override
    public ITransferSubTeam getSubTeam() {
        return subTeam;
    }
}
