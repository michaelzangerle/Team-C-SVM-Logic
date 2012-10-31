package svm.logic.abstraction.transferobjects;


import svm.logic.abstraction.exception.IllegalGetInstanceException;

/**
 * Projectteam
 * Date: 24.10.12
 */
public interface ITransferPerson extends ITransfer {

    String getName();

    ITransferAddress getAddress() throws IllegalGetInstanceException, IllegalGetInstanceException;
}
