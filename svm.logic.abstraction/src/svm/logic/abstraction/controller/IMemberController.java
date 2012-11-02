package svm.logic.abstraction.controller;

import svm.domain.abstraction.exception.DomainAttributeException;
import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.logic.abstraction.transferobjects.ITransferLocation;
import svm.logic.abstraction.transferobjects.ITransferMember;

import java.util.Date;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public interface IMemberController extends IController {

    ITransferMember getMember();

    void setTitle(String title) throws DomainAttributeException;

    void setFirstName(String firstName) throws DomainAttributeException;

    void setLastName(String lastName) throws DomainAttributeException;

    void setSocialNumber(String socialNumber) throws DomainAttributeException;

    void setBirthDate(Date birthDate) throws DomainParameterCheckException;

    void setGender(String gender) throws DomainParameterCheckException, DomainAttributeException;

    void setEntryDate(Date entryDate) throws DomainParameterCheckException;

    void setFee(float fee);

    /**
     * Sets the phone1 in the contact details for a member
     *
     * @param val
     */
    void setPhone1(String val) throws DomainAttributeException;

    /**
     * Sets the phone2 in the contact details for a member
     *
     * @param val
     */
    void setPhone2(String val) throws DomainAttributeException;

    /**
     * Sets the email1 in the contact details for a member
     *
     * @param val
     */
    void setEmail1(String val) throws DomainAttributeException;

    /**
     * Sets the email2 in the contact details for a member
     *
     * @param val
     */
    void setEmail2(String val) throws DomainAttributeException;

    /**
     * Sets the fax in the contact details for a member
     *
     * @param val
     */
    void setFax(String val) throws DomainAttributeException;

    /**
     * Sets the street in the contact details for a member
     *
     * @param val
     */
    void setStreet(String val) throws DomainAttributeException;

    /**
     * Sets the street number in the contact details for a member
     *
     * @param val
     */
    void setStreetNumber(String val) throws DomainAttributeException;

    /**
     * Sets the latitude in the contact details for a member
     *
     * @param val
     */
    void setLat(String val) throws DomainAttributeException;

    /**
     * Sets the longitude in the contact details for a member
     *
     * @param val
     */
    void setLong(String val) throws DomainAttributeException;

    /**
     * Sets the location in the contact details for a member
     *
     * @param location
     */
    void setLocation(ITransferLocation location) throws DomainAttributeException;
}
