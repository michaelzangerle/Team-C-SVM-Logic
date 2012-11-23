package svm.logic.abstraction.transferobjects.impl;

import svm.domain.abstraction.modelInterfaces.IContest;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferContactDetails;
import svm.logic.abstraction.transferobjects.ITransferContest;
import svm.logic.abstraction.transferobjects.ITransferSport;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Projectteam: Team C
 * Date: 31.10.12
 */
public class TransferContest implements ITransferContest, IHasModel<IContest> {

    private IContest contest;
    private String name;
    private Date start;
    private Date end;
    private float fee;
    private ITransferContactDetails contactDetails;
    private ITransferSport sport;

    @Override
    public void setObject(Object obj) throws IllegalGetInstanceException {
        this.contest = (IContest) obj;
        this.name = contest.getName();
        this.start = contest.getStart();
        this.end = contest.getEnd();
        this.fee = contest.getFee();
        if (!contest.getContactDetails().isNull()) {
            this.contactDetails = (ITransferContactDetails) TransferObjectCreator.getInstance(TransferContactDetails.class, contest.getContactDetails());
        }
        if (!contest.getSport().isNull()) {
            this.sport = (ITransferSport) TransferObjectCreator.getInstance(TransferSport.class, contest.getSport());
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Date getStart() {
        return start;
    }

    @Override
    public Date getEnd() {
        return end;
    }

    @Override
    public float getFee() {
        return fee;
    }

    @Override
    public ITransferContactDetails getContactDetails() throws IllegalGetInstanceException {
        return contactDetails;
    }

    @Override
    public ITransferSport getSport() throws IllegalGetInstanceException {
        return sport;
    }

    @Override
    public boolean isFinished() {
        return this.contest.getFinished();
    }

    @Override
    public String toString() {

        String format = "dd.MM.YY hh:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        return this.contest.getName() + " (" + sdf.format(this.contest.getStart()) + ")";
    }

    @Override
    public IContest getModel() {
        return this.contest;
    }
}
