package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.ISubTeam;
import svm.domain.abstraction.modelInterfaces.ISubTeamsHasMembers;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferSubTeamHasMember;

/**
 * Projectteam: Team C
 * Date: 02.11.12
 */
public class TransferSubTeamHasMember implements IHasModel<ISubTeamsHasMembers>, ITransferSubTeamHasMember {

    private ISubTeamsHasMembers subteamHasMember;

    @Override
    public ISubTeamsHasMembers getModel() {
        return subteamHasMember;
    }

    @Override
    public void setObject(Object o) {
        this.subteamHasMember = (ISubTeamsHasMembers) o;
    }
}
