package svm.logic.implementation.controller;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.modelInterfaces.IContestHasTeam;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.logic.abstraction.controller.IContestConfirmationController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferContestHasTeams;
import svm.logic.implementation.tranferobjects.TransferContestHasTeams;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;

import java.util.LinkedList;
import java.util.List;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public class ContestConfirmationController implements IContestConfirmationController {

    private List<ITransferContestHasTeams> transferContestHasTeams;
    private IMember member;
    private Integer sessionId;

    public ContestConfirmationController(IMember member) {
        this.member = member;
    }


    @Override
    public void start() throws NoSessionFoundException, IllegalGetInstanceException {
        this.sessionId = DomainFacade.generateSessionId();
        DomainFacade.reattachObjectToSession(this.sessionId, member);

        transferContestHasTeams = new LinkedList<ITransferContestHasTeams>();

        for (IContestHasTeam tmp : member.getContestsHasTeamsForPerson()) {
            this.transferContestHasTeams.add((ITransferContestHasTeams) TransferObjectCreator.getInstance(TransferContestHasTeams.class, tmp));
        }
    }

    @Override
    public void commit() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException {
        DomainFacade.startTransaction(this.sessionId);
        DomainFacade.commitTransaction(this.sessionId);
        DomainFacade.closeSession(this.sessionId);
    }

    @Override
    public void abort() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException {
        DomainFacade.startTransaction(this.sessionId);
        DomainFacade.abortTransaction(this.sessionId);
        DomainFacade.closeSession(this.sessionId);
    }

    @Override
    public List<ITransferContestHasTeams> getTeamsForNotConfirmedContests() throws IllegalGetInstanceException {

        List<ITransferContestHasTeams> result = new LinkedList<ITransferContestHasTeams>();

        for (IContestHasTeam tmp : member.getContestsHasTeamsForPerson()) {
            result.add((ITransferContestHasTeams) TransferObjectCreator.getInstance(TransferContestHasTeams.class, tmp));
        }

        return result;

    }

    @Override
    public void confirmParticipationOfATeam(ITransferContestHasTeams transferTeamHasContest, boolean confirm, String comment, boolean paid) {
        IContestHasTeam contestHasTeam = ((IHasModel<IContestHasTeam>) this.transferContestHasTeams).getModel();
        contestHasTeam.setComment(comment);
        contestHasTeam.setConfirmed(confirm);
        contestHasTeam.setPaid(paid);
    }
}
