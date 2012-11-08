package svm.logic.abstraction.controller;

import svm.domain.abstraction.exception.DomainException;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.exception.LogicException;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.abstraction.transferobjects.ITransferSubTeam;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NotSupportedException;

import java.rmi.RemoteException;
import java.util.List;

/**
 * ProjectTeam: Team C
 * Date: 30.10.12
 */
public interface ISubTeamController extends IController {

    ITransferSubTeam getSubTeam() throws RemoteException;

    void setName(String name) throws RemoteException;

    void addMember(ITransferMember member) throws LogicException, NoSessionFoundException, DomainException, IllegalAccessException, InstantiationException, RemoteException, NotSupportedException;

    void removeMember(ITransferMember member) throws RemoteException;

    List<ITransferMember> getMembersOfSubTeam() throws IllegalGetInstanceException;

    List<ITransferMember> getMemberOfTeam() throws IllegalGetInstanceException;
}

