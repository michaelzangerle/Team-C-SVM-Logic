package svm.logic.implementation.controller;

import svm.logic.abstraction.controller.IContestConfirmationController;
import svm.logic.abstraction.transferobjects.ITransferTeam;

import java.util.List;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public class ContestConfirmationController implements IContestConfirmationController {
    @Override
    public List<ITransferTeam> getTeamForNotConfirmedContests(int personId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ITransferTeam> confirmParticipationOfATeamInAContest(int teamId, int contestId, boolean confirm, String comment, boolean paid) {
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
