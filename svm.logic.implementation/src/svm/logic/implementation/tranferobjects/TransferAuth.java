package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.domain.abstraction.modelInterfaces.IUserPrivilege;
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
        if(member.isIn(IUserPrivilege.Privileges.ADMIN)||member.isIn(IUserPrivilege.Privileges.MANAGER)|member.isIn(IUserPrivilege.Privileges.MATCH_MANAGER)||member.isIn(IUserPrivilege.Privileges.MEMBER_MANAGER))
            return true;
        else
            return false;
    }

    @Override
    public boolean isAllowedForMemberViewing() {
        if(member.isIn(IUserPrivilege.Privileges.ADMIN)||member.isIn(IUserPrivilege.Privileges.MANAGER)||member.isIn(IUserPrivilege.Privileges.MEMBER_MANAGER)||member.isIn(IUserPrivilege.Privileges.VIEW_ONLY))
            return true;
        else
            return false;
    }

    @Override
    public boolean isAllowedForMemberChanging() {
        if(member.isIn(IUserPrivilege.Privileges.ADMIN)||member.isIn(IUserPrivilege.Privileges.MANAGER)||member.isIn(IUserPrivilege.Privileges.MEMBER_MANAGER))
            return true;
        else
            return false;
    }

    @Override
    public boolean isAllowedForMemberDeleting() {
        if(member.isIn(IUserPrivilege.Privileges.ADMIN)||member.isIn(IUserPrivilege.Privileges.MANAGER)||member.isIn(IUserPrivilege.Privileges.MEMBER_MANAGER))
            return true;
        else
            return false;
    }

    @Override
    public boolean isAllowedForMemberAdding() {
        if(member.isIn(IUserPrivilege.Privileges.ADMIN)||member.isIn(IUserPrivilege.Privileges.MANAGER)||member.isIn(IUserPrivilege.Privileges.MEMBER_MANAGER))
            return true;
        else
            return false;
    }

    @Override
    public boolean isAllowedForMemberAddingPrivileges() {
        if(member.isIn(IUserPrivilege.Privileges.ADMIN))
            return true;
        else
            return false;
    }

    @Override
    public boolean isAllowedForContestViewing() {
        if(member.isIn(IUserPrivilege.Privileges.ADMIN)||member.isIn(IUserPrivilege.Privileges.MANAGER)||member.isIn(IUserPrivilege.Privileges.MATCH_MANAGER)||member.isIn(IUserPrivilege.Privileges.VIEW_ONLY))
            return true;
        else
            return false;
    }

    @Override
    public boolean isAllowedForContestChanging() {
        if(member.isIn(IUserPrivilege.Privileges.ADMIN)||member.isIn(IUserPrivilege.Privileges.MANAGER)||member.isIn(IUserPrivilege.Privileges.MATCH_MANAGER))
            return true;
        else
            return false;
    }

    @Override
    public boolean isAllowedForContestDeleting() {
        if(member.isIn(IUserPrivilege.Privileges.ADMIN)||member.isIn(IUserPrivilege.Privileges.MANAGER))
            return true;
        else
            return false;
    }

    @Override
    public boolean isAllowedForContestAdding() {
        if(member.isIn(IUserPrivilege.Privileges.ADMIN)||member.isIn(IUserPrivilege.Privileges.MANAGER))
            return true;
        else
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
