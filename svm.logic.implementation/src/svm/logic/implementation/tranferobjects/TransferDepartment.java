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

    @Override
    public IDepartment getModel() {
        return department;
    }

    @Override
    public void setObject(Object o) {
        this.department = (IDepartment) o;
    }
}
