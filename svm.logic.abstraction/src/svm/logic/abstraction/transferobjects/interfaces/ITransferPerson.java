package svm.logic.abstraction.transferobjects.interfaces;


import svm.logic.abstraction.exceptions.IllegalGetInstanceException;

/**
 * Projectteam
 * Date: 24.10.12
 */
public interface ITransferPerson extends ITransfer{

    String getName();
    ITransferAddress getAddress() throws IllegalGetInstanceException;
}
