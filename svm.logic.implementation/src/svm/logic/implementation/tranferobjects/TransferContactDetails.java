package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.IContactDetails;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferContactDetails;
import svm.logic.abstraction.transferobjects.ITransferLocation;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;

/**
 * Projectteam: Team C
 * Date: 31.10.12
 */
public class TransferContactDetails implements ITransferContactDetails, IHasModel<IContactDetails> {

    private IContactDetails contactDetails;

    @Override
    public void setObject(Object obj) {
        this.contactDetails = (IContactDetails) obj;
    }

    @Override
    public String getPhone1() {
        return contactDetails.getPhone1();
    }

    @Override
    public String getPhone2() {
        return contactDetails.getPhone2();
    }

    @Override
    public String getEmail1() {
        return contactDetails.getEmail1();
    }

    @Override
    public String getEmail2() {
        return contactDetails.getEmail2();
    }

    @Override
    public String getFax() {
        return contactDetails.getFax();
    }

    @Override
    public String getStreet() {
        return contactDetails.getStreet();
    }

    @Override
    public String getStreetNumber() {
        return contactDetails.getStreetNumber();
    }

    @Override
    public String getLat() {
        return contactDetails.getCoordLat();
    }

    @Override
    public String getLong() {
        return contactDetails.getCoordLong();
    }

    @Override
    public ITransferLocation getLocation() throws IllegalGetInstanceException {
        return (ITransferLocation) TransferObjectCreator.getInstance(ITransferLocation.class, contactDetails.getLocation());
    }

    @Override
    public IContactDetails getModel() {
        return this.contactDetails;
    }
}
