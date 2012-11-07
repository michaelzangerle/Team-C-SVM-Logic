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
    private boolean confirmed;
    private String comment;
    private boolean paid;

    @Override
    public void setObject(Object o) {
        this.contestHasTeams = (IContestHasTeam) o;
        confirmed = contestHasTeams.isConfirmed();
        comment = contestHasTeams.getComment();
        paid = contestHasTeams.isPaid();
    }

    @Override
    public boolean getConfirmed() {
        return confirmed;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public boolean getPaid() {
        return paid;
    }

    @Override
    public IContestHasTeam getModel() {
        return this.contestHasTeams;
    }
}
