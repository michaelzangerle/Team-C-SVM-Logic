package svm.logic.implementation.tranferobjects;

import svm.logic.abstraction.transferobjects.ITransferAddress;
import test.IAddress;

/**
 * Projectteam
 * Date: 24.10.12
 */
public class TransferAddress implements ITransferAddress {

    IAddress address;

    @Override
    public String getPlz() {
        return address.getPlz();
    }

    @Override
    public void setObject(Object o) {
       this.address=(IAddress)o;
    }
}
