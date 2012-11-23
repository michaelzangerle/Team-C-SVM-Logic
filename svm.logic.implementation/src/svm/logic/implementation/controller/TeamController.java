package svm.logic.implementation.controller;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.modelInterfaces.IContestHasTeam;
import svm.domain.abstraction.modelInterfaces.ITeam;
import svm.logic.abstraction.controller.ITeamController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.ITransferAuth;
import svm.logic.abstraction.transferobjects.ITransferContest;
import svm.logic.abstraction.transferobjects.ITransferTeam;
import svm.logic.abstraction.transferobjects.impl.TransferContest;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;
import svm.persistence.abstraction.exceptions.NotSupportedException;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 * ProjectTeam: Team C
 * Date: 15.11.12
 */
public class TeamController implements ITeamController {

    private Integer sessionId;
    private ITransferAuth user;
    private ITransferTeam transferTeam;

    private ITeam team;

    public TeamController(ITeam team, ITransferAuth user) {
        this.user = user;
        this.team = team;
    }

    @Override
    public ITransferTeam getTeam() {
        return transferTeam;
    }

    @Override
    public List<ITransferContest> getContests() {
        List<ITransferContest> contests = new LinkedList<ITransferContest>();
        for (IContestHasTeam contestHasTeam : this.team.getAllContests()) {
            try {
                contests.add((ITransferContest) TransferObjectCreator.getInstance(TransferContest.class, contestHasTeam.getContest()));
            } catch (IllegalGetInstanceException e) {
                e.printStackTrace();
            }
        }
        return contests;
    }

    @Override
    public void start() throws NoSessionFoundException, IllegalGetInstanceException, RemoteException, NotSupportedException, InstantiationException, IllegalAccessException {
        this.sessionId = DomainFacade.generateSessionId();
        DomainFacade.reattachObjectToSession(sessionId, team);
    }

    @Override
    public void commit() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException, RemoteException {
        DomainFacade.closeSession(sessionId);
    }

    @Override
    public void abort() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException, RemoteException {
        DomainFacade.closeSession(sessionId);
    }
}
