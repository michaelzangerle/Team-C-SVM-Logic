package svm.logic.abstraction.controller;

import svm.domain.abstraction.exception.DomainAttributeException;
import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.logic.abstraction.transferobjects.*;

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
    void setContestName(String name) throws DomainAttributeException;

    /**
     * Sets the start date of a contest
     *
     * @param start
     */
    void setContestStartDate(Date start) throws DomainParameterCheckException;

    /**
     * Sets the end date of a contest
     *
     * @param end
     */
    void setContestEndDate(Date end) throws DomainParameterCheckException;

    /**
     * Sets the fee for a contest
     *
     * @param val
     */
    void setContestFee(float val) throws DomainParameterCheckException, DomainAttributeException;

    ITransferContest getTransferContest();

    // TODO contact details
}
