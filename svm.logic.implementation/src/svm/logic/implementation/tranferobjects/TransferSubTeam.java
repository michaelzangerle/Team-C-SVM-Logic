package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.ISubTeam;
import svm.domain.abstraction.modelInterfaces.ISubTeamsHasMembers;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.abstraction.transferobjects.ITransferSubTeam;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;

import java.util.List;

/**
 * ProjectTeam: Team C
 * Date: 01.11.12
 */
public class TransferSubTeam implements ITransferSubTeam, IHasModel<ISubTeam> {

    private ISubTeam subTeam;

    private List<ITransferMember> subTeamMembers;

    @Override
    public void setObject(Object o) {
        this.subTeam = (ISubTeam) o;

        if(!subTeam.getSubTeamMembers().isEmpty()&&subTeam.getSubTeamMembers()!=null)
        {
            for(ISubTeamsHasMembers member:subTeam.getSubTeamMembers())
            {
                try {
                    subTeamMembers.add((ITransferMember) TransferObjectCreator.getInstance(TransferMember.class,member.getMember()));
                } catch (IllegalGetInstanceException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }

    @Override
    public ISubTeam getModel() {
        return subTeam;
    }

    @Override
    public List<ITransferMember> getSubTeamMembers() {
        return subTeamMembers;
    }

    @Override
    public String toString() {
        return this.subTeam.getName();
    }
}
