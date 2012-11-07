package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.IDepartment;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferDepartment;

/**
 * ProjectTeam: Team C
 * Date: 31.10.12
 */
public class TransferDepartment implements ITransferDepartment, IHasModel<IDepartment> {
    private IDepartment department;
    private String name;

    @Override
    public IDepartment getModel() {
        return department;
    }

    @Override
    public String getName() {
        return name;
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
