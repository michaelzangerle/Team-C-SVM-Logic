package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.ISubTeam;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.abstraction.transferobjects.ITransferSubTeam;

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
