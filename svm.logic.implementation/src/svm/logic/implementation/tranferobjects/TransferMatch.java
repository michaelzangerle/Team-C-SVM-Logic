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
    private String name;
    private Date start;
    private Date end;
    private boolean cancelled;
    private ITransferContactDetails contactDetails;
    private ITransferMatchType matchType;
    private String description;
    private String remarks;
    private List<ITransferContestant> contestants;

    @Override
    public String getName() {
        return this.match.getName();
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
    public boolean isCanceled() {
        return cancelled;
    }

    @Override
    public ITransferContactDetails getContactDetails() throws IllegalGetInstanceException {
        return contactDetails;
    }

    @Override
    public ITransferMatchType getMatchType() throws IllegalGetInstanceException {
        return matchType;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public List<ITransferContestant> getContestants() throws IllegalGetInstanceException {
        return contestants;
    }

    @Override
    public void setObject(Object o) throws IllegalGetInstanceException {
        this.match = (IMatch) o;
        name = match.getName();
        start = match.getStart();
        end = match.getEnd();
        cancelled = match.getCancelled();
        contactDetails = (ITransferContactDetails) TransferObjectCreator.getInstance(TransferContactDetails.class, this.match.getContactDetails());;
        matchType = (ITransferMatchType) TransferObjectCreator.getInstance(TransferMatchType.class, this.match.getMatchType());;
        description = match.getDescription();
        remarks = match.getRemarks();
        contestants = new LinkedList<ITransferContestant>();
        for (IContestant c : this.match.getContestants()){
            contestants.add((ITransferContestant) TransferObjectCreator.getInstance(TransferContestant.class,c));
        }
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
