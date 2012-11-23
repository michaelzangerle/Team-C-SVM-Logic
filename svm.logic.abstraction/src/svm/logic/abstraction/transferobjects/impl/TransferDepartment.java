package svm.logic.abstraction.transferobjects.impl;

import svm.domain.abstraction.modelInterfaces.IDepartment;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferDepartment;
import svm.logic.abstraction.transferobjects.ITransferMember;

/**
 * ProjectTeam: Team C
 * Date: 31.10.12
 */
public class TransferDepartment implements ITransferDepartment, IHasModel<IDepartment> {
    private IDepartment department;
    private String name;
    private ITransferMember departmentHead;

    @Override
    public IDepartment getModel() {
        return department;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ITransferMember getDepartmentHead() {
        return departmentHead;
    }

    @Override
    public void setObject(Object o) {
        this.department = (IDepartment) o;
        this.name = department.getName();
   }

    @Override
    public String toString() {
        return getName();
    }
}
