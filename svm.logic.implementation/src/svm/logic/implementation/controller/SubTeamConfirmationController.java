package svm.logic.implementation.controller;

import svm.logic.abstraction.controller.ISubTeamConfirmationController;
import svm.logic.abstraction.transferobjects.ITransferSubTeam;

import java.util.List;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public class SubTeamConfirmationController implements ISubTeamConfirmationController {
    @Override
    public List<ITransferSubTeam> getSubTeamsOfMember() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ITransferSubTeam> getCurrentSubTeamsOfMember() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setConfirmationForSubTeam(int subTeamID, boolean confirmation, String comment) {
        //To change body of implemented methods use File | Settings | File Templates.
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
