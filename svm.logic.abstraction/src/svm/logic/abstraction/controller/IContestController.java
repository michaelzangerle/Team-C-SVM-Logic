package svm.logic.abstraction.controller;

import svm.domain.abstraction.exception.DomainAttributeException;
import svm.domain.abstraction.exception.DomainException;
import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.exception.LogicException;
import svm.logic.abstraction.exception.NotAllowException;
import svm.logic.abstraction.transferobjects.ITransferContest;
import svm.logic.abstraction.transferobjects.ITransferLocation;
import svm.logic.abstraction.transferobjects.ITransferMatch;
import svm.logic.abstraction.transferobjects.ITransferTeam;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

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
    void setContestName(String name) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the start date of a contest
     *
     * @param start
     */
    void setContestStartDate(Date start) throws DomainParameterCheckException, RemoteException, NotAllowException;

    /**
     * Sets the end date of a contest
     *
     * @param end
     */
    void setContestEndDate(Date end) throws DomainParameterCheckException, RemoteException, NotAllowException;

    /**
     * Sets the fee for a contest
     *
     * @param val
     */
    void setContestFee(float val) throws DomainParameterCheckException, DomainAttributeException, RemoteException, NotAllowException;

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
    void setPhone1(String val) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the phone2 in the contact details for a contest
     *
     * @param val
     */
    void setPhone2(String val) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the email1 in the contact details for a contest
     *
     * @param val
     */
    void setEmail1(String val) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the email2 in the contact details for a contest
     *
     * @param val
     */
    void setEmail2(String val) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the fax in the contact details for a contest
     *
     * @param val
     */
    void setFax(String val) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the street in the contact details for a contest
     *
     * @param val
     */
    void setStreet(String val) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the street number in the contact details for a contest
     *
     * @param val
     */
    void setStreetNumber(String val) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the latitude in the contact details for a contest
     *
     * @param val
     */
    void setLat(String val) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the longitude in the contact details for a contest
     *
     * @param val
     */
    void setLong(String val) throws DomainAttributeException, RemoteException, NotAllowException;

    /**
     * Sets the location in the contact details for a contest
     *
     * @param location
     */
    void setLocation(ITransferLocation location) throws DomainAttributeException, RemoteException, NotAllowException;

    List<ITransferMatch> getMatches() throws RemoteException, IllegalGetInstanceException;

    List<ITransferTeam> getTeams() throws RemoteException, IllegalGetInstanceException;

    void removeTeam(ITransferTeam team) throws RemoteException, DomainException, NotAllowException;

    void addTeam(ITransferTeam team) throws RemoteException, DomainException, NoSessionFoundException, InstantiationException, IllegalAccessException, NotAllowException;

    void setDateForMatch(ITransferMatch match, Date start) throws NotAllowException;

    void addMatch(ITransferTeam home, ITransferTeam away, Date start, Date end) throws RemoteException, DomainException, NoSessionFoundException, InstantiationException, IllegalAccessException, LogicException, NotAllowException;

    void setResult(ITransferMatch match, Integer home, Integer away) throws NoSessionFoundException, DomainException, InstantiationException, IllegalAccessException, NotAllowException;
}
