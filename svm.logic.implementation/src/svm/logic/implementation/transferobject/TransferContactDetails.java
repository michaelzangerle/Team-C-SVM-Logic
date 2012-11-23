package svm.logic.implementation.transferobject;

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
    private String phone1;
    private String phone2;
    private String email1;
    private String email2;
    private String fax;
    private String street;
    private String streetNumber;
    private String coordLat;
    private String coordLong;
    private ITransferLocation location;

    @Override
    public void setObject(Object obj) throws IllegalGetInstanceException {
        this.contactDetails = (IContactDetails) obj;
        this.phone1 = contactDetails.getPhone1();
        this.phone2 = contactDetails.getPhone2();
        this.email1 = contactDetails.getEmail1();
        this.email2 = contactDetails.getEmail2();
        this.fax = contactDetails.getFax();
        this.street = contactDetails.getStreet();
        this.streetNumber = contactDetails.getStreetNumber();
        this.coordLat = contactDetails.getCoordLat();
        this.coordLong = contactDetails.getCoordLong();
        if (!contactDetails.getLocation().isNull()) {
            this.location = (ITransferLocation) TransferObjectCreator.getInstance(TransferLocation.class, contactDetails.getLocation());
        }
    }

    @Override
    public String getPhone1() {
        return phone1;
    }

    @Override
    public String getPhone2() {
        return phone2;
    }

    @Override
    public String getEmail1() {
        return email1;
    }

    @Override
    public String getEmail2() {
        return email2;
    }

    @Override
    public String getFax() {
        return fax;
    }

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public String getStreetNumber() {
        return streetNumber;
    }

    @Override
    public String getLat() {
        return coordLat;
    }

    @Override
    public String getLong() {
        return coordLong;
    }

    @Override
    public ITransferLocation getLocation() throws IllegalGetInstanceException {
        return location;
    }

    @Override
    public IContactDetails getModel() {
        return this.contactDetails;
    }
}
