package svm.logic.abstraction.controller;

import svm.logic.abstraction.transferobjects.interfaces.ITransferMember;
import svm.logic.abstraction.transferobjects.interfaces.ITransferSubTeam;

/**
 * ProjectTeam: Team C
 * Date: 30.10.12
 */
public interface ISubTeamController extends IController {

    ITransferSubTeam getSubTeam();

    void setName(String name);

    void addMember(ITransferMember member);

    void removeMember(ITransferMember member);
}
