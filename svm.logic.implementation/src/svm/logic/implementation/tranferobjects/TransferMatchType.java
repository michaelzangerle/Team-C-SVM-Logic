package svm.logic.implementation.tranferobjects;

import svm.domain.abstraction.modelInterfaces.IMatchType;
import svm.logic.abstraction.transferobjects.ITransferMatchType;

/**
 * Projectteam: Team C
 * Date: 05.11.12
 */
public class TransferMatchType implements ITransferMatchType {
    private IMatchType matchType;

    @Override
    public void setObject(Object o) {
        this.matchType = (IMatchType) o;
    }

    @Override
    public String getName(){
        return this.matchType.getName();
    }

    @Override
    public String toString() {


        return this.matchType.getName();
    }
}
