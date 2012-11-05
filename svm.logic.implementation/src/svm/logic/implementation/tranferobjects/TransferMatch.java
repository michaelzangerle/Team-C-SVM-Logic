package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.IContactDetails;
import svm.domain.abstraction.modelInterfaces.IMatch;
import svm.domain.implementation.model.ContactDetails;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferContactDetails;
import svm.logic.abstraction.transferobjects.ITransferMatch;
import svm.logic.abstraction.transferobjects.ITransferMatchType;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;

import java.util.Date;

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
    public void setObject(Object o) {
        this.match = (IMatch) o;
    }

    @Override
    public IMatch getModel() {
        return this.match;
    }
}
