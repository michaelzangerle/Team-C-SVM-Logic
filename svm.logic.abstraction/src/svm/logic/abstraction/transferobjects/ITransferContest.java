package svm.logic.abstraction.transferobjects;

import svm.logic.abstraction.exception.IllegalGetInstanceException;

import java.util.Date;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public interface ITransferContest extends ITransfer {
    String getName();

    Date getStart();

    Date getEnd();

    float getFee();

    ITransferContactDetails getContactDetails() throws IllegalGetInstanceException;
}
