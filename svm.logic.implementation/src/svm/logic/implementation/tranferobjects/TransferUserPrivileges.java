package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.domain.abstraction.modelInterfaces.IUserPrivilege;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferUserPrivilege;

/**
 * Projectteam: Team C
 * Date: 05.11.12
 */
public class TransferUserPrivileges implements ITransferUserPrivilege, IHasModel<IUserPrivilege> {

    private IUserPrivilege userPrivilege;
    private String name;
    private String description;

    @Override
    public IUserPrivilege getModel() {
        return userPrivilege;
    }

    @Override
    public void setObject(Object o) throws IllegalGetInstanceException, DomainParameterCheckException {
        this.userPrivilege = (IUserPrivilege) o;
        name = userPrivilege.getName();
        description = userPrivilege.getDescription();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name;
    }
}
