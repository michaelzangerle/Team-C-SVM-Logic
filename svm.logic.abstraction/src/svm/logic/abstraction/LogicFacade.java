package svm.logic.abstraction;

import svm.logic.abstraction.controller.IContestConfirmationController;
import svm.logic.abstraction.controller.IContestController;
import svm.logic.abstraction.transferobjects.ITransferContest;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.implementation.ControllerFactory;

/**
 * ProjectTeam: Team C
 * Date: 31.10.12
 */
public class LogicFacade {

    /**
     * Create new Contest
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public IContestController getContestController() throws IllegalAccessException, InstantiationException {
        return ControllerFactory.getInstance().getContestController();
    }

    /**
     * Change Contest
     *
     * @param contest
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public IContestController getContestController(ITransferContest contest) throws IllegalAccessException, InstantiationException {
        return ControllerFactory.getInstance().getContestController(contest);
    }

    /**
     * Confirm a Contest for Member
     *
     * @param member
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public IContestConfirmationController getContestConfirmationController(ITransferMember member) {
        return ControllerFactory.getInstance().getContestConfirmationController(member);
    }
}
