package svm.logic.abstraction.controller;

import svm.logic.abstraction.transferobjects.ITransferPerson;

import java.util.List;

/**
 * Projectteam: Team C
 * Date: 30.10.12
 */
public interface ISearchController extends IController {

    // TODO transfer member instead of transfer person
    List<ITransferPerson> getMembersByName();

}
