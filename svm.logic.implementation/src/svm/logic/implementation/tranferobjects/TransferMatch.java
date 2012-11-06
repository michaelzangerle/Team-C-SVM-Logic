package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.IContactDetails;
import svm.domain.abstraction.modelInterfaces.IContestant;
import svm.domain.abstraction.modelInterfaces.IMatch;
import svm.domain.implementation.model.ContactDetails;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.*;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Projectteam: Team C
 * Date: 05.11.12
 */
public class TransferMatch implements ITransferMatch, IHasModel<IMatch> {

    private IMatch match;

    @Override
    public String getName() {
        return this.match.getName();
    }

    @Override
    public Date getStart() {
        return this.match.getStart();
    }

    @Override
    public Date getEnd() {
        return this.match.getEnd();
    }

    @Override
    public boolean isCanceled() {
        return this.match.getCancelled();
    }

    @Override
    public ITransferContactDetails getContactDetails() throws IllegalGetInstanceException {
        IContactDetails contactDetails = this.match.getContactDetails();
        return (ITransferContactDetails) TransferObjectCreator.getInstance(TransferContactDetails.class, contactDetails);
    }

    @Override
    public ITransferMatchType getMatchType() throws IllegalGetInstanceException {
        return (ITransferMatchType) TransferObjectCreator.getInstance(TransferMatchType.class, this.match.getMatchType());
    }

    @Override
    public String getDescription() {
        return this.match.getDescription();
    }

    @Override
    public String getRemarks() {
        return this.match.getRemarks();
    }

    @Override
    public List<ITransferContestant> getContestants() throws IllegalGetInstanceException {
        List<ITransferContestant> contestants = new LinkedList<ITransferContestant>();
        for (IContestant c : this.match.getContestants()){
            contestants.add((ITransferContestant) TransferObjectCreator.getInstance(TransferContestant.class,c));
        }
        return contestants;
    }

    @Override
    public void setObject(Object o) {
        this.match = (IMatch) o;
    }

    @Override
    public IMatch getModel() {
        return this.match;
    }

    @Override
    public String toString() {

        String format = "dd.MM.YY hh:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        return this.match.getName() + " ("+sdf.format(this.match.getStart())+")";
    }

}
