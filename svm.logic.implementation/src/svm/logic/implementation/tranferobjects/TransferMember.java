package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.IMember;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferMember;

/**
 * ProjectTeam: Team C
 * Date: 31.10.12
 */
public class TransferMember implements ITransferMember, IHasModel<IMember> {
    private IMember member;

    @Override
    public IMember getModel() {
        return this.member;
    }

    @Override
    public String getFirstName() {
        return member.getFirstName();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getLastName() {
        return member.getLastName();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setObject(Object o) {
        this.member = (IMember) o;
    }
}
