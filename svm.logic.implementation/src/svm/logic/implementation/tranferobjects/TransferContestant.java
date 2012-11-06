package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.IContestant;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferContestant;

/**
 * Projectteam: Team C
 * Date: 06.11.12
 */
public class TransferContestant implements ITransferContestant, IHasModel<IContestant> {

    private IContestant contestant;

    @Override
    public IContestant getModel() {
        return this.contestant;
    }

    @Override
    public float getResult() {
        return contestant.getResult();
    }

    @Override
    public void setObject(Object o) {
        this.contestant = (IContestant) o;
    }
}
