package svm.logic.abstraction.transferobjects;

import svm.logic.abstraction.exception.IllegalGetInstanceException;

/**
 * Projectteam: Team C
 * Date: 31.10.12
 */
public interface ITransferContactDetails extends ITransfer {

    String getPhone1();

    String getPhone2();

    String getEmail1();

    String getEmail2();

    String getFax();

    String getStreet();

    String getStreetNumber();

    String getLat();

    String getLong();

    ITransferLocation getLocation() throws IllegalGetInstanceException;

}
