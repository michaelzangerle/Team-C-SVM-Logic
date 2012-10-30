package svm.logic.implementation.tranferobjects;

import svm.logic.abstraction.exceptions.IllegalGetInstanceException;
import svm.logic.abstraction.transferobjects.interfaces.ITransferAddress;
import svm.logic.abstraction.transferobjects.interfaces.ITransferPerson;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;
import test.IPerson;

/**
 * Projectteam
 * Date: 24.10.12
 */
public class TransferPerson implements ITransferPerson {

    IPerson person;

      @Override
    public void setObject(Object obj)
    {
        this.person=(IPerson)obj;
    }


    @Override
    public String getName() {
        return person.getName();
    }

    @Override
    public ITransferAddress getAddress() throws IllegalGetInstanceException {
        return(ITransferAddress)TransferObjectCreator.getInstance(TransferAddress.class,person.getAddress());
    }
}
