package svm.logic.abstraction.transferobjects;

import svm.domain.abstraction.modelInterfaces.IModel;

/**
 * ProjectTeam: Team C
 * Date: 31.10.12
 */
public interface IHasModel<T extends IModel> {
    T getModel();
}
