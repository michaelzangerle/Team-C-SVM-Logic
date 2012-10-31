package svm.logic.implementation.controller;

import svm.logic.abstraction.controller.IContestController;
import svm.logic.abstraction.transferobjects.*;

import java.util.Date;
import java.util.List;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public class ContestController implements IContestController {
    @Override
    public ITransferContest createContest() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setContestName(String name) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setContestStartDate(Date start) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setContestEndDate(Date end) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setContestFee(float val) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ITransferContest> getContestByName(String name) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ITransferContest> getContestByDate(Date date) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ITransferTeam> getContestTeams() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ITransferSubTeam> getContestSubTeams() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ITransferResult> getContestResults() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ITransferMatch> getContestMatches() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ITransferLeague getLeague() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void start() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void commit() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void abort() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
