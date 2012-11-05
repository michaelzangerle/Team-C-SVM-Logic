package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.IExternalTeam;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferExternalTeam;
import svm.logic.abstraction.transferobjects.ITransferTeam;

/**
 * Projectteam: Team C
 * Date: 05.11.12
 */
public class TransferExternalTeam extends TransferTeam implements ITransferExternalTeam{
    IExternalTeam externalTeamEntity;

    public TransferExternalTeam(IExternalTeam externalTeamEntity) {
        this.externalTeamEntity = externalTeamEntity;
    }

    @Override
    public String getName() {
        return externalTeamEntity.getName();
    }

    @Override
    public IExternalTeam getModel() {
        return externalTeamEntity;
    }

    @Override
    public void setObject(Object o) {
        this.externalTeamEntity = (IExternalTeam) o;
    }
}
