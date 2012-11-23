package svm.logic.implementation.transferobject;

import svm.domain.abstraction.modelInterfaces.ISubTeam;
import svm.logic.abstraction.transferobjects.IHasModel;
import svm.logic.abstraction.transferobjects.ITransferSubTeam;

/**
 * ProjectTeam: Team C
 * Date: 01.11.12
 */
public class TransferSubTeam implements ITransferSubTeam, IHasModel<ISubTeam> {

    private ISubTeam subTeam;

    private int uid;
    private String name;

    @Override
    public void setObject(Object o) {
        this.subTeam = (ISubTeam) o;
        uid = subTeam.getUID();
        name = subTeam.getName();
    }

    @Override
    public ISubTeam getModel() {
        return subTeam;
    }


    @Override
    public String toString() {
        return this.subTeam.getName();
    }

    @Override
    public int getUID() {
        return uid;
    }

    @Override
    public String getName() {
        return name;
    }
}
