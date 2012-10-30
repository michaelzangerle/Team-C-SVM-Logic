package svm.logic.abstraction.controller;

import svm.logic.abstraction.transferobjects.interfaces.ITransferMember;

import java.util.Date;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public interface IMemberController extends IController {

    ITransferMember getMember();

    void setTitle(String title);

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setSocialNumber(String socialNumber);

    void setBirthDate(Date birthDate);

    void setGender(Character gender);

    void setEntryDate(Date entryDate);

    void setFee(float fee);

    // TODO ContactDetails

}
