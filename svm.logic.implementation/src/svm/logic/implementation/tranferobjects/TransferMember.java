package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.IMember;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferLocation;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;

import java.util.Date;

/**
 * ProjectTeam: Team C
 * Date: 31.10.12
 */
public class TransferMember implements ITransferMember, IHasModel<IMember> {
    private IMember member;

    @Override
    public IMember getModel() {
        return this.member;
    }

    @Override
    public String getFirstName() {
        return member.getFirstName();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getLastName() {
        return member.getLastName();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getTitle() {
        return this.member.getTitle();
    }

    @Override
    public String getSocialNumber() {
        return this.member.getSocialNumber();
    }

    @Override
    public Date getBirthDate() {
        return this.getBirthDate();
    }

    @Override
    public String getGender() {
        return this.member.getGender();
    }

    @Override
    public Date getEntryDate() {
        return this.getEntryDate();
    }

    @Override
    public String getPhone1() {
       return this.member.getContactDetails().getPhone1();
    }

    @Override
    public String getPhone2() {
        return this.member.getContactDetails().getPhone2();
    }

    @Override
    public String getEmail1() {
        return this.member.getContactDetails().getEmail1();
    }

    @Override
    public String getEmail2() {
        return this.member.getContactDetails().getEmail2();
    }

    @Override
    public String getFax() {
        return this.member.getContactDetails().getFax();
    }

    @Override
    public String getStreet() {
        return this.member.getContactDetails().getStreet();
    }

    @Override
    public String getStreetNumber() {
        return this.member.getContactDetails().getStreetNumber();
    }

    @Override
    public String getLat() {
        return this.member.getContactDetails().getCoordLat();
    }

    @Override
    public String getLong() {
        return this.member.getContactDetails().getCoordLong();
    }

    @Override
    public ITransferLocation getLocation() throws IllegalGetInstanceException {
        return (TransferLocation) TransferObjectCreator.getInstance(TransferLocation.class, this.member.getContactDetails().getLocation());
    }

    @Override
    public void setObject(Object o) {
        this.member = (IMember) o;
    }
}
