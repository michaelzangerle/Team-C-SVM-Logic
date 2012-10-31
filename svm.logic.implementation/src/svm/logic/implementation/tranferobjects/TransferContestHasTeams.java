package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.IContestHasTeam;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferContestHasTeams;

/**
 * Projectteam: Team C
 * Date: 31.10.12
 */
public class TransferContestHasTeams implements ITransferContestHasTeams, IHasModel<IContestHasTeam> {

    private IContestHasTeam contestHasTeams;

    @Override
    public void setObject(Object o) {
        this.contestHasTeams = (IContestHasTeam) o;
    }

    @Override
    public boolean getConfirmed() {
        return this.contestHasTeams.isConfirmed();
    }

    @Override
    public String getComment() {
        return this.contestHasTeams.getComment();
    }

    @Override
    public boolean getPaid() {
        return this.contestHasTeams.isPaid();
    }

    @Override
    public IContestHasTeam getModel() {
        return this.contestHasTeams;
    }
}
