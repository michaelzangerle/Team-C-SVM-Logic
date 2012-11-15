package svm.logic.abstraction.controller;

import svm.logic.abstraction.transferobjects.ITransferContest;
import svm.logic.abstraction.transferobjects.ITransferTeam;

import java.util.List;

/**
 * ProjectTeam: Team C
 * Date: 15.11.12
 */
public interface ITeamController extends IController {

    ITransferTeam getTeam();

    List<ITransferContest> getContests();
}
