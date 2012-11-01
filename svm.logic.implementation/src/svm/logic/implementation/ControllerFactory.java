package svm.logic.implementation;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.modelInterfaces.IContest;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.domain.abstraction.modelInterfaces.ISubTeam;
import svm.domain.abstraction.modelInterfaces.ITeam;
import svm.logic.abstraction.controller.IContestConfirmationController;
import svm.logic.abstraction.controller.IContestController;
import svm.logic.abstraction.controller.ISubTeamConfirmationController;
import svm.logic.abstraction.controller.ISubTeamController;
import svm.logic.abstraction.transferobjects.*;
import svm.logic.implementation.controller.ContestConfirmationController;
import svm.logic.implementation.controller.ContestController;
import svm.logic.implementation.controller.SubTeamConfirmationController;
import svm.logic.implementation.controller.SubTeamController;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;

/**
 * ProjectTeam: Team C
 * Date: 31.10.12
 */
public class ControllerFactory {

    private static ControllerFactory instance;

    public static ControllerFactory getInstance() {
        if (instance == null) instance = new ControllerFactory();
        return instance;
    }

    private ControllerFactory() {
    }

    /**
     * Create new Contest
     *
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public IContestController getContestController() throws IllegalAccessException, InstantiationException, NoSessionFoundException {
        return new ContestController(DomainFacade.getContestModelDAO().generateObject());
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
        return new ContestController(((IHasModel<IContest>) contest).getModel());
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
        return new ContestConfirmationController(((IHasModel<IMember>) member).getModel());
    }

    public ISubTeamConfirmationController getSubTeamConfirmationController ()
    {
        return new SubTeamConfirmationController();
    }

    public ISubTeamController getSubTeamController(ITransferSubTeam subTeam)
    {
        return new SubTeamController(((IHasModel<ISubTeam>) subTeam).getModel());
    }

    public ISubTeamController getSubTeamController(ITransferTeam team, ITransferContest contest) throws NoSessionFoundException, InstantiationException, IllegalAccessException {
        return new SubTeamController(((IHasModel<ITeam>) team).getModel(),((IHasModel<IContest>) contest).getModel());
    }
}
