package svm.logic.abstraction.controller;

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
     * Returns an empty contest object
     *
     * @return
     */
    ITransferContest createContest();

    /**
     * Sets the name of a contest
     *
     * @param name
     */
    void setContestName(String name);

    /**
     * Sets the start date of a contest
     *
     * @param start
     */
    void setContestStartDate(Date start);

    /**
     * Sets the end date of a contest
     *
     * @param end
     */
    void setContestEndDate(Date end);

    /**
     * Sets the fee for a contest
     *
     * @param val
     */
    void setContestFee(float val);

    // TODO contact details?

    /**
     * Returns a list of contests with the given name
     *
     * @return
     */
    List<ITransferContest> getContestByName(String name);

    /**
     * Returns all contests where the startdate is >= and the end date <= the given date
     *
     * @param date
     * @return
     */
    List<ITransferContest> getContestByDate(Date date);

    /**
     * Returns the teams which are related with a contests
     *
     * @return
     */
    List<ITransferTeam> getContestTeams();

    /**
     * Returns the subteams of a contests
     *
     * @return
     */
    List<ITransferSubTeam> getContestSubTeams();

    /**
     * Returns all results of the whole contest
     *
     * @return
     */
    List<ITransferResult> getContestResults();

    /**
     * Returns all matches of a contest
     *
     * @return
     */
    List<ITransferMatch> getContestMatches();

    /**
     * Returns the Leque of a contest
     *
     * @return
     */
    ITransferLeague getLeague();

}
