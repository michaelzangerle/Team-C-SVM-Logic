package svm.logic.abstraction.transferobjects;

import svm.logic.abstraction.exception.IllegalGetInstanceException;

import java.util.Date;

/**
 * ProjectTeam: Team C
 * Date: 30.10.12
 */
public interface ITransferMember extends ITransfer {
    String getFirstName();

    String getLastName();

    String getTitle();

    String getSocialNumber();

    Date getBirthDate();

    String getGender();

    Date getEntryDate();

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
