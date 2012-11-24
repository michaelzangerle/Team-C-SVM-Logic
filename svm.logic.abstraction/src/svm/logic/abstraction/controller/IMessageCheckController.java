package svm.logic.abstraction.controller;

import svm.logic.abstraction.jmsobjects.IMemberMessage;
import svm.logic.abstraction.jmsobjects.ISubTeamMessage;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;

/**
 * ProjectTeam: Team C
 * Date: 24.11.12
 */
public interface IMessageCheckController extends IController {
    boolean myMemberMessage(IMemberMessage message) throws NoSessionFoundException;

    boolean mySubTeamMessage(ISubTeamMessage message) throws NoSessionFoundException;
}
