package svm.logic.abstraction.controller;

import svm.domain.abstraction.exception.DomainAttributeException;
import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.logic.abstraction.transferobjects.ITransferContest;
import svm.logic.abstraction.transferobjects.ITransferLocation;

import java.rmi.RemoteException;
import java.util.Date;

/**
 * Interface for the contest controller
 * Projectteam: Team C
 * Date: 30.10.12
 */
public interface IContestController extends IController {

    /**
     * Sets the name of a contest
     *
     * @param name
     */
    void setContestName(String name) throws DomainAttributeException, RemoteException;

    /**
     * Sets the start date of a contest
     *
     * @param start
     */
    void setContestStartDate(Date start) throws DomainParameterCheckException, RemoteException;

    /**
     * Sets the end date of a contest
     *
     * @param end
     */
    void setContestEndDate(Date end) throws DomainParameterCheckException, RemoteException;

    /**
     * Sets the fee for a contest
     *
     * @param val
     */
    void setContestFee(float val) throws DomainParameterCheckException, DomainAttributeException, RemoteException;

    /**
     * Returns a specific contest
     *
     * @return
     */
    ITransferContest getTransferContest() throws RemoteException;

    /**
     * Sets the phone1 in the contact details for a contest
     *
     * @param val
     */
    void setPhone1(String val) throws DomainAttributeException, RemoteException;

    /**
     * Sets the phone2 in the contact details for a contest
     *
     * @param val
     */
    void setPhone2(String val) throws DomainAttributeException, RemoteException;

    /**
     * Sets the email1 in the contact details for a contest
     *
     * @param val
     */
    void setEmail1(String val) throws DomainAttributeException, RemoteException;

    /**
     * Sets the email2 in the contact details for a contest
     *
     * @param val
     */
    void setEmail2(String val) throws DomainAttributeException, RemoteException;

    /**
     * Sets the fax in the contact details for a contest
     *
     * @param val
     */
    void setFax(String val) throws DomainAttributeException, RemoteException;

    /**
     * Sets the street in the contact details for a contest
     *
     * @param val
     */
    void setStreet(String val) throws DomainAttributeException, RemoteException;

    /**
     * Sets the street number in the contact details for a contest
     *
     * @param val
     */
    void setStreetNumber(String val) throws DomainAttributeException, RemoteException;

    /**
     * Sets the latitude in the contact details for a contest
     *
     * @param val
     */
    void setLat(String val) throws DomainAttributeException, RemoteException;

    /**
     * Sets the longitude in the contact details for a contest
     *
     * @param val
     */
    void setLong(String val) throws DomainAttributeException, RemoteException;

    /**
     * Sets the location in the contact details for a contest
     *
     * @param location
     */
    void setLocation(ITransferLocation location) throws DomainAttributeException, RemoteException;

}
