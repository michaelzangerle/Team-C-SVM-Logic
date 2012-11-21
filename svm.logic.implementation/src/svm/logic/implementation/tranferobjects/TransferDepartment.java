package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.IDepartment;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferDepartment;
import svm.logic.abstraction.transferobjects.ITransferMember;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;

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

        if(!department.getDepartmentHead().isNull())
        {
            try {
                this.departmentHead=(ITransferMember) TransferObjectCreator.getInstance(TransferMember.class,department.getDepartmentHead());
            } catch (IllegalGetInstanceException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
