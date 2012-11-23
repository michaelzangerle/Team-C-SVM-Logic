package svm.logic.abstraction.transferobjects.impl;

import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferTeam;

/**
 * Projectteam: Team C
 * Date: 05.11.12
 */
public abstract class TransferTeam implements ITransferTeam, IHasModel {

    @Override
    public abstract String getName();

}
