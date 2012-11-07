package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferAuth;
import svm.logic.abstraction.transferobjects.ITransferLocation;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;

/**
 * ProjectTeam: Team C
 * Date: 31.10.12
 */
public class TransferAuth implements ITransferAuth, IHasModel<IMember> {
    private IMember member;
    private String firstName;
    private ITransferLocation location;
    private String userName;
    private String lastName;
    private String title;

    @Override
    public IMember getModel() {
        return this.member;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean isAllowedForSearching() {
        // TODO
        return false;
    }

    @Override
    public boolean isAllowedForMemberViewing() {
        // TODO
        return false;
    }

    @Override
    public boolean isAllowedForMemberChanging() {
        // TODO
        return false;
    }

    @Override
    public boolean isAllowedForMemberDeleting() {
        // TODO
        return false;
    }

    @Override
    public boolean isAllowedForMemberAdding() {
        // TODO
        return false;
    }

    @Override
    public boolean isAllowedForMemberAddingPrivileges() {
        // TODO
        return false;
    }

    @Override
    public boolean isAllowedForContestViewing() {
        // TODO
        return false;
    }

    @Override
    public boolean isAllowedForContestChanging() {
        // TODO
        return false;
    }

    @Override
    public boolean isAllowedForContestDeleting() {
        // TODO
        return false;
    }

    @Override
    public boolean isAllowedForContestAdding() {
        // TODO
        return false;
    }

    @Override
    public void setObject(Object o) throws IllegalGetInstanceException, DomainParameterCheckException {
        this.member = (IMember) o;
        this.firstName = member.getFirstName();
        this.location = (TransferLocation) TransferObjectCreator.getInstance(TransferLocation.class, this.member.getContactDetails().getLocation());
        this.userName = member.getUserName();
        this.lastName = member.getLastName();
        this.title = member.getTitle();
    }

    @Override
    public String toString() {
        return this.member.getFirstName() + " " + this.member.getLastName();
    }
}
