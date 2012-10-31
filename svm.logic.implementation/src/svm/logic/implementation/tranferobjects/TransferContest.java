package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.IContest;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferContactDetails;
import svm.logic.abstraction.transferobjects.ITransferContest;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;

import java.util.Date;

/**
 * Projectteam: Team C
 * Date: 31.10.12
 */
public class TransferContest implements ITransferContest, IHasModel<IContest> {

    private IContest contest;

    @Override
    public void setObject(Object obj) {
        this.contest = (IContest) obj;
    }

    @Override
    public String getName() {
        return this.contest.getName();
    }

    @Override
    public Date getStart() {
        return this.contest.getStart();
    }

    @Override
    public Date getEnd() {
        return this.contest.getEnd();
    }

    @Override
    public float getFee() {
        return this.contest.getFee();
    }

    @Override
    public ITransferContactDetails getContactDetails() throws IllegalGetInstanceException {
        return (ITransferContactDetails) TransferObjectCreator.getInstance(TransferContactDetails.class, contest.getContactDetails());
    }

    @Override
    public IContest getModel() {
        return this.contest;
    }
}
