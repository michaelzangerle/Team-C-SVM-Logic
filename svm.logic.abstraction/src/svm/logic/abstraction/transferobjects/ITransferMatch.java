package svm.logic.abstraction.transferobjects;

import svm.logic.abstraction.exception.IllegalGetInstanceException;

import java.util.Date;
import java.util.List;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public interface ITransferMatch extends ITransfer {

    String getName();

    Date getStart();

    Date getEnd();

    boolean isCanceled();

    ITransferContactDetails getContactDetails() throws IllegalGetInstanceException;

    ITransferMatchType getMatchType() throws IllegalGetInstanceException;

    String getDescription();

    String getRemarks();

    List<ITransferContestant> getContestants() throws IllegalGetInstanceException;

}
