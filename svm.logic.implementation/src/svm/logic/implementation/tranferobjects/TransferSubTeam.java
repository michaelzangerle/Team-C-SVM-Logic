package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.ISubTeam;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferSubTeam;

/**
 * ProjectTeam: Team C
 * Date: 01.11.12
 */
public class TransferSubTeam implements ITransferSubTeam, IHasModel<ISubTeam> {
    private ISubTeam subTeam;

    @Override
    public void setObject(Object o) {
        this.subTeam = (ISubTeam) o;
    }

    @Override
    public ISubTeam getModel() {
        return subTeam;
    }
}
