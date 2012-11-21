package svm.logic.abstraction.controller;

import svm.logic.abstraction.transferobjects.ITransferMember;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.List;

/**
 * Projectteam : Team C
 * Date: 21.11.12
 */
public interface IMessageController extends Serializable, Remote, IController {

    List<TransferMessage> update(ITransferMember member);
}
