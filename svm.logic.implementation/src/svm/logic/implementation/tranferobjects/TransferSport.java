package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.domain.abstraction.modelInterfaces.ISport;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferDepartment;
import svm.logic.abstraction.transferobjects.ITransferSport;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;

/**
 * Projectteam: Team C
 * Date: 14.11.12
 */
public class TransferSport implements ITransferSport, IHasModel<ISport> {

    private ISport sport;
    private String name;
    private ITransferDepartment department;



    @Override
    public ISport getModel() {
      return this.sport;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ITransferDepartment getDepartment() {
        return this.department;
    }

    @Override
    public void setObject(Object o) throws IllegalGetInstanceException, DomainParameterCheckException {
        this.sport = (ISport) o;
        this.name = sport.getName();

        if(!this.sport.getDepartment().isNull())
        {
            this.department=(ITransferDepartment) TransferObjectCreator.getInstance(TransferDepartment.class,sport.getDepartment());
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
