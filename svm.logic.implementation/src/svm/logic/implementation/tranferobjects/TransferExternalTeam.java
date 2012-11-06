package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.IExternalTeam;
import svm.logic.abstraction.transferobjects.ITransferExternalTeam;

/**
 * Projectteam: Team C
 * Date: 05.11.12
 */
public class TransferExternalTeam extends TransferTeam implements ITransferExternalTeam {

    private IExternalTeam externalTeamEntity;

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

    @Override
    public String toString() {
        return this.externalTeamEntity.getName();
    }

}
