package svm.logic.abstraction.transferobjects.impl;

import svm.domain.abstraction.modelInterfaces.ISubTeamsHasMembers;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferSubTeamHasMember;

/**
 * Projectteam: Team C
 * Date: 02.11.12
 */
public class TransferSubTeamHasMember implements IHasModel<ISubTeamsHasMembers>, ITransferSubTeamHasMember {

    private ISubTeamsHasMembers subTeamHasMember;

    @Override
    public ISubTeamsHasMembers getModel() {
        return subTeamHasMember;
    }

    @Override
    public void setObject(Object o) {
        this.subTeamHasMember = (ISubTeamsHasMembers) o;
    }
}
