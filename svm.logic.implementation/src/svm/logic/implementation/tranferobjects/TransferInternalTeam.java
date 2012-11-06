package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.ITeam;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferInternalTeam;
import svm.logic.abstraction.transferobjects.ITransferTeam;

/**
 * Projectteam: Team C
 * Date: 05.11.12
 */
public class TransferInternalTeam extends TransferTeam implements ITransferInternalTeam {
    ITeam teamEntity;

    public TransferInternalTeam(ITeam teamEntity) {
        this.teamEntity = teamEntity;
    }

    @Override
    public String getName() {
        return teamEntity.getName();
    }

    @Override
    public ITeam getModel() {
        return teamEntity;
    }

    @Override
    public void setObject(Object o) {
        this.teamEntity = (ITeam) o;
    }

    @Override
    public String toString() {
        return this.teamEntity.getName();
    }

}
