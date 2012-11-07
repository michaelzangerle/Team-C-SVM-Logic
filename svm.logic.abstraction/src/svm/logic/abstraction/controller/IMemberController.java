package svm.logic.abstraction.controller;

import svm.domain.abstraction.exception.DomainAttributeException;
import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.logic.abstraction.exception.NotAllowException;
import svm.logic.abstraction.transferobjects.ITransferLocation;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.abstraction.transferobjects.ITransferUserPrivilege;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;

import javax.transaction.NotSupportedException;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public interface IMemberController extends IController {

    ITransferMember getMember();

    void setTitle(String title) throws DomainAttributeException, RemoteException, NotAllowException;

    void setFirstName(String firstName) throws DomainAttributeException, RemoteException, NotAllowException;

    void setLastName(String lastName) throws DomainAttributeException, RemoteException, NotAllowException;

    void setSocialNumber(String socialNumber) throws DomainAttributeException, RemoteException, NotAllowException;

    void setBirthDate(Date birthDate) throws DomainParameterCheckException, RemoteException, NotAllowException;

    void setGender(String gender) throws DomainParameterCheckException, DomainAttributeException, RemoteException, NotAllowException;

    void setEntryDate(Date entryDate) throws DomainParameterCheckException, RemoteException, NotAllowException;


    /**
     * Sets the phone1 in the contact details for a member
     *
     * @param val
     */
    void setPhone1(String val) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the phone2 in the contact details for a member
     *
     * @param val
     */
    void setPhone2(String val) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the email1 in the contact details for a member
     *
     * @param val
     */
    void setEmail1(String val) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the email2 in the contact details for a member
     *
     * @param val
     */
    void setEmail2(String val) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the fax in the contact details for a member
     *
     * @param val
     */
    void setFax(String val) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the street in the contact details for a member
     *
     * @param val
     */
    void setStreet(String val) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the street number in the contact details for a member
     *
     * @param val
     */
    void setStreetNumber(String val) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the latitude in the contact details for a member
     *
     * @param val
     */
    void setLat(String val) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the longitude in the contact details for a member
     *
     * @param val
     */
    void setLong(String val) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the location in the contact details for a member
     *
     * @param location
     */
    void setLocation(ITransferLocation location) throws DomainAttributeException, RemoteException, NotAllowException;

    void setPaidCurrentYear() throws NoSessionFoundException, InstantiationException, IllegalAccessException, NotAllowException, NotSupportedException;

    void setUsername(String name) throws DomainAttributeException, NotAllowException;

    void addPrivilege(ITransferUserPrivilege privilege) throws NotAllowException, DomainParameterCheckException, NoSessionFoundException, DomainAttributeException, IllegalAccessException, InstantiationException;

    void removePrivilege(ITransferUserPrivilege privilege) throws NotAllowException, DomainParameterCheckException, DomainAttributeException;
}
